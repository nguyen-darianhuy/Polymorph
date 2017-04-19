package durianhln.polymorph.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import durianhln.polymorph.game.PolyGame;
import durianhln.polymorph.gameobject.Player;
import durianhln.polymorph.game.Shape;
import durianhln.polymorph.gameobject.ShapeColor;
import durianhln.polymorph.hud.HealthBar;
import durianhln.polymorph.hud.ColorButton;
import durianhln.polymorph.Polymorph;
import durianhln.polymorph.game.Match;
import durianhln.polymorph.hud.ShapeButton;

/**
 *
 * @author Darian
 */
public class GameScreen implements Screen {
    // screen variables
    private Polymorph polymorph;
    private FPSLogger fps; // TODO remove this

    // screen properties
    private OrthographicCamera camera;
    private Viewport viewport;

    // utils
    private SpriteBatch batch;
    private BitmapFont font;

    // game variables
    private PolyGame polyGame;
    private Music gameMusic;
    private Stage hud;

    public GameScreen(Polymorph polymorph) {
        this.polymorph = polymorph;

        AssetManager assetManager = polymorph.getAssetManager();
        TextureAtlas textureAtlas = assetManager.get(Polymorph.MASTER_PATH, TextureAtlas.class);
        initAssets(assetManager);
        this.polyGame = new PolyGame(textureAtlas);

        camera = new OrthographicCamera();
        viewport = new StretchViewport(Polymorph.WORLD_WIDTH, Polymorph.WORLD_HEIGHT, camera);
        viewport.apply(true);
        camera.update();

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("timeburnerbold.ttf"));
        FreeTypeFontParameter fontSettings = new FreeTypeFontParameter();
        fontSettings.size = 80;
        fontSettings.minFilter = TextureFilter.Linear;
        fontSettings.magFilter = TextureFilter.Linear;
        font = fontGenerator.generateFont(fontSettings);

        initHud(textureAtlas);
        Gdx.input.setInputProcessor(new InputMultiplexer(new KeyboardInputHandler(), hud));

