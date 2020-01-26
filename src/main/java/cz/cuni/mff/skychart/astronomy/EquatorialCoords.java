package cz.cuni.mff.skychart.astronomy;

import java.time.ZonedDateTime;

/**
 * An object representing coordinates in an equatorial coordinate system (right-ascension, declination).
 *
 * @author Peter Grajcar
 */
public class EquatorialCoords {

    private double rightAscension;
    private double declination;

    public EquatorialCoords() {
    }

    public EquatorialCoords(double rightAscension, double declination) {
        this.rightAscension = rightAscension;
        this.declination = declination;
    }

    /**
     * Converts the equatorial coordinates to the horizontal system at given time and location.
     *
     * @param time Time of the observation
     * @param location Geographical location of the observer
     * @return Coordinates in horizontal system
     */
    public HorizontalCoords toHorizontal(ZonedDateTime time, Location location) {
        return Coordinates.equatorialToHorizontal(this, time, location);
    }

    public double getRightAscension() {
        return rightAscension;
    }

    public void setRightAscension(double rightAscension) {
        this.rightAscension = rightAscension;
    }

    public double getDeclination() {
        return declination;
    }

    public void setDeclination(double declination) {
        this.declination = declination;
    }
}
