package jolie.runtime.embedding.java;

import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import jolie.runtime.ByteArray;
import jolie.runtime.embedding.java.util.JolieName;
import jolie.util.Pair;

public abstract class TypedStructure implements JolieValue {

	private volatile SoftReference<Map<String,List<JolieValue>>> children = new SoftReference<>( null );
	
	@Override
	public Map<String,List<JolieValue>> children() {
		return Objects.requireNonNullElseGet( children.get(), this::createChildren );
	}

	@Override
	public boolean equals( Object obj ) { return obj != null && obj instanceof JolieValue j && content().equals( j.content() ) && children().equals( j.children() ); }
    
	@Override
	public int hashCode() {
		if ( children().isEmpty() )
			return content().hashCode();

        int hash = 7;
        hash = 31 * hash + content().hashCode();
        hash = 31 * hash + children().hashCode();
        return hash;
    }

	@Override
    public String toString() {
        return (content() instanceof JolieNative.JolieString ? "\"" + content().toString() + "\"" : content().toString())
            + children()
                .entrySet()
                .parallelStream()
                .flatMap( e -> {
                    final List<JolieValue> ls = e.getValue();
                    return ls.size() == 1
                        ? Stream.of( e.getKey() + " = " + ls.getFirst().toString() )
                        : IntStream.range( 0, ls.size() ).mapToObj( i -> e.getKey() + "[" + i + "] = " + ls.get( i ).toString() );
                } )
                .reduce( ( s1, s2 ) -> s1 + "\n" + s2 )
                .map( s -> "\n" + s.indent( 4 ) )
                .orElse( "" );
    }

	private synchronized Map<String,List<JolieValue>> createChildren() {
		Map<String,List<JolieValue>> c = children.get();
		if ( c == null ) {
			c = Arrays.stream( this.getClass().getDeclaredFields() )
				.map( f -> Optional.ofNullable( f.getAnnotation( JolieName.class ) )
					.map( JolieName::value )
					.map( n -> Pair.of( n, fromField( f ) ) ) )
				.filter( Optional::isPresent )
				.map( Optional::get )
				.filter( p -> !p.value().isEmpty() )
				.collect( Collectors.toUnmodifiableMap(
					Pair::key, 
					Pair::value ) );
			children = new SoftReference<>( c );
		}
		return c;
	}

	private List<JolieValue> fromField( Field field ) {
		try {
			field.trySetAccessible();
			return toChild( field.get( this ) );
		} catch( IllegalArgumentException | IllegalAccessException e ) {
			e.printStackTrace();
			return List.of();
		}
	}

	protected static Set<String> fieldKeys( Class<?> cls ) {
		return Arrays.stream( cls.getDeclaredFields() )
			.map( f -> Optional.ofNullable( f.getAnnotation( JolieName.class ) ).map( JolieName::value ) )
			.filter( Optional::isPresent )
			.map( Optional::get )
			.collect( Collectors.toSet() );
	}

	private static List<JolieValue> toChild( Object obj ) {
		if ( obj instanceof List<?> ls )
			return ls.parallelStream().map( TypedStructure::toJolieValue ).toList();

		final JolieValue j = toJolieValue( obj );
		return j == null ? List.of() : List.of( j );
	}

	private static JolieValue toJolieValue( Object obj ) {
		return switch ( obj ) {
			case null -> null;
			case JolieValue j -> j;
			case JolieNative<?> n -> JolieValue.of( n );
			case Boolean v -> JolieValue.of( v );
			case Integer v -> JolieValue.of( v );
			case Long v -> JolieValue.of( v );
			case Double v -> JolieValue.of( v );
			case String v -> JolieValue.of( v );
			case ByteArray v -> JolieValue.of( v );
			default -> null;
		};
	}
}
