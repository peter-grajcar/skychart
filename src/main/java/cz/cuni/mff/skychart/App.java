package cz.cuni.mff.skychart;

import cz.cuni.mff.skychart.astronomy.HorizontalCoords;
import cz.cuni.mff.skychart.astronomy.Location;
import cz.cuni.mff.skychart.catalogue.Catalogue;
import cz.cuni.mff.skychart.catalogue.Star;
import cz.cuni.mff.skychart.catalogue.bsc5.BSC5Catalogue;
import cz.cuni.mff.skychart.catalogue.bsc5.BSC5FormatException;
import cz.cuni.mff.skychart.catalogue.bsc5.BSC5Star;
import cz.cuni.mff.skychart.graphics.PerspectiveProjectionPlane;
import cz.cuni.mff.skychart.graphics.Vector2;
import cz.cuni.mff.skychart.graphics.Vector3;
import cz.cuni.mff.skychart.settings.Localisation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.ResourceBundle;

/**
 * JavaFX application.
 *
 * @author Peter Grajcar
 */
public class App extends Application {

    private static final Logger logger = LogManager.getLogger(App.class);

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

        //drawStars(canvas);
        drawCube(canvas);

        root.getChildren().addAll(canvas);
        stage.setScene(scene);
        stage.show();
    }

    private void drawCube(Canvas canvas) {
        PerspectiveProjectionPlane plane = new PerspectiveProjectionPlane(
                new Vector3(),
                new Vector3(1, 0, 0),
                new Vector3(0, 1, 0)
        );
        Vector3[] vertices = {
                new Vector3(5, 1, -1), new Vector3(5, 1, 1),
                new Vector3(6, 1, 1), new Vector3(6, 1, -1),
                new Vector3(5, -1, -1), new Vector3(5, -1, 1),
                new Vector3(6, -1, 1), new Vector3(6, -1, -1),
        };

        GraphicsContext context = canvas.getGraphicsContext2D();

        new AnimationTimer() {
            private long prevNanoTime = -1;
            @Override
            public void handle(long l) {
                long dt = 0;
                if(prevNanoTime != -1)
                    dt = System.nanoTime() - prevNanoTime;
                prevNanoTime = System.nanoTime();

                context.setFill(Color.WHITE);
                context.fillRect(0,0, canvas.getWidth(), canvas.getHeight());

                context.setFill(Color.BLACK);
                for (Vector3 vertex : vertices) {
                    Vector2 p = plane.project(vertex);
                    double x = canvas.getWidth() / 2 + p.getX() * 400;
                    double y = canvas.getHeight() / 2 + p.getY() * 400;
                    context.fillOval(x - 3, y - 3, 6, 6);
                }

                plane.rotate(Vector3.Axis.Y, dt / 10_000_000_000d);
            }
        }.start();
    }

    private void drawStars(Canvas canvas) throws BSC5FormatException, IOException {
        Catalogue catalogue = new BSC5Catalogue();
        List<Star> stars = catalogue.starList();

        GraphicsContext context = canvas.getGraphicsContext2D();

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                context.setFill(Color.rgb(0,0,32));
                context.fillRect(0,0, canvas.getWidth(), canvas.getHeight());

                long t = System.nanoTime() - startNanoTime;
                ZonedDateTime time = LocalDateTime.parse("2020-01-27T22:00:00").atZone(ZoneOffset.UTC).plusSeconds(t/1_000_000);

                for (Star star : stars) {
                    BSC5Star bsc5Star = (BSC5Star) star;
                    HorizontalCoords horizontalCoords = star.getCoords().toHorizontal(
                            time,
                            new Location(48.2, 17.4)
                    );

                    if (horizontalCoords.getAltitude() < 0) continue;

                    double r = (90 - horizontalCoords.getAltitude()) / 90 * 400;
                    double x = 400 + r * Math.cos(horizontalCoords.getAzimuthRadians());
                    double y = 400 + r * Math.sin(horizontalCoords.getAzimuthRadians());
                    double size = 0.5 + 4 * Math.sqrt(Math.pow(2.512, -star.getVisualMagnitude()));

                    context.setFill(Color.WHITE);
                    context.fillOval(x - size / 2, y - size / 2, size, size);
                    if(star.getVisualMagnitude() < 2.0)
                        context.fillText(bsc5Star.getBayerName(), x, y);
                }
            }
        }.start();
    }

}
