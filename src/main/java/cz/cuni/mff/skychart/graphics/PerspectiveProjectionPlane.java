package cz.cuni.mff.skychart.graphics;

/**
 * This class allows to project three-dimensional vector onto a plane using perspective projection.
 *
 * @author Peter Grajcar
 */
public class PerspectiveProjectionPlane {

    private Vector3 pointOfView;

    /*
     * Not only this vector represents a direction, it also defines
     * the distance to the picture plane.
     */
    private Vector3 direction;

    private Vector3 up;
    private Vector3 side;

    /**
     * Constructs a projection plane with initial point of view, direction of view, and base vectors of the plane.
     *
     * @param pov point of view.
     * @param dir the direction and distance to the plane.
     * @param up a vector pointing upwards
     */
    public PerspectiveProjectionPlane(Vector3 pov, Vector3 dir, Vector3 up) {
        this.pointOfView = pov;
        this.direction = dir;
        this.up = up.normalise();
        this.side = getSide();
    }

    /**
     * Rotates the direction of view by given angle around given axis.
     *
     * @param axis axis around which the direction of view will be rotated.
     * @param angle angle in radians.
     */
    public void rotate(Vector3.Axis axis, double angle) {

    }

    /**
     * Calculates and returns the projected coordinates of given object on the plane.
     *
     * <a href="http://geomalgorithms.com/a06-_intersect-2.html">link</a>
     *
     * @param object an object to project.
     * @return coordinates of the projected object on the plane.
     */
    public Vector2 project(Vector3 object) {
        Vector3 intersection = intersection(object);
        Vector3 P = pointOfView.add(direction);
        Vector3 u = up.subtract(P);
        Vector3 v = side.subtract(P);
        Vector3 w = intersection.subtract(P);

        double denom = u.dot(v)*u.dot(v) - u.normSquared() * u.normSquared();
        double x = (u.dot(v) * w.dot(u) - u.normSquared() * w.dot(v)) / denom;
        double y = (u.dot(v) * w.dot(v) - v.normSquared() * w.dot(u)) / denom;

        return new Vector2(x, y);
    }

    /**
     * Calculates and returns the intersection point of a ray defined by the object and the point of view.
     *
     * <a href="http://geomalgorithms.com/a05-_intersect-1.html">link</a>
     *
     * @param object an object defining the ray.
     * @return the intersection point.
     */
    private Vector3 intersection(Vector3 object) {
        // Defines a ray from the object to the point of view.
        // R(t) = O + u * t
        Vector3 u = pointOfView.subtract(object);
        // A point on the picture plane
        Vector3 P = pointOfView.add(direction);
        Vector3 w = object.subtract(P);

        double t = - direction.dot(w) / direction.dot(u);
        return object.add(u.multiply(t));
    }

    /**
     * Returns a normalised vector perpendicular to the direction of view and upward vector.
     *
     * @return an unit vector perpendicular to the direction vector and upward vector.
     */
    private Vector3 getSide() {
        return direction.cross(up).normalise();
    }

}