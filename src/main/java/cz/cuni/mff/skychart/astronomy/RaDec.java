package cz.cuni.mff.skychart.astronomy;

/**
 * created: 25/01/2020
 *
 * @author Peter Grajcar
 */
public class RaDec {

    private double rightAscension;
    private double declination;

    public RaDec() {
    }

    public RaDec(double rightAscension, double declination) {
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
