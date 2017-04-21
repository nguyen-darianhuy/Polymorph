package durianhln.polymorph.gameobject;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Represents a shape's color and transparency.
 * @author Evan
 */
public enum ShapeColor {
    RED(new Color(1, 0.2f, 0.6f, 0.9f)),
    GREEN(new Color(0, 1, 0, 0.9f)),
    BLUE(new Color(0, 1, 1, 0.9f));

    public final Color color;
    private TextureRegion texture;
    ShapeColor(Color color) {
        this.color = color;
        this.texture = null;
    }

    public TextureRegion getTexture() {
        if (texture == null) {
            throw new NullPointerException("Texture not loaded.");
        }
        return texture;
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }
}
