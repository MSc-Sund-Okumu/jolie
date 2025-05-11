package joliex.meta.spec.types;

/**
 * this class is a {@link jolie.runtime.embedding.java.TypedStructure} which can be described as
 * follows:
 *
 * <pre>
 * start: {@link java.lang.Integer}
 * end: {@link java.lang.Integer}
 * </pre>
 *
 * @see jolie.runtime.embedding.java.JolieValue
 * @see jolie.runtime.embedding.java.JolieNative
 * @see #builder()
 */
public final class ColumnInterval extends jolie.runtime.embedding.java.TypedStructure {

	private static final java.util.Set< java.lang.String > FIELD_KEYS = fieldKeys( ColumnInterval.class );

	@jolie.runtime.embedding.java.util.JolieName( "start" )
	private final java.lang.Integer start;
	@jolie.runtime.embedding.java.util.JolieName( "end" )
	private final java.lang.Integer end;

	public ColumnInterval( java.lang.Integer start, java.lang.Integer end ) {
		this.start = jolie.runtime.embedding.java.util.ValueManager.validated( "start", start );
		this.end = jolie.runtime.embedding.java.util.ValueManager.validated( "end", end );
	}

	public java.lang.Integer start() {
		return start;
	}

	public java.lang.Integer end() {
		return end;
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

	public static jolie.runtime.embedding.java.util.StructureListBuilder< ColumnInterval, Builder > listBuilder() {
		return new jolie.runtime.embedding.java.util.StructureListBuilder<>( ColumnInterval::builder );
	}

	public static jolie.runtime.embedding.java.util.StructureListBuilder< ColumnInterval, Builder > listBuilder(
		java.util.SequencedCollection< ? extends jolie.runtime.embedding.java.JolieValue > from ) {
		return from != null
			? new jolie.runtime.embedding.java.util.StructureListBuilder<>( from, ColumnInterval::from,
				ColumnInterval::builder )
			: listBuilder();
	}

	public static ColumnInterval from( jolie.runtime.embedding.java.JolieValue j )
		throws jolie.runtime.embedding.java.TypeValidationException {
		return new ColumnInterval(
			jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "start" ),
				c -> c.content() instanceof jolie.runtime.embedding.java.JolieNative.JolieInt content ? content.value()
					: null ),
			jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "end" ),
				c -> c.content() instanceof jolie.runtime.embedding.java.JolieNative.JolieInt content ? content.value()
					: null ) );
	}

	public static ColumnInterval fromValue( jolie.runtime.Value v ) throws jolie.runtime.typing.TypeCheckingException {
		jolie.runtime.embedding.java.util.ValueManager.requireChildren( v, FIELD_KEYS );
		return new ColumnInterval(
			jolie.runtime.embedding.java.util.ValueManager.singleFieldFrom( v, "start",
				jolie.runtime.embedding.java.JolieNative.JolieInt::fieldFromValue ),
			jolie.runtime.embedding.java.util.ValueManager.singleFieldFrom( v, "end",
				jolie.runtime.embedding.java.JolieNative.JolieInt::fieldFromValue ) );
	}

	public static jolie.runtime.Value toValue( ColumnInterval t ) {
		final jolie.runtime.Value v = jolie.runtime.Value.create();

		v.getFirstChild( "start" ).setValue( t.start() );
		v.getFirstChild( "end" ).setValue( t.end() );

		return v;
	}

	public static class Builder {

		private java.lang.Integer start;
		private java.lang.Integer end;

		private Builder() {}

		private Builder( jolie.runtime.embedding.java.JolieValue j ) {
			this.start = jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "start" ),
				c -> c.content() instanceof jolie.runtime.embedding.java.JolieNative.JolieInt content ? content.value()
					: null );
			this.end = jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "end" ),
				c -> c.content() instanceof jolie.runtime.embedding.java.JolieNative.JolieInt content ? content.value()
					: null );
		}

		public Builder start( java.lang.Integer start ) {
			this.start = start;
			return this;
		}

		public Builder end( java.lang.Integer end ) {
			this.end = end;
			return this;
		}

		public ColumnInterval build() {
			return new ColumnInterval( start, end );
		}
	}
}
