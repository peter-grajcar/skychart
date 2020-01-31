package cz.cuni.mff.skychart.ui;

import cz.cuni.mff.skychart.catalogue.Star;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Contains methods for conversion of the star info to {@link ObservableList ObservableList} used by
 * {@link javafx.scene.control.TableView TableView}.
 *
 * @author Peter Grajcar
 */
public class StarItems {

    /**
     * Retrieves information about the star and converts it to {@link ObservableList ObservableList} of {@link FieldValue FieldValue}s so it can be used
     * with {@link javafx.scene.control.TableView TableView}.
     *
     * @param star selected star.
     * @return information about the star.
     */
    public static ObservableList<FieldValue> getItems(Star star) {
        Map<String, String> info = star.getInfo();
        List<FieldValue> fieldValues = new ArrayList<>();
        for(String key : info.keySet())
            fieldValues.add(new FieldValue(key, info.get(key)));
        return FXCollections.observableList(fieldValues);
    }

}
