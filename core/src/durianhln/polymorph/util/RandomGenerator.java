package durianhln.polymorph.util;

import com.badlogic.gdx.graphics.Color;
import durianhln.polymorph.game.Shape;
import durianhln.polymorph.game.ShapeColor;

import java.util.Random;

/**
 * Utility class for getting random calculations without needing a Random instance.
 * @author Darian
 */
public abstract class RandomGenerator {
    public static final Random rng = new Random();


    public static Shape getRandomShape() {
        return Shape.values()[rng.nextInt(Shape.values().length)];
    }

    public static Color getRandomColor() {
        return ShapeColor.values()[rng.nextInt(ShapeColor.values().length)].color;
    }
}
