package durianhln.polymorph.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import durianhln.polymorph.game.Shape;
import durianhln.polymorph.gameobject.Mob;

/**
 * Represents a shaped button that a player touches and drags to a ColorButton in order to morph.
 * @author Darian
 */
public class ShapeButton extends Image {
    private boolean pressed;
    private final Vector2 touch;
    public ShapeButton(final Mob mob, final Shape shape, final ColorButton[] colorButtons) {
        super(new TextureRegionDrawable(shape.getTexture()));
        pressed = false;
        touch = new Vector2();

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                for (ColorButton colorButton : colorButtons) {
                    colorButton.moveFrom(ShapeButton.this);
                }
                return pressed = true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                touch.set(translateCoordinates(x, y));
                for (ColorButton colorButton : colorButtons) {
                    if (colorButton.contains(touch)) {
                        setColor(colorButton.getColor());
                        return;
                    }
                }
                setColor(Color.WHITE);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                touch.set(translateCoordinates(x, y));
                for (ColorButton colorButton : colorButtons) {
                    if (colorButton.contains(touch)) {
                        mob.morph(shape, colorButton.getColor());
                    }
                    colorButton.reset();
                }

                setColor(Color.WHITE);
                pressed = false;
            }
        });
    }

    private Vector2 translateCoordinates(float x, float y) {
        return this.localToStageCoordinates(touch.set(x, y));
    }

    public boolean isPressed() {
        return pressed;
    }

    public float getCenterX() {
        return getX() + getWidth()/2;
    }

    public float getCenterY() {
        return getY() + getHeight()/2;
    }
}
