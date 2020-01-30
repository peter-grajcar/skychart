package cz.cuni.mff.skychart.astronomy;

/**
 * A set of constellations.
 *
 * @author Peter Grajcar
 */
public enum Constellation {
    ORION("Ori", "Orion", "Orionis"),
    CANIS_MAJOR("CMa", "Canis Major", "Canis Majoris"),
    URSA_MAJOR("UMa", "Ursa Major", "Ursae Majoris"),
    ;

    private String abbr;
    private String nominative;
    private String genitive;

    Constellation(String abbr, String nominative, String genitive) {
        this.abbr = abbr;
        this.nominative = nominative;
        this.genitive = genitive;
    }

    /**
     * Returns an abbreviated form of the constellation's name.
     *
     * @return an abbreviated constellation's name.
     */
    public String getAbbr() {
        return abbr;
    }

    /**
     * Returns constellations's latin name in nominative.
     *
     * @return constellations's latin name in nominative.
     */
    public String getNominative() {
        return nominative;
    }

    /**
     * Returns constellations's latin name in genitive.
     *
     * @return constellations's latin name in genitive.
     */
    public String getGenitive() {
        return genitive;
    }

    /**
     * Returns a constellation matching the abbreviation.
     *
     * @param abbr an abbreviation.
     * @return constellation matching the abbreviation.
     */
    public static Constellation fromAbbr(String abbr) {
        for(Constellation constellation : Constellation.values()) {
            if(constellation.abbr.equals(abbr))
                return constellation;
        }
        return null;
    }
}
