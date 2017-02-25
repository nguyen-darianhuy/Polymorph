package durianhln.polymorph.util;

import com.badlogic.gdx.graphics.Color;
import durianhln.polymorph.game.Shape;
import java.util.Random;

/**
 * Utility class for getting random calculations without needing a Random instance.
 * @author Darian
 */
public abstract class RandomGenerator {
    public static final Random rng = new Random();
    public static final Color[] colors = {new Color(1, 0.3f, 0, 1), //RED
                                          new Color(0, 1, 0.5f, 1), //GREEN
                                          new Color(0, 0.5f, 1, 1)};//BLUE
    public static final Color[] transparentColors = {colors[0].cpy().sub(0, 0, 0, 0.2f), //RED
                                                     colors[1].cpy().sub(0, 0, 0, 0.2f), //GREEN
                                                     colors[2].cpy().sub(0, 0, 0, 0.2f)};//BLUE

    public static Shape getRandomShape() {
        return Shape.values()[rng.nextInt(Shape.values().length)];
    }

    public static Color getRandomColor() {
        return colors[rng.nextInt(colors.length)];
    }

    public static Color getRandomTransparentColor() {
        return transparentColors[rng.nextInt(transparentColors.length)];
    }
}
