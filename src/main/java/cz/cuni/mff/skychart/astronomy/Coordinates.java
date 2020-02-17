package cz.cuni.mff.skychart.astronomy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.JulianFields;

/**
 * This class contains various methods for coordinate conversion and manipulation.
 *
 * @author Peter Grajcar
 */
public class Coordinates {

    private static final Logger logger = LogManager.getLogger(Coordinates.class);

    /**
     * Computes the Universal Time (UT) in seconds at given time.
     *
     * @param time Time
     * @return Universal Time
     */
    public static double getUniversalTime(ZonedDateTime time) {
        LocalDateTime utc = LocalDateTime.ofInstant(time.toInstant(), ZoneOffset.UTC);
        return utc.getHour() * 3600d + utc.getMinute() * 60d + utc.getSecond();
    }

    /**
     * Computes the Greenwich Mean Sidereal Time (GMST) in seconds at given time and location.
     *
     * @param time Time
     * @return Greenwich Mean Sidereal Time
     */
    public static double getGreenwichMeanSiderealTime(ZonedDateTime time) {
        LocalDateTime utc = LocalDateTime.ofInstant(time.toInstant(), ZoneOffset.UTC);

        double universalTime = getUniversalTime(time);
        double epochJ2000 = utc.getLong(JulianFields.JULIAN_DAY) - 2451545.5;
        double Tu = epochJ2000 / 36525d;
        // H0 - Greenwich Mean Sidereal Time at midnight
        double H0 = 24110.54841 + 8640184.812866 * Tu + 0.093104 * Tu * Tu - 6.2e-6 * Tu * Tu * Tu;
        // omega - Earth's rotation rate (sidereal second / UT second)
        double omega = 1.00273790935 + 5.9e-11 * Tu;
        // add longitude in seconds to get Local Sidereal Time instead of Greenwich
        double H = H0 + omega * universalTime;

        return H % 86400d;
    }

    /**
     * Converts the equatorial coordinates to the horizontal system.
     *
     * @param eq Equatorial coordinates
     * @param time Time of the observation
     * @param location Geographical location of the observer
     * @return Coordinates in horizontal system
     */
    public static HorizontalCoords equatorialToHorizontal(EquatorialCoords eq, ZonedDateTime time, Location location) {
        double lst = getGreenwichMeanSiderealTime(time);

        // LST needs to be converted from seconds to radians
        double hourAngle = (lst / 3600 + location.getLongitude() / 15 - eq.getRightAscension()) * Math.PI / 12;

        double lat = location.getLatitudeRadians();
        if(90 - location.getLatitude() < 1e-8 )
            lat -= 1e-8;

        double alt =  Math.asin(
                Math.sin(eq.getDeclinationRadians()) * Math.sin(lat)
                        + Math.cos(eq.getDeclinationRadians()) * Math.cos(lat) * Math.cos(hourAngle)
        );

        double az = Math.acos(
                (Math.sin(eq.getDeclinationRadians()) - Math.sin(lat) * Math.sin(alt))
                        / (Math.cos(lat) * Math.cos(alt))
        );
        az = Math.sin(hourAngle) < 0 ? az : 2*Math.PI - az;

        return new HorizontalCoords(alt * 180 / Math.PI, az * 180 / Math.PI);
    }

}
