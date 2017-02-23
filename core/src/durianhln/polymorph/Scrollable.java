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

    public abstract void reset(float x, float y);

    public boolean isScrolled() {
        return isScrolled;
    }

    public void setScrolled(boolean isScrolled) {
        this.isScrolled = isScrolled;
    }
}
