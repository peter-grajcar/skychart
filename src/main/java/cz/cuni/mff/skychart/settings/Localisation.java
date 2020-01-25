package cz.cuni.mff.skychart.settings;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * created: 25/01/2020
 *
 * @author Peter Grajcar
 */
public class Localisation {

    private static final String PREFERENCES_LANGUAGE = "lang";

    public static void setLocale(Locale locale) {
        Preferences prefs = Preferences.userNodeForPackage(cz.cuni.mff.skychart.App.class);
        prefs.put(PREFERENCES_LANGUAGE, locale.getLanguage());
    }

    public static Locale getLocale() {
        Preferences prefs = Preferences.userNodeForPackage(cz.cuni.mff.skychart.App.class);
        return new Locale(prefs.get(PREFERENCES_LANGUAGE, "en"));
    }

    public static ResourceBundle getBundle() {
        return ResourceBundle.getBundle("localisation", getLocale());
    }

}
