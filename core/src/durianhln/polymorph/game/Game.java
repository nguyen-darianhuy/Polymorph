package durianhln.polymorph.game;

import durianhln.polymorph.gameobject.Updatable;
import durianhln.polymorph.gameobject.Slot;
import durianhln.polymorph.gameobject.Polymorph;
import durianhln.polymorph.gameobject.Map;
import durianhln.polymorph.gameobject.Player;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import java.awt.Dimension;

/**
 * Represents the core Polymorph game logic.
 * @author Darian
 */
public class Game implements Updatable {
    public static final Color[] colors = {new Color(1, 0.3f, 0, 1), //RED
                                          new Color(0, 1, 0.5f, 1), //GREEN
                                          new Color(0, 0.5f, 1, 1)};//BLUE
    public static final Color[] transparentColors = {colors[0].cpy().sub(0, 0, 0, 0.2f), //RED
                                                     colors[1].cpy().sub(0, 0, 0, 0.2f), //GREEN
                                                     colors[2].cpy().sub(0, 0, 0, 0.2f)};//BLUE

    private Player player;
    private Array<Slot> slots;
    private Pool<Slot> slotPool;

    private Map[] maps;
    private Map mapFront;
    private Map mapBack;

    private float runtime;
    private State state;

    private float slotSpawnTime;
    private final Vector2 slotSpawn;
    private final Vector2 slotVelocity;

    private final Vector2 mapVelocity;

    private final float MIN_SLOT_SPAWN_TIME;
    private final float MAX_SLOT_VELOCITY;

    public Game(final Dimension screenSize, AssetManager assetManager) {
        TextureAtlas textureAtlas = assetManager.get(Polymorph.OBJECTS_PATH, TextureAtlas.class);
        initTextures(textureAtlas);
        MIN_SLOT_SPAWN_TIME = 0.8f;
        MAX_SLOT_VELOCITY = 350;

        final int mobWidth = screenSize.width/4;
        player = new Player(new Vector2(screenSize.width/2 - mobWidth/2, 2*screenSize.height/3),
                            new Dimension(mobWidth, mobWidth));

        slotSpawnTime = 3.0f;
        slotSpawn = new Vector2(screenSize.width/2 - mobWidth/2, -mobWidth);
        slotVelocity = new Vector2(0, 100);

        slots = new Array<Slot>();
        slotPool = new Pool<Slot>() {
            @Override
            protected Slot newObject() {
                return new Slot(new Vector2(slotSpawn),
                                new Vector2(slotVelocity),
                                new Dimension(mobWidth, mobWidth));
            }
        };

        Dimension mapSize = new Dimension(screenSize.width, (int)(screenSize.height*1.1f));
        TextureRegion mapTexture = textureAtlas.findRegion("background");
        mapVelocity = new Vector2(0, 200);
        mapFront = new Map(new Vector2(0, 0), mapVelocity, mapSize, mapTexture);
        mapBack = new Map(new Vector2(0, -mapSize.height + 5), mapVelocity, mapSize, mapTexture);
        maps = new Map[]{mapFront, mapBack};


        runtime = 0;
        state = State.READY;
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
        runtime += delta;

        if (player.isDead()) {
            //TODO: change this shit
        }
        //update all entities
        player.update(delta);
        for (Slot slot : slots) {
            slot.update(delta);
        }
        for (Map map : maps) {
            map.update(delta);
        }

        //spawn slot
        if (runtime > slotSpawnTime) {
            Slot slot = slotPool.obtain();
            slot.init(slotSpawn, slotVelocity);
            slots.add(slot);

            if (slotSpawnTime > MIN_SLOT_SPAWN_TIME) {
                slotSpawnTime -= 0.1f;
            }
            if (slotVelocity.y < MAX_SLOT_VELOCITY) {
                slotVelocity.y += 20;
            }
            runtime = 0;
        }
        //collision detection
        for (int i = slots.size; --i >= 0;) { //safe concurrent modification
            Slot slot = slots.get(i);
            if (slot.getPosition().y >= player.getPosition().y) {
                Match match = player.match(slot);

                player.setHitpoints(player.getHitpoints() + match.value);
                int scoreDelta = (int)(player.getMultiplier()*(slot.getVelocity().y*match.multiplier));
                player.setScore(player.getScore() + scoreDelta);

                slots.removeIndex(i);
                slotPool.free(slot);
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Array<Slot> getSlots() {
        return slots;
    }

    public Map getMapFront() {
        return mapFront;
    }

    public Map getMapBack() {
        return mapBack;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
