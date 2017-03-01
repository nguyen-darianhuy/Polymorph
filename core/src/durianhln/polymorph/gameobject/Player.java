package durianhln.polymorph.gameobject;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import durianhln.polymorph.game.Match;
import java.awt.Dimension;

/**
 * Represents a Player-controlled Mob that can shape-shift.
 * @author Darian
 */
public class Player extends Mob {
    private int hitpoints;

    private int score;
    private float multiplier;

    public Player(Vector2 position, Dimension size) {
        super(position, new Vector2(0, 0), size);

        hitpoints = 50;

        score = 0;
        multiplier = 1;
    }

    @Override
    public void update(float delta) {

    }

    public Match match(Slot slot) {
        if (slot.getPosition().y < this.getPosition().y) {
            throw new IllegalArgumentException("Slot and Player are not within range!");
        }

        Match match;
        if (slot.getShape() == this.getShape() && slot.getColor() == this.getColor()) {
            match = Match.GOOD;
        } else if (slot.getShape() == this.getShape()) {
            match = Match.HALF;
        } else {
            match = Match.BAD;
            multiplier = 1;
        }

        if (hitpoints + match.value > 100) {
            if (multiplier <= 2.0f) {
                multiplier += 0.05f;
            }
        } else {
            hitpoints += match.value;
        }
        return match;
    }

    public boolean isDead() {
        return hitpoints <= 0;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public float getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }
}
