package cz.cuni.mff.skychart.graphics;

import cz.cuni.mff.skychart.catalogue.Star;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A simple star renderer.
 *
 * @author Peter Grajcar
 */
public abstract class StarRenderer extends ObjectRenderer<Star> {

    /**
     * Constructs a new Star renderer.
     *
     * @param context a graphical context.
     */
    public StarRenderer(GraphicsContext context) {
        super(context);
    }

    /**
     * Renders a single star at given position.
     *
     * @param x x coordinate.
     * @param y y coordinate.
     * @param star a star to render.
     */
    @Override
    public void render(double x, double y, Star star) {
        double size = 0.5 + 4 * Math.sqrt(Math.pow(2.512, -star.getVisualMagnitude()));
        context.setFill(Color.WHITE);
        context.fillOval(x - size / 2, y - size / 2, size, size);
    }

}
