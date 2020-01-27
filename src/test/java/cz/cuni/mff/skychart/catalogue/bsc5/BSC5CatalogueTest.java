package cz.cuni.mff.skychart.catalogue.bsc5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.*;

/**
 * Unit tests for BSC5 catalogue.
 *
 * @author Peter Grajcar
 */
public class BSC5CatalogueTest {

    private static final Logger logger = LogManager.getLogger(BSC5CatalogueTest.class);

    @Test
    public void parseCatalogueEntry() throws BSC5FormatException {
        BSC5Star star = BSC5Star.parse("3994 41Lam HyaBD-11 2820  88284155785 381I   7671           100542.7-115135101035.3-122115253.01 34.50 3.61  +1.01 +0.92 +0.48   K0IIICN1           -0.202-0.089 +.027+019SB1O < 19: 7.7 112.2AC   3*");
        assertEquals(" 41Lam Hya", star.getName());
        assertEquals(3.61, star.getVisualMagnitude(), 1e-3);
        logger.debug(star.getCoords());
    }

    @Test
    public void allStarsAreLoaded() throws IOException, BSC5FormatException {
        BSC5Catalogue catalogue = new BSC5Catalogue();
        assertEquals(9110, catalogue.starList().size());
    }

}

