package durianhln.polymorph.gameobject;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import java.awt.Dimension;

/**
 * Represents a moving Entity backdrop that moves vertically.
 * @author Darian
 */
public class Map extends Entity {
    private TextureRegion texture;
    public Map(Vector2 position, Vector2 velocity, Dimension size, TextureRegion texture) {
        super(position, velocity, size);
        this.texture = texture;
    }

    @Override
    public void render(Batch batch) {
        batch.draw(texture, getPosition().x, getPosition().y, getSize().width, getSize().height);
    }

    @Override
    public void update(float delta) {
        getPosition().add(getVelocity().cpy().scl(delta));
        int height = getSize().height;
        if (getPosition().y <= -height) {
            getPosition().y = height;
        }
    }
}
