package cz.cuni.mff.skychart.projection;

/**
 * A functional interface used for mapping objects into three-dimensional vectors.
 *
 * @author Peter Grajcar
 */
@FunctionalInterface
public interface Vector3Mapping<T> {
   Vector3 map(T obj);
}
