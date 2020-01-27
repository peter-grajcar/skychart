package cz.cuni.mff.skychart.catalogue.bsc5;

import cz.cuni.mff.skychart.astronomy.EquatorialCoords;
import cz.cuni.mff.skychart.catalogue.Star;

/**
 * An object representing star in BSC5 catalogue.
 *
 * @author Peter Grajcar
 */
public class BSC5Star extends Star {

    /*
     * indices of field separators
     * TODO: make enum out of this
     */
    private static final int[] CATALOGUE_INDICES = {
            0, 4, 14, 25, 31, 37, 41, 42, 43, 44, 49, 51, 60,
            62, 64, 68, 69, 71, 73, 75, 77, 79, 83, 84, 86, 88, 90,
            96, 102, 107, 108, 109, 114, 115, 120, 121, 126, 127,
            147, 148, 154, 160, 161, 166, 170, 174, 176, 179, 180,
            184, 190, 194, 196, 197
    };

    /**
     * Returns star's name as combination of a greek or latin letter and constellation name (Bayer designation).
     *
     * @return star's name in Bayer designation.
     */
    public String getBayerName() {
        String letter = name.substring(3, 7).strip();
        String constellation = name.substring(7, 10);
        return letter + (letter.isBlank() ? "" : " " + constellation);
    }

    /**
     * Returns star's name as combination of a number and constellation name (Flamsteed designation).
     *
     * @return star's name in Flamsteed designation.
     */
    public String getFlamsteedName() {
        String number = name.substring(0, 3).strip();
        String constellation = name.substring(7, 10);
        return number + (number.isBlank() ? "" : " " + constellation);
    }

    /**
     * Parses the string argument as an entry in BSC5 catalogue.
     *
     * @param s entry from BSC5 catalogue.
     * @return a star matching the entry.
     * @throws BSC5FormatException if the string is not in valid format.
     */
    public static BSC5Star parse(String s) throws BSC5FormatException {
        if(s.length() < 197) {
            StringBuilder sb = new StringBuilder(s);
            for(int i = s.length(); i < 197; ++i)
                sb.append(' ');
            s = sb.toString();
        }

        String[] fields = new String[CATALOGUE_INDICES.length - 1];
        for(int i = 0; i < fields.length; ++i) {
            fields[i] = s.substring(CATALOGUE_INDICES[i], CATALOGUE_INDICES[i + 1]);
        }

        String name = fields[1];
        int dec_sign;
        double ra_h, ra_m, ra_s, dec_d, dec_m, dec_s, visMag;
        try {
            ra_h = fields[19].isBlank() ? Double.NaN : Double.parseDouble(fields[19]);
            ra_m = fields[20].isBlank() ? Double.NaN  : Double.parseDouble(fields[20]);
            ra_s = fields[21].isBlank() ? Double.NaN  : Double.parseDouble(fields[21]);
            dec_sign = "+".equals(fields[22]) ? 1 : -1;
            dec_d = fields[23].isBlank() ? Double.NaN  : Double.parseDouble(fields[23]);
            dec_m = fields[24].isBlank() ? Double.NaN  : Double.parseDouble(fields[24]);
            dec_s = fields[25].isBlank() ? Double.NaN  : Double.parseDouble(fields[25]);
            visMag = fields[28].isBlank() ? Double.NaN  : Double.parseDouble(fields[28]);
        } catch (NumberFormatException e) {
            throw new BSC5FormatException("Error while parsing BSC5 entry.", e);
        }

        double ra = ra_h + ra_m / 60d + ra_s / 3600d;
        double dec = dec_sign * (dec_d + dec_m / 60d + dec_s / 3600d);

        BSC5Star star = new BSC5Star();
        star.setName(name);
        star.setVisualMagnitude(visMag);
        star.setCoords(new EquatorialCoords(ra, dec));
        return star;
    }

}
