package cz.cuni.mff.skychart.astronomy;

/**
 * An object representing geographical location.
 *
 * @author Peter Grajcar
 */
public class Location {

    private double longitude;
    private double latitude;

    public Location(double latitude, double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitudeRadians() {
        return longitude * Math.PI / 180d;
    }

    public double getLatitudeRadians() {
        return latitude * Math.PI / 180d;
    }

    @Override
    public String toString() {
        return String.format("%.4f\u00B0%c %.4f\u00B0%c", latitude, latitude < 0 ? 'S' : 'N', longitude, longitude < 0 ? 'E' : 'W');
    }
}
