package durianhln.polymorph.screen;

import java.awt.Dimension;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import durianhln.polymorph.gameobject.Polymorph;

/**
 * 
 * @author Evan
 */
public class Splash implements Screen {

    private Polymorph game;
    private AssetManager assetManager;
    private SpriteBatch batch;
    private Texture splash;
    private long startTime;
    private BitmapFont font;
    private Dimension screenSize;

    public Splash(Polymorph game, AssetManager assetManager) {
        this.game = game;
        this.assetManager = assetManager;
        // delete later
        font = new BitmapFont(false);
        screenSize = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        startTime = TimeUtils.millis();
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        batch = new SpriteBatch();
        splash = new Texture(Gdx.files.internal(Polymorph.SPLASH_PATH));

    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        batch.begin();
        // change to draw splash image
        font.draw(batch, "twoPointFive Studios Presents", screenSize.width / 5 + 10, screenSize.height / 2 + 20);
        batch.end();

        // change to set screen to main menu
        if (Gdx.input.justTouched())
            game.setScreen(new GameScreen(game, assetManager));
        if (TimeUtils.millis() > (startTime + 3000))
            game.setScreen(new GameScreen(game, assetManager));

    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }
}
