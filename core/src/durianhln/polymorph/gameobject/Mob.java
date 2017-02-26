package durianhln.polymorph.gameobject;

import durianhln.polymorph.game.Shape;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import durianhln.polymorph.util.RandomGenerator;
import java.awt.Dimension;

/**
 * Represents an Entity that has a Shape and Color.
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

    public void morph(Shape shape, Color color) {
        this.shape = shape;
        setTexture(shape.getTexture());
        this.color = color;
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
