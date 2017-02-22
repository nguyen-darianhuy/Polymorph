package durianhln.polymorph;

import com.badlogic.gdx.math.Vector2;
import java.awt.Dimension;

/**
 *
 * @author Darian
 */
public class Game implements Updatable {
    private Player player;
    private Map mapFront;
    private Map mapBack;

    public Game(Dimension screenSize) {
        player = new Player(new Vector2(1*screenSize.width/3, screenSize.height/6),
                            new Dimension(screenSize.width/3, screenSize.height/6));
        Vector2 mapVelocity = new Vector2(0, -100);
        mapFront = new Map(new Vector2(0, 0), mapVelocity, screenSize);
        mapBack = new Map(new Vector2(0, screenSize.height), mapVelocity, screenSize);
    }

    @Override
    public void update(float delta) {
        player.update(delta);
        mapFront.update(delta);
        mapBack.update(delta);

        if (mapFront.isScrolled()) {
            mapFront.reset(new Vector2(0, mapFront.getSize().height - 1)); //TODO: change to avoid continuous GC!
        } else if (mapBack.isScrolled()) {
            mapBack.reset(new Vector2(0, mapBack.getSize().height - 1));
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Map getMapFront() {
        return mapFront;
    }

    public Map getMapBack() {
        return mapBack;
    }
}
