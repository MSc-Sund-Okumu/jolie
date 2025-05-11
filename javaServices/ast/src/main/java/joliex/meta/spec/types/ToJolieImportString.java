package joliex.meta.spec.types;

/**
 * this class is a {@link jolie.runtime.embedding.java.TypedStructure} which can be described as
 * follows:
 *
 * <pre>
 * importedModule: {@link java.lang.String}
 * module: {@link java.lang.String}
 * </pre>
 *
 * @see jolie.runtime.embedding.java.JolieValue
 * @see jolie.runtime.embedding.java.JolieNative
 * @see #builder()
 */
public final class ToJolieImportString extends jolie.runtime.embedding.java.TypedStructure {

	private static final java.util.Set< java.lang.String > FIELD_KEYS = fieldKeys( ToJolieImportString.class );

	@jolie.runtime.embedding.java.util.JolieName( "importedModule" )
	private final java.lang.String importedModule;
	@jolie.runtime.embedding.java.util.JolieName( "module" )
	private final java.lang.String module;

	public ToJolieImportString( java.lang.String importedModule, java.lang.String module ) {
		this.importedModule =
			jolie.runtime.embedding.java.util.ValueManager.validated( "importedModule", importedModule );
		this.module = jolie.runtime.embedding.java.util.ValueManager.validated( "module", module );
	}

	public java.lang.String importedModule() {
		return importedModule;
	}

	public java.lang.String module() {
		return module;
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

	public static jolie.runtime.embedding.java.util.StructureListBuilder< ToJolieImportString, Builder > listBuilder() {
		return new jolie.runtime.embedding.java.util.StructureListBuilder<>( ToJolieImportString::builder );
	}

	public static jolie.runtime.embedding.java.util.StructureListBuilder< ToJolieImportString, Builder > listBuilder(
		java.util.SequencedCollection< ? extends jolie.runtime.embedding.java.JolieValue > from ) {
		return from != null
			? new jolie.runtime.embedding.java.util.StructureListBuilder<>( from, ToJolieImportString::from,
				ToJolieImportString::builder )
			: listBuilder();
	}

	public static ToJolieImportString from( jolie.runtime.embedding.java.JolieValue j )
		throws jolie.runtime.embedding.java.TypeValidationException {
		return new ToJolieImportString(
			jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "importedModule" ),
				c -> c.content() instanceof jolie.runtime.embedding.java.JolieNative.JolieString content
					? content.value()
					: null ),
			jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "module" ),
				c -> c.content() instanceof jolie.runtime.embedding.java.JolieNative.JolieString content
					? content.value()
					: null ) );
	}

	public static ToJolieImportString fromValue( jolie.runtime.Value v )
		throws jolie.runtime.typing.TypeCheckingException {
		jolie.runtime.embedding.java.util.ValueManager.requireChildren( v, FIELD_KEYS );
		return new ToJolieImportString(
			jolie.runtime.embedding.java.util.ValueManager.singleFieldFrom( v, "importedModule",
				jolie.runtime.embedding.java.JolieNative.JolieString::fieldFromValue ),
			jolie.runtime.embedding.java.util.ValueManager.singleFieldFrom( v, "module",
				jolie.runtime.embedding.java.JolieNative.JolieString::fieldFromValue ) );
	}

	public static jolie.runtime.Value toValue( ToJolieImportString t ) {
		final jolie.runtime.Value v = jolie.runtime.Value.create();

		v.getFirstChild( "importedModule" ).setValue( t.importedModule() );
		v.getFirstChild( "module" ).setValue( t.module() );

		return v;
	}

	public static class Builder {

		private java.lang.String importedModule;
		private java.lang.String module;

		private Builder() {}

		private Builder( jolie.runtime.embedding.java.JolieValue j ) {
			this.importedModule =
				jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "importedModule" ),
					c -> c.content() instanceof jolie.runtime.embedding.java.JolieNative.JolieString content
						? content.value()
						: null );
			this.module = jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "module" ),
				c -> c.content() instanceof jolie.runtime.embedding.java.JolieNative.JolieString content
					? content.value()
					: null );
		}

		public Builder importedModule( java.lang.String importedModule ) {
			this.importedModule = importedModule;
			return this;
		}

		public Builder module( java.lang.String module ) {
			this.module = module;
			return this;
		}

		public ToJolieImportString build() {
			return new ToJolieImportString( importedModule, module );
		}
	}
}
