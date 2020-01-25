package cz.cuni.mff.skychart.astronomy;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
