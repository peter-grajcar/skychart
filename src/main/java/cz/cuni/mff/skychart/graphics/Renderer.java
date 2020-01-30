package cz.cuni.mff.skychart.graphics;

import javafx.scene.canvas.GraphicsContext;

/**
 * An abstract class for rendering non-object entities.
 *
 * @author Peter Grajcar
 */
public abstract class Renderer {

    protected GraphicsContext context;

    public Renderer(GraphicsContext context){
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
     * renders the entity.
     */
    public abstract void render();

}
