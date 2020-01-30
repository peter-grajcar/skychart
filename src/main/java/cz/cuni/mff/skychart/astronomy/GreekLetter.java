package cz.cuni.mff.skychart.astronomy;

/**
 * A set of greek letters.
 *
 * @author Peter Grajcar
 */
public enum GreekLetter {
    ALPHA("Alp", "Alpha", '\u03B1'),
    BETA("Bet", "Beta", '\u03B2'),
    GAMMA("Gam", "Gamma", '\u03B3'),
    DELTA("Del", "Delta", '\u03B4'),
    EPSILON("Eps", "Epsilon", '\u03B5'),
    ZETA("Zet", "Zeta", '\u03B6'),
    ETA("Eta", "Eta", '\u03B7'),
    THETA("The", "Theta", '\u03B8'),
    IOTA("Iot", "Iota", '\u03B9'),
    KAPPA("Kap", "Kappa", '\u03BA'),
    LAMBDA("Lam", "Lambda", '\u03BB'),
    MU("Mu", "Mu", '\u03BC'),
    NU("Nu", "Nu", '\u03BD'),
    XI("Xi", "Xi", '\u03BE'),
    OMICRON("Omi", "Omicron", '\u03BF'),
    PI("Pi", "Pi", '\u03C0'),
    RHO("Rho", "Rho", '\u03C1'),
    SIGMA("Sig", "Sigma", '\u03C3'),
    TAU("Tau", "Tau", '\u03C4'),
    UPSILON("Ups", "Upsilon", '\u03C5'),
    PHI("Phi", "Phi", '\u03C6'),
    CHI("Chi", "Chi", '\u03C7'),
    PSI("Psi", "Psi", '\u03C8'),
    OMEGA("Ome", "Omega", '\u03C9'),
    ;

    private String abbr;
    private String name;
    private char symbol;

    GreekLetter(String abbr, String name, char symbol) {
        this.abbr = abbr;
        this.name = name;
        this.symbol = symbol;
    }

    /**
     * Returns an abbreviated form of the letter's name.
     *
     * @return an abbreviated letter name.
     */
    public String getAbbr() {
        return abbr;
    }

    /**
     * Returns a full english letter's name.
     *
     * @return a full letter name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a Unicode symbol for the letter.
     *
     * @return a Unicode symbol for the letter.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Returns a greek letter matching the abbreviation.
     *
     * @param abbr an abbrviation of the letter's name.
     * @return
     */
    public static GreekLetter fromAbbr(String abbr) {
        for(GreekLetter letter : GreekLetter.values()) {
            if(letter.abbr.equals(abbr))
                return letter;
        }
        return null;
    }

}
