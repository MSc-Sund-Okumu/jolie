package jolie.runtime.expression;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import jolie.ExecutionThread;
import jolie.Interpreter;
import jolie.lang.Constants;
import jolie.lang.parse.context.ParsingContext;
import jolie.monitoring.events.OperationCallEvent;
import jolie.monitoring.events.OperationReplyEvent;
import jolie.net.CommChannel;
import jolie.net.CommMessage;
import jolie.net.ports.OutputPort;
import jolie.process.TransformationReason;
import jolie.runtime.FaultException;
import jolie.runtime.Value;
import jolie.runtime.typing.RequestResponseTypeDescription;
import jolie.runtime.typing.Type;
import jolie.runtime.typing.TypeCheckingException;
import jolie.tracer.MessageTraceAction;
import jolie.tracer.Tracer;

public class SolicitResponseExpression implements Expression {

    private final String operationId;
    private final OutputPort outputPort;
    private final Expression outputExpression; // may be null
    private final RequestResponseTypeDescription types;
    private final ParsingContext context;

    public SolicitResponseExpression(
        String operationId,
        OutputPort outputPort,
        Expression outputExpression,
        RequestResponseTypeDescription types,
        ParsingContext context ) {
        this.operationId = operationId;
        this.outputPort = outputPort;
        this.outputExpression = outputExpression;
        this.types = types;
        this.context = context;
    }

    @Override
    public Expression cloneExpression( TransformationReason reason ) {
        return new SolicitResponseExpression(
            operationId,
            outputPort,
            (outputExpression == null) ? null : outputExpression.cloneExpression( reason ),
            types,
            context );
    }

    private void log( String log, CommMessage message ) {
        final Tracer tracer = Interpreter.getInstance().tracer();
        tracer.trace( () -> new MessageTraceAction(
            MessageTraceAction.Type.SOLICIT_RESPONSE,
            operationId + "@" + outputPort.id(),
            log,
            message,
            context ) );
    }


