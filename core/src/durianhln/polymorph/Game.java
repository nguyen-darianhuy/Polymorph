package durianhln.polymorph;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import java.awt.Dimension;

/**
 *
 * @author Darian
 */
public class Game implements Updatable {
    private Player player;
    private Array<Slot> slots;
    private Pool<Slot> slotPool;

    private Map[] maps;
    private Map mapFront;
    private Map mapBack;

    public Game(final Dimension screenSize, AssetManager assetManager) {
        TextureAtlas textureAtlas = assetManager.get(Polymorph.OBJECTS_PATH, TextureAtlas.class);
        initTextures(textureAtlas);

        final int playerWidth = screenSize.width/4;
        player = new Player(new Vector2(screenSize.width/2 - playerWidth/2, 2*screenSize.height/3),
                            new Dimension(playerWidth, playerWidth));

        slots = new Array<Slot>();
        slotPool = new Pool<Slot>() {
            @Override
            protected Slot newObject() {
                return new Slot(new Vector2(screenSize.width/2 - playerWidth/2, 2*screenSize.height/3),
                                new Vector2(0, 50),
                                new Dimension(playerWidth, playerWidth));
            }
        };

        Vector2 mapVelocity = new Vector2(0, 200);
        mapFront = new Map(new Vector2(0, 0), mapVelocity, screenSize, textureAtlas.findRegion("background"));
        mapBack = new Map(new Vector2(0, -screenSize.height), mapVelocity, screenSize, textureAtlas.findRegion("background"));
        maps = new Map[]{mapFront, mapBack};
    }

    private void initTextures(TextureAtlas textureAtlas) {
        for (TextureRegion texture : textureAtlas.getRegions()) {
            texture.flip(false, true); //flip y axis
        }
        for (Shape shape : Shape.values()) {
            shape.setTexture(textureAtlas.findRegion(shape.name));
        }
    }

    @Override
    public void update(float delta) {
        player.update(delta);
        for (Map map : maps) {
            map.update(delta);
        }

        for (Slot slot : slots) {

        }

        for (Map map : maps) {
            if (map.isScrolled()) {
                map.reset(0, -map.getSize().height);
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
