package durianhln.polymorph.screen;

import java.awt.Dimension;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import durianhln.polymorph.Polymorph;

/**
 *
 * @author Evan
 */
public class Splash implements Screen {
    private Polymorph polymorph;
    private Dimension screenSize;

    private SpriteBatch batch;
    private Texture splash;

    public Splash(final Polymorph polymorph) {
        this.polymorph = polymorph;

        AssetManager assetManager = polymorph.getAssetManager();
        splash = assetManager.get(Polymorph.SPLASH_PATH);

        batch = new SpriteBatch();
        screenSize = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Timer.schedule(new Task() {
            @Override
            public void run() {
                polymorph.setScreen(new MainMenu(polymorph));
            }

        }, 3); //change screen after 3 seconds
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(splash, 0, 0, screenSize.width, screenSize.height);
        batch.end();

        if (Gdx.input.justTouched()) {
            Timer.instance().clear();
            polymorph.setScreen(new MainMenu(polymorph));
        }
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
