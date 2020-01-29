package cz.cuni.mff.skychart.graphics;

import cz.cuni.mff.skychart.projection.ProjectionPlane;
import javafx.scene.canvas.GraphicsContext;

/**
 * Renders Altitude-Azimuth grid.
 *
 * @author Peter Grajcar
 */
public class AltAzGridRenderer extends Renderer {

    private ProjectionPlane projectionPlane;
    private double altitudeStep;
    private double azimuthStep;

    /**
     * Constructs a new Altitude-Azimuth grid renderer with default angles between the grid lines (10 degrees).
     *
     * @param context a graphics context.
     */
    public AltAzGridRenderer(GraphicsContext context, ProjectionPlane plane) {
        super(context);
        this.projectionPlane = plane;
        this.altitudeStep = 10;
        this.azimuthStep = 10;
    }

    /**
     * Constructs a new altitude-azimuth grid renderer with given angles between the grid lines.
     *
     * @param context a graphics context.
     * @param altitudeStep angle between two altitude lines.
     * @param azimuthStep angle between two azimuth lines.
     */
    public AltAzGridRenderer(GraphicsContext context, ProjectionPlane plane, double altitudeStep, double azimuthStep) {
        this(context, plane);
        this.altitudeStep = altitudeStep;
        this.azimuthStep = azimuthStep;
    }

    /**
     * Renders the altitude-azimuth grid.
     */
    @Override
    public void render() {

    }
}
