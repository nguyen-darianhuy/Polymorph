package durianhln.polymorph.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Represents a Shape with a Texture.
 * @author Darian
 */
public enum Shape {
    TRIANGLE("triangle"),
    CIRCLE("circle"),
    SQUARE("square");

    public final String name;
    private TextureRegion texture;
    Shape(String name) {
        this.name = name;
        this.texture = null;
    }

    public TextureRegion getTexture() {
        if (texture == null) {
            throw new NullPointerException("Texture not loaded.");
        }
        return texture;
    }

    public void setTexture(TextureRegion texture) {
        if (this.texture != null) {
            throw new IllegalStateException("Texture already loaded!");
        }
        this.texture = texture;
    }
}
