package cz.cuni.mff.skychart.graphics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class allows to project three-dimensional vector onto a plane using perspective projection.
 *
 * @author Peter Grajcar
 */
public class PerspectiveProjectionPlane {

    private static final Logger logger = LogManager.getLogger(PerspectiveProjectionPlane.class);

    private Vector3 pointOfView;

    /*
     * Not only this vector represents a direction, it also defines
     * the distance to the picture plane.
     */
    private Vector3 direction;

    private Vector3 up;
    private Vector3 side;

    private double rotationY, rotationZ;

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
        /*pointOfView = pointOfView.rotate(axis, angle);
        direction = direction.rotate(axis, angle);
        up = up.rotate(axis, angle);
        side = side.rotate(axis, angle);*/
        switch (axis){
            case Z:
                rotationZ += angle;
                break;
            case Y:
                rotationY += angle;
                break;
            default:
                break;
        }
    }

    /**
     * Sets rotation of the view.
     *
     * @param rotationY angle about y axis.
     * @param rotationZ angle about z axis.
     */
    public void setRotation(double rotationY, double rotationZ) {
        this.rotationY = rotationY;
        this.rotationZ = rotationZ;
    }

    /**
     * Returns actual rotation.
     *
     * @return vector with angles about each axis.
     */
    public Vector3 getRotation() {
        return new Vector3(0, rotationY, rotationZ);
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

        Vector3 pov = pointOfView.rotate(Vector3.Axis.Z, rotationZ).rotate(Vector3.Axis.Y, rotationY);
        Vector3 dir = direction.rotate(Vector3.Axis.Z, rotationZ).rotate(Vector3.Axis.Y, rotationY);
        Vector3 upRot = up.rotate(Vector3.Axis.Z, rotationZ).rotate(Vector3.Axis.Y, rotationY);
        Vector3 sideRot = side.rotate(Vector3.Axis.Z, rotationZ).rotate(Vector3.Axis.Y, rotationY);

        Vector3 P = pov.add(dir);
        Vector3 u = upRot;
        Vector3 v = sideRot;
        Vector3 w = intersection.subtract(P);

        double denominator = u.dot(v)*u.dot(v) - u.dot(u) * v.dot(v);
        double x = (u.dot(v) * w.dot(u) - u.dot(u) * w.dot(v)) / denominator;
        double y = (u.dot(v) * w.dot(v) - v.dot(v) * w.dot(u)) / denominator;

        return new Vector2(x, y);
    }

    public boolean isFront(Vector3 object, double epsilon){
        Vector3 pov = pointOfView.rotate(Vector3.Axis.Z, rotationZ).rotate(Vector3.Axis.Y, rotationY);
        Vector3 dir = direction.rotate(Vector3.Axis.Z, rotationZ).rotate(Vector3.Axis.Y, rotationY);

        Vector3 P = pov.add(dir);
        return dir.dot(object.subtract(P)) > epsilon;
    }

    public <T> Vector2 project(T obj, Vector3Mapping<T> mapping) {
        return project(mapping.map(obj));
    }

    public <T> boolean isFront(T obj, Vector3Mapping<T> mapping, double epsilon) {
        return isFront(mapping.map(obj), epsilon);
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
        Vector3 pov = pointOfView.rotate(Vector3.Axis.Z, rotationZ).rotate(Vector3.Axis.Y, rotationY);
        Vector3 dir = direction.rotate(Vector3.Axis.Z, rotationZ).rotate(Vector3.Axis.Y, rotationY);
        // Defines a ray from the object to the point of view.
        // R(t) = O + u * t
        Vector3 u = pov.subtract(object);
        // A point on the picture plane
        Vector3 P = pov.add(dir);
        Vector3 w = object.subtract(P);

        double t = - dir.dot(w) / dir.dot(u);
        return object.add(u.multiply(t));
    }

    /**
     * Returns a normalised vector perpendicular to the direction of view and upward vector.
     *
     * @return an unit vector perpendicular to the direction vector and upward vector.
     */
    private Vector3 getSide() {
        Vector3 dir = direction.rotate(Vector3.Axis.Z, rotationZ).rotate(Vector3.Axis.Y, rotationY);
        Vector3 upRot = up.rotate(Vector3.Axis.Z, rotationZ).rotate(Vector3.Axis.Y, rotationY);
        return dir.cross(upRot).normalise();
    }

}
