package durianhln.polymorph;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import java.awt.Dimension;

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
        super(position, new Vector2(0, 0), new Dimension(0, 0));
        shape = Shape.getRandomShape(); //probably should change to random shape
        color = Color.RED; //should change to random color
        hitpoints = 0.5;
        score = 0;
    }

    @Override
    public void update(float delta) {

    }
}
