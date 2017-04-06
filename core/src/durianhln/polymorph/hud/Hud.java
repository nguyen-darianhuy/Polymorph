package durianhln.polymorph.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 *
 * @author Darian
 */
public class Hud extends Stage {
    private final ShapeRenderer shapeRenderer;

    private final ShapeButton[] shapeButtons;
    private final ColorButton[] colorButtons;

    public Hud(ShapeButton[] shapeButtons, ColorButton[] colorButtons) {
        this.shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(getCamera().combined);

        this.shapeButtons = shapeButtons;
        this.colorButtons = colorButtons;

        for (ShapeButton shapeButton : shapeButtons) {
            addActor(shapeButton);
        }
        for (ColorButton colorButton : colorButtons) {
            addActor(colorButton);
        }
    }

    public Hud(ColorButton[] colorButtons, ShapeButton[] shapeButtons) {
        this(shapeButtons, colorButtons);
    }

    @Override
    public void draw() {
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.YELLOW);
        for (ShapeButton shapeButton : shapeButtons) {
            if (shapeButton.isHeld()) {
                for (ColorButton colorButton : colorButtons) {
                    shapeRenderer.line(shapeButton.getCenterX(), shapeButton.getCenterY(),
                                       colorButton.getCenterX(), colorButton.getCenterY());
                }
            }
        }
        shapeRenderer.end();
        super.draw();
    }
}
