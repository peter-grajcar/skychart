package cz.cuni.mff.skychart;

import cz.cuni.mff.skychart.astronomy.HorizontalCoords;
import cz.cuni.mff.skychart.astronomy.Location;
import cz.cuni.mff.skychart.catalogue.Catalogue;
import cz.cuni.mff.skychart.catalogue.Star;
import cz.cuni.mff.skychart.catalogue.bsc5.BSC5Catalogue;
import cz.cuni.mff.skychart.catalogue.bsc5.BSC5FormatException;
import cz.cuni.mff.skychart.catalogue.bsc5.BSC5Star;
import cz.cuni.mff.skychart.graphics.AltAzGridRenderer;
import cz.cuni.mff.skychart.graphics.BSC5StarRenderer;
import cz.cuni.mff.skychart.projection.*;
import cz.cuni.mff.skychart.settings.Localisation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/App.fxml"));
        GridPane root = loader.load();

        Scene scene = new Scene(root);

        Canvas canvas = (Canvas) root.lookup("#canvas");
        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());
        canvas.setOnMouseClicked(mouseEvent -> {
          canvas.requestFocus();
        });

        //drawStars(canvas);
        //drawCube(canvas);
        drawStarProjection(canvas, scene);

        stage.setScene(scene);
        stage.show();
    }


    private void drawStarProjection(Canvas canvas, Scene scene) throws BSC5FormatException, IOException {
        GraphicsContext context = canvas.getGraphicsContext2D();

        Catalogue catalogue = new BSC5Catalogue();
        List<Star> stars = catalogue.starList();
        ZonedDateTime time = LocalDateTime.parse("2020-01-27T22:00:00").atZone(ZoneOffset.UTC);
        Location location = new Location(48.2, 17.4);

        PerspectiveProjectionPlane plane = new PerspectiveProjectionPlane(
                new Vector3(),
                new Vector3(2, 0, 0),
                new Vector3(0, 1, 0)
        );
        AltAzGridRenderer gridRenderer = new AltAzGridRenderer(context, plane);
        BSC5StarRenderer starRenderer = new BSC5StarRenderer(context);

        DoubleProperty rotationY = new SimpleDoubleProperty();
        DoubleProperty rotationZ = new SimpleDoubleProperty();
        DoubleProperty dist = new SimpleDoubleProperty(2);
        BooleanProperty paused = new SimpleBooleanProperty(false);
        final double rotationRate = 0.1;


        scene.setOnKeyPressed(keyEvent -> {
            Timeline timeline;
            switch (keyEvent.getCode()) {
                case RIGHT:
                    //plane.rotate(Vector3.Axis.Y, -0.05);
                    timeline = new Timeline(
                            new KeyFrame(
                                    Duration.seconds(0),
                                    new KeyValue(rotationY, plane.getRotation().getY()),
                                    new KeyValue(rotationZ, plane.getRotation().getZ())
                            ),
                            new KeyFrame(
                                    Duration.seconds(0.5),
                                    new KeyValue(rotationY, plane.getRotation().getY() - rotationRate),
                                    new KeyValue(rotationZ, plane.getRotation().getZ())
                            )
                    );
                    timeline.play();
                    keyEvent.consume();
                    break;
                case LEFT:
                    timeline = new Timeline(
                            new KeyFrame(
                                    Duration.seconds(0),
                                    new KeyValue(rotationY, plane.getRotation().getY()),
                                    new KeyValue(rotationZ, plane.getRotation().getZ())
                            ),
                            new KeyFrame(
                                    Duration.seconds(0.5),
                                    new KeyValue(rotationY, plane.getRotation().getY() + rotationRate),
                                    new KeyValue(rotationZ, plane.getRotation().getZ())
                            )
                    );
                    timeline.play();
                    keyEvent.consume();
                    break;
                case UP:
                    timeline = new Timeline(
                            new KeyFrame(
                                    Duration.seconds(0),
                                    new KeyValue(rotationY, plane.getRotation().getY()),
                                    new KeyValue(rotationZ, plane.getRotation().getZ())
                            ),
                            new KeyFrame(
                                    Duration.seconds(0.5),
                                    new KeyValue(rotationY, plane.getRotation().getY()),
                                    new KeyValue(rotationZ, plane.getRotation().getZ() - rotationRate)
                            )
                    );
                    timeline.play();
                    keyEvent.consume();
                    break;
                case DOWN:
                    timeline = new Timeline(
                            new KeyFrame(
                                    Duration.seconds(0),
                                    new KeyValue(rotationY, plane.getRotation().getY()),
                                    new KeyValue(rotationZ, plane.getRotation().getZ())
                            ),
                            new KeyFrame(
                                    Duration.seconds(0.5),
                                    new KeyValue(rotationY, plane.getRotation().getY()),
                                    new KeyValue(rotationZ, plane.getRotation().getZ()+ rotationRate)
                            )
                    );
                    timeline.play();
                    keyEvent.consume();
                    break;
                case M:
                    dist.setValue(dist.getValue() + 0.5);
                    break;
                case N:
                    if(dist.getValue() > 1)
                        dist.setValue(dist.getValue() - 0.5);
                    break;
                case SPACE:
                    paused.set(!paused.get());
                    break;
            }
        });

        new AnimationTimer() {
            private long prevTime = -1;
            private long elapsedTime;
            @Override
            public void handle(long l) {
                long dt = prevTime == -1 ? 0 : System.nanoTime() - prevTime;
                prevTime = System.nanoTime();
                if(!paused.get())
                    elapsedTime += dt;
                ZonedDateTime zonedDateTime = LocalDateTime.parse("2020-01-27T22:00:00").atZone(ZoneOffset.UTC).plusSeconds(elapsedTime / 10_000_000);

                Vector3Mapping<HorizontalCoords> horizontalCoordsMapping = coords -> new Vector3(
                            -10 * Math.sin(Math.PI/2 - coords.getAltitudeRadians()) * Math.cos(coords.getAzimuthRadians()),
                            -10 * Math.cos(Math.PI/2 - coords.getAltitudeRadians()),
                            -10 * Math.sin(Math.PI/2 - coords.getAltitudeRadians()) * Math.sin(coords.getAzimuthRadians())
                    );
                Vector2Mapping<Vector2> planeCoordsMapping = point -> new Vector2(
                    canvas.getWidth()/2 + 400 * point.getX(),
                    canvas.getHeight()/2 + 400 * point.getY()
                );

                plane.setRotation(rotationY.doubleValue(), rotationZ.doubleValue());
                plane.setDistance(dist.doubleValue());

                // Draw the sky
                context.setFill(Color.rgb(0, 0, 32));
                context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                // Draw the alt-az grid
                gridRenderer.render();

                // Draw the stars
                for (Star star : stars) {
                    HorizontalCoords coords = star.getCoords().toHorizontal(zonedDateTime, location);

                    if(coords.getAltitude() < 0 || !plane.isFront(coords, horizontalCoordsMapping, 1e-6))
                        continue;

                    Vector2 point = plane.project(coords, horizontalCoordsMapping);

                    starRenderer.render(planeCoordsMapping.map(point), star);
                }

                // Time and location
                context.setFont(Font.font("Courier New", 12));
                context.fillText(zonedDateTime.toString(), 10, canvas.getHeight() - 30);
                context.fillText(location.toString(), 10, canvas.getHeight() - 15);
            }
        }.start();

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
