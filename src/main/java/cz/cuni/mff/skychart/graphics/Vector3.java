package cz.cuni.mff.skychart.graphics;

/**
 * A class representing three-dimensional vector.
 *
 * @author Peter Grajcar
 */
public class Vector3 {

    public static enum Axis {
        X, Y, Z;
    }

    private double x, y, z;

    /**
     * Constructs a zero vector.
     */
    public Vector3() {
        this(0, 0, 0);
    }

    /**
     * Construct a vector with given component values.
     *
     * @param x x component.
     * @param y y component.
     * @param z z component.
     */
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
     * Returns z component of the vector.
     *
     * @return z component.
     */
    public double getZ() {
        return z;
    }

    /**
     * Sets the z component of the vector.
     *
     * @param z z component.
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Returns a new vector with reversed component values.
     *
     * @return reversed vector.
     */
    public Vector3 reverse() {
        return new Vector3(-x, -y, -z);
    }

    /**
     * Returns the sum of two vectors.
     *
     * @param other the other vector
     * @return the sum of two vectors.
     */
    public Vector3 add(Vector3 other) {
        return new Vector3(x + other.x, y + other.y, z + other.z);
    }

    /**
     * Returns the difference of two vectors.
     *
     * @param other the other vector
     * @return the difference of two vectors.
     */
    public Vector3 subtract(Vector3 other) {
        return new Vector3(x - other.x, y - other.y, z - other.z);
    }

    /**
     * Returns the sum of vector and a scalar value.
     *
     * @return sum vector and a scalar value.
     */
    public Vector3 multiply(double n) {
        return new Vector3(x * n, y * n, z * n);
    }

    /**
     * Returns a vector divided by a scalar value.
     *
     * @return divided vector.
     */
    public Vector3 divide(double n) {
        return new Vector3(x / n, y / n, z / n);
    }

    /**
     * Returns the dot product of two vectors.
     *
     * @return dot product.
     */
    public double dot(Vector3 other) {
        return x * other.x + y * other.y + z * other.z;
    }

    /**
     * Returns the cross product of two vectors.
     *
     * @return cross product.
     */
    public Vector3 cross(Vector3 other) {
        return new Vector3(
                y * other.z - z * other.y,
                z * other.x - x * other.z,
                x * other.y - y * other.x
        );
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
        return x * x + y * y + z * z;
    }

    /**
     * Returns a normalised vector of unit length.
     *
     * @return normalised vector.
     */
    public Vector3 normalise() {
        return this.divide(this.norm());
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
            case Z: return getZ();
            default: return 0;
        }
    }

}
