package cz.cuni.mff.skychart.graphics;

import cz.cuni.mff.skychart.projection.Vector2;
import javafx.scene.canvas.GraphicsContext;

/**
 * An abstract class for rendering objects.
 *
 * @author Peter Grajcar
 */
public abstract class ObjectRenderer<T> {

    protected GraphicsContext context;

    /**
     * Constructs a new object renderer.
     *
     * @param context a graphics context.
     */
    public ObjectRenderer(GraphicsContext context) {
        this.context = context;
    }

    /**
     * Returns the graphical context.
     *
     * @return the graphical context.
     */
    public GraphicsContext getContext() {
        return context;
    }

    /**
     * Sets a new graphical context
     *
     * @param context a new graphical context.
     */
    public void setContext(GraphicsContext context) {
        this.context = context;
    }

    /**
     * Renders an object at given position.
     *
     * @param x x coordinate.
     * @param y y coordinate.
     * @param obj an object to render.
     */
    public abstract void render(double x, double y, T obj);

    /**
     * Renders an object at given position.
     *
     * @param point a position of the object.
     * @param obj an object to render.
     */
    public void render(Vector2 point, T obj) {
        render(point.getX(), point.getY(), obj);
    }

}
