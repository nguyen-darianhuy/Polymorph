package durianhln.polymorph.hud;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import durianhln.polymorph.game.Shape;
import durianhln.polymorph.gameobject.Mob;

/**
 * Represents a shaped button that a player touches and drags to a ColorButton in order to morph.
 * @author Darian
 */
public class ShapeButton extends Button {
    public ShapeButton(final Mob mob, final Shape shape, final ColorButton[] colorButtons) {
        super(new TextureRegionDrawable(shape.getTexture()));

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                for (ColorButton colorButton : colorButtons) {
                    colorButton.moveFrom(ShapeButton.this);
                }
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Vector2 touch = (ShapeButton.this).localToStageCoordinates(new Vector2(x, y));
                for (ColorButton colorButton : colorButtons) {
                    if (colorButton.contains(touch)) {
                        mob.morph(shape, colorButton.getColor());
                        break;
                    }
                }
                for (ColorButton colorButton : colorButtons) {
                    colorButton.reset();
                }
            }
        });
    }
}
