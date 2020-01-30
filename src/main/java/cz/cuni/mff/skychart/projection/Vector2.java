package cz.cuni.mff.skychart.projection;

/**
 * A class representing two-dimensional vector.
 *
 * @author Peter Grajcar
 */
public class Vector2 {

    /**
     * Named axes of two-dimensional vector.
     */
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

    /**
     * Returns the sum of two vectors.
     *
     * @param other the other vector
     * @return the sum of two vectors.
     */
    public Vector2 add(Vector2 other) {
        return new Vector2(x + other.x, y + other.y);
    }

    /**
     * Returns the difference of two vectors.
     *
     * @param other the other vector
     * @return the difference of two vectors.
     */
    public Vector2 subtract(Vector2 other) {
        return new Vector2(x - other.x, y - other.y);
    }

    /**
     * Returns the norm of the vector.
     *
     * @return the norm.
     */
    public double norm() {
        return Math.sqrt(this.normSquared());
    }

    /**
     * Returns the squared norm of the vector.
     *
     * @return the squared norm.
     */
    public double normSquared() {
        return x * x + y * y;
    }

    @Override
    public String toString() {
        return String.format("(%.4f, %.4f)", getX(), getY());
    }
}
