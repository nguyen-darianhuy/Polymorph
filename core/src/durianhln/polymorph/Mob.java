package durianhln.polymorph;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.awt.Dimension;

/**
 * Represents an Entity that has a Shape and Color, and thus is related to gameplay.
 * @author Darian
 */
public abstract class Mob extends Entity {
    protected Shape shape;
    protected Color color;
    public Mob(Vector2 position, Vector2 velocity, Dimension size) {
        super(position, velocity, size, null);
        shape = RandomGenerator.getRandomShape();
        setTexture(shape.getTexture());
        color = RandomGenerator.getRandomColor();
    }

    @Override
    public void render(SpriteBatch batch) {
        Color originalColor = batch.getColor();
        batch.setColor(color);

        super.render(batch);

        batch.setColor(originalColor);
    }

    public Shape getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }
}
