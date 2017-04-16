package durianhln.polymorph.gameobject;

import durianhln.polymorph.game.Shape;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import durianhln.polymorph.util.Dimension;
import durianhln.polymorph.util.RandomGenerator;


/**
 * Represents an Entity that has a Shape and Color.
 * @author Darian
 */
public abstract class Mob extends Entity {
    protected Shape shape;
    protected Color color;
    public Mob(Vector2 position, Vector2 velocity, Dimension size) {
        super(position, velocity, size);
        shape = RandomGenerator.getRandomShape();
        color = RandomGenerator.getRandomColor();
    }

    public void morph(Shape shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    public void render(Batch batch) {
        Color originalColor = batch.getColor();
        batch.setColor(color);

        batch.draw(shape.getTexture(), getPosition().x, getPosition().y, getSize().width, getSize().height);

        batch.setColor(originalColor);
    }

    public Shape getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }
}
