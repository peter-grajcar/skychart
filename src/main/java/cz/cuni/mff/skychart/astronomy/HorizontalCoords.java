package cz.cuni.mff.skychart.astronomy;

/**
 * An object representing coordinates in a horizontal coordinate system (altitude-azimuth). Values are in degrees.
 *
 * @author Peter Grajcar
 */
public class HorizontalCoords {

    private double altitude;
    private double azimuth;

    /**
     * Constructs new horizontal coordinates with default values.
     */
    public HorizontalCoords() {
    }

    /**
     * Constructs new horizontal coordinates from given altitude and azimuth.
     *
     * @param altitude an altitude in degrees.
     * @param azimuth an azimuth in degrees.
     */
    public HorizontalCoords(double altitude, double azimuth) {
        this.altitude = altitude;
        this.azimuth = azimuth;
    }

    /**
     * Returns the altitude in degrees.
     *
     * @return the altitude in degrees.
     */
    public double getAltitude() {
        return altitude;
    }

    /**
     * Sets a new altitude in degrees.
     *
     * @param altitude an altitude in degrees.
     */
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    /**
     * Returns the azimuth in degrees.
     *
     * @return the azimuth in degrees.
     */
    public double getAzimuth() {
        return azimuth;
    }

    /**
     * Sets a new azimuth in degrees.
     *
     * @param azimuth an azimuth in degrees.
     */
    public void setAzimuth(double azimuth) {
        this.azimuth = azimuth;
    }

    /**
     * Returns the altitude in radians.
     *
     * @return the altitude in radians.
     */
    public double getAltitudeRadians() {
        return altitude * Math.PI / 180d;
    }

    /**
     * Returns the azimuth in radians.
     *
     * @return the azimuth in radians.
     */
    public double getAzimuthRadians() {
        return azimuth * Math.PI / 180d;
    }

    /**
     * Sets a new altitude in radians.
     *
     * @param altitude an altitude in radians.
     */
    public void setAltitudeRadians(double altitude) {
        this.altitude = altitude * 180d / Math.PI;
    }

    /**
     * Sets a new azimuth in radians.
     *
     * @param azimuth an azimuth in radians.
     */
    public void setAzimuthRadians(double azimuth) {
        this.azimuth = azimuth * 180d / Math.PI;
    }

    @Override
    public String toString() {
        return String.format("ALT%+.4f\u00B0, AZ%+.4f\u00B0", altitude, azimuth);
    }
}
