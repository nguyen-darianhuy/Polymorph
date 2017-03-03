package durianhln.polymorph.game;

import com.badlogic.gdx.Gdx;
import durianhln.polymorph.gameobject.Updatable;
import durianhln.polymorph.gameobject.Slot;
import durianhln.polymorph.gameobject.Map;
import durianhln.polymorph.gameobject.Player;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import durianhln.polymorph.gameobject.Polymorph;
import durianhln.polymorph.gameobject.ShapeColor;
import java.awt.Dimension;

/**
 * Represents the core Polymorph game logic.
 * @author Darian
 */
public class Game implements Updatable {
    //game variables
    private float runtime;
    private State state;

    //assets
    private TextureAtlas textureAtlas;
    private Music backgroundMusic;

    //entities
    private Player player;
    private Array<Slot> slots;
    private Pool<Slot> slotPool;
    private Map[] maps;
    private Map mapFront;
    private Map mapBack;

    //entity variables
    private float slotSpawnTime;
    private Vector2 slotVelocity;
    private Vector2 mapVelocity;

    //entity constants
    private final Vector2 SLOT_SPAWN_POINT;
    private final float MIN_SLOT_SPAWN_TIME;
    private final float MAX_SLOT_VELOCITY;

    public Game(AssetManager assetManager) {
        initAssets(assetManager);
        initGameVariables();
        initEntities();

        //init entity constants
        Dimension screenSize = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        SLOT_SPAWN_POINT = new Vector2(screenSize.width/2 - player.getSize().width/2, -player.getSize().height);
        MIN_SLOT_SPAWN_TIME = 0.8f;
        MAX_SLOT_VELOCITY = 0.55f*screenSize.height;

        //init game fields
        runtime = 0;
        state = State.READY;
    }

    private void initAssets(AssetManager assetManager) {
        //init textures
        textureAtlas = assetManager.get(Polymorph.OBJECTS_PATH, TextureAtlas.class);
        for (TextureRegion texture : textureAtlas.getRegions()) {
            texture.flip(false, true); //flip y axis
        }
        for (Shape shape : Shape.values()) {
            shape.setTexture(textureAtlas.findRegion(shape.name));
        }
        for (ShapeColor shapeColor : ShapeColor.values()) {
            shapeColor.setTexture(textureAtlas.findRegion("capsule"));
        }

        //init sounds
        backgroundMusic = assetManager.get(Polymorph.MUSIC_PATH, Music.class);
        backgroundMusic.setLooping(true);
        Match.values()[0].setSound(assetManager.get(Polymorph.GOOD_PATH, Sound.class));
    	Match.values()[1].setSound(assetManager.get(Polymorph.HALF_PATH, Sound.class));
    	Match.values()[2].setSound(assetManager.get(Polymorph.BAD_PATH, Sound.class));
    }

    private void initGameVariables() {
        slotSpawnTime = 3.0f;
        slotVelocity = new Vector2(0, 100);
        mapVelocity = new Vector2(0, 200);
    }

    private void initEntities() {
        Dimension screenSize = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        final int mobWidth = screenSize.width/4;
        player = new Player(new Vector2(screenSize.width/2 - mobWidth/2, 2*screenSize.height/3),
                            new Dimension(mobWidth, mobWidth));

        slots = new Array<Slot>();
        slotPool = new Pool<Slot>() {
            @Override
            protected Slot newObject() {
                return new Slot(new Vector2(SLOT_SPAWN_POINT),
                                new Vector2(slotVelocity),
                                new Dimension(mobWidth, mobWidth));
            }
        };

        Dimension mapSize = new Dimension(screenSize.width, (int)(screenSize.height*1.1f));
        TextureRegion mapTexture = textureAtlas.findRegion("background");
        mapFront = new Map(new Vector2(0, 0), mapVelocity, mapSize, mapTexture);
        mapBack = new Map(new Vector2(0, -mapSize.height + 5), mapVelocity, mapSize, mapTexture);
        maps = new Map[]{mapFront, mapBack};
    }

    @Override
    public void update(float delta) {
        runtime += delta;

        if (player.isDead()) {
            stop();//TODO: change this shit
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
            slot.init(SLOT_SPAWN_POINT, slotVelocity);
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
                int scoreDelta = (int)(player.getMultiplier()*(slot.getVelocity().y*match.multiplier));

                match.getSound().play();
                player.setScore(player.getScore() + scoreDelta);

                slots.removeIndex(i);
                slotPool.free(slot);
            }
        }
    }

    public void render(SpriteBatch batch) {
        batch.disableBlending();
        //draw opaques
        mapFront.render(batch);
        mapBack.render(batch);

        batch.enableBlending();
        //draw transparents
        player.render(batch);
        for (Slot slot : slots) {
            slot.render(batch);
        }
    }

    public void start() {
        state = State.RUNNING;
        backgroundMusic.play();
    }

    public void stop() {
        state = State.STOPPED;
        backgroundMusic.stop();
    }

    public Player getPlayer() { //TODO: temporary
        return player;
    }

    public State getState() {
        return state;
    }
}
