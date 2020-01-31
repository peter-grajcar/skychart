package cz.cuni.mff.skychart.ui.control;

import cz.cuni.mff.skychart.settings.Localisation;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * A location picker component.
 *
 * @author Peter Grajcar
 */
public class LocationPicker extends GridPane {

    public LocationPicker() throws IOException {
        ResourceBundle localisation = Localisation.getBundle();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/control/LocationPicker.fxml"), localisation);
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }
}
