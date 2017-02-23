package durianhln.polymorph;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.awt.Dimension;

/**
 *
 * @author Darian
 */
public class Player extends Entity {
    private double hitpoints;
    private int score;

    private Shape currentShape;
    private Color color;

    public Player(Vector2 position, Dimension size) {
        super(position, new Vector2(0, 0), size, null);
        currentShape = Shape.getRandomShape();
        setTexture(currentShape.getTexture());
        color = Color.RED; //should change to random color

        hitpoints = 0.5;
        score = 0;
    }

    @Override
    public void render(SpriteBatch batch) {
        Color originalColor = batch.getColor();
        batch.setColor(color);

        super.render(batch);

        batch.setColor(originalColor);
    }
    @Override
    public void update(float delta) {

    }
}
