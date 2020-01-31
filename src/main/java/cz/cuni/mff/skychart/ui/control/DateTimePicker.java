package cz.cuni.mff.skychart.ui.control;

import cz.cuni.mff.skychart.settings.Localisation;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * A date time picker component.
 *
 * @author Peter Grajcar
 */
public class DateTimePicker extends GridPane {

    @FXML
    private DatePicker datePicker;

    public static final String DEFAULT_FORMAT = "dd.MM.yyyy HH:mm:ss";

    private final static Logger logger = LogManager.getLogger(DateTimePicker.class);

    private DateTimeFormatter formatter;
    private ObjectProperty<LocalDateTime> dateTimeValue = new SimpleObjectProperty<>(LocalDateTime.now());
    private ObjectProperty<String> format = new SimpleObjectProperty<String>() {
        public void set(String newValue) {
            super.set(newValue);
            formatter = DateTimeFormatter.ofPattern(newValue);
        }
    };

    private ChangeListener<LocalDateTime> onDateTimeChangedListener;

    public DateTimePicker() throws IOException {
        ResourceBundle localisation = Localisation.getBundle();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/control/DateTimePicker.fxml"), localisation);
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        getStyleClass().add("datetime-picker");
        setFormat(DEFAULT_FORMAT);
        datePicker.setConverter(new InternalConverter());

        // Syncronize changes to the underlying date value back to the dateTimeValue
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                dateTimeValue.set(null);
            } else {
                if (dateTimeValue.get() == null) {
                    dateTimeValue.set(LocalDateTime.of(newValue, LocalTime.now()));
                } else {
                    LocalTime time = dateTimeValue.get().toLocalTime();
                    dateTimeValue.set(LocalDateTime.of(newValue, time));
                }
            }
            if(onDateTimeChangedListener != null)
                onDateTimeChangedListener.changed(dateTimeValue, dateTimeValue.get(), dateTimeValue.get());
        });

        // Syncronize changes to dateTimeValue back to the underlying date value
        dateTimeValue.addListener((observable, oldValue, newValue) -> {
            datePicker.setValue(newValue == null ? null : newValue.toLocalDate());
            datePicker.getEditor().setText(getDateTimeValue().format(formatter));
            if(onDateTimeChangedListener != null)
                onDateTimeChangedListener.changed(dateTimeValue, dateTimeValue.get(), dateTimeValue.get());
        });

        // Persist changes onblur
        datePicker.getEditor().focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue)
                simulateEnterPressed();
        });

    }

    public void setOnDateTimeChangedListener(ChangeListener<LocalDateTime> onDateTimeChangedListener) {
        this.onDateTimeChangedListener = onDateTimeChangedListener;
    }

    private void simulateEnterPressed() {
        datePicker.getEditor().fireEvent(new KeyEvent(datePicker.getEditor(), datePicker.getEditor(), KeyEvent.KEY_PRESSED, null, null, KeyCode.ENTER, false, false, false, false));
    }

    public LocalDateTime getDateTimeValue() {
        return dateTimeValue.get();
    }

    public void setDateTimeValue(LocalDateTime dateTimeValue) {
        this.dateTimeValue.set(dateTimeValue);
    }

    public ObjectProperty<LocalDateTime> dateTimeValueProperty() {
        return dateTimeValue;
    }

    public String getFormat() {
        return format.get();
    }

    public ObjectProperty<String> formatProperty() {
        return format;
    }

    public void setFormat(String format) {
        this.format.set(format);
    }

    class InternalConverter extends StringConverter<LocalDate> {
        public String toString(LocalDate object) {
            LocalDateTime value = getDateTimeValue();
            return (value != null) ? value.format(formatter) : "";
        }

        public LocalDate fromString(String value) {
            if (value == null) {
                dateTimeValue.set(null);
                return null;
            }

            dateTimeValue.set(LocalDateTime.parse(value, formatter));
            return dateTimeValue.get().toLocalDate();
        }
    }
}
