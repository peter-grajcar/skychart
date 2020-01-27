package cz.cuni.mff.skychart.astronomy;

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
     * Computes the Local Sidereal Time (LST) in seconds at given time and location.
     *
     * @param time Time
     * @param location Geographical location
     * @return Local Sidereal Time
     */
    public static double getLocalSiderealTime(ZonedDateTime time, Location location) {
        LocalDateTime utc = LocalDateTime.ofInstant(time.toInstant(), ZoneOffset.UTC);

        double longitudeSeconds = location.getLongitude() * 240d; // note: 86400 / 360 = 240
        double universalTime = getUniversalTime(time);
        double epochJ2000 = utc.getLong(JulianFields.JULIAN_DAY) - 2451545.5;
        double Tu = epochJ2000 / 36525d;
        // H0 - Greenwich Mean Sidereal Time at midnight
        double H0 = 24110.54841 + 8640184.812866 * Tu + 0.093104 * Tu * Tu - 6.2e-6 * Tu * Tu * Tu;
        // omega - Earth's rotation rate (sidereal second / UT second)
        double omega = 1.00273790935 + 5.9e-11 * Tu;
        // add longitude in seconds to get Local Sidereal Time instead of Greenwich
        double H = H0 + omega * universalTime + longitudeSeconds;

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
        double lst = getLocalSiderealTime(time, location);

        // LST needs to be converted from seconds to radians
        double hourAngle = lst * (2 * Math.PI) / 86400 - eq.getRightAscension();

        double alt =  Math.asin(Math.sin(eq.getDeclination())*Math.sin(location.getLatitudeRadians()) + Math.cos(eq.getDeclination())*Math.cos(location.getLatitudeRadians())*Math.cos(hourAngle) );
        double az = Math.acos( Math.sin(eq.getDeclination()) - Math.sin(alt)*Math.sin(location.getLatitudeRadians()) ) / (alt)*Math.cos(location.getLatitudeRadians());
        az = Math.sin(hourAngle) < 0 ? az : 2*Math.PI - az;

        return new HorizontalCoords(alt, az);
    }

}
