package durianhln.polymorph;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;
import java.awt.Dimension;

/**
 *
 * @author Darian
 */
public class Slot extends Entity implements Poolable {
    public Slot(Vector2 position, Vector2 velocity, Dimension size) {
        super(position, velocity, size, null);
    }

    @Override
    public void update(float delta) {
        getPosition().add(getVelocity().cpy().scl(delta));

    }

    @Override
    public void reset() {

    }
}
