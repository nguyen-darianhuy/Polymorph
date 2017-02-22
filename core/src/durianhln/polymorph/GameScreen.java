package durianhln.polymorph;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public GameScreen(AssetManager assetManager) {
        game = new Game(new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        camera = new OrthographicCamera();
        camera.setToOrtho(true);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        this.assetManager = assetManager;

        Gdx.input.setInputProcessor(new InputHandler());
    }

    @Override
    public void render(float delta) {
        Player player = game.getPlayer();
        Map mapFront = game.getMapFront();
        Map mapBack = game.getMapBack();

        Texture background;
        Texture square, circle, triangle;
        background = assetManager.get(Polymorph.BACKGROUND_PATH, Texture.class);
        square = assetManager.get(Polymorph.SQUARE_PATH, Texture.class);
        //TODO: continue loading

        game.update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.disableBlending();
        //draw opaques
        batch.draw(background, mapFront.getPosition().x, mapFront.getPosition().y, mapFront.getSize().width, mapFront.getSize().height);
        batch.draw(background, mapBack.getPosition().x, mapBack.getPosition().y, mapBack.getSize().width, mapBack.getSize().height);
        batch.enableBlending();
        //draw transparents
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

    private class InputHandler implements InputProcessor {
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
