package durianhln.polymorph;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Darian
 */
public class Player extends Entity {
    private Shape shape;
    private Color color;
    private double hitpoints;
    private int score;
    public Player(Vector2 position) {
        super(position);
        shape = Shape.getRandomShape(); //probably should change to random shape
        color = Color.RED;
        hitpoints = 0.5;
        score = 0;
    }

    @Override
    public void update(float delta) {

    }
}
