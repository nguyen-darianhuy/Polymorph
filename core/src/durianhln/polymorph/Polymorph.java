package durianhln.polymorph;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Polymorph extends Game {
    public final static String OBJECTS_PATH = "objects.pack";

    private AssetManager assetManager;

    @Override
    public void create() {
        assetManager = new AssetManager();
        loadAssets();
        setScreen(new GameScreen(assetManager));
    }

    private void loadAssets() {
        assetManager.load(OBJECTS_PATH, TextureAtlas.class);
        assetManager.finishLoading();
    }

    @Override
    public void dispose() {
        super.dispose();
        assetManager.dispose();
    }
}
