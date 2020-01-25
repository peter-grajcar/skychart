package cz.cuni.mff.skychart;

import cz.cuni.mff.skychart.settings.Localisation;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * created: 25/01/2020
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
        Scene scene = new Scene(root, 800, 600);

        Canvas canvas = new Canvas(800, 500);
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setFill(Color.BLACK);
        context.fillRect(0,0, canvas.getWidth(), canvas.getHeight());

        Button btn = new Button("Click");

        root.getChildren().addAll(canvas, btn);
        stage.setScene(scene);
        stage.show();
    }

}
