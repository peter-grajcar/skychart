package cz.cuni.mff.skychart.astronomy;

/**
 * An object representing a star.
 *
 * @author Peter Grajcar
 */
public class Star {

    private String name;
    private EquatorialCoords coords;
    private double magnitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EquatorialCoords getCoords() {
        return coords;
    }

    public void setCoords(EquatorialCoords coords) {
        this.coords = coords;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }
}
