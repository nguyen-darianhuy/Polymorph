package durianhln.polymorph;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private TextureRegion texture;

    public Entity(Vector2 position, Vector2 velocity, Dimension size, TextureRegion texture) {
        this.position = position;
        this.velocity = velocity;
        this.size = size;
        this.texture = texture;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, size.width, size.height);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Dimension getSize() {
        return size;
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }
}
