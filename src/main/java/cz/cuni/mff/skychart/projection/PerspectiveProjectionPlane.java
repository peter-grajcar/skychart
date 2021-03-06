package cz.cuni.mff.skychart.projection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class allows to project three-dimensional vector onto a plane using perspective projection.
 *
 * @author Peter Grajcar
 */
public class PerspectiveProjectionPlane implements ProjectionPlane {

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
        this.side = calcSide();
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
     * Sets the distance to the picture plane.
     *
     * @param dist a new distance to the picture plane.
     */
    public void setDistance(double dist) {
        direction = direction.normalise().multiply(dist);
    }

    /**
     * Returns the distance of the point of view to the picture plane.
     *
     * @return the distance to the picture plane.
     */
    public double getDistance() {
        return direction.norm();
    }

    /**
     * Calculates and returns the projected coordinates of given vector on the plane.
     *
     * <a href="http://geomalgorithms.com/a06-_intersect-2.html">link</a>
     *
     * @param vector a vector to project.
     * @return coordinates of the projected vector on the plane.
     */
    @Override
    public Vector2 project(Vector3 vector) {
        Vector3 intersection = intersection(vector);

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



    /**
     * Returns true if the vector is in front of the picture plane.
     *
     * @param vector a vector.
     * @param epsilon a small number considered zero.
     * @return true if vector is in front of the plane.
     */
    @Override
    public boolean isFront(Vector3 vector, double epsilon){
        Vector3 pov = pointOfView.rotate(Vector3.Axis.Z, rotationZ).rotate(Vector3.Axis.Y, rotationY);
        Vector3 dir = direction.rotate(Vector3.Axis.Z, rotationZ).rotate(Vector3.Axis.Y, rotationY);

        Vector3 P = pov.add(dir);
        return dir.dot(vector.subtract(P)) > epsilon;
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
     *
     *
     * @return
     */
    private Vector3 calcSide() {
        Vector3 dir = direction.rotate(Vector3.Axis.Z, rotationZ).rotate(Vector3.Axis.Y, rotationY);
        Vector3 upRot = up.rotate(Vector3.Axis.Z, rotationZ).rotate(Vector3.Axis.Y, rotationY);
        return dir.cross(upRot).normalise();
    }

    /**
     * Returns a normalised vector perpendicular to the direction of view and upward vector.
     *
     * @return an unit vector perpendicular to the direction vector and upward vector.
     */
    public Vector3 getSide() {
        return side.normalise()
                .rotate(Vector3.Axis.Z, rotationZ)
                .rotate(Vector3.Axis.Y, rotationY);
    }

    /**
     * Returns a normalised vector with direction of view.
     *
     * @return an unit vector with direction of view.
     */
    @Override
    public Vector3 getForward() {
        return direction.normalise()
                .rotate(Vector3.Axis.Z, rotationZ)
                .rotate(Vector3.Axis.Y, rotationY);
    }

    /**
     * Returns a normalised vector pointing upwards.
     *
     * @return an unit vector pointing upwards.
     */
    public Vector3 getUp() {
        return up.normalise()
                .rotate(Vector3.Axis.Z, rotationZ)
                .rotate(Vector3.Axis.Y, rotationY);
    }

    /**
     * Returns the center point of the picture plane.
     *
     * @return the center point of the picture plane.
     */
    public Vector3 getPlaneCenter() {
        return pointOfView.add(getForward().multiply(getDistance()));
    }
}
