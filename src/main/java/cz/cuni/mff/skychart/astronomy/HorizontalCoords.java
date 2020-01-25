package cz.cuni.mff.skychart.astronomy;

/**
 * An object representing coordinates in a horizontal coordinate system (altitude-azimuth).
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
}
