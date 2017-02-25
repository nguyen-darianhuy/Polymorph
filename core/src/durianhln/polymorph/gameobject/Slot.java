package durianhln.polymorph.gameobject;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;
import durianhln.polymorph.util.RandomGenerator;
import java.awt.Dimension;

/**
 * Represents a moving Mob such that the Player must morph to accommodate it.
 * @author Darian
 */
public class Slot extends Mob implements Poolable {
    public Slot(Vector2 position, Vector2 velocity, Dimension size) {
        super(position, velocity, size);
    }

    public void init(Vector2 position, Vector2 velocity) {
        getPosition().set(position);
        getVelocity().set(velocity);

        shape = RandomGenerator.getRandomShape();
        setTexture(shape.getTexture());
        color = RandomGenerator.getRandomTransparentColor();
    }

    @Override
    public void reset() {
        getPosition().set(0, 0); //change to out of bounds
        getVelocity().set(0, 0);
    }

    @Override
    public void update(float delta) {
        getPosition().add(getVelocity().cpy().scl(delta));
    }
}
