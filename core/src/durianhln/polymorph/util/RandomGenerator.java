package durianhln.polymorph.util;

import com.badlogic.gdx.graphics.Color;
import durianhln.polymorph.game.Shape;
import durianhln.polymorph.gameobject.ShapeColor;

import java.util.Random;

/**
 * Utility class for getting random calculations without needing a Random instance.
 * @author Darian
 */
public abstract class RandomGenerator {
    private static Shape[] shapes = Shape.values();
    private static ShapeColor[] shapeColors = ShapeColor.values();
    public static final Random rng = new Random();

    public static Shape getRandomShape() {
        return shapes[rng.nextInt(Shape.values().length)];
    }

    public static Color getRandomColor() {
        return shapeColors[rng.nextInt(ShapeColor.values().length)].color;
    }
}
