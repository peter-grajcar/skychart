package cz.cuni.mff.skychart.astronomy;

/**
 * An object representing geographical location.
 *
 * @author Peter Grajcar
 */
public class Location {

    private double longitude;
    private double latitude;

    /**
     * Constructs a new location from given latitude and longitude.
     *
     * @param latitude latitude in degrees.
     * @param longitude longitude in degrees.
     */
    public Location(double latitude, double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Returns longitude in degrees.
     *
     * @return longitude in degrees.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets a new longitude in degrees.
     *
     * @param longitude longitude in degrees.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Returns latitude in degrees.
     *
     * @return longitude in degrees.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets a new latitude in degrees.
     *
     * @param latitude longitude in degrees.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns longitude in radians.
     *
     * @return longitude in radians.
     */
    public double getLongitudeRadians() {
        return longitude * Math.PI / 180d;
    }

    /**
     * Returns latitude in radians.
     *
     * @return latitude in radians.
     */
    public double getLatitudeRadians() {
        return latitude * Math.PI / 180d;
    }

    @Override
    public String toString() {
        return String.format("%.4f\u00B0%c %.4f\u00B0%c", latitude, latitude < 0 ? 'S' : 'N', longitude, longitude < 0 ? 'E' : 'W');
    }
}
