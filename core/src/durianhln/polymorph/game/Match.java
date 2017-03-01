package durianhln.polymorph.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Represents the type of match a slot and a player can make, and its "quality."
 * @author Darian
 */
public enum Match {
    GOOD(5, 1.0f),
    HALF(-10, 0.5f),
    BAD(-20, 0.0f);

    public final int value;
    public final float multiplier;
    private Sound sound;
    Match(int value, float multiplier) {
        this.value = value;
        this.multiplier = multiplier;
        this.sound = null;
    }

    public Sound getSound() {
        if (sound == null) {
            throw new NullPointerException("Sound not loaded.");
        }
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }
}
