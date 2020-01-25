package cz.cuni.mff.skychart.astronomy;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.JulianFields;

/**
 * This class contains various methods for coordinate conversion and manipulation.
 *
 * @author Peter Grajcar
 */
public class Coordinates {

    public static double getUniversalTime(ZonedDateTime time) {
        LocalDateTime utc = LocalDateTime.ofInstant(time.toInstant(), ZoneOffset.UTC);
        return utc.getHour() + utc.getMinute()/60 + utc.getSecond()/3600;
    }

    public static double getLocalSiderealTime(ZonedDateTime time, Location location) {
        double universalTime = getUniversalTime(time);
        double epochJ2000 = time.getLong(JulianFields.JULIAN_DAY);

        //TODO: Rewrite using radians instead of degrees
        double lst = (100.46 + 0.985647 * epochJ2000 + location.getLongitude() + 15*universalTime) % 360;
        lst = lst > 0 ? lst : 360 + lst;
        return lst * Math.PI / 180;
    }

    public static HorizontalCoords equatorialToHorizontal(EquatorialCoords eq, ZonedDateTime time, Location location) {
        double lst = getLocalSiderealTime(time, location);
        double hourAngle = lst - eq.getRightAscension();

        double alt =  Math.asin(Math.sin(eq.getDeclination())*Math.sin(location.getLatitudeRadians()) + Math.cos(eq.getDeclination())*Math.cos(location.getLatitudeRadians())*Math.cos(hourAngle) );
        double az = Math.acos( Math.sin(eq.getDeclination()) - Math.sin(alt)*Math.sin(location.getLatitudeRadians()) ) / (alt)*Math.cos(location.getLatitudeRadians());
        az = Math.sin(hourAngle) < 0 ? az : 2*Math.PI - az;

        return new HorizontalCoords(alt, az);
    }

}
