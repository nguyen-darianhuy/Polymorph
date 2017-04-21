package durianhln.polymorph;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import durianhln.polymorph.game.Match;
import durianhln.polymorph.screen.Splash;

public class Polymorph extends Game {
    //assets paths
    public final static String SKIN_PATH = "skin/uiskin.json";
    public final static String MASTER_PATH = "master.pack";
    public final static String FONT_NORMAL_PATH = "timeburnernormal.ttf";
    public final static String FONT_BOLD_PATH = "timeburnerbold.ttf";
    public final static String MUSIC_PATH = "music/mainmusic.ogg";
    public final static String MAIN_MENU_MUSIC_PATH = "music/mainmenumusic.ogg";
    public final static String GOOD_PATH = "music/match.mp3";
    public final static String HALF_PATH = "music/halfmatch.mp3";
    public final static String BAD_PATH = "music/fail.mp3";

    //preferences paths
    public final static String MUSIC_VOLUME = "musicVolume";
    public final static String SOUND_VOLUME = "soundVolume";
    public final static String HIGH_SCORE = "highScore";

    //app constants
    public final static float WORLD_WIDTH = 1080; //banana units
    public final static float WORLD_HEIGHT = 1920;

    private AssetManager assetManager;
    private Preferences preferences;

    @Override
    public void create() {
        preferences = Gdx.app.getPreferences("Polymorph");
        initSettings();

        loadAssets();
        initAssets();
        setScreen(new Splash(this));
    }

    private void initSettings() {
        preferences.putFloat(MUSIC_VOLUME, preferences.getFloat(MUSIC_VOLUME, 0.5f));
        preferences.putFloat(SOUND_VOLUME, preferences.getFloat(SOUND_VOLUME, 0.5f));
        preferences.putInteger(HIGH_SCORE, preferences.getInteger(HIGH_SCORE, 0));
        preferences.flush();
    }

    private void loadAssets() {
        InternalFileHandleResolver fileHandler = new InternalFileHandleResolver();
        assetManager = new AssetManager(fileHandler);
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(fileHandler));

        assetManager.load(SKIN_PATH, Skin.class);
        assetManager.load(MASTER_PATH, TextureAtlas.class);
        assetManager.load(FONT_NORMAL_PATH, FreeTypeFontGenerator.class);
        assetManager.load(FONT_BOLD_PATH, FreeTypeFontGenerator.class);
        assetManager.load(MUSIC_PATH, Music.class);
        assetManager.load(MAIN_MENU_MUSIC_PATH, Music.class);
        assetManager.load(GOOD_PATH, Sound.class);
        assetManager.load(HALF_PATH, Sound.class);
        assetManager.load(BAD_PATH, Sound.class);
        assetManager.finishLoading();
    }

    private void initAssets() {
        Match.GOOD.setSound(assetManager.get(Polymorph.GOOD_PATH, Sound.class));
        Match.HALF.setSound(assetManager.get(Polymorph.HALF_PATH, Sound.class));
        Match.BAD.setSound(assetManager.get(Polymorph.BAD_PATH, Sound.class));
    }

    @Override
    public void dispose() {
        super.dispose();
        assetManager.dispose();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public Preferences getPreferences() {
        return preferences;
    }
}
