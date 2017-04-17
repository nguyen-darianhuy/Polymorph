package durianhln.polymorph.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


/**
 * Represents the colored capsule button that a player drags a touch to in order to morph.
 * @author Darian
 */
public class ColorButton extends Image {
    private final Vector2 targetPosition;
    public ColorButton(TextureRegion texture, Color color, Vector2 targetPosition) {
        super(texture);
        setColor(color);
        reset();

        this.targetPosition = targetPosition;
    }

    public void moveFrom(ShapeButton shapeButton) {
        setVisible(true);
        setPosition(shapeButton.getCenterX(), shapeButton.getCenterY());

        final float ANIMATION_SPEED = 0.1f;
        addAction(Actions.parallel(Actions.moveTo(targetPosition.x, targetPosition.y, ANIMATION_SPEED),
                                   Actions.scaleTo(1.0f, 1.0f, ANIMATION_SPEED)));
    }

    public final void reset() {
        clearActions();
        setVisible(false);
        addAction(Actions.scaleTo(0, 0));
    }

    public boolean contains(Vector2 point) {
        //hitbox intentionally square for more lenient hit detection
        if (!isTouchable()) return false;
        return targetPosition.x <= point.x && point.x <= targetPosition.x+getWidth() &&
               targetPosition.y <= point.y && point.y <= targetPosition.y+getWidth(); //intentional
    }
}
