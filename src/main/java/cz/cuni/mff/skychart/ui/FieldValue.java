package cz.cuni.mff.skychart.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Field-value pair
 *
 * @author Peter Grajcar
 */
public class FieldValue {
    private StringProperty field;
    private StringProperty value;

    public FieldValue(String field, String value) {
        this.field = new SimpleStringProperty(field);
        this.value = new SimpleStringProperty(value);
    }

    public String getField() {
        return field.get();
    }

    public StringProperty fieldProperty() {
        return field;
    }

    public void setField(String field) {
        this.field.set(field);
    }

    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

}
