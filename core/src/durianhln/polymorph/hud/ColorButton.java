package durianhln.polymorph.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


/**
 *
 * @author Darian
 */
public class ColorButton extends Image {
    private final Vector2 targetPosition;
    public ColorButton(TextureRegion texture, Color color, Vector2 targetPosition) {
        super(texture);
        setColor(color);
        setPosition(0, -getHeight());

        this.targetPosition = targetPosition;
    }

    public void moveFrom(Button button) {
        setPosition(button.getX(), button.getY());
        addAction(Actions.moveTo(targetPosition.x, targetPosition.y, 0.1f));
    }

    public void reset() {
        clearActions();
        setPosition(0, -getHeight());
    }

    public boolean contains(Vector2 point) {
        //height intentionally larger for more lenient hit detection
        return getX() <= point.x && point.x <= getX()+getWidth() &&
               getY() <= point.y && point.y <= getY()+getWidth(); //intentional
    }
}
