package cz.cuni.mff.skychart.catalogue;

import cz.cuni.mff.skychart.astronomy.EquatorialCoords;

import java.util.Map;
import java.util.TreeMap;

/**
 * An object representing a star.
 *
 * @author Peter Grajcar
 */
public class Star {

    protected String name;
    protected EquatorialCoords coords;
    protected double visualMagnitude;

    /**
     * Returns the name of the star.
     *
     * @return the name of the star.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a new name of the star.
     *
     * @param name a name of the star.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the equatorial coordinates of the star.
     *
     * @return the equatorial coordinates of the star.
     */
    public EquatorialCoords getCoords() {
        return coords;
    }

    /**
     * Sets a new equatorial coordinates of the star.
     *
     * @param coords an equatorial coordinates of the star.
     */
    public void setCoords(EquatorialCoords coords) {
        this.coords = coords;
    }

    /**
     * Returns the visual magnitude of the star.
     *
     * @return the visual magnitude of the star.
     */
    public double getVisualMagnitude() {
        return visualMagnitude;
    }

    /**
     * Sets a new visual magnitude of the star.
     *
     * @param visualMagnitude an visual magnitude of the star.
     */
    public void setVisualMagnitude(double visualMagnitude) {
        this.visualMagnitude = visualMagnitude;
    }

    @Override
    public String toString() {
        return String.format("Name:      %s\nVis. Mag.: %.2f\nCoords:    (%s)", name, visualMagnitude, coords);
    }

    /**
     * Returns a map with all available information about the star.
     *
     * @return all available information about the star.
     */
    public Map<String, String> getInfo() {
        Map<String, String> fieldValues = new TreeMap<>();
        fieldValues.put("Name", name);
        fieldValues.put("Visual Magnitude", Double.toString(visualMagnitude));
        fieldValues.put("Equatorial Coordinates", coords.toString());
        return fieldValues;
    }
}
