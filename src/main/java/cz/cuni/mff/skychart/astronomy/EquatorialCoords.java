package cz.cuni.mff.skychart.astronomy;

import java.time.ZonedDateTime;

/**
 * An object representing coordinates in an equatorial coordinate system (right-ascension, declination). Right ascension
 * in hours and declination in degrees.
 *
 * @author Peter Grajcar
 */
public class EquatorialCoords {

    private double rightAscension;
    private double declination;

    /**
     * Constructs new equatorial coordinates with default values.
     */
    public EquatorialCoords() {
    }

    /**
     * Constructs new equatorial coordinates from give right ascension and declination.
     *
     * @param rightAscension right ascension.
     * @param declination declination.
     */
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

    /**
     * Returns the right ascension in hours.
     *
     * @return the right ascension in hours.
     */
    public double getRightAscension() {
        return rightAscension;
    }

    /**
     * Sets a new right ascension in hours.
     *
     * @param rightAscension a new right ascension in hours.
     */
    public void setRightAscension(double rightAscension) {
        this.rightAscension = rightAscension;
    }

    /**
     * Returns the declination in degrees.
     *
     * @return the declination in degrees.
     */
    public double getDeclination() {
        return declination;
    }

    /**
     * Sets a new declination in degrees.
     *
     * @param declination  a new declination in degrees.
     */
    public void setDeclination(double declination) {
        this.declination = declination;
    }

    /**
     * Returns the right ascension in radians.
     *
     * @return the right ascension in radians.
     */
    public double getRightAscensionRadians() {
        return rightAscension * Math.PI / 24d;
    }

    /**
     * Returns the declination in radians.
     *
     * @return the declination in radians.
     */
    public double getDeclinationRadians() {
        return declination * Math.PI / 180d;
    }

    @Override
    public String toString() {
        return String.format("RA%+08.4fh DEC%+08.4f\u00B0", rightAscension, declination);
    }
}
