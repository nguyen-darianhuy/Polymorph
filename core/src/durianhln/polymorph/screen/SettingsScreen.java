package durianhln.polymorph.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

import durianhln.polymorph.Polymorph;
import durianhln.polymorph.game.Match;
import durianhln.polymorph.util.Dimension;

/**
 *
 * @author Evan
 */
public class SettingsScreen implements Screen {

    private Polymorph polymorph;
    private Dimension screenSize;

    private Music mainMenuMusic;

    private Texture background;
    private Texture musicVolumeTexture;
    private Texture soundVolumeTexture;
    private Stage stage;

    private Slider musicVolumeSlider;
    private Slider soundVolumeSlider;

    public SettingsScreen(Polymorph polymorph) {
        AssetManager assetManager = polymorph.getAssetManager();
        this.polymorph = polymorph;

        screenSize = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mainMenuMusic = assetManager.get(Polymorph.MAIN_MENU_MUSIC_PATH, Music.class);
        background = assetManager.get(Polymorph.SETTINGS_SCREEN_BACKGROUND_PATH);
        screenSize = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage();
        stage.clear();
        initButtons(assetManager);
        Gdx.input.setInputProcessor(stage);
    }

    public void initButtons(AssetManager assetManager) {
        TextureAtlas buttonAtlas = polymorph.getAssetManager().get(Polymorph.MASTER_PATH);
        final Preferences preferences = polymorph.getPreferences();
        Skin buttonSkin = new Skin();
        buttonSkin.addRegions(buttonAtlas);

        // TODO FIX THIS SHIT INDENTATION
        ImageButtonStyle backButtonStyle = new ImageButtonStyle();
        backButtonStyle.up = buttonSkin.getDrawable("backbutton"); // change to back button texture
        backButtonStyle.down = buttonSkin.getDrawable("backbutton");
        ImageButton backButton = new ImageButton(backButtonStyle);
        backButton.setPosition(0f, screenSize.height - backButton.getHeight());
        backButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                polymorph.setScreen(new MainMenu(polymorph));
                return false;
            }
        });
        Skin sliderSkin = assetManager.get(Polymorph.SKIN_PATH, Skin.class);

        musicVolumeTexture = new Texture(Gdx.files.internal("raw/musicvolume.png")); // Get this shit from AssetManager
        musicVolumeSlider = new Slider(0f, 1f, 0.1f, false, sliderSkin);
        musicVolumeSlider.setValue(preferences.getFloat(Polymorph.MUSIC_VOLUME));
        musicVolumeSlider.setAnimateDuration(0.05f);
        musicVolumeSlider.setPosition(screenSize.width / 2 - musicVolumeSlider.getWidth() / 2,
                2 * screenSize.height / 3);
        musicVolumeSlider.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                preferences.putFloat(Polymorph.MUSIC_VOLUME, musicVolumeSlider.getValue());
                mainMenuMusic.setVolume(preferences.getFloat(Polymorph.MUSIC_VOLUME));
            }
        });

        soundVolumeTexture = new Texture(Gdx.files.internal("raw/soundvolume.png"));
        soundVolumeSlider = new Slider(0f, 1f, 0.1f, false, sliderSkin);
        soundVolumeSlider.setValue(preferences.getFloat(Polymorph.SOUND_VOLUME));
        soundVolumeSlider.setAnimateDuration(0.05f);
        soundVolumeSlider.setPosition(screenSize.width / 2 - soundVolumeSlider.getWidth() / 2,
                screenSize.height / 2 - 2 * soundVolumeTexture.getHeight());
        soundVolumeSlider.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                preferences.putFloat(Polymorph.SOUND_VOLUME, soundVolumeSlider.getValue());
                Match.GOOD.getSound().play(soundVolumeSlider.getValue());
            }
        });
        stage.addActor(backButton);
        stage.addActor(musicVolumeSlider);
        stage.addActor(soundVolumeSlider);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // TODO Fix magic numbers
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, screenSize.width, screenSize.height);
        stage.getBatch().draw(musicVolumeTexture, musicVolumeSlider.getX() - 25,
                musicVolumeSlider.getY() + musicVolumeSlider.getHeight(), 200, 50);
        stage.getBatch().draw(soundVolumeTexture, soundVolumeSlider.getX() - 25,
                soundVolumeSlider.getY() + soundVolumeSlider.getHeight(), 200, 50);
        stage.getBatch().end();
        stage.draw();
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        polymorph.getPreferences().flush();
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}
