package durianhln.polymorph;

/**
 * Represents the type of match a slot and a player can make, and its "quality".
 * @author Darian
 */
public enum Match {
    GOOD(5, 1.0f),
    HALF(-10, 0.5f),
    BAD(-20, 0.0f);

    public final int value;
    public final float multiplier;
    Match(int value, float multiplier) {
        this.value = value;
        this.multiplier = multiplier;
    }
}
