package joliex.meta.spec.faults;

public class CodeCheckException extends jolie.runtime.FaultException {

	private final joliex.meta.spec.types.CodeCheckExceptionType fault;

	public CodeCheckException( joliex.meta.spec.types.CodeCheckExceptionType fault ) {
		super( "CodeCheckException", joliex.meta.spec.types.CodeCheckExceptionType.toValue( fault ) );
		this.fault = java.util.Objects.requireNonNull( fault );
	}

	public joliex.meta.spec.types.CodeCheckExceptionType fault() {
		return fault;
	}
}
