package cz.cuni.mff.skychart.projection;

/**
 * An interface for projection planes;
 *
 * @author Peter Grajcar
 */
public interface ProjectionPlane {

    /**
     * Calculates and returns the projected coordinates of given vector on the plane.
     *
     * @param vector a vector to project.
     * @return coordinates of the projected vector on the plane.
     */
    Vector2 project(Vector3 vector);

    /**
     * Calculates and returns the projected coordinates of given object on the plane.
     *
     * @param obj an object to project.
     * @param mapping a mapping that maps the object to {@link Vector3 Vector3}.
     * @param <T> a type of the object.
     * @return coordinates of the projected object on the plane.
     */
    default <T> Vector2 project(T obj, Vector3Mapping<T> mapping) { return project(mapping.map(obj)); };

    /**
     * Returns true if the vector is in front of the picture plane.
     *
     * @param vector a vector.
     * @param epsilon a small number considered zero.
     * @return true if vector is in front of the plane.
     */
    boolean isFront(Vector3 vector, double epsilon);

    /**
     * Returns true if the vector is in front of the picture plane.
     *
     * @param vector a vector
     * @return true if the vector is in front of the plane.
     */
    default boolean isFront(Vector3 vector) { return isFront(vector, 0); };

    /**
     * Returns true if the object is in front of the picture plane.
     *
     * @param obj an object.
     * @param mapping a mapping that maps the object to {@link Vector3 Vector3}.
     * @param epsilon a small number considered zero.
     * @param <T> a type of the object.
     * @return true if the vector is in front of the plane.
     */
    default <T> boolean isFront(T obj, Vector3Mapping<T> mapping, double epsilon) { return isFront(mapping.map(obj), epsilon); }

    /**
     * Returns true if the object is in front of the picture plane.
     *
     * @param obj an object.
     * @param mapping a mapping that maps the object to {@link Vector3 Vector3}.
     * @param <T> a type of the object.
     * @return true if the vector is in front of the plane.
     */
    default <T> boolean isFront(T obj, Vector3Mapping<T> mapping) { return isFront(mapping.map(obj), 0); }

    /**
     * Returns rotation as yaw, pitch, roll vector.
     *
     * @return a vector with angles about each axis.
     */
    Vector3 getRotation();

    /**
     * Returns an unit vector with direction of view.
     *
     * @return an unit vector with direction of view.
     */
    Vector3 getForward();

}
