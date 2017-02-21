package durianhln.polymorph;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Polymorph extends Game {
    public final static String BACKGROUND_PATH = "background.png";
    public final static String TRIANGLE_PATH = "triangle.png";
    public final static String CIRCLE_PATH = "circle.png";
    public final static String SQUARE_PATH = "square.png";

    private SpriteBatch batch;
    private AssetManager assetManager;

    @Override
    public void create() {
        assetManager = new AssetManager();
        loadAssets();
        setScreen(new GameScreen(assetManager));
    }

    private void loadAssets() {
        assetManager.load(BACKGROUND_PATH, Texture.class);
        assetManager.load(TRIANGLE_PATH, Texture.class);
        assetManager.load(CIRCLE_PATH, Texture.class);
        assetManager.load(SQUARE_PATH, Texture.class);
        assetManager.finishLoading();
    }

    @Override
    public void dispose() {
        super.dispose();
        assetManager.dispose();
    }
}
