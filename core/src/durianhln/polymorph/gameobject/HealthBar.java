package durianhln.polymorph.gameobject;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Represents a HUD health bar
 * @author Darian
 */
public class HealthBar extends Actor {
    private Image barImage;
    private Image healthImage;

    private final float MAX_HEALTH_HEIGHT;

    public HealthBar(Image barImage, Image healthImage) {
        setBounds(barImage.getX(), barImage.getY(), barImage.getWidth(), barImage.getHeight());
        this.barImage = barImage;
        this.healthImage = healthImage;
        //magic numbers
        this.healthImage.setBounds(barImage.getX() + barImage.getWidth()*0.27f,
                                   barImage.getY() + barImage.getHeight()*0.033f,
                                   barImage.getWidth()*0.45f,
                                   barImage.getHeight()*0.93f);

        MAX_HEALTH_HEIGHT = healthImage.getHeight();
    }

    @Override
    public void draw(Batch batch, float alpha) {
        barImage.draw(batch, alpha);
        healthImage.draw(batch, alpha);
    }

    public void setValue(int value) {
        healthImage.setHeight(MAX_HEALTH_HEIGHT*(value/100.0f));
    }
}
