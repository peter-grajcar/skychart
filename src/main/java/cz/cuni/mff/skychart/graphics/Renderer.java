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

    public abstract void render();

}
