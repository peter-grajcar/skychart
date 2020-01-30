package cz.cuni.mff.skychart.graphics;

import cz.cuni.mff.skychart.catalogue.Star;
import cz.cuni.mff.skychart.catalogue.bsc5.BSC5Star;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * An interface for rendering stars from BSC5 catalogue.
 *
 * @author Peter Grajcar
 */
public class BSC5StarRenderer extends StarRenderer {

    private double magnitudeThreshold;

    /**
     * Constructs a new renderer with given context and default visual magnitude threshold (2.0).
     *
     * @param context a graphical context.
     */
    public BSC5StarRenderer(GraphicsContext context) {
        super(context);
        this.magnitudeThreshold = 2.0;
    }

    /**
     * Constructs a new renderer with given context and visual magnitude threshold. Stars with visual magnitude
     * below the threshold will have their name displayed next to them.
     *
     * @param context a graphical context.
     * @param threshold a visual magnitude threshold for displaying names.
     */
    public BSC5StarRenderer(GraphicsContext context, double threshold) {
       super(context);
       this.magnitudeThreshold = threshold;
    }

    /**
     * Returns the visual magnitude threshold.
     *
     * @return current visual magnitude threshold.
     */
    public double getMagnitudeThreshold() {
        return magnitudeThreshold;
    }

    /**
     * Sets a new visual magnitude threshold.
     *
     * @param magnitudeThreshold a visual magnitude threshold for displaying names.
     */
    public void setMagnitudeThreshold(double magnitudeThreshold) {
        this.magnitudeThreshold = magnitudeThreshold;
    }

    /**
     * Renders a single BSC5 star at given position.
     *
     * @param x x coordinate.
     * @param y y coordinate.
     * @param star a BSC5 star to render.
     */
    @Override
    public void render(double x, double y, Star star) {
        if(!(star instanceof BSC5Star))
            throw new ClassCastException("Expected BSC5 star.");
        render(x, y, (BSC5Star) star);
    }

    /**
     * Renders a BSC5 single star at given position.
     *
     * @param x x coordinate.
     * @param y y coordinate.
     * @param star a BSC5 star to render.
     */
    public void render(double x, double y, BSC5Star star) {
        double size = 0.5 + 4 * Math.sqrt(Math.pow(2.512, -star.getVisualMagnitude()));
        context.setFill(Color.WHITE);
        context.fillOval(x - size / 2, y - size / 2, size, size);

        if(star.getVisualMagnitude() < 2.0) {
            context.setFont(Font.font(8));
            context.fillText(star.getBayerName(), x + 4, y + 4);
        }
    }
}
