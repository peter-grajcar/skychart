package cz.cuni.mff.skychart.astronomy;

/**
 * created: 25/01/2020
 *
 * @author Peter Grajcar
 */
public class AltAz {

    private double altitude;
    private double azimuth;

    public AltAz() {
    }

    public AltAz(double altitude, double azimuth) {
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
