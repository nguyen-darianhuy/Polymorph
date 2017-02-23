package durianhln.polymorph;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Random;

/**
 *
 * @author Darian
 */
public enum Shape {
    SQUARE("square"),
    TRIANGLE("triangle"),
    CIRCLE("circle");

    public final String name;
    private TextureRegion texture;
    Shape(String name) {
        this.name = name;
        this.texture = null;
    }

    public static Shape getRandomShape() {
        Random rng = new Random();
        return Shape.values()[rng.nextInt(Shape.values().length)];
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
