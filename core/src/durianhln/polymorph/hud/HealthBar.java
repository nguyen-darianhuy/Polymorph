package durianhln.polymorph.hud;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import durianhln.polymorph.gameobject.Player;

/**
 * Represents a HUD health bar
 * @author Darian
 */
public class HealthBar extends Actor {
    private final Player player;

    private final Image barImage;
    private final Image healthImage;
    private final float MAX_HEALTH_HEIGHT;

    public HealthBar(Player player, Image barImage, Image healthImage) {
        setBounds(barImage.getX(), barImage.getY(), barImage.getWidth(), barImage.getHeight());
        this.player = player;
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
    public void act(float delta) {
        super.act(delta);
        healthImage.setHeight(MAX_HEALTH_HEIGHT*(player.getHitpoints()/100.0f));
    }

    @Override
    public void draw(Batch batch, float alpha) {
        barImage.draw(batch, 0.7f);
        healthImage.draw(batch, 0.8f);
    }
}
