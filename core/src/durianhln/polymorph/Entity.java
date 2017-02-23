package durianhln.polymorph;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    public void setPosition(Vector2 position) {
        this.position = position;
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
