package durianhln.polymorph.gameobject;

import com.badlogic.gdx.math.Vector2;
import durianhln.polymorph.game.Match;
import durianhln.polymorph.util.Dimension;


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
        //animate?
    }

    public Match match(Slot slot) {
        if (slot.getPosition().y > this.getPosition().y) {
            throw new IllegalArgumentException("Slot and Player are not within range!");
        }

        Match match;
        if (slot.getShape() == this.getShape() && slot.getColor().equals(this.getColor())) {
            match = Match.GOOD;
        } else if (slot.getShape() == this.getShape()) {
            match = Match.HALF;
        } else {
            match = Match.BAD;
            multiplier = 1;
        }

        //update hp
        if (hitpoints + match.value > 100) {
            if (multiplier <= 2.0f) {
                multiplier += 0.05f;
            }
        } else if (hitpoints + match.value < 0) {
            hitpoints = 0;
        } else {
            hitpoints += match.value;
        }

        //update score
        //TODO: rework this calculation
        score += multiplier*(-slot.getVelocity().y*match.multiplier);

        return match;
    }

    public boolean isDead() {
        return hitpoints <= 0;
    }

    public int getScore() {
        return score;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public float getMultiplier() {
        return multiplier;
    }
}
