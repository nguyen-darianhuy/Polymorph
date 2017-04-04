package durianhln.polymorph;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import durianhln.polymorph.game.Match;
import durianhln.polymorph.screen.Splash;

public class Polymorph extends Game {
    public final static String OBJECTS_PATH = "objects.pack";
    public final static String SKIN_PATH = "skin/neon-ui.json";
    public final static String MUSIC_PATH = "music/mainmusic.ogg";
    public final static String MAIN_MENU_MUSIC_PATH = "music/mainmenumusic.ogg";
    public final static String GOOD_PATH = "music/match.mp3";
    public final static String HALF_PATH = "music/halfmatch.mp3";
    public final static String BAD_PATH = "music/fail.mp3";
    public final static String SPLASH_PATH = "raw/splashscreen.png";
    public final static String MAIN_MENU_BACKGROUND_PATH = "raw/mainmenu.png";
    public final static String SETTINGS_SCREEN_BACKGROUND_PATH = "raw/settingsscreen.png";
    public final static String SETTINGS_SCREEN_OPTIONS_PATH = "raw/settingsscreenoptions.pack";
    public final static String BUTTONS_PATH = "buttons/buttons.pack";

    private AssetManager assetManager;

    private float musicVolume;
    private float soundVolume;

    @Override
    public void create() {
        assetManager = new AssetManager();
        musicVolume = 0.5f;
        soundVolume = 0.5f;
        loadAssets();
        initAssets();
        setScreen(new Splash(this));
    }

    private void loadAssets() {
        assetManager.load(OBJECTS_PATH, TextureAtlas.class);
        assetManager.load(SKIN_PATH, Skin.class);
        assetManager.load(MUSIC_PATH, Music.class);
        assetManager.load(MAIN_MENU_MUSIC_PATH, Music.class);
        assetManager.load(GOOD_PATH, Sound.class);
        assetManager.load(HALF_PATH, Sound.class);
        assetManager.load(BAD_PATH, Sound.class);
        assetManager.load(SPLASH_PATH, Texture.class);
        assetManager.load(MAIN_MENU_BACKGROUND_PATH, Texture.class);
        assetManager.load(SETTINGS_SCREEN_BACKGROUND_PATH, Texture.class);
        assetManager.load(SETTINGS_SCREEN_OPTIONS_PATH, TextureAtlas.class);
        assetManager.load(BUTTONS_PATH, TextureAtlas.class);
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

    public float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume=musicVolume;
        System.out.print(musicVolume+"  ");
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(float soundVolume) {
        this.soundVolume=soundVolume;
    }
}
