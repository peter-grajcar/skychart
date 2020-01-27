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
    public void allStarsAreLoaded() throws IOException, BSC5FormatException {
        BSC5Catalogue catalogue = new BSC5Catalogue();
        assertEquals(9110, catalogue.starList().size());
    }

}

