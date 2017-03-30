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
    private Dimension screenSize;
    
    private SpriteBatch batch;
    private Texture splash;
    
    private long startTime;

    public Splash(Polymorph game) {
        this.game = game;
        batch = new SpriteBatch();
        splash = new Texture(Gdx.files.internal(Polymorph.SPLASH_PATH));
        screenSize = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        startTime = TimeUtils.millis();
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

        if (Gdx.input.justTouched())
            game.setScreen(new MainMenu(game));
        if (TimeUtils.millis() > (startTime + 3000))
            game.setScreen(new MainMenu(game));

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
