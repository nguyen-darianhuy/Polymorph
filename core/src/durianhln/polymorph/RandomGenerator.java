package durianhln.polymorph;

import com.badlogic.gdx.graphics.Color;
import java.util.Random;

/**
 * Utility class for getting random calculations without needing a Random instance.
 * @author Darian
 */
public abstract class RandomGenerator {
    public static Random rng = new Random();
    public static Shape getRandomShape() {
        return Shape.values()[rng.nextInt(Shape.values().length)];
    }

    public static Color getRandomColor() {
        int randomNum = rng.nextInt(3);
        Color color;
        switch (randomNum) {
            case 0:
                color = Color.RED;
                break;
            case 1:
                color = Color.BLUE;
                break;
            case 2:
                color = Color.GREEN;
                break;
            default:
                throw new IllegalStateException("Nonexisting Color generated");
        }
        return color;
    }
}
