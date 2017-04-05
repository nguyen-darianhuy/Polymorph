package durianhln.polymorph;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import durianhln.polymorph.game.Match;
import durianhln.polymorph.screen.Splash;

public class Polymorph extends Game {
    //assets paths
    public final static String SKIN_PATH = "skin/uiskin.json";
    public final static String OBJECTS_PATH = "objects.pack";
    public final static String SETTINGS_SCREEN_OPTIONS_PATH = "settingsscreenoptions.pack";
    public final static String BUTTONS_PATH = "buttons.pack";
    public final static String MUSIC_PATH = "music/mainmusic.ogg";
    public final static String MAIN_MENU_MUSIC_PATH = "music/mainmenumusic.ogg";
    public final static String GOOD_PATH = "music/match.mp3";
    public final static String HALF_PATH = "music/halfmatch.mp3";
    public final static String BAD_PATH = "music/fail.mp3";
    public final static String SPLASH_PATH = "raw/splashscreen.png";
    public final static String MAIN_MENU_BACKGROUND_PATH = "raw/mainmenu.png";
    public final static String SETTINGS_SCREEN_BACKGROUND_PATH = "raw/settingsscreen.png";

    //preferences paths
    public final static String MUSIC_VOLUME = "musicVolume";
    public final static String SOUND_VOLUME = "soundVolume";
    public final static String HIGH_SCORE = "highScore";

    private AssetManager assetManager;
    private Preferences preferences;

    @Override
    public void create() {
        assetManager = new AssetManager();
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
        assetManager.load(SKIN_PATH, Skin.class);
        assetManager.load(OBJECTS_PATH, TextureAtlas.class);
        assetManager.load(SETTINGS_SCREEN_OPTIONS_PATH, TextureAtlas.class);
        assetManager.load(BUTTONS_PATH, TextureAtlas.class);
        assetManager.load(MUSIC_PATH, Music.class);
        assetManager.load(MAIN_MENU_MUSIC_PATH, Music.class);
        assetManager.load(GOOD_PATH, Sound.class);
        assetManager.load(HALF_PATH, Sound.class);
        assetManager.load(BAD_PATH, Sound.class);
        assetManager.load(SPLASH_PATH, Texture.class);
        assetManager.load(MAIN_MENU_BACKGROUND_PATH, Texture.class);
        assetManager.load(SETTINGS_SCREEN_BACKGROUND_PATH, Texture.class);
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
