package cz.cuni.mff.skychart.catalogue.bsc5;

import cz.cuni.mff.skychart.astronomy.EquatorialCoords;
import cz.cuni.mff.skychart.catalogue.Star;

/**
 * An object representing star in BSC5 catalogue.
 *
 * @author Peter Grajcar
 */
public class BSC5Star extends Star {

    /**
     * Parses the string argument as an entry in BSC5 catalogue.
     *
     * @param s entry from BSC5 catalogue.
     * @return a star matching the entry.
     * @throws BSC5FormatException if the string is not in valid format.
     */
    public static BSC5Star parse(String s) throws BSC5FormatException {
        if(s.length() != 197)
            throw new BSC5FormatException("Invalid lenghth. Expected: 197, actual: " + s.length());

        String name = s.substring(4, 14);
        double ra_h = Double.parseDouble(s.substring(75, 77));
        double ra_m = Double.parseDouble(s.substring(77, 79));
        double ra_s = Double.parseDouble(s.substring(79, 83));
        int dec_sign = s.charAt(84) == '+' ? 1 : -1;
        double dec_d = Double.parseDouble(s.substring(84, 86));
        double dec_m = Double.parseDouble(s.substring(86, 88));
        double dec_s = Double.parseDouble(s.substring(88, 90));
        double visMag = Double.parseDouble(s.substring(102, 107));

        double ra = ra_h + ra_m / 60d + ra_s / 3600d;
        double dec = dec_sign * (dec_d + dec_m / 60d + dec_s / 3600d);

        BSC5Star star = new BSC5Star();
        star.setName(name);
        star.setVisualMagnitude(visMag);
        star.setCoords(new EquatorialCoords(ra, dec));
        return star;
    }

}
