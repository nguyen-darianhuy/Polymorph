package durianhln.polymorph.gameobject;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import java.awt.Dimension;

/**
 * Represents a game entity that has a position, can move, and has a texture.
 * @author Darian
 */
public abstract class Entity implements Updatable {
    private final Vector2 position;
    private final Vector2 velocity;
    private Dimension size;

    public Entity(Vector2 position, Vector2 velocity, Dimension size) {
        this.position = position;
        this.velocity = velocity;
        this.size = size;
    }

    public abstract void render(Batch batch);

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Dimension getSize() {
        return size;
    }
}
