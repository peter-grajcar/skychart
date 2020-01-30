package cz.cuni.mff.skychart.projection;

/**
 * A functional interface used for mapping objects into two-dimensional vectors.
 *
 * @author Peter Grajcar
 */
@FunctionalInterface
public interface Vector2Mapping<T> {
    /**
     * Maps the object to a two-dimensional vector.
     *
     * @param obj the object to map.
     * @return a two-dimensional vector.
     */
    Vector2 map(T obj);
}
