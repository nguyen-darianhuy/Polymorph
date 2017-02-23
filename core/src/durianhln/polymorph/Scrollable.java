package durianhln.polymorph;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import java.awt.Dimension;

/**
 *
 * @author Darian
 */
public abstract class Scrollable extends Entity {
    private boolean isScrolled;
    public Scrollable(Vector2 position, Vector2 velocity, Dimension size, TextureRegion texture) {
        super(position, velocity, size, texture);
        isScrolled = false;
    }

    @Override
    public void update(float delta) {
        getPosition().add(getVelocity().cpy().scl(delta));
        if (getPosition().y + getSize().height <= 0) {
            isScrolled = true;

        }
    }

    public abstract void reset(Vector2 position);

    public boolean isScrolled() {
        return isScrolled;
    }

    public void setScrolled(boolean isScrolled) {
        this.isScrolled = isScrolled;
    }
}
