package cz.cuni.mff.skychart.astronomy;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;

import static org.junit.Assert.*;

/**
 * created: 25/01/2020
 *
 * @author Peter Grajcar
 */
public class CoordinatesTest {

    @Test
    public void localSiderealTimeCalculation() {
        ZonedDateTime time = LocalDateTime.parse("2020-01-25T19:50:21").atZone(ZoneOffset.UTC);

        Location location = new Location(48.2, 17.4);

        double lst = Coordinates.getLocalSiderealTime(time, location);
        System.out.println(lst);
        //assertEquals(0.0, lst, 1e-10);
    }

    @Test
    public void equatorialToHorizontalConversion() {

    }

}
