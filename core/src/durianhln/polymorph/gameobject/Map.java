package durianhln.polymorph.gameobject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import java.awt.Dimension;

/**
 * Represents a moving Entity backdrop that moves vertically.
 * @author Darian
 */
public class Map extends Entity {
    public Map(Vector2 position, Vector2 velocity, Dimension size, TextureRegion texture) {
        super(position, velocity, size, texture);
    }

    @Override
    public void update(float delta) {
        getPosition().add(getVelocity().cpy().scl(delta));
        int height = getSize().height;
        if (getPosition().y >= height) {
            getPosition().y = -height + 10;
        }
    }
}
