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

    /**
     * A set of fields present in BSC5 catalogue.
     */
    public enum BSC5Field {
        NUMBER(0, 4), NAME(4, 14), DM(14, 25),
        HD(25, 31), SAO(31, 37), FK5(37, 41),
        IR_FLAG(41, 42), IR_FLAG_REF(42, 43), MULTIPLE(43, 44), 
        ADS(44, 49), ADS_COMPONENTS(49, 51), VAR_ID(51, 60), 
        RA_HOUR_B1900(60, 62), RA_MIN_B1900(62, 64), RA_SEC_B1900(64, 68),
        DEC_SIGN_B1900(68, 69), DEC_DEG_B1900(69, 71), DEC_MIN_B1900(71, 73),
        DEC_SEC_B1900(73, 75), RA_HOUR_J2000(75, 77), RA_MIN_J2000(77, 79),
        RA_SEC_J2000(79, 83), DEC_SIGN_J2000(83, 84), DEC_DEG_J2000(84, 86),
        DEC_MIN_J2000(86, 88), DEC_SEC_J2000(88, 90), GALACTIC_LON(90, 96),
        GALACTIC_LAT(96, 102), VISUAL_MAGNITUDE(102, 107), VISUAL_MAGNITUDE_CODE(107, 108),
        VISUAL_MAG_UNCERTAINTY(108, 109), UBV(109, 114), UBV_UNCERTAINTY(114, 115), 
        UBV_COLOUR(115, 120), UBV_COLOUR_UNCERTAINTY(120, 121), RI(121, 126), 
        RI_CODE(126, 127), SPECTRAL_TYPE(127, 147), SPECTRAL_TYPE_CODE(147, 148),
        PM_RA(148, 154), PM_DEC(154, 160), DYNAMIC_PARALLAX(160, 161),
        PARALLAX(161, 166), RADIAL_VELOCITY(166, 170), RADIAL_VELOCITY_C(170, 174),
        ROTATIONAL_VELOCITY_LIM(174, 176), ROTATIONAL_VELOCITY(176, 179), 
        ROTATIONAL_VELOCITY_UNCERTAINTY(179, 180), MAGNITUDE_DIFFERENCE(180, 184),
        SEPARATION(184, 190), MULTIPLE_ID(190, 194), MULTIPLE_COUNT(194, 196),
        NOTE(196, 197);

        private int start, end;
        BSC5Field(int s, int e) {
            this.start = s;
            this.end = e;
        }

        /**
         * Returns the index of the start of the field.
         *
         * @return index of the start of the field.
         */
        public int getStart() {
            return start;
        }
        /**
         * Returns the index of the end of the field.
         *
         * @return index of the end of the field.
         */
        public int getEnd() {
            return end;
        }

        /**
         * Extracts the field from given entry as String.
         *
         * @param entry an entry from BSC5 catalogue.
         * @return a string matching the field in the entry.
         * @throws BSC5FormatException if the length of the entry is not valid.
         */
        public String asString(String entry) throws BSC5FormatException{
            if(entry.length() != 197)
                throw new BSC5FormatException("Invalid length of entry.");
            return entry.substring(this.start, this.end);
        }

        /**
         * Extracts the field from given entry as double.
         *
         * @param entry an entry from BSC5 catalogue.
         * @return a double matching the field in the entry.
         * @throws BSC5FormatException if the length of the entry is not valid or cannot parse the field.
         */
        public double asDouble(String entry) throws BSC5FormatException{
            String field = asString(entry);
            if(field.isBlank())
                return Double.NaN;
            try {
                return Double.parseDouble(field);
            } catch (NumberFormatException e) {
                throw new BSC5FormatException("Cannot parse value \"" + field + "\".");
            }
        }
    }

    private List<Star> stars;

    public BSC5Catalogue() throws IOException, BSC5FormatException {
        stars = new ArrayList<>();

        int entry = 1;
        try(FileReader reader = new FileReader(getClass().getClassLoader().getResource("bsc5/bsc5.dat").getFile());
            BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                BSC5Star star = BSC5Star.parse(line);
                stars.add(star);
                ++entry;
            }
        } catch (IOException e) {
            logger.error("Error while loading the BSC5 catalogue.", e);
            throw e;
        } catch (BSC5FormatException e) {
            logger.error("Error while parsing BSC5 catalogue entry #" + entry, e);
            throw e;
        }
    }

    @Override
    public List<Star> starList() {
        return stars;
    }

}
