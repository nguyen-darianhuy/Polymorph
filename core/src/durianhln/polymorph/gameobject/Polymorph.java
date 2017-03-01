package durianhln.polymorph.gameobject;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import durianhln.polymorph.screen.GameScreen;

public class Polymorph extends Game {
    public final static String OBJECTS_PATH = "objects.pack";
    public final static String SKIN_PATH = "skin/neon-ui.json";
    public final static String MUSIC_PATH = "mainmusic2.wav";
    public final static String GOOD_PATH = "match.mp3";
    public final static String HALF_PATH = "halfmatch.mp3";
    public final static String BAD_PATH = "fail.mp3";

    private AssetManager assetManager;

    @Override
    public void create() {
        assetManager = new AssetManager();
        loadAssets();
        setScreen(new GameScreen(assetManager));
        assetManager.get(MUSIC_PATH, Music.class).setLooping(true);
        assetManager.get(MUSIC_PATH, Music.class).play();
    }

    private void loadAssets() {
        assetManager.load(OBJECTS_PATH, TextureAtlas.class);
        //assetManager.load(SKIN_PATH, Skin.class);
        assetManager.load(MUSIC_PATH, Music.class);
        assetManager.load(GOOD_PATH, Sound.class);
        assetManager.load(HALF_PATH, Sound.class);
        assetManager.load(BAD_PATH, Sound.class);
        assetManager.finishLoading();
    }

    @Override
    public void dispose() {
        super.dispose();
        assetManager.dispose();
    }
}
