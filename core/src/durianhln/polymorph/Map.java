package durianhln.polymorph;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import java.awt.Dimension;

/**
 *
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
        getPosition().x = x;
        getPosition().y = y;
        setScrolled(false);
    }
}
