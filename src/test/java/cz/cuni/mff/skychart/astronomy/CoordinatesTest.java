package cz.cuni.mff.skychart.astronomy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link Coordinates Coordinates} class.
 *
 * @author Peter Grajcar
 */
public class CoordinatesTest {

    private static final Logger logger = LogManager.getLogger(CoordinatesTest.class);

    @Test
    public void greenwichMeanSiderealTimeCalculation() {
        ZonedDateTime time = LocalDateTime.parse("2019-01-01T08:00:00").atZone(ZoneOffset.UTC);

        double lst = Coordinates.getGreenwichMeanSiderealTime(time);
        logger.debug("GMST in hours: " + (lst / 3600d));

        assertEquals(52965.37916293584, lst, 1e-8);
    }

    @Test
    public void equatorialToHorizontalConversion() {
        //TODO: create test
    }

}
