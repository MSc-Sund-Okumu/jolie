package joliex.meta.spec.types;

/**
 * this class is a {@link jolie.runtime.embedding.java.TypedStructure} which can be described as
 * follows:
 *
 * <pre>
 * textLocation: {@link joliex.meta.spec.types.Location}
 * modulePath: {@link joliex.meta.spec.types.LocatedString}
 * importedSymbols[0,2147483647]: {@link joliex.meta.spec.types.ImportedSymbol}
 * </pre>
 *
 * @see jolie.runtime.embedding.java.JolieValue
 * @see jolie.runtime.embedding.java.JolieNative
 * @see joliex.meta.spec.types.Location
 * @see joliex.meta.spec.types.LocatedString
 * @see joliex.meta.spec.types.ImportedSymbol
 * @see #builder()
 */
public final class ImportDef extends jolie.runtime.embedding.java.TypedStructure {

	private static final java.util.Set< java.lang.String > FIELD_KEYS = fieldKeys( ImportDef.class );

	@jolie.runtime.embedding.java.util.JolieName( "textLocation" )
	private final joliex.meta.spec.types.Location textLocation;
	@jolie.runtime.embedding.java.util.JolieName( "modulePath" )
	private final joliex.meta.spec.types.LocatedString modulePath;
	@jolie.runtime.embedding.java.util.JolieName( "importedSymbols" )
	private final java.util.List< joliex.meta.spec.types.ImportedSymbol > importedSymbols;

	public ImportDef( joliex.meta.spec.types.Location textLocation, joliex.meta.spec.types.LocatedString modulePath,
		java.util.SequencedCollection< joliex.meta.spec.types.ImportedSymbol > importedSymbols ) {
		this.textLocation = jolie.runtime.embedding.java.util.ValueManager.validated( "textLocation", textLocation );
		this.modulePath = jolie.runtime.embedding.java.util.ValueManager.validated( "modulePath", modulePath );
		this.importedSymbols = jolie.runtime.embedding.java.util.ValueManager.validated( "importedSymbols",
			importedSymbols, 0, 2147483647, t -> t );
	}

	public joliex.meta.spec.types.Location textLocation() {
		return textLocation;
	}

	public joliex.meta.spec.types.LocatedString modulePath() {
		return modulePath;
	}

	public java.util.List< joliex.meta.spec.types.ImportedSymbol > importedSymbols() {
		return importedSymbols;
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

	public static jolie.runtime.embedding.java.util.StructureListBuilder< ImportDef, Builder > listBuilder() {
		return new jolie.runtime.embedding.java.util.StructureListBuilder<>( ImportDef::builder );
	}

	public static jolie.runtime.embedding.java.util.StructureListBuilder< ImportDef, Builder > listBuilder(
		java.util.SequencedCollection< ? extends jolie.runtime.embedding.java.JolieValue > from ) {
		return from != null
			? new jolie.runtime.embedding.java.util.StructureListBuilder<>( from, ImportDef::from, ImportDef::builder )
			: listBuilder();
	}

	public static ImportDef from( jolie.runtime.embedding.java.JolieValue j )
		throws jolie.runtime.embedding.java.TypeValidationException {
		return new ImportDef(
			jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "textLocation" ),
				joliex.meta.spec.types.Location::from ),
			jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "modulePath" ),
				joliex.meta.spec.types.LocatedString::from ),
			jolie.runtime.embedding.java.util.ValueManager.fieldFrom(
				j.getChildOrDefault( "importedSymbols", java.util.List.of() ),
				joliex.meta.spec.types.ImportedSymbol::from ) );
	}

	public static ImportDef fromValue( jolie.runtime.Value v ) throws jolie.runtime.typing.TypeCheckingException {
		jolie.runtime.embedding.java.util.ValueManager.requireChildren( v, FIELD_KEYS );
		return new ImportDef(
			jolie.runtime.embedding.java.util.ValueManager.singleFieldFrom( v, "textLocation",
				joliex.meta.spec.types.Location::fromValue ),
			jolie.runtime.embedding.java.util.ValueManager.singleFieldFrom( v, "modulePath",
				joliex.meta.spec.types.LocatedString::fromValue ),
			jolie.runtime.embedding.java.util.ValueManager.vectorFieldFrom( v, "importedSymbols",
				joliex.meta.spec.types.ImportedSymbol::fromValue ) );
	}

	public static jolie.runtime.Value toValue( ImportDef t ) {
		final jolie.runtime.Value v = jolie.runtime.Value.create();

		v.getFirstChild( "textLocation" ).deepCopy( joliex.meta.spec.types.Location.toValue( t.textLocation() ) );
		v.getFirstChild( "modulePath" ).deepCopy( joliex.meta.spec.types.LocatedString.toValue( t.modulePath() ) );
		t.importedSymbols().forEach(
			c -> v.getNewChild( "importedSymbols" ).deepCopy( joliex.meta.spec.types.ImportedSymbol.toValue( c ) ) );

		return v;
	}

	public static class Builder {

		private joliex.meta.spec.types.Location textLocation;
		private joliex.meta.spec.types.LocatedString modulePath;
		private java.util.SequencedCollection< joliex.meta.spec.types.ImportedSymbol > importedSymbols;

		private Builder() {}

		private Builder( jolie.runtime.embedding.java.JolieValue j ) {
			this.textLocation = jolie.runtime.embedding.java.util.ValueManager
				.fieldFrom( j.getFirstChild( "textLocation" ), joliex.meta.spec.types.Location::from );
			this.modulePath = jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "modulePath" ),
				joliex.meta.spec.types.LocatedString::from );
			this.importedSymbols = jolie.runtime.embedding.java.util.ValueManager.fieldFrom(
				j.getChildOrDefault( "importedSymbols", java.util.List.of() ),
				joliex.meta.spec.types.ImportedSymbol::from );
		}

		public Builder textLocation( joliex.meta.spec.types.Location textLocation ) {
			this.textLocation = textLocation;
			return this;
		}

		public Builder textLocation(
			java.util.function.Function< joliex.meta.spec.types.Location.Builder, joliex.meta.spec.types.Location > f ) {
			return textLocation( f.apply( joliex.meta.spec.types.Location.builder() ) );
		}

		public Builder modulePath( joliex.meta.spec.types.LocatedString modulePath ) {
			this.modulePath = modulePath;
			return this;
		}

		public Builder modulePath(
			java.util.function.Function< joliex.meta.spec.types.LocatedString.Builder, joliex.meta.spec.types.LocatedString > f ) {
			return modulePath( f.apply( joliex.meta.spec.types.LocatedString.builder() ) );
		}

		public Builder importedSymbols(
			java.util.SequencedCollection< joliex.meta.spec.types.ImportedSymbol > importedSymbols ) {
			this.importedSymbols = importedSymbols;
			return this;
		}

		public Builder importedSymbols(
			java.util.function.Function< jolie.runtime.embedding.java.util.StructureListBuilder< joliex.meta.spec.types.ImportedSymbol, joliex.meta.spec.types.ImportedSymbol.Builder >, java.util.List< joliex.meta.spec.types.ImportedSymbol > > f ) {
			return importedSymbols( f.apply( new jolie.runtime.embedding.java.util.StructureListBuilder<>(
				joliex.meta.spec.types.ImportedSymbol::builder ) ) );
		}

		public ImportDef build() {
			return new ImportDef( textLocation, modulePath, importedSymbols );
		}
	}
}
