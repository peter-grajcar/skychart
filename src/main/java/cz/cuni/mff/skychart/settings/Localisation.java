package cz.cuni.mff.skychart.settings;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * A class for retrieving localised texts used in the application.
 *
 * @author Peter Grajcar
 */
public class Localisation {

    private static final String PREFERENCES_LANGUAGE = "lang";

    /**
     * Sets a new user's locale.
     * @param locale a new locale.
     */
    public static void setLocale(Locale locale) {
        Preferences prefs = Preferences.userNodeForPackage(cz.cuni.mff.skychart.App.class);
        prefs.put(PREFERENCES_LANGUAGE, locale.getLanguage());
    }

    /**
     * Returns a locale based on user's settings.
     * @return User's locale
     */
    public static Locale getLocale() {
        Preferences prefs = Preferences.userNodeForPackage(cz.cuni.mff.skychart.App.class);
        return new Locale(prefs.get(PREFERENCES_LANGUAGE, "en"));
    }

    /**
     * Returns a localised bundle based on user's settings. Locale can be changed using {@link #getLocale() getLocale}
     * method.
     * @return A localised bundle
     */
    public static ResourceBundle getBundle() {
        return ResourceBundle.getBundle("localisation", getLocale());
    }

}
