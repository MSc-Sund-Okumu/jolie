package joliex.meta.spec.types;

/**
 * this class is a {@link jolie.runtime.embedding.java.TypedStructure} which can be described as
 * follows:
 *
 * <pre>
 * localSymbolName: {@link joliex.meta.spec.types.LocatedSymbolRef}
 * originalSymbolName: {@link joliex.meta.spec.types.LocatedSymbolRef}
 * </pre>
 *
 * @see jolie.runtime.embedding.java.JolieValue
 * @see jolie.runtime.embedding.java.JolieNative
 * @see joliex.meta.spec.types.LocatedSymbolRef
 * @see #builder()
 */
public final class ImportedSymbol extends jolie.runtime.embedding.java.TypedStructure {

	private static final java.util.Set< java.lang.String > FIELD_KEYS = fieldKeys( ImportedSymbol.class );

	@jolie.runtime.embedding.java.util.JolieName( "localSymbolName" )
	private final joliex.meta.spec.types.LocatedSymbolRef localSymbolName;
	@jolie.runtime.embedding.java.util.JolieName( "originalSymbolName" )
	private final joliex.meta.spec.types.LocatedSymbolRef originalSymbolName;

	public ImportedSymbol( joliex.meta.spec.types.LocatedSymbolRef localSymbolName,
		joliex.meta.spec.types.LocatedSymbolRef originalSymbolName ) {
		this.localSymbolName =
			jolie.runtime.embedding.java.util.ValueManager.validated( "localSymbolName", localSymbolName );
		this.originalSymbolName =
			jolie.runtime.embedding.java.util.ValueManager.validated( "originalSymbolName", originalSymbolName );
	}

	public joliex.meta.spec.types.LocatedSymbolRef localSymbolName() {
		return localSymbolName;
	}

	public joliex.meta.spec.types.LocatedSymbolRef originalSymbolName() {
		return originalSymbolName;
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

	public static jolie.runtime.embedding.java.util.StructureListBuilder< ImportedSymbol, Builder > listBuilder() {
		return new jolie.runtime.embedding.java.util.StructureListBuilder<>( ImportedSymbol::builder );
	}

	public static jolie.runtime.embedding.java.util.StructureListBuilder< ImportedSymbol, Builder > listBuilder(
		java.util.SequencedCollection< ? extends jolie.runtime.embedding.java.JolieValue > from ) {
		return from != null
			? new jolie.runtime.embedding.java.util.StructureListBuilder<>( from, ImportedSymbol::from,
				ImportedSymbol::builder )
			: listBuilder();
	}

	public static ImportedSymbol from( jolie.runtime.embedding.java.JolieValue j )
		throws jolie.runtime.embedding.java.TypeValidationException {
		return new ImportedSymbol(
			jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "localSymbolName" ),
				joliex.meta.spec.types.LocatedSymbolRef::from ),
			jolie.runtime.embedding.java.util.ValueManager.fieldFrom( j.getFirstChild( "originalSymbolName" ),
				joliex.meta.spec.types.LocatedSymbolRef::from ) );
	}

	public static ImportedSymbol fromValue( jolie.runtime.Value v ) throws jolie.runtime.typing.TypeCheckingException {
		jolie.runtime.embedding.java.util.ValueManager.requireChildren( v, FIELD_KEYS );
		return new ImportedSymbol(
			jolie.runtime.embedding.java.util.ValueManager.singleFieldFrom( v, "localSymbolName",
				joliex.meta.spec.types.LocatedSymbolRef::fromValue ),
			jolie.runtime.embedding.java.util.ValueManager.singleFieldFrom( v, "originalSymbolName",
				joliex.meta.spec.types.LocatedSymbolRef::fromValue ) );
	}

	public static jolie.runtime.Value toValue( ImportedSymbol t ) {
		final jolie.runtime.Value v = jolie.runtime.Value.create();

		v.getFirstChild( "localSymbolName" )
			.deepCopy( joliex.meta.spec.types.LocatedSymbolRef.toValue( t.localSymbolName() ) );
		v.getFirstChild( "originalSymbolName" )
			.deepCopy( joliex.meta.spec.types.LocatedSymbolRef.toValue( t.originalSymbolName() ) );

		return v;
	}

	public static class Builder {

		private joliex.meta.spec.types.LocatedSymbolRef localSymbolName;
		private joliex.meta.spec.types.LocatedSymbolRef originalSymbolName;

		private Builder() {}

		private Builder( jolie.runtime.embedding.java.JolieValue j ) {
			this.localSymbolName = jolie.runtime.embedding.java.util.ValueManager
				.fieldFrom( j.getFirstChild( "localSymbolName" ), joliex.meta.spec.types.LocatedSymbolRef::from );
			this.originalSymbolName = jolie.runtime.embedding.java.util.ValueManager
				.fieldFrom( j.getFirstChild( "originalSymbolName" ), joliex.meta.spec.types.LocatedSymbolRef::from );
		}

		public Builder localSymbolName( joliex.meta.spec.types.LocatedSymbolRef localSymbolName ) {
			this.localSymbolName = localSymbolName;
			return this;
		}

		public Builder localSymbolName(
			java.util.function.Function< joliex.meta.spec.types.LocatedSymbolRef.Builder, joliex.meta.spec.types.LocatedSymbolRef > f ) {
			return localSymbolName( f.apply( joliex.meta.spec.types.LocatedSymbolRef.builder() ) );
		}

		public Builder originalSymbolName( joliex.meta.spec.types.LocatedSymbolRef originalSymbolName ) {
			this.originalSymbolName = originalSymbolName;
			return this;
		}

		public Builder originalSymbolName(
			java.util.function.Function< joliex.meta.spec.types.LocatedSymbolRef.Builder, joliex.meta.spec.types.LocatedSymbolRef > f ) {
			return originalSymbolName( f.apply( joliex.meta.spec.types.LocatedSymbolRef.builder() ) );
		}

		public ImportedSymbol build() {
			return new ImportedSymbol( localSymbolName, originalSymbolName );
		}
	}
}
