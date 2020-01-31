package cz.cuni.mff.skychart.ui;

import cz.cuni.mff.skychart.catalogue.Star;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * created: 31/01/2020
 *
 * @author Peter Grajcar
 */
public class StarInfo {

    /**
     * Retrieves information about the star and converts it to {@link ObservableList ObservableList} of {@link FieldValue FieldValue}s so it can be used
     * with {@link javafx.scene.control.TableView TableView}.
     *
     * @param star selected star.
     * @return information about the star.
     */
    public static ObservableList<FieldValue> getInfo(Star star) {
        Map<String, String> info = star.getInfo();
        List<FieldValue> fieldValues = new ArrayList<>();
        for(String key : info.keySet())
            fieldValues.add(new FieldValue(key, info.get(key)));
        return FXCollections.observableList(fieldValues);
    }

}
