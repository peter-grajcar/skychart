package cz.cuni.mff.skychart.graphics;

/**
 * A class representing three-dimensional vector.
 *
 * @author Peter Grajcar
 */
public class Vector3 {

    private double x, y, z;

    public Vector3() {
        this(0, 0, 0);
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Vector3 reverse() {
        return new Vector3(-x, -y, -z);
    }

    public Vector3 add(Vector3 other) {
        return new Vector3(x + other.x, y + other.y, z + other.z);
    }

    public Vector3 subtract(Vector3 other) {
        return new Vector3(x - other.x, y - other.y, z - other.z);
    }

    public double dot(Vector3 other) {
        return x * other.x + y * other.y + z * other.z;
    }

    public double norm() {
        return Math.sqrt(normSquared());
    }

    public double normSquared() {
        return x * x + y * y + z * z;
    }

}
