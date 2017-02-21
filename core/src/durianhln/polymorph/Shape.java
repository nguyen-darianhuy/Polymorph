package durianhln.polymorph;

import java.util.Random;

/**
 *
 * @author Darian
 */
public enum Shape {
    SQUARE,
    TRIANGLE,
    CIRCLE;

    public static Shape getRandomShape() {
        Random rng = new Random();
        return Shape.values()[rng.nextInt(Shape.values().length)];
    }
}
