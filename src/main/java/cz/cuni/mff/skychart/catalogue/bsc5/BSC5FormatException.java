package cz.cuni.mff.skychart.catalogue.bsc5;

/**
 * Thrown to indicate invalid BSC5 entry format.
 *
 * @author Peter Grajcar
 */
public class BSC5FormatException extends Exception {

    public BSC5FormatException() {
        super("Invalid BSC5 format.");
    }

    public BSC5FormatException(String message) {
        super(message);
    }

    public BSC5FormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
