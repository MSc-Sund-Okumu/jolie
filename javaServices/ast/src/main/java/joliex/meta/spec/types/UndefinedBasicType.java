package joliex.meta.spec.types;

/**
 * this class is a {@link jolie.runtime.embedding.java.TypedStructure} which can be described as
 * follows:
 *
 * <pre>
 * undefinedTag("undefined"): {@link jolie.runtime.embedding.java.JolieNative.JolieVoid}
 * </pre>
 *
 * @see jolie.runtime.embedding.java.JolieValue
 * @see jolie.runtime.embedding.java.JolieNative
 * @see #builder()
 */
public final class UndefinedBasicType extends jolie.runtime.embedding.java.TypedStructure {

	private static final java.util.Set< java.lang.String > FIELD_KEYS = fieldKeys( UndefinedBasicType.class );

	@jolie.runtime.embedding.java.util.JolieName( "undefined" )
	private final jolie.runtime.embedding.java.JolieNative.JolieVoid undefinedTag;

	public UndefinedBasicType( jolie.runtime.embedding.java.JolieNative.JolieVoid undefinedTag ) {
		this.undefinedTag = jolie.runtime.embedding.java.util.ValueManager.validated( "undefinedTag", undefinedTag );
	}

	public jolie.runtime.embedding.java.JolieNative.JolieVoid undefinedTag() {
		return undefinedTag;
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

	public static jolie.runtime.embedding.java.util.StructureListBuilder< UndefinedBasicType, Builder > listBuilder() {
		return new jolie.runtime.embedding.java.util.StructureListBuilder<>( UndefinedBasicType::builder );
	}

	public static jolie.runtime.embedding.java.util.StructureListBuilder< UndefinedBasicType, Builder > listBuilder(
		java.util.SequencedCollection< ? extends jolie.runtime.embedding.java.JolieValue > from ) {
		return from != null
			? new jolie.runtime.embedding.java.util.StructureListBuilder<>( from, UndefinedBasicType::from,
				UndefinedBasicType::builder )
			: listBuilder();
	}

	public static UndefinedBasicType from( jolie.runtime.embedding.java.JolieValue j )
		throws jolie.runtime.embedding.java.TypeValidationException {
		return new UndefinedBasicType(
			jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "undefined" ),
				c -> c.content() instanceof jolie.runtime.embedding.java.JolieNative.JolieVoid content ? content
					: null ) );
	}

	public static UndefinedBasicType fromValue( jolie.runtime.Value v )
		throws jolie.runtime.typing.TypeCheckingException {
		jolie.runtime.embedding.java.util.ValueManager.requireChildren( v, FIELD_KEYS );
		return new UndefinedBasicType(
			jolie.runtime.embedding.java.util.ValueManager.singleFieldFrom( v, "undefined",
				jolie.runtime.embedding.java.JolieNative.JolieVoid::fromValue ) );
	}

	public static jolie.runtime.Value toValue( UndefinedBasicType t ) {
		final jolie.runtime.Value v = jolie.runtime.Value.create();

		v.getFirstChild( "undefined" ).setValue( t.undefinedTag().value() );

		return v;
	}

	public static class Builder {

		private jolie.runtime.embedding.java.JolieNative.JolieVoid undefinedTag;

		private Builder() {}

		private Builder( jolie.runtime.embedding.java.JolieValue j ) {
			this.undefinedTag =
				jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "undefined" ),
					c -> c.content() instanceof jolie.runtime.embedding.java.JolieNative.JolieVoid content ? content
						: null );
		}

		public Builder undefinedTag( jolie.runtime.embedding.java.JolieNative.JolieVoid undefinedTag ) {
			this.undefinedTag = undefinedTag;
			return this;
		}

		public UndefinedBasicType build() {
			return new UndefinedBasicType( undefinedTag );
		}
	}
}
