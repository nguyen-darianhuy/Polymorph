package durianhln.polymorph.util;

/**
 * Encapsulates an object's width and height into immutable dimensions.
 * @author Evan
 */
public class Dimension {
    public final float width;
    public final float height;

    public Dimension(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return String.format("W: %.2f, H: %.2f\n", width, height);
    }
}