    @Override
    public Value evaluate()
        throws FaultException {
        CommChannel channel = null;
        CommMessage response = null;
        try {

            CommMessage message =
                CommMessage.createRequest(
                    operationId,
                    outputPort.getResourcePath(),
                    (outputExpression == null) ? Value.UNDEFINED_VALUE : outputExpression.evaluate() );

            log( "SENDING", message );
            if( types.requestType() != null ) {
                try {
                    types.requestType().check( message.value() );
                } catch( TypeCheckingException e ) {
                    log( "TYPE MISMATCH", message );
                    // just for logging also cause
                    Value tmpValue = Value.create();
                    tmpValue.setValue( e.getMessage() );
                    log( "TYPE MISMATCH", new CommMessage( message.id(), message.operationName(),
                        message.resourcePath(), tmpValue, null ) );
                    if( Interpreter.getInstance().isMonitoring() ) {
                        Interpreter.getInstance().fireMonitorEvent(
                            new OperationCallEvent( operationId, ExecutionThread.currentThread().getSessionId(),
                                Long.toString( message.id() ), OperationCallEvent.FAULT,
                                "TypeMismatch:" + e.getMessage(), outputPort.id(), message.value() ) );
                    }

                    throw (e);
                }
            }

            channel = outputPort.getCommChannel();
            channel.send( message );
            // channel.release(); TODO release channel if possible (i.e. it will not be closed)
            log( "SENT", message );
            if( Interpreter.getInstance().isMonitoring() ) {
                Interpreter.getInstance()
                    .fireMonitorEvent( new OperationCallEvent( operationId,
                        ExecutionThread.currentThread().getSessionId(), Long.toString( message.id() ),
                        OperationCallEvent.SUCCESS, "", outputPort.id(), message.value() ) );
            }

            do {
                try {
                    response = channel.recvResponseFor( message ).get( Interpreter.getInstance().responseTimeout(),
                        TimeUnit.MILLISECONDS );
                } catch( InterruptedException e ) {
                    throw new IOException( e );
                } catch( ExecutionException e ) {
                    if( e.getCause() instanceof IOException ) {
                        throw (IOException) e.getCause();
                    } else {
                        throw new IOException( e.getCause() );
                    }
                }
            } while( response == null );
            log( "RECEIVED", response );

            if( response.isFault() ) {
                Type faultType = types.getFaultType( response.fault().faultName() );
                if( faultType != null ) {
                    try {
                        faultType.check( response.fault().value() );
                        if( Interpreter.getInstance().isMonitoring() ) {
                            Interpreter.getInstance()
                                .fireMonitorEvent( new OperationReplyEvent( operationId,
                                    ExecutionThread.currentThread().getSessionId(),
                                    Long.toString( response.id() ), OperationReplyEvent.FAULT,
                                    response.fault().faultName(), outputPort.id(), response.fault().value() ) );
                        }
                    } catch( TypeCheckingException e ) {
                        if( Interpreter.getInstance().isMonitoring() ) {
                            Interpreter.getInstance()
                                .fireMonitorEvent( new OperationReplyEvent( operationId,
                                    ExecutionThread.currentThread().getSessionId(),
                                    Long.toString( response.id() ), OperationReplyEvent.FAULT,
                                    "TypeMismatch on fault:" + response.fault().faultName() + "." + e.getMessage(),
                                    outputPort.id(), response.fault().value() ) );
                        }
                        throw new FaultException( Constants.TYPE_MISMATCH_FAULT_NAME,
                            "Received fault " + response.fault().faultName() + " TypeMismatch (" + operationId + "@"
                                + outputPort.id() + "): " + e.getMessage() );
                    }
                } else {
                    if( Interpreter.getInstance().isMonitoring() ) {
                        Interpreter.getInstance().fireMonitorEvent(
                            new OperationReplyEvent( operationId, ExecutionThread.currentThread().getSessionId(),
                                Long.toString( response.id() ), OperationReplyEvent.FAULT,
                                response.fault().faultName(), outputPort.id(), response.fault().value() ) );
                    }
                }
                throw response.fault();
            } else {
                if( types.responseType() != null ) {
                    try {
                        types.responseType().check( response.value() );
                        if( Interpreter.getInstance().isMonitoring() ) {
                            Interpreter.getInstance()
                                .fireMonitorEvent( new OperationReplyEvent( operationId,
                                    ExecutionThread.currentThread().getSessionId(),
                                    Long.toString( response.id() ), OperationReplyEvent.SUCCESS, "",
                                    outputPort.id(), response.value() ) );
                        }
                    } catch( TypeCheckingException e ) {
                        if( Interpreter.getInstance().isMonitoring() ) {
                            Interpreter.getInstance()
                                .fireMonitorEvent( new OperationReplyEvent( operationId,
                                    ExecutionThread.currentThread().getSessionId(),
                                    Long.toString( response.id() ), OperationReplyEvent.FAULT, e.getMessage(),
                                    outputPort.id(), response.value() ) );
                        }
                        throw new FaultException( Constants.TYPE_MISMATCH_FAULT_NAME, "Received message TypeMismatch ("
                            + operationId + "@" + outputPort.id() + "): " + e.getMessage() );
                    }
                } else {
                    if( Interpreter.getInstance().isMonitoring() ) {
                        Interpreter.getInstance().fireMonitorEvent( new OperationReplyEvent( operationId,
                            ExecutionThread.currentThread().getSessionId(), Long.toString( response.id() ),
                            OperationReplyEvent.SUCCESS, "", outputPort.id(), response.value() ) );
                    }
                }
            }

        } catch( TimeoutException e ) { // The response timed out
            throw new FaultException( Constants.TIMEOUT_EXCEPTION_FAULT_NAME );
        } catch( IOException e ) {
            throw new FaultException( Constants.IO_EXCEPTION_FAULT_NAME, e );
        } catch( URISyntaxException e ) {
            Interpreter.getInstance().logSevere( e );
        } catch( TypeCheckingException e ) {
            throw new FaultException( Constants.TYPE_MISMATCH_FAULT_NAME,
                "Output message TypeMismatch (" + operationId + "@" + outputPort.id() + "): " + e.getMessage() );
        } finally {
            if( channel != null ) {
                try {
                    channel.release();
                } catch( IOException e ) {
                    Interpreter.getInstance().logWarning( e );
                }
            }
        }

        return response.value();
    }

}
