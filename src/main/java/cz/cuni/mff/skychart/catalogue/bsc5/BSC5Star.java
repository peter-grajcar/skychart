package cz.cuni.mff.skychart.catalogue.bsc5;

import cz.cuni.mff.skychart.astronomy.Constellation;
import cz.cuni.mff.skychart.astronomy.EquatorialCoords;
import cz.cuni.mff.skychart.astronomy.GreekLetter;
import cz.cuni.mff.skychart.catalogue.Star;

import java.util.HashMap;
import java.util.Map;

/**
 * An object representing star in BSC5 catalogue.
 *
 * @author Peter Grajcar
 */
public class BSC5Star extends Star {

    private String entry;


    /**
     * Returns the original entry from the BSC5 catalogue.
     *
     * @return the original entry from the catalogue.
     */
    public String getEntry() {
        return entry;
    }

    /**
     * Returns the field from the entry from the BSC5 catalogue as {@code String}.
     *
     * @return the field from the catalogue as {@code String}.
     */
    public String getString(BSC5Field field) {
        try {
            return field.asString(this.entry);
        } catch (BSC5FormatException e) {
            return null;
        }
    }

    /**
     * Returns the field from the entry from the BSC5 catalogue as {@code double}.
     *
     * @return the field from the catalogue as {@code double}.
     * @throws NumberFormatException if unable to interpret the field as number.
     */
    public double getDouble(BSC5Field field) throws NumberFormatException {
        try {
            return field.asDouble(this.entry);
        } catch (BSC5FormatException e) {
            return Double.NaN;
        }
    }

    /**
     * Returns star's name as combination of a greek or latin letter and constellation name (Bayer designation).
     *
     * @return star's name in Bayer designation.
     */
    public String getBayerName() {
        if(name.isBlank()) return "";
        String letterAbbr = name.substring(3, 7).strip();
        if(letterAbbr.isBlank()) return "";
        GreekLetter letter = GreekLetter.fromAbbr(letterAbbr);
        String constellation = name.substring(7, 10);
        return letter.getSymbol() + " " + constellation;
    }

    /**
     * Returns star's name as combination of a greek or latin letter and constellation name (Bayer designation).
     *
     * @return star's name in Bayer designation.
     */
    public String getBayerFullName() {
        if(name.isBlank()) return "";
        String letterAbbr = name.substring(3, 7).strip();
        if(letterAbbr.isBlank()) return "";
        GreekLetter letter = GreekLetter.fromAbbr(letterAbbr);
        String constellationAbbr = name.substring(7, 10);
        Constellation constellation = Constellation.fromAbbr(constellationAbbr);
        return letter.getName() + " " + (constellation == null ? constellationAbbr : constellation.getGenitive());
    }

    /**
     * Returns star's name as combination of a number and constellation name (Flamsteed designation).
     *
     * @return star's name in Flamsteed designation.
     */
    public String getFlamsteedName() {
        if(name.isBlank()) return "";
        String number = name.substring(0, 3).strip();
        if(number.isBlank()) return "";
        String constellation = name.substring(7, 10);
        return number + " " + constellation;
    }

    /**
     * Returns star's name as combination of a number and constellation name (Flamsteed designation).
     *
     * @return star's name in Flamsteed designation.
     */
    public String getFlamsteedFullName() {
        if(name.isBlank()) return "";
        String number = name.substring(0, 3).strip();
        if(number.isBlank()) return "";
        String constellationAbbr = name.substring(7, 10);
        Constellation constellation = Constellation.fromAbbr(constellationAbbr);
        return number + " " + (constellation == null ? constellationAbbr : constellation.getGenitive());
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
        star.entry = entry;
        star.setName(name);
        star.setVisualMagnitude(visMag);
        star.setCoords(new EquatorialCoords(ra, dec));
        return star;
    }

    @Override
    public String toString() {
        return String.format("Name:      %s\nVis. Mag.: %.2f\nCoords:    %s", getBayerName().isBlank() ? getFlamsteedFullName() : getBayerFullName(), visualMagnitude, coords);
    }

    @Override
    public Map<String, String> getInfo() {
        Map<String, String> fieldValues = new HashMap<>();
        for(BSC5Field field : BSC5Field.values()) {
            fieldValues.put(field.name(), this.getString(field).strip());
        }
        return fieldValues;
    }
}
