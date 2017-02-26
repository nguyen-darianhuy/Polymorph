package durianhln.polymorph.screen;

import durianhln.polymorph.gameobject.Slot;
import durianhln.polymorph.gameobject.Map;
import durianhln.polymorph.gameobject.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import durianhln.polymorph.game.Game;
import durianhln.polymorph.game.Shape;
import durianhln.polymorph.game.State;
import java.awt.Dimension;

/**
 *
 * @author Darian
 */
public class GameScreen implements Screen {
    private Game game;
    private Dimension screenSize;

    private Stage hud;
    private OrthographicCamera camera;

    private AssetManager assetManager;

    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;

    private FPSLogger fps;

    public GameScreen(AssetManager assetManager) {
        screenSize = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game = new Game(screenSize, assetManager);

        this.assetManager = assetManager;

        //Skin skin = assetManager.get(Polymorph.SKIN_PATH, Skin.class);
        hud = new Stage();
        camera = new OrthographicCamera();
        camera.setToOrtho(true, screenSize.width, screenSize.height);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        font = new BitmapFont(true);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        Gdx.input.setInputProcessor(new InputHandler());
        fps = new FPSLogger();
    }

    @Override
    public void render(float delta) {
        delta = Math.min(delta, 0.03f);

        Player player = game.getPlayer();
        Array<Slot> slots = game.getSlots();
        Map mapFront = game.getMapFront();
        Map mapBack = game.getMapBack();

        switch (game.getState()) {
            case READY: //TODO: Change this shit
                System.out.println("HERE WE GO");
                game.setState(State.RUNNING);
                return;
            case RUNNING:
                game.update(delta);
                break;
            case STOPPED:
                dispose(); //TODO: gracefully end the game
                return;
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        fps.log();
        batch.begin();
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
        //>>>DEMO START
        for (int i = 0; i < Shape.values().length; i++) {
            Shape shape = Shape.values()[i];
            int x = i*screenSize.width/3 + 25;
            batch.draw(shape.getTexture(), x, screenSize.height - 80, screenSize.width/5, screenSize.width/5);
        }
        //draw text
        font.draw(batch, String.format("HP: %d%%\nScore: %d\nMultiplier: %.2f\n%s",
                player.getHitpoints(), player.getScore(), player.getMultiplier(),
                player.isDead() ? "Oh dear, you are dead!" : ""),
                10, 10);

        //>>>DEMO END
        batch.end();
        //>>>DEMO START
        shapeRenderer.begin(ShapeType.Filled);
        for (int i = 0; i < Game.colors.length; i++) {
            shapeRenderer.setColor(Game.colors[i]);
            shapeRenderer.rect(i*screenSize.width/3 + 25, screenSize.height - 110, screenSize.width/5, 20);
        }
        shapeRenderer.end();
        //>>>DEMO END
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

    private class InputHandler implements InputProcessor { //touch coordinates are y-down!!!
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
                    game.getPlayer().morph(shapeHeld, Game.colors[0]);
                    break;
                case Input.Keys.NUMPAD_8:
                    game.getPlayer().morph(shapeHeld, Game.colors[1]);
                    break;
                case Input.Keys.NUMPAD_9:
                    game.getPlayer().morph(shapeHeld, Game.colors[2]);
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
            System.out.printf("X: %d, Y: %d\n", x, y);
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
