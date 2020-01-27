package cz.cuni.mff.skychart.catalogue;

import java.util.List;

/**
 * A star catalogue.
 *
 * @author Peter Grajcar
 */
public interface Catalogue {

    /**
     * Returns a list of all stars in the catalogue.
     *
     * @return the list of all stars in the catalogue.
     */
    List<Star> starList();

}
