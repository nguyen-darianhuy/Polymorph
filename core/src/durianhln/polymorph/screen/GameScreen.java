package durianhln.polymorph.screen;

import durianhln.polymorph.gameobject.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import durianhln.polymorph.game.PolyGame;
import durianhln.polymorph.game.Shape;
import durianhln.polymorph.gameobject.ShapeColor;
import durianhln.polymorph.game.State;
import durianhln.polymorph.gameobject.HealthBar;
import durianhln.polymorph.Polymorph;
import java.awt.Dimension;

/**
 *
 * @author Darian
 */
public class GameScreen implements Screen {
    //screen variables
    private Polymorph polymorph;
    private Dimension screenSize; //TODO remove this
    private AssetManager assetManager; //TODO remove this
    private FPSLogger fps; //TODO remove this

    //screen properties
    private OrthographicCamera camera;

    //utils
    private SpriteBatch batch;
    private BitmapFont font;

    //game variables
    private PolyGame game;
    private Stage hud;
    private HealthBar playerHealthBar;

    public GameScreen(Polymorph polymorph) {
        this.polymorph = polymorph;
        assetManager = polymorph.getAssetManager();
        screenSize = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.game = new PolyGame(assetManager);

        initHud();
        camera = new OrthographicCamera();
        camera.setToOrtho(true, screenSize.width, screenSize.height);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        font = new BitmapFont(true);

        Gdx.input.setInputProcessor(new InputMultiplexer(new InputHandler(), hud));
        fps = new FPSLogger();
    }

    private void initHud() {
        hud = new Stage();
        TextureAtlas textureAtlas = assetManager.get(Polymorph.OBJECTS_PATH, TextureAtlas.class);

        Image barImage = new Image(textureAtlas.findRegion("hpbar-empty"));
        barImage.setBounds(10, screenSize.height/5, screenSize.width/6, 3*screenSize.height/4);
        playerHealthBar = new HealthBar(barImage, new Image(textureAtlas.findRegion("hpbar-full")));


        hud.addActor(playerHealthBar);
    }

    @Override
    public void render(float delta) {
        delta = Math.min(delta, 0.03f);

        Player player = game.getPlayer(); // temporary

        switch (game.getState()) {
        case READY: // TODO: Change this shit
            System.out.println("HERE WE GO");
            game.start();
            break;
        case RUNNING:
            game.update(delta);
            break;
        case STOPPED:
            // TODO: gracefully end the game
            break;
        }
        batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.render(batch);
        playerHealthBar.setValue(player.getHitpoints());
        // >>>DEMO START
        for (int i = 0; i < Shape.values().length; i++) {
            Shape shape = Shape.values()[i];
            int x = i * screenSize.width / 3 + 25;
            int y = screenSize.height - 80;
            int width = screenSize.width / 5;
            batch.draw(shape.getTexture(), x, y, width, width);

            Color originalColor = batch.getColor();
            batch.setColor(ShapeColor.values()[i].color);
            batch.draw(ShapeColor.values()[i].getTexture(), x, y - 30, width, 20);
            batch.setColor(originalColor);
        }
        // draw text
        font.draw(batch, String.format("Score: %d\n", player.getScore()), screenSize.width - 100, 10);
        font.draw(batch, String.format("Multiplier: %.2f\n%s",
                  player.getMultiplier(), player.isDead() ? "Oh dear, you are dead!" : ""),
                  10, 10);

        // >>>DEMO END
        batch.end();

        fps.log();
        hud.draw();
        hud.act(delta);
    }

    @Override
    public void show() {

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

    private class InputHandler implements InputProcessor { // touch coordinates
                                                           // are y-down!!!
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
                game.getPlayer().morph(shapeHeld, ShapeColor.values()[0].color);
                break;
            case Input.Keys.NUMPAD_8:
                game.getPlayer().morph(shapeHeld, ShapeColor.values()[1].color);
                break;
            case Input.Keys.NUMPAD_9:
                game.getPlayer().morph(shapeHeld, ShapeColor.values()[2].color);
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
            if (game.getState() == State.STOPPED) {
                // game = new Game(screenSize, assetManager); //TODO: Fix this
                // shiz
            } else {
                System.out.printf("X: %d, Y: %d\n", x, y);
            }
            return true;
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
