package cz.cuni.mff.skychart.graphics;

import cz.cuni.mff.skychart.astronomy.HorizontalCoords;
import cz.cuni.mff.skychart.projection.ProjectionPlane;
import cz.cuni.mff.skychart.projection.Vector2;
import cz.cuni.mff.skychart.projection.Vector3;
import cz.cuni.mff.skychart.projection.Vector3Mapping;
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
     * Renders the altitude-azimuth grid.
     */
    @Override
    public void render() {
        Canvas canvas = context.getCanvas();

        Vector3Mapping<HorizontalCoords> mapping = coords -> new Vector3(
                -10 * Math.sin(Math.PI/2 - coords.getAltitudeRadians()) * Math.cos(coords.getAzimuthRadians()),
                -10 * Math.cos(Math.PI/2 - coords.getAltitudeRadians()),
                -10 * Math.sin(Math.PI/2 - coords.getAltitudeRadians()) * Math.sin(coords.getAzimuthRadians())
        );

        context.setStroke(Color.rgb(128, 0, 0));
        for(int j = 0; j < 90; j += 10) {
            context.beginPath();
            for (int i = 0; i < 360; ++i) {
                HorizontalCoords coords = new HorizontalCoords(j, i);
                if (coords.getAltitude() < 0 || !projectionPlane.isFront(coords, mapping, 1e-6)) continue;

                Vector2 point = projectionPlane.project(coords, mapping);
                double x = canvas.getWidth() / 2 + 400 * point.getX();
                double y = canvas.getHeight() / 2 + 400 * point.getY();

                if (i ==0 ) {
                    context.moveTo(x, y);
                }
                else {
                    context.lineTo(x, y);
                }
            }
            context.stroke();
        }
        for(int j = 0; j < 360; j += 10) {
            context.beginPath();
            for (int i = 0; i <= 90; ++i) {
                HorizontalCoords coords = new HorizontalCoords(i, j);
                if (coords.getAltitude() < 0 || !projectionPlane.isFront(coords, mapping, 1e-6)) continue;

                Vector2 point = projectionPlane.project(coords, mapping);
                double x = canvas.getWidth() / 2 + 400 * point.getX();
                double y = canvas.getHeight() / 2 + 400 * point.getY();

                if (i ==0 ) {
                    context.moveTo(x, y);
                }
                else {
                    context.lineTo(x, y);
                }
            }
            context.stroke();
        }
    }
}