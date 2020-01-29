package cz.cuni.mff.skychart.graphics;

import javafx.scene.canvas.GraphicsContext;

/**
 * An abstract class for rendering objects.
 *
 * @author Peter Grajcar
 */
public abstract class ObjectRenderer<T> {

    protected GraphicsContext context;

    public ObjectRenderer(GraphicsContext context) {
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

}
