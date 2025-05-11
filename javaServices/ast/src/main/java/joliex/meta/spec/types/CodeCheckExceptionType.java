package joliex.meta.spec.types;

/**
 * this class is a {@link jolie.runtime.embedding.java.TypedStructure} which can be described as
 * follows:
 *
 * <pre>
 * columnInterval: {@link joliex.meta.spec.types.ColumnInterval}
 * stackTrace[0,1]: {@link java.lang.String}
 * exceptionMessage: {@link java.lang.String}
 * lineInterval: {@link joliex.meta.spec.types.LineInterval}
 * </pre>
 *
 * @see jolie.runtime.embedding.java.JolieValue
 * @see jolie.runtime.embedding.java.JolieNative
 * @see joliex.meta.spec.types.ColumnInterval
 * @see joliex.meta.spec.types.LineInterval
 * @see #builder()
 */
public final class CodeCheckExceptionType extends jolie.runtime.embedding.java.TypedStructure {

	private static final java.util.Set< java.lang.String > FIELD_KEYS = fieldKeys( CodeCheckExceptionType.class );

	@jolie.runtime.embedding.java.util.JolieName( "columnInterval" )
	private final joliex.meta.spec.types.ColumnInterval columnInterval;
	@jolie.runtime.embedding.java.util.JolieName( "stackTrace" )
	private final java.lang.String stackTrace;
	@jolie.runtime.embedding.java.util.JolieName( "exceptionMessage" )
	private final java.lang.String exceptionMessage;
	@jolie.runtime.embedding.java.util.JolieName( "lineInterval" )
	private final joliex.meta.spec.types.LineInterval lineInterval;

	public CodeCheckExceptionType( joliex.meta.spec.types.ColumnInterval columnInterval, java.lang.String stackTrace,
		java.lang.String exceptionMessage, joliex.meta.spec.types.LineInterval lineInterval ) {
		this.columnInterval =
			jolie.runtime.embedding.java.util.ValueManager.validated( "columnInterval", columnInterval );
		this.stackTrace = stackTrace;
		this.exceptionMessage =
			jolie.runtime.embedding.java.util.ValueManager.validated( "exceptionMessage", exceptionMessage );
		this.lineInterval = jolie.runtime.embedding.java.util.ValueManager.validated( "lineInterval", lineInterval );
	}

	public joliex.meta.spec.types.ColumnInterval columnInterval() {
		return columnInterval;
	}

	public java.util.Optional< java.lang.String > stackTrace() {
		return java.util.Optional.ofNullable( stackTrace );
	}

	public java.lang.String exceptionMessage() {
		return exceptionMessage;
	}

	public joliex.meta.spec.types.LineInterval lineInterval() {
		return lineInterval;
	}

