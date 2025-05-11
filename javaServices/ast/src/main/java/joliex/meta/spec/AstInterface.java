package joliex.meta.spec;

public interface AstInterface {

	/**
	 * Returns the URI of the module pointed to by the request string relative to
	 * request.textLocation.source (textLocation.range is not used)
	 */
	java.lang.String fromJolieImportString( joliex.meta.spec.types.LocatedString request );

	joliex.meta.spec.types.ResolveSymbolResponse resolveSymbol( joliex.meta.spec.types.LocatedSymbolRef request )
		throws joliex.meta.spec.faults.CodeCheckException;

	joliex.meta.spec.types.Module parseModule( java.lang.String request )
		throws joliex.meta.spec.faults.CodeCheckException;

	/**
	 *
	 * Returns the import path required to import request.importedModule in the source file
	 * request.module. If request.importedModule is in the standard library, an absolute path is
	 * returned, i.e. "console" instead of ......dist.jolie.packages.console
	 *
	 */
	java.lang.String toJolieImportString( joliex.meta.spec.types.ToJolieImportString request );
}
