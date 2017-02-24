package durianhln.polymorph.gameobject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import java.awt.Dimension;

/**
 * Represents a moving Scrollable backdrop that moves vertically.
 * @author Darian
 */
public class Map extends Scrollable {
    public Map(Vector2 position, Vector2 velocity, Dimension size, TextureRegion texture) {
        super(position, velocity, size, texture);
    }

    @Override
    public void update(float delta) {
        getPosition().add(getVelocity().cpy().scl(delta));
        if (getPosition().y >= getSize().height) {
            setScrolled(true);
        }
    }

    @Override
    public void reset(float x, float y) {
        getPosition().set(x, y);
        setScrolled(false);
    }
}