	public jolie.runtime.embedding.java.JolieNative.JolieVoid content() {
		return new jolie.runtime.embedding.java.JolieNative.JolieVoid();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder( jolie.runtime.embedding.java.JolieValue from ) {
		return from != null ? new Builder( from ) : builder();
	}

	public static jolie.runtime.embedding.java.util.StructureListBuilder< CodeCheckExceptionType, Builder > listBuilder() {
		return new jolie.runtime.embedding.java.util.StructureListBuilder<>( CodeCheckExceptionType::builder );
	}

	public static jolie.runtime.embedding.java.util.StructureListBuilder< CodeCheckExceptionType, Builder > listBuilder(
		java.util.SequencedCollection< ? extends jolie.runtime.embedding.java.JolieValue > from ) {
		return from != null
			? new jolie.runtime.embedding.java.util.StructureListBuilder<>( from, CodeCheckExceptionType::from,
				CodeCheckExceptionType::builder )
			: listBuilder();
	}

	public static CodeCheckExceptionType from( jolie.runtime.embedding.java.JolieValue j )
		throws jolie.runtime.embedding.java.TypeValidationException {
		return new CodeCheckExceptionType(
			jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "columnInterval" ),
				joliex.meta.spec.types.ColumnInterval::from ),
			jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "stackTrace" ),
				c -> c.content() instanceof jolie.runtime.embedding.java.JolieNative.JolieString content
					? content.value()
					: null ),
			jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "exceptionMessage" ),
				c -> c.content() instanceof jolie.runtime.embedding.java.JolieNative.JolieString content
					? content.value()
					: null ),
			jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "lineInterval" ),
				joliex.meta.spec.types.LineInterval::from ) );
	}

	public static CodeCheckExceptionType fromValue( jolie.runtime.Value v )
		throws jolie.runtime.typing.TypeCheckingException {
		jolie.runtime.embedding.java.util.ValueManager.requireChildren( v, FIELD_KEYS );
		return new CodeCheckExceptionType(
			jolie.runtime.embedding.java.util.ValueManager.singleFieldFrom( v, "columnInterval",
				joliex.meta.spec.types.ColumnInterval::fromValue ),
			jolie.runtime.embedding.java.util.ValueManager.singleFieldFrom( v, "stackTrace",
				jolie.runtime.embedding.java.JolieNative.JolieString::fieldFromValue ),
			jolie.runtime.embedding.java.util.ValueManager.singleFieldFrom( v, "exceptionMessage",
				jolie.runtime.embedding.java.JolieNative.JolieString::fieldFromValue ),
			jolie.runtime.embedding.java.util.ValueManager.singleFieldFrom( v, "lineInterval",
				joliex.meta.spec.types.LineInterval::fromValue ) );
	}

	public static jolie.runtime.Value toValue( CodeCheckExceptionType t ) {
		final jolie.runtime.Value v = jolie.runtime.Value.create();

		v.getFirstChild( "columnInterval" )
			.deepCopy( joliex.meta.spec.types.ColumnInterval.toValue( t.columnInterval() ) );
		t.stackTrace().ifPresent( c -> v.getFirstChild( "stackTrace" ).setValue( c ) );
		v.getFirstChild( "exceptionMessage" ).setValue( t.exceptionMessage() );
		v.getFirstChild( "lineInterval" ).deepCopy( joliex.meta.spec.types.LineInterval.toValue( t.lineInterval() ) );

		return v;
	}

	public static class Builder {

		private joliex.meta.spec.types.ColumnInterval columnInterval;
		private java.lang.String stackTrace;
		private java.lang.String exceptionMessage;
		private joliex.meta.spec.types.LineInterval lineInterval;

		private Builder() {}

		private Builder( jolie.runtime.embedding.java.JolieValue j ) {
			this.columnInterval = jolie.runtime.embedding.java.util.ValueManager
				.fieldFrom( j.getFirstChild( "columnInterval" ), joliex.meta.spec.types.ColumnInterval::from );
			this.stackTrace = jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "stackTrace" ),
				c -> c.content() instanceof jolie.runtime.embedding.java.JolieNative.JolieString content
					? content.value()
					: null );
			this.exceptionMessage =
				jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "exceptionMessage" ),
					c -> c.content() instanceof jolie.runtime.embedding.java.JolieNative.JolieString content
						? content.value()
						: null );
			this.lineInterval = jolie.runtime.embedding.java.util.ValueManager
				.fieldFrom( j.getFirstChild( "lineInterval" ), joliex.meta.spec.types.LineInterval::from );
		}

		public Builder columnInterval( joliex.meta.spec.types.ColumnInterval columnInterval ) {
			this.columnInterval = columnInterval;
			return this;
		}

		public Builder columnInterval(
			java.util.function.Function< joliex.meta.spec.types.ColumnInterval.Builder, joliex.meta.spec.types.ColumnInterval > f ) {
			return columnInterval( f.apply( joliex.meta.spec.types.ColumnInterval.builder() ) );
		}

		public Builder stackTrace( java.lang.String stackTrace ) {
			this.stackTrace = stackTrace;
			return this;
		}

		public Builder exceptionMessage( java.lang.String exceptionMessage ) {
			this.exceptionMessage = exceptionMessage;
			return this;
		}

		public Builder lineInterval( joliex.meta.spec.types.LineInterval lineInterval ) {
			this.lineInterval = lineInterval;
			return this;
		}

		public Builder lineInterval(
			java.util.function.Function< joliex.meta.spec.types.LineInterval.Builder, joliex.meta.spec.types.LineInterval > f ) {
			return lineInterval( f.apply( joliex.meta.spec.types.LineInterval.builder() ) );
		}

		public CodeCheckExceptionType build() {
			return new CodeCheckExceptionType( columnInterval, stackTrace, exceptionMessage, lineInterval );
		}
	}
}
