package cz.cuni.mff.skychart.projection;

/**
 * A class representing three-dimensional vector.
 *
 * @author Peter Grajcar
 */
public class Vector3 {

    /**
     * Named axes of three-dimensional vector.
     */
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
     * @param n a scalar multiplier.
     * @return sum vector and a scalar value.
     */
    public Vector3 multiply(double n) {
        return new Vector3(x * n, y * n, z * n);
    }

    /**
     * Returns a vector divided by a scalar value.
     *
     *
     * @param n a scalar divisor.
     * @return divided vector.
     */
    public Vector3 divide(double n) {
        return new Vector3(x / n, y / n, z / n);
    }

    /**
     * Returns the dot product of two vectors.
     *
     * @param other the other vector.
     * @return dot product.
     */
    public double dot(Vector3 other) {
        return x * other.x + y * other.y + z * other.z;
    }

    /**
     * Returns the cross product of two vectors.
     *
     * @param other the other vector.
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

    /**
     * Rotates matrix around given axis by given angle. Rotation is done using rotation matrices.
     *
     * @param axis axis to rotate around.
     * @param angle angle to rotate by.
     * @return rotated vector.
     */
    public Vector3 rotate(Axis axis, double angle) {
        switch (axis) {
            case X:
                return new Vector3(
                        x,
                        y * Math.cos(angle) - z * Math.sin(angle),
                        y * Math.sin(angle) + z * Math.cos(angle)
                );
            case Y:
                return new Vector3(
                        x * Math.cos(angle) + z * Math.sin(angle),
                        y,
                        - x * Math.sin(angle) + z * Math.cos(angle)
                );
            case Z:
                return new Vector3(
                        x * Math.cos(angle) - y * Math.sin(angle),
                        x * Math.sin(angle) + y * Math.cos(angle),
                        z
                );
            default:
                return new Vector3(x, y, z);
        }
    }

    @Override
    public String toString() {
        return String.format("(%.4f, %.4f, %.4f)", getX(), getY(), getZ());
    }

}
