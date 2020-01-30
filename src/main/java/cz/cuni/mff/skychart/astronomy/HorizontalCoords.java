package cz.cuni.mff.skychart.astronomy;

/**
 * An object representing coordinates in a horizontal coordinate system (altitude-azimuth). Values are in degrees.
 *
 * @author Peter Grajcar
 */
public class HorizontalCoords {

    private double altitude;
    private double azimuth;

    public HorizontalCoords() {
    }

    public HorizontalCoords(double altitude, double azimuth) {
        this.altitude = altitude;
        this.azimuth = azimuth;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(double azimuth) {
        this.azimuth = azimuth;
    }

    public double getAltitudeRadians() {
        return altitude * Math.PI / 180d;
    }

    public double getAzimuthRadians() {
        return azimuth * Math.PI / 180d;
    }

    public void setAltitudeRadians(double altitude) {
        this.altitude = altitude * 180d / Math.PI;
    }

    public void setAzimuthRadians(double azimuth) {
        this.azimuth = azimuth * 180d / Math.PI;
    }

    @Override
    public String toString() {
        return String.format("ALT%+.4f\u00B0, AZ%+.4f\u00B0", altitude, azimuth);
    }
}
