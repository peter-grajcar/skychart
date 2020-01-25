package cz.cuni.mff.skychart.astronomy;

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
