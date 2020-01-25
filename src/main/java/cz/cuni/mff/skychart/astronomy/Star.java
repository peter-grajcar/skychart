package cz.cuni.mff.skychart.astronomy;

/**
 * An object representing a star.
 *
 * @author Peter Grajcar
 */
public class Star {

    protected String name;
    protected EquatorialCoords coords;
    protected double visualMagnitude;

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

    public double getVisualMagnitude() {
        return visualMagnitude;
    }

    public void setVisualMagnitude(double visualMagnitude) {
        this.visualMagnitude = visualMagnitude;
    }
}