        fps = new FPSLogger();
    }

    private void initAssets(AssetManager assetManager) {
        // init audio
        gameMusic = assetManager.get(Polymorph.MUSIC_PATH, Music.class);
        gameMusic.setLooping(true);
    }

    private void initHud(TextureAtlas textureAtlas) {
        hud = new Stage(viewport, batch);

        //init widgets
        HealthBar playerHealthBar = createHealthBar(textureAtlas);
        ColorButton[] colorButtons = createColorButtons();
        final ShapeButton[] shapeButtons = createShapeButtons(colorButtons);
        Image pauseButton = createPauseButton(textureAtlas);
        // add widgets to stage
        for (ShapeButton shapeButton : shapeButtons) {
            hud.addActor(shapeButton);
        }
        for (ColorButton colorButton : colorButtons) {
            hud.addActor(colorButton);
        }
        hud.addActor(playerHealthBar);

        // init pause button

        hud.addActor(pauseButton);
    }

    private HealthBar createHealthBar(TextureAtlas textureAtlas) {
        Image barImage = new Image(textureAtlas.findRegion("hpbar-empty"));
        barImage.setBounds(Polymorph.WORLD_WIDTH/35, Polymorph.WORLD_HEIGHT/5.3f,
                Polymorph.WORLD_WIDTH/6, 3*Polymorph.WORLD_HEIGHT/4);
        Image healthImage = new Image(textureAtlas.findRegion("hpbar-full"));

        return new HealthBar(polyGame.getPlayer(), barImage, healthImage);
    }

    private ColorButton[] createColorButtons() {
        final ShapeColor[] shapeColors = ShapeColor.values();

        ColorButton[] colorButtons = new ColorButton[shapeColors.length];
        for (int i = 0; i < colorButtons.length; i++) {
            colorButtons[i] = new ColorButton(shapeColors[i].getTexture(), shapeColors[i].color,
                    new Vector2(i*Polymorph.WORLD_WIDTH/3 + Polymorph.WORLD_WIDTH/15, Polymorph.WORLD_HEIGHT/6.5f));
            colorButtons[i].setSize(Polymorph.WORLD_WIDTH/5, Polymorph.WORLD_HEIGHT/32);
        }

        return colorButtons;
    }

    private ShapeButton[] createShapeButtons(ColorButton[] colorButtons) {
        final Shape[] shapes = Shape.values();

        ShapeButton[] shapeButtons = new ShapeButton[shapes.length];
        for (int i = 0; i < shapeButtons.length; i++) {
            shapeButtons[i] = new ShapeButton(polyGame.getPlayer(), shapes[i], colorButtons);
            shapeButtons[i].setBounds(i*Polymorph.WORLD_WIDTH/3 + Polymorph.WORLD_WIDTH/15, Polymorph.WORLD_HEIGHT/50,
                                      Polymorph.WORLD_WIDTH/5, Polymorph.WORLD_WIDTH/5);
        }
        return shapeButtons;
    }

    private Image createPauseButton(TextureAtlas textureAtlas) {
        final Image pauseButton = new Image(textureAtlas.findRegion("pausebutton"));
        float width = Polymorph.WORLD_WIDTH/10;
        pauseButton.setBounds(Polymorph.WORLD_WIDTH - width*1.2f, Polymorph.WORLD_HEIGHT - width*1.2f,
                              width, width);
        pauseButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                switch (polyGame.getState()) {
                    case RUNNING:
                        polyGame.pause();
                        gameMusic.stop();
                        for (Actor actor : hud.getActors()) {
                            if (actor != pauseButton) {
                                actor.setTouchable(Touchable.disabled);
                            }
                        }
                        break;
                    case PAUSED:
                        polyGame.start();
                        gameMusic.play();
                        for (Actor actor : hud.getActors()) {
                            actor.setTouchable(Touchable.enabled);
                        }
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
        return pauseButton;
    }

    @Override
    public void render(float delta) {
        delta = Math.min(delta, 0.03f);

        Player player = polyGame.getPlayer(); // TODO temporary
        Preferences preferences = polymorph.getPreferences();

        switch (polyGame.getState()) {
        case READY: // TODO: Change this shit
            System.out.println("HERE WE GO");
            polyGame.start();
            gameMusic.play();
            break;
        case RUNNING:
            Match match = polyGame.update(delta);
            if (match != null) { // TODO Optimize this a bit more
                match.getSound().play(preferences.getFloat(Polymorph.SOUND_VOLUME));
            }
            break;
        case STOPPED: // TODO: gracefully save and end the game
            preferences.putInteger(Polymorph.HIGH_SCORE, player.getScore());
            preferences.flush();
            gameMusic.stop();
            polymorph.setScreen(new DeathScreen(polymorph, player.getScore()));
            break;
        }

        batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        polyGame.render(batch);

        String multiplier = player.getMultiplier() > 1.0f ? String.format(" x %.2f", player.getMultiplier()) : "";
        int alignment = player.getScore() > 1000 ? -1 : 1;
        font.draw(batch, player.getScore() + multiplier,
                 Polymorph.WORLD_WIDTH/35, Polymorph.WORLD_HEIGHT*0.98f,
                 Polymorph.WORLD_WIDTH/6, alignment, false);

        batch.end();

        fps.log();
        hud.act(delta);
        hud.draw();
    }

    @Override
    public void show() {
        gameMusic.setVolume(polymorph.getPreferences().getFloat(Polymorph.MUSIC_VOLUME));
        gameMusic.play();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        camera.update();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    private class KeyboardInputHandler implements InputProcessor { // touch coordinates are y-down!!!
        private Shape shapeHeld;

        @Override
        public boolean keyDown(int keycode) {
            switch (keycode) {
            case Input.Keys.NUMPAD_4:
                shapeHeld = Shape.TRIANGLE;
                break;
            case Input.Keys.NUMPAD_5:
                shapeHeld = Shape.CIRCLE;
                break;
            case Input.Keys.NUMPAD_6:
                shapeHeld = Shape.SQUARE;
                break;
            default:
                return false;
            }
            return true;
        }

        @Override
        public boolean keyUp(int keycode) {
            if (shapeHeld == null) {
                return false;
            }

            switch (keycode) {
            case Input.Keys.NUMPAD_7:
                polyGame.getPlayer().morph(shapeHeld, ShapeColor.values()[0].color);
                break;
            case Input.Keys.NUMPAD_8:
                polyGame.getPlayer().morph(shapeHeld, ShapeColor.values()[1].color);
                break;
            case Input.Keys.NUMPAD_9:
                polyGame.getPlayer().morph(shapeHeld, ShapeColor.values()[2].color);
                break;
            }
            return true;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int x, int y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchUp(int x, int y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int x, int y, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int x, int y) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }
}
