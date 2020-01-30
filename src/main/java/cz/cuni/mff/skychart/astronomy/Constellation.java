package cz.cuni.mff.skychart.astronomy;

/**
 * A set of modern constellations.
 *
 * @author Peter Grajcar
 */
public enum Constellation {
    ANDROMEDA("And", "Andromeda", "Andromedae"),
    ANTLIA("Ant", "Antlia", "Antliae"),
    APUS("Aps", "Apus", "Apodis"),
    AQUARIUS("Aqr", "Aquarius", "Aquarii"),
    AQUILA("Aql", "Aquila", "Aquilae"),
    ARA("Ara", "Ara", "Arae"),
    ARIES("Ari", "Aries", "Arietis"),
    AURIGA("Aur", "Auriga", "Aurigae"),
    BOOTES("Boo", "Bootes", "Bootis"),
    CAELUM("Cae", "Caelum", "Caeli"),
    CAMELOPARDALIS("Cam", "Camelopardalis", "Camelopardalis"),
    CANCER("Cnc", "Cancer", "Cancri"),
    CANES_VENATICI("CVn", "Canes Venatici", "Canum Venaticorum"),
    CANIS_MAJOR("CMa", "Canis Major", "Canis Majoris"),
    CANIS_MINOR("CMi", "Canis Minor", "Canis Minoris"),
    CAPRICORNUS("Cap", "Capricornus", "Capricorni"),
    CARINA("Car", "Carina", "Carinae"),
    CASSIOPEIA("Cas", "Cassiopeia", "Cassiopeiae"),
    CENTAURUS("Cen", "Centaurus", "Centauri"),
    CEPHEUS("Cep", "Cepheus", "Cephei"),
    CETUS("Cet", "Cetus", "Ceti"),
    CHAMAELEON("Cha", "Chamaeleon", "Chamaeleontis"),
    CIRCINUS("Cir", "Circinus", "Circini"),
    COLUMBA("Col", "Columba", "Columbae"),
    COMA_BERENICES("Com", "Coma Berenices", "Comae Berenices"),
    CORONA_AUSTRALIS("CrA", "Corona Australis", "Coronae Australis"),
    CORONA_BOREALIS("CrB", "Corona Borealis", "Coronae Borealis"),
    CORVUS("Crv", "Corvus", "Corvi"),
    CRATER("Crt", "Crater", "Crateris"),
    CRUX("Cru", "Crux", "Crucis"),
    CYGNUS("Cyg", "Cygnus", "Cygni"),
    DELPHINUS("Del", "Delphinus", "Delphini"),
    DORADO("Dor", "Dorado", "Doradus"),
    DRACO("Dra", "Draco", "Draconis"),
    EQUULEUS("Equ", "Equuleus", "Equulei"),
    ERIDANUS("Eri", "Eridanus", "Eridani"),
    FORNAX("For", "Fornax", "Fornacis"),
    GEMINI("Gem", "Gemini", "Geminorum"),
    GRUS("Gru", "Grus", "Gruis"),
    HERCULES("Her", "Hercules", "Herculi"),
    HOROLOGIUM("Hor", "Horologium", "Horologii"),
    HYDRA("Hya", "Hydra", "Hydrae"),
    HYDRUS("Hyi", "Hydrus", "Hydri"),
    INDUS("Ind", "Indus", "Indi"),
    LACERTA("Lac", "Lacerta", "Lacertae"),
    LEO("Leo", "Leo", "Leonis"),
    LEO_MINOR("LMi", "Leo Minor", "Leonis Minoris"),
    LEPUS("Lep", "Lepus", "Leporis"),
    LIBRA("Lib", "Libra", "Librae"),
    LUPUS("Lup", "Lupus", "Lupi"),
    LYNX("Lyn", "Lynx", "Lyncis"),
    LYRA("Lyr", "Lyra", "Lyrae"),
    MENSA("Men", "Mensa", "Mensae"),
    MICROSCOPIUM("Mic", "Microscopium", "Mocroscopii"),
    MONOCEROS("Mon", "Monoceros", "Monocerotis"),
    MUSCA("Mus", "Musca", "Muscae"),
    NORMA("Nor", "Norma", "Normae"),
    OCTANS("Oct", "Octans", "Octantis"),
    OPHIUCHUS("Oph", "Ophiuchus", "Ophiuchi"),
    ORION("Ori", "Orion", "Orionis"),
    PAVO("Pav", "Pavo", "Pavonis"),
    PEGASUS("Peg", "Pegasus", "Pegasi"),
    PERSEUS("Per", "Perseus", "Persei"),
    PHOENIX("Phe", "Phoenix", "Phoenicis"),
    PICTOR("Pic", "Pictor", "Pictoris"),
    PISCES("Psc", "Pisces", "Piscium"),
    PISCIS_AUSTRINUS("PsA", "Piscis Austrinus", "Piscis Austrini"),
    PUPPIS("Pup", "Puppis", "Puppis"),
    PYXIS("Pyx", "Pyxis", "Pyxidis"),
    RETICULUM("Ret", "Reticulum", "Reticuli"),
    SAGITTA("Sge", "Sagitta", "Sagittae"),
    SAGITTARIUS("Sgr", "Sagittarius", "Sagittarii"),
    SCORPIUS("Sco", "Scorpius", "Scorpii"),
    SCULPTOR("Scl", "Sculptor", "Sculptoris"),
    SCUTUM("Sct", "Scutum", "Scuti"),
    SERPENS("Ser", "Serpens", "Serpentis"),
    SEXTANS("Sex", "Sextans", "Sextantis"),
    TAURUS("Tau", "Taurus", "Tauri"),
    TELESCOPIUM("Tel", "Telescopium", "Telescopii"),
    TRIANGULUM("Tri", "Triangulum", "Trianguli"),
    TRIANGULUM_AUSTRALE("TrA", "Triangulum Australe", "Trianguli Australis"),
    TUCANA("Tuc", "Tucana", "Tucanae"),
    URSA_MAJOR("UMa", "Ursa Major", "Ursae Majoris"),
    URSA_MINOR("UMi", "Ursa Minor", "Ursae Minoris"),
    VELA("Vel", "Vela", "Velorum"),
    VIRGO("Vir", "Virgo", "Virginis"),
    VOLANS("Vol", "Volans", "Volantis"),
    VULPACULA("Vul", "Vulpecula", "Vulpeculae"),
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
     * Returns an abbreviated form (IAU) of the constellation's name.
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
