package cz.cuni.mff.skychart.projection;

/**
 * created: 28/01/2020
 *
 * @author Peter Grajcar
 */
public class Vector2 {

    public static enum Axis {
        X, Y;
    }

    double x, y;

    /**
     * Constructs a zero vector.
     */
    public Vector2() {
        this(0, 0);
    }

    /**
     * Construct a vector with given component values.
     *
     * @param x x component.
     * @param y y component.
     */
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns x component of the vector.
     *
     * @return x component.
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x component of the vector.
     *
     * @param x x component.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Returns y component of the vector.
     *
     * @return y component.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y component of the vector.
     *
     * @param y y component.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Returns a component matching given axis.
     *
     * @param axis axis of the component to get.
     * @return component matching given axis.
     */
    public double get(Axis axis) {
        switch (axis) {
            case X: return getX();
            case Y: return getY();
            default: return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("(%.4f, %.4f)", getX(), getY());
    }
}
