package cz.cuni.mff.skychart.catalogue.bsc5;

import cz.cuni.mff.skychart.catalogue.Star;
import cz.cuni.mff.skychart.catalogue.Catalogue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of {@link Catalogue Catalogue} that gets its data from Yale Bright Star Catalog (BSC5).
 *
 * <a href="http://tdc-www.harvard.edu/catalogs/bsc5.html">link</a> to the catalogue.
 *
 * @author Peter Grajcar
 */
public class BSC5Catalogue implements Catalogue {

    private static final Logger logger = LogManager.getLogger(BSC5Catalogue.class);

    private List<Star> stars;

    public BSC5Catalogue() throws IOException, BSC5FormatException {
        stars = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader("bsc5/bsc5.dat"))) {
            String line;
            while((line = reader.readLine()) != null) {
                BSC5Star star = BSC5Star.parse(line);
                stars.add(star);
            }
        } catch (IOException e) {
            logger.error("Error while loading the BSC5 catalogue.", e);
            throw e;
        } catch (BSC5FormatException e) {
            logger.error("Error while parsing BSC5 catalogue entry.", e);
            throw e;
        }
    }

    @Override
    public List<Star> starList() {
        return stars;
    }

}
