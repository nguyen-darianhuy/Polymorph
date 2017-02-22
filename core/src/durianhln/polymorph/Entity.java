package durianhln.polymorph;

import com.badlogic.gdx.math.Vector2;
import java.awt.Dimension;

/**
 *
 * @author Darian
 */
public abstract class Entity implements Updatable {
    private Vector2 position;
    private Vector2 velocity;
    private Dimension size;

    public Entity(Vector2 position, Vector2 velocity, Dimension size) {
        this.position = position;
        this.velocity = velocity;
        this.size = size;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Dimension getSize() {
        return size;
    }
}
