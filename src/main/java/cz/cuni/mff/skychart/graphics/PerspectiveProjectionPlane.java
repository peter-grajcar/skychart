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

    private Vector2 planeSize;

    /**
     * Constructs a projection plane with initial point of view, direction of view, and size of the plane.
     *
     * @param pov point of view.
     * @param dir direction of view, also defines the distance to the plane.
     * @param size size of the plane.
     */
    public PerspectiveProjectionPlane(Vector3 pov, Vector3 dir, Vector2 size) {
        this.pointOfView = pov;
        this.direction = dir;
        this.planeSize = size;
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
     * @param object an object to project.
     * @return coordinates of the projected object on the plane.
     */
    public Vector2 project(Vector3 object) {
        // TODO: implement
        return null;
    }

}
