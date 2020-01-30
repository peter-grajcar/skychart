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

        drawStarProjection(canvas, scene);

        stage.setScene(scene);
        stage.show();
    }

    private ZonedDateTime time;
    private Location location;
    private Star selectedStar;

    private void drawStarProjection(Canvas canvas, Scene scene) throws BSC5FormatException, IOException {
        GraphicsContext context = canvas.getGraphicsContext2D();

        Catalogue catalogue = new BSC5Catalogue();
        List<Star> stars = catalogue.starList();
        time = LocalDateTime.parse("2020-01-27T22:00:00").atZone(ZoneOffset.UTC);
        location = new Location(48.2, 17.4);

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

        Vector3Mapping<HorizontalCoords> horizontalCoordsMapping = coords -> new Vector3(
                -10 * Math.sin(Math.PI/2 - coords.getAltitudeRadians()) * Math.cos(coords.getAzimuthRadians()),
                -10 * Math.cos(Math.PI/2 - coords.getAltitudeRadians()),
                -10 * Math.sin(Math.PI/2 - coords.getAltitudeRadians()) * Math.sin(coords.getAzimuthRadians())
        );
        Vector2Mapping<Vector2> planeToScreenMapping = point -> new Vector2(
                canvas.getWidth()/2 + 400 * point.getX(),
                canvas.getHeight()/2 + 400 * point.getY()
        );
        Vector2Mapping<Vector2> screenToPlaneMapping = point -> new Vector2(
                (point.getX() - canvas.getWidth()/2) / 400,
                (point.getY() - canvas.getHeight()/2) / 400
        );

        new AnimationTimer() {
            private long prevTime = -1;
            private long elapsedTime;
            @Override
            public void handle(long l) {
                long dt = prevTime == -1 ? 0 : System.nanoTime() - prevTime;
                prevTime = System.nanoTime();
                if(!paused.get())
                    time = time.plusSeconds(dt / 10_000_000);

                plane.setRotation(rotationY.doubleValue(), rotationZ.doubleValue());
                plane.setDistance(dist.doubleValue());

                // Draw the sky
                context.setFill(Color.rgb(0, 0, 32));
                context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                // Draw the alt-az grid
                gridRenderer.render();

                // Draw the stars
                for (Star star : stars) {
                    HorizontalCoords coords = star.getCoords().toHorizontal(time, location);

                    if(coords.getAltitude() < 0 || !plane.isFront(coords, horizontalCoordsMapping, 1e-6))
                        continue;

                    Vector2 point = plane.project(coords, horizontalCoordsMapping);

                    starRenderer.render(planeToScreenMapping.map(point), star);
                    if(star.equals(selectedStar)) {
                        Vector2 screenPoint = planeToScreenMapping.map(point);
                        context.setStroke(Color.rgb(128, 0, 0));
                        context.setLineWidth(2);
                        context.strokeOval(screenPoint.getX() - 6, screenPoint.getY() - 6, 12, 12);
                    }
                }

                // Time and location
                context.setFont(Font.font("Courier New", 12));
                context.fillText(time.toString(), 10, canvas.getHeight() - 30);
                context.fillText(location.toString(), 10, canvas.getHeight() - 15);
            }
        }.start();

        for (Star star : stars) {
            BSC5Star bsc5Star = (BSC5Star) star;
            if(bsc5Star.getBayerName().equals("Alp CMa")) {
                logger.debug(star.getCoords().toHorizontal(time, location));
            }
        }

        canvas.setOnMouseClicked(mouseEvent -> {
            Vector2 point = new Vector2(mouseEvent.getX(), mouseEvent.getY());
            Vector2 pointOnPlane = screenToPlaneMapping.map(point);
            HorizontalCoords coords = pointOnPlaneToHorizontalCoords(pointOnPlane, plane);

            Star closest = null;
            double closestDistance = 0;
            for (Star star : stars) {
                HorizontalCoords starCoords = star.getCoords().toHorizontal(time, location);
                double dAlt = coords.getAltitude() - starCoords.getAltitude();
                double dAz = coords.getAzimuth() - starCoords.getAzimuth();
                double distance = dAlt * dAlt + dAz * dAz;
                if(closest == null || distance < closestDistance) {
                    closest = star;
                    closestDistance = distance;
                }

            }
            logger.debug( closest);
            selectedStar = closest;
        });

    }

    private HorizontalCoords pointOnPlaneToHorizontalCoords(Vector2 point, PerspectiveProjectionPlane plane) {
        Vector3 planeRotation = plane.getRotation();
        double angleZ = Math.atan(point.getY() / plane.getDistance());
        double angleY = Math.atan(point.getX() / plane.getDistance());

        HorizontalCoords horizontalCoords =  new HorizontalCoords();
        horizontalCoords.setAltitudeRadians(-planeRotation.getZ() - angleZ);
        horizontalCoords.setAzimuthRadians(Math.PI-planeRotation.getY() + angleY);

        return horizontalCoords;
    }

}
