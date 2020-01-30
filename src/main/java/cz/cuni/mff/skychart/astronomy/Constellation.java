package cz.cuni.mff.skychart.astronomy;

/**
 * created: 30/01/2020
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

    public String getAbbr() {
        return abbr;
    }

    public String getNominative() {
        return nominative;
    }


    public String getGenitive() {
        return genitive;
    }

    public static Constellation fromAbbr(String abbr) {
        for(Constellation constellation : Constellation.values()) {
            if(constellation.abbr.equals(abbr))
                return constellation;
        }
        return null;
    }
}
