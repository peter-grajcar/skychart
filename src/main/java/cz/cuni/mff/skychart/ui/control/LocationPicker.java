package cz.cuni.mff.skychart.ui.control;

import cz.cuni.mff.skychart.astronomy.Location;
import cz.cuni.mff.skychart.settings.Localisation;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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

    private ObjectProperty<Location> location = new SimpleObjectProperty<>();

    public LocationPicker() throws IOException {
        ResourceBundle localisation = Localisation.getBundle();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/control/LocationPicker.fxml"), localisation);
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        latitude.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        longitude.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));

        location.addListener((observableValue, oldLocation, newLocation) -> {
            latitude.setText(Double.toString(newLocation.getLatitude()));
            longitude.setText(Double.toString(newLocation.getLongitude()));
        });

        latitude.textProperty().addListener((observableValue, oldLatitude, newLatitude) -> {
            try {
                double newLat = Double.parseDouble(newLatitude);
                if(newLat > 90) newLat = 90;
                else if(newLat < -90) newLat = -90;
                location.get().setLatitude(newLat);
            } catch (NumberFormatException e) {

            }
        });
        longitude.textProperty().addListener((observableValue, oldLongitude, newLongitude) -> {
            try {
                double newLong = Double.parseDouble(newLongitude);
                if(newLong > 180) newLong = 180;
                else if(newLong < -180) newLong = -180;
                location.get().setLongitude(newLong);
            } catch (NumberFormatException e){

            }
        });
    }

    public void setValue(Location location) {
        this.location.set(location);
    }

    public Location getValue() {
        return location.getValue();
    }

}
