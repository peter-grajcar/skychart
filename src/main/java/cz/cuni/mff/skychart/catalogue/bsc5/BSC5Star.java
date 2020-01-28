package cz.cuni.mff.skychart.catalogue.bsc5;

import cz.cuni.mff.skychart.astronomy.EquatorialCoords;
import cz.cuni.mff.skychart.catalogue.Star;
import static cz.cuni.mff.skychart.catalogue.bsc5.BSC5Catalogue.BSC5Field;

/**
 * An object representing star in BSC5 catalogue.
 *
 * @author Peter Grajcar
 */
public class BSC5Star extends Star {

    /**
     * Returns star's name as combination of a greek or latin letter and constellation name (Bayer designation).
     *
     * @return star's name in Bayer designation.
     */
    public String getBayerName() {
        if(name.isBlank()) return "";
        String letter = name.substring(3, 7).strip();
        String constellation = name.substring(7, 10);
        return letter + " " + constellation;
    }

    /**
     * Returns star's name as combination of a number and constellation name (Flamsteed designation).
     *
     * @return star's name in Flamsteed designation.
     */
    public String getFlamsteedName() {
        if(name.isBlank()) return "";
        String number = name.substring(0, 3).strip();
        String constellation = name.substring(7, 10);
        return number + " " + constellation;
    }

    /**
     * Parses the string argument as an entry in BSC5 catalogue.
     *
     * @param entry entry from BSC5 catalogue.
     * @return a star matching the entry.
     * @throws BSC5FormatException if the string is not in valid format.
     */
    public static BSC5Star parse(String entry) throws BSC5FormatException {
        if(entry.length() < 197) {
            StringBuilder sb = new StringBuilder(entry);
            for(int i = entry.length(); i < 197; ++i)
                sb.append(' ');
            entry = sb.toString();
        }

        String name = BSC5Field.NAME.asString(entry);
        double ra_h = BSC5Field.RA_HOUR_J2000.asDouble(entry);
        double ra_m = BSC5Field.RA_MIN_J2000.asDouble(entry);
        double ra_s = BSC5Field.RA_SEC_J2000.asDouble(entry);
        int dec_sign = BSC5Field.DEC_SIGN_J2000.asString(entry).equals("+") ? 1 : -1;
        double dec_d = BSC5Field.DEC_DEG_J2000.asDouble(entry);
        double dec_m = BSC5Field.DEC_MIN_J2000.asDouble(entry);
        double dec_s = BSC5Field.DEC_SEC_J2000.asDouble(entry);
        double visMag = BSC5Field.VISUAL_MAGNITUDE.asDouble(entry);

        double ra = ra_h + ra_m / 60d + ra_s / 3600d;
        double dec = dec_sign * (dec_d + dec_m / 60d + dec_s / 3600d);

        BSC5Star star = new BSC5Star();
        star.setName(name);
        star.setVisualMagnitude(visMag);
        star.setCoords(new EquatorialCoords(ra, dec));
        return star;
    }

}
