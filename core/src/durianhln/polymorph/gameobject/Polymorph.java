package durianhln.polymorph.gameobject;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import durianhln.polymorph.game.PolyGame;
import durianhln.polymorph.screen.GameScreen;
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
    public final static String BUTTONS_PATH = "buttons/buttons.pack";
    public final static String SLIDER_PATH = "buttons/slider.png";

    private AssetManager assetManager;
    
    private float musicVolume;
    private float soundVolume;

    @Override
    public void create() {
        assetManager = new AssetManager();
        musicVolume = 1.0f;
        soundVolume = 1.0f;
        loadAssets();
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
        assetManager.load(BUTTONS_PATH, TextureAtlas.class);
        assetManager.load(SLIDER_PATH, Texture.class);
        assetManager.finishLoading();
    }

    @Override
    public void dispose() {
        super.dispose();
        assetManager.dispose();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
    
    public void changeMusicVolume(float changeInVolume) {
        musicVolume+=changeInVolume;
    }
    
    public void changeSoundVolume(float changeInVolume) {
        soundVolume+=changeInVolume;
    }
}
