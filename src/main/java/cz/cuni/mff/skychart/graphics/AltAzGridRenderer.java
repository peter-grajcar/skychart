package cz.cuni.mff.skychart.graphics;

import cz.cuni.mff.skychart.astronomy.HorizontalCoords;
import cz.cuni.mff.skychart.projection.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Renders an Altitude-Azimuth grid.
 *
 * @author Peter Grajcar
 */
public class AltAzGridRenderer extends Renderer {

    private ProjectionPlane projectionPlane;
    private double altitudeStep;
    private double azimuthStep;

    /**
     * Constructs a new altitude-azimuth grid renderer with default angles between the grid lines (10 degrees).
     *
     * @param context a graphics context.
     * @param plane a projection plane.
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
     * @param plane a projection plane.
     * @param altitudeStep angle between two altitude lines.
     * @param azimuthStep angle between two azimuth lines.
     */
    public AltAzGridRenderer(GraphicsContext context, ProjectionPlane plane, double altitudeStep, double azimuthStep) {
        this(context, plane);
        this.altitudeStep = altitudeStep;
        this.azimuthStep = azimuthStep;
    }

    /**
     * Returns the projections plane.
     *
     * @return the projections plane.
     */
    public ProjectionPlane getProjectionPlane() {
        return projectionPlane;
    }

    /**
     * Sets a new projection plane.
     *
     * @param projectionPlane a new projection plane.
     */
    public void setProjectionPlane(ProjectionPlane projectionPlane) {
        this.projectionPlane = projectionPlane;
    }

    /**
     * Returns the angle between the altitude grid lines.
     *
     * @return the angle between the altitude grid lines.
     */
    public double getAltitudeStep() {
        return altitudeStep;
    }

    /**
     * Sets a new angle between the altitude grid lines.
     *
     * @param altitudeStep a new angle between the altitude grid lines.
     */
    public void setAltitudeStep(double altitudeStep) {
        this.altitudeStep = altitudeStep;
    }

    /**
     * Returns the angle between the azimuth grid lines.
     *
     * @return the angle between the azimuth grid lines.
     */
    public double getAzimuthStep() {
        return azimuthStep;
    }

    /**
     * Sets a new angle between the azimuth grid lines.
     *
     * @param azimuthStep a new angle between the azimuth grid lines.
     */
    public void setAzimuthStep(double azimuthStep) {
        this.azimuthStep = azimuthStep;
    }

    /**
     * Renders the altitude-azimuth grid.
     */
    @Override
    public void render() {
        Canvas canvas = context.getCanvas();

        Vector3Mapping<HorizontalCoords> horizontalCoordsMapping = coords -> new Vector3(
                -10 * Math.sin(Math.PI/2 - coords.getAltitudeRadians()) * Math.cos(coords.getAzimuthRadians()),
                -10 * Math.cos(Math.PI/2 - coords.getAltitudeRadians()),
                -10 * Math.sin(Math.PI/2 - coords.getAltitudeRadians()) * Math.sin(coords.getAzimuthRadians())
        );
        Vector2Mapping<Vector2> planeCoordsMapping = point -> new Vector2(
                canvas.getWidth() / 2 + 400 * point.getX(),
                canvas.getHeight() / 2 + 400 * point.getY()
        );

        context.setStroke(Color.rgb(128, 0, 0));
        context.setLineWidth(1);
        for(int j = 0; j < 90; j += 10) {
            context.beginPath();
            for (int i = 0; i < 360; ++i) {
                HorizontalCoords coords = new HorizontalCoords(j, i);
                if (!projectionPlane.isFront(coords, horizontalCoordsMapping, 1e-6))
                    continue;

                Vector2 point = projectionPlane.project(coords, horizontalCoordsMapping);
                Vector2 screenPoint = planeCoordsMapping.map(point);

                if (i ==0 ) {
                    context.moveTo(screenPoint.getX(), screenPoint.getY());
                }
                else {
                    context.lineTo(screenPoint.getX(), screenPoint.getY());
                }
            }
            context.stroke();
        }
        for(int j = 0; j < 360; j += 10) {
            context.beginPath();
            for (int i = 0; i <= 90; ++i) {
                HorizontalCoords coords = new HorizontalCoords(i, j);
                if (!projectionPlane.isFront(coords, horizontalCoordsMapping, 1e-6))
                    continue;

                Vector2 point = projectionPlane.project(coords, horizontalCoordsMapping);
                Vector2 screenPoint = planeCoordsMapping.map(point);

                if (i ==0 ) {
                    context.moveTo(screenPoint.getX(), screenPoint.getY());
                }
                else {
                    context.lineTo(screenPoint.getX(), screenPoint.getY());
                }
            }
            context.stroke();
        }
    }
}
