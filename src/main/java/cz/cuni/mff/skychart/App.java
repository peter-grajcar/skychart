package cz.cuni.mff.skychart;

import cz.cuni.mff.skychart.astronomy.HorizontalCoords;
import cz.cuni.mff.skychart.astronomy.Location;
import cz.cuni.mff.skychart.catalogue.Catalogue;
import cz.cuni.mff.skychart.catalogue.Star;
import cz.cuni.mff.skychart.catalogue.bsc5.BSC5Catalogue;
import cz.cuni.mff.skychart.settings.Localisation;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.ResourceBundle;

/**
 * JavaFX application.
 *
 * @author Peter Grajcar
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ResourceBundle localisation = Localisation.getBundle();

        stage.setTitle(localisation.getString("window.title"));

        Group root = new Group();
        Scene scene = new Scene(root, 800, 800);

        Canvas canvas = new Canvas(800, 800);
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setFill(Color.rgb(0,0,32));
        context.fillRect(0,0, canvas.getWidth(), canvas.getHeight());

        Catalogue catalogue = new BSC5Catalogue();
        List<Star> stars = catalogue.starList();

        context.setFill(Color.WHITE);
        for(Star star : stars) {
            HorizontalCoords horizontalCoords = star.getCoords().toHorizontal(
                    LocalDateTime.parse("2020-01-27T22:00:00").atZone(ZoneOffset.UTC),
                    new Location(0, 0)
            );

            if(horizontalCoords.getAltitude() < 0) continue;

            double r = (90 - horizontalCoords.getAltitude())/90 * 400;
            double x = 400 + r * Math.cos(horizontalCoords.getAzimuthRadians());
            double y = 400 + r * Math.sin(horizontalCoords.getAzimuthRadians());

            double size = 0.5 + 4 * Math.sqrt(Math.pow(2.512, -star.getVisualMagnitude()));
            context.fillOval(x - size/2, y - size/2, size, size);
        }

        root.getChildren().addAll(canvas);
        stage.setScene(scene);
        stage.show();
    }

}
