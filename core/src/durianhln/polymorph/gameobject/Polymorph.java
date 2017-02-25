package durianhln.polymorph.gameobject;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import durianhln.polymorph.screen.GameScreen;

public class Polymorph extends Game {
    public final static String OBJECTS_PATH = "objects.pack";
    public final static String SKIN_PATH = "skin/neon-ui.json";

    private AssetManager assetManager;

    @Override
    public void create() {
        assetManager = new AssetManager();
        loadAssets();
        setScreen(new GameScreen(assetManager));
    }

    private void loadAssets() {
        assetManager.load(OBJECTS_PATH, TextureAtlas.class);
        //assetManager.load(SKIN_PATH, Skin.class);
        assetManager.finishLoading();
    }

    @Override
    public void dispose() {
        super.dispose();
        assetManager.dispose();
    }
}
