package durianhln.polymorph;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import java.awt.Dimension;
import java.util.List;

/**
 *
 * @author Darian
 */
public class Game implements Updatable {
    private Player player;
    private Map[] maps;
    private Map mapFront;
    private Map mapBack;

    public Game(Dimension screenSize, TextureAtlas textureAtlas) {
        for (Shape shape : Shape.values()) {
            shape.setTexture(textureAtlas.findRegion(shape.name));
        }

        player = new Player(new Vector2(1*screenSize.width/3, screenSize.height/6),
                            new Dimension(screenSize.width/4, screenSize.width/6));

        Vector2 mapVelocity = new Vector2(0, -200);
        mapFront = new Map(new Vector2(0, 0), mapVelocity, screenSize, textureAtlas.findRegion("background"));
        mapBack = new Map(new Vector2(0, screenSize.height - 1), mapVelocity, screenSize, textureAtlas.findRegion("background"));
        maps = new Map[]{mapFront, mapBack};
    }

    @Override
    public void update(float delta) {
        player.update(delta);
        for (Map map : maps) {
            map.update(delta);
        }

        final int OVERLAP_OFFSET = 5;
        for (Map map : maps) {
            if (map.isScrolled()) {
                map.reset(new Vector2(0, map.getSize().height - OVERLAP_OFFSET));
            }
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
