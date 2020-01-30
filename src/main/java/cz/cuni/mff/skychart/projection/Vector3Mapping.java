package cz.cuni.mff.skychart.projection;

/**
 * A functional interface used for mapping objects into three-dimensional vectors.
 *
 * @author Peter Grajcar
 */
@FunctionalInterface
public interface Vector3Mapping<T> {
   /**
    * Maps the object to a three-dimensional vector.
    *
    * @param obj the object to map.
    * @return a three-dimensional vector.
    */
   Vector3 map(T obj);
}
