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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import durianhln.polymorph.game.PolyGame;
import durianhln.polymorph.gameobject.Player;
import durianhln.polymorph.game.Shape;
import durianhln.polymorph.gameobject.ShapeColor;
import durianhln.polymorph.game.State;
import durianhln.polymorph.hud.HealthBar;
import durianhln.polymorph.hud.ColorButton;
import durianhln.polymorph.Polymorph;
import durianhln.polymorph.game.Match;
import durianhln.polymorph.hud.ShapeButton;
import durianhln.polymorph.util.Dimension;

/**
 *
 * @author Darian
 */
public class GameScreen implements Screen {
    //screen variables
    private Polymorph polymorph;
    private Dimension screenSize; //TODO remove this
    private FPSLogger fps; //TODO remove this

    //screen properties
    private OrthographicCamera camera;

    //utils
    private SpriteBatch batch;
    private BitmapFont font;
    private TextureAtlas textureAtlas; //TODO remove this

    //game variables
    private PolyGame polyGame;
    private Music gameMusic;
    private Stage hud;

    public GameScreen(Polymorph polymorph) {
        this.polymorph = polymorph;
        initAssets(polymorph.getAssetManager());
        this.polyGame = new PolyGame(textureAtlas);

        screenSize = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        initHud();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenSize.width, screenSize.height); //change this

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        font = new BitmapFont(false);

        Gdx.input.setInputProcessor(new InputMultiplexer(new KeyboardInputHandler(), hud));
        fps = new FPSLogger();
    }

    private void initAssets(AssetManager assetManager) {
        textureAtlas = assetManager.get(Polymorph.MASTER_PATH, TextureAtlas.class);
        for (Shape shape : Shape.values()) {
            shape.setTexture(textureAtlas.findRegion(shape.name));
        }
        for (ShapeColor shapeColor : ShapeColor.values()) {
            shapeColor.setTexture(textureAtlas.findRegion("capsule"));
        }

        //init audio
        gameMusic = assetManager.get(Polymorph.MUSIC_PATH, Music.class);
        gameMusic.setLooping(true);
    }

    private void initHud() {
        hud = new Stage();

        //init widgets
        HealthBar playerHealthBar = initHealthBar();
        ColorButton[] colorButtons = initColorButtons();
        final ShapeButton[] shapeButtons = initShapeButtons(colorButtons);

        //add widgets to stage
        for (ShapeButton shapeButton : shapeButtons) {
            hud.addActor(shapeButton);
        }
        for (ColorButton colorButton : colorButtons) {
            hud.addActor(colorButton);
        }
        hud.addActor(playerHealthBar);

      //init pause button
        Skin buttonSkin = new Skin(textureAtlas);
        ImageButton pauseButton=new ImageButton(buttonSkin.getDrawable("pausebutton"),buttonSkin.getDrawable("pausebutton"));
        pauseButton.setSize(50, 50);
        pauseButton.setPosition(screenSize.width-pauseButton.getWidth(), screenSize.height-pauseButton.getHeight());
        pauseButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(polyGame.getState()==State.RUNNING) {
                    polyGame.pause();
                    for (ShapeButton shapeButton : shapeButtons) {
                        shapeButton.setTouchable(Touchable.disabled);
                    }
                    return true;
                }
                if (polyGame.getState()==State.PAUSED) {
                    polyGame.start();
                    for (ShapeButton shapeButton : shapeButtons) {
                        shapeButton.setTouchable(Touchable.enabled);
                    }
                    return true;
                }
                return true;
            }
        });
        hud.addActor(pauseButton);
    }

    private HealthBar initHealthBar() {
        Image barImage = new Image(textureAtlas.findRegion("hpbar-empty"));
        barImage.setBounds(10, screenSize.height/5, screenSize.width/6, 3*screenSize.height/4);
        return new HealthBar(polyGame.getPlayer(), barImage, new Image(textureAtlas.findRegion("hpbar-full")));
    }

    private ColorButton[] initColorButtons() {
        final ShapeColor[] shapeColors = ShapeColor.values();

        ColorButton[] colorButtons = new ColorButton[shapeColors.length];
        for (int i = 0; i < colorButtons.length; i++) {
            colorButtons[i] = new ColorButton(shapeColors[i].getTexture(), shapeColors[i].color,
                                              new Vector2(i*screenSize.width/3 + 25, 90));
            colorButtons[i].setSize(screenSize.width/5, 20);
        }

        return colorButtons;
    }

    private ShapeButton[] initShapeButtons(ColorButton[] colorButtons) {
        final Shape[] shapes = Shape.values();

        ShapeButton[] shapeButtons = new ShapeButton[shapes.length];
        for (int i = 0; i < shapeButtons.length; i++) {
            shapeButtons[i] = new ShapeButton(polyGame.getPlayer(), shapes[i], colorButtons);
            shapeButtons[i].setBounds(i*screenSize.width/3 + 25, 10, screenSize.width/5, screenSize.width/5);
        }
        return shapeButtons;
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
                if (match != null) { //TODO Optimize this a bit more
                    match.getSound().play(preferences.getFloat(Polymorph.SOUND_VOLUME));
                }
                break;
            case STOPPED: // TODO: gracefully save and end the game
                preferences.putInteger(Polymorph.HIGH_SCORE, player.getScore());
                preferences.flush();
                gameMusic.stop();
                polymorph.setScreen(new DeathScreen(polymorph,player.getScore()));
                break;
        }
        hud.act(delta);

        batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        polyGame.render(batch);

        font.draw(batch, String.format("Score: %d\n", player.getScore()), screenSize.width - 100, screenSize.height-10);

        batch.end();

        //fps.log();
        hud.draw();
    }


    @Override
    public void show() {
        gameMusic.setVolume(polymorph.getPreferences().getFloat(Polymorph.MUSIC_VOLUME));
        gameMusic.play();
    }

    @Override
    public void resize(int width, int height) {
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
