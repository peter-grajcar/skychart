package cz.cuni.mff.skychart.astronomy;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.JulianFields;

/**
 * This class contains various methods for coordinate conversion and manipulation.
 *
 * @author Peter Grajcar
 */
public class Coordinates {

    public static HorizontalCoords equatorialToHorizontal(EquatorialCoords eq, Instant time, Location location) {
        ZonedDateTime utc = time.atZone(ZoneOffset.UTC);

        double universalTime = utc.getHour() + utc.getMinute()/60 + utc.getSecond()/3600;
        double epochJ2000 = time.get(JulianFields.JULIAN_DAY);

        //Local Sidereal Time
        double lst = (100.46 + 0.985647 * epochJ2000 + location.getLongitude() + 15*universalTime) % 360;
        lst = lst > 0 ? lst : 360 - lst;
        lst /= 2 * Math.PI;

        double hourAngle = lst - eq.getRightAscension();

        double alt =  Math.asin(Math.sin(eq.getDeclination())*Math.sin(location.getLatitudeRadians()) + Math.cos(eq.getDeclination())*Math.cos(location.getLatitudeRadians())*Math.cos(hourAngle) );
        double az = Math.acos( Math.sin(eq.getDeclination()) - Math.sin(alt)*Math.sin(location.getLatitudeRadians()) ) / (alt)*Math.cos(location.getLatitudeRadians());
        az = Math.sin(hourAngle) < 0 ? az : 2*Math.PI - az;

        return new HorizontalCoords(alt, az);
    }

}
