package cz.cuni.mff.skychart.ui.control;

import cz.cuni.mff.skychart.astronomy.Location;
import cz.cuni.mff.skychart.settings.Localisation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * A location picker component.
 *
 * @author Peter Grajcar
 */
public class LocationPicker extends GridPane {

    @FXML
    private TextField latitude;
    @FXML
    private TextField longitude;

    private Location location;

    public LocationPicker() throws IOException {
        ResourceBundle localisation = Localisation.getBundle();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/control/LocationPicker.fxml"), localisation);
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        latitude.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        longitude.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
    }

    public void setLocation(Location location) {
        this.location = location;
        latitude.setText(Double.toString(location.getLatitude()));
        longitude.setText(Double.toString(location.getLongitude()));
    }
}
