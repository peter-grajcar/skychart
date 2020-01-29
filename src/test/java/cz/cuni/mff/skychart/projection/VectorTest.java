package cz.cuni.mff.skychart.projection;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for vector operations on {@link Vector3 Vector3} class.
 *
 * @author Peter Grajcar
 */
public class VectorTest {

    @Test
    public void testVectorAddition() {
        Vector3 a = new Vector3(1, 4, 2);
        Vector3 b = new Vector3(3, 4, 1);
        Vector3 c = a.add(b);
        assertEquals(4, c.getX(), 1e-8);
        assertEquals(8, c.getY(), 1e-8);
        assertEquals(3, c.getZ(), 1e-8);
    }

    @Test
    public void testVectorSubtraction() {
        Vector3 a = new Vector3(1, 4, 2);
        Vector3 b = new Vector3(3, 4, 1);
        Vector3 c = a.subtract(b);
        assertEquals(-2, c.getX(), 1e-8);
        assertEquals(0, c.getY(), 1e-8);
        assertEquals(1, c.getZ(), 1e-8);
    }

    @Test
    public void testDotProduct() {
        Vector3 a = new Vector3(1, 4, 2);
        Vector3 b = new Vector3(3, 4, 1);
        double product = a.dot(b);
        assertEquals(21, product, 1e-8);
    }

    @Test
    public void testVectorNormSquaredEqualsDotProduct() {
        Vector3 a = new Vector3(1, 4, 2);
        double product = a.dot(a);
        double norm = a.normSquared();
        assertEquals(norm, product, 1e-8);
    }

    @Test
    public void testVectorNormalisation() {
        Vector3 a = new Vector3(1, 4, 2);
        Vector3 normalised = a.normalise();
        assertEquals(1, normalised.norm(), 1e-8);
    }

    @Test
    public void testCrossProduct() {
        Vector3 a = new Vector3(1, 4, 2);
        Vector3 b = new Vector3(3, 4, 1);
        Vector3 c = a.cross(b);
        assertEquals(-4, c.getX(), 1e-8);
        assertEquals(5, c.getY(), 1e-8);
        assertEquals(-8, c.getZ(), 1e-8);
    }

    @Test
    public void testRotation() {
        Vector3 a = new Vector3(3, 4, 5);
        Vector3 c = a.rotate(Vector3.Axis.Z, Math.PI/2);
        assertEquals(-4, c.getX(), 1e-8);
        assertEquals(3, c.getY(), 1e-8);
        assertEquals(5, c.getZ(), 1e-8);
    }

}
