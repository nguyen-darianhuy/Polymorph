package durianhln.polymorph.screen;

import durianhln.polymorph.gameobject.Slot;
import durianhln.polymorph.gameobject.Map;
import durianhln.polymorph.gameobject.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import durianhln.polymorph.game.Game;
import durianhln.polymorph.game.State;
import java.awt.Dimension;

/**
 *
 * @author Darian
 */
public class GameScreen implements Screen {
    private Game game;


    private OrthographicCamera camera;
    private SpriteBatch batch;
    private AssetManager assetManager;
    private FPSLogger fps;


    public GameScreen(AssetManager assetManager) {
        Dimension screenSize = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game = new Game(screenSize, assetManager);

        camera = new OrthographicCamera();
        camera.setToOrtho(true, screenSize.width, screenSize.height);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        this.assetManager = assetManager;

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
                break;
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
        batch.end();
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
        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
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
