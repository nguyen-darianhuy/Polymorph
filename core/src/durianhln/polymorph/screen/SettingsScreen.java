package durianhln.polymorph.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import durianhln.polymorph.Polymorph;
import durianhln.polymorph.game.Match;

/**
 *
 * @author Evan
 */
public class SettingsScreen implements Screen {
    private Polymorph polymorph;

    private Music mainMenuMusic;

    private TextureRegion background;
    private TextureRegion musicVolumeText;
    private TextureRegion soundVolumeText;
    private Stage stage;

    private Slider musicVolumeSlider;
    private Slider soundVolumeSlider;

    public SettingsScreen(Polymorph polymorph) {
        AssetManager assetManager = polymorph.getAssetManager();
        TextureAtlas textureAtlas = assetManager.get(Polymorph.MASTER_PATH, TextureAtlas.class);

        this.polymorph = polymorph;

        mainMenuMusic = assetManager.get(Polymorph.MAIN_MENU_MUSIC_PATH, Music.class);
        background = textureAtlas.findRegion("settingsscreen");

        stage = new Stage(new StretchViewport(Polymorph.WORLD_WIDTH, Polymorph.WORLD_HEIGHT));
        stage.clear();
        initButtons(textureAtlas);
        Gdx.input.setInputProcessor(stage);
    }

    private void initButtons(TextureAtlas textureAtlas) {
        final Preferences preferences = polymorph.getPreferences();

        ImageButton backButton = new ImageButton(new TextureRegionDrawable(textureAtlas.findRegion("backbutton")));
        float backButtonWidth = Polymorph.WORLD_WIDTH/8;
        backButton.setBounds(0, Polymorph.WORLD_HEIGHT - backButtonWidth, backButtonWidth, backButtonWidth);
        backButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                polymorph.setScreen(new MainMenu(polymorph));
                return false;
            }
        });

        //TODO draw this text with font
        musicVolumeText = textureAtlas.findRegion("musicvolume");
        soundVolumeText = textureAtlas.findRegion("soundvolume");

        //TODO set proper bounds for the sliders
        Skin sliderSkin = polymorph.getAssetManager().get(Polymorph.SKIN_PATH, Skin.class);

        musicVolumeSlider = new Slider(0f, 1f, 0.01f, false, sliderSkin);
        musicVolumeSlider.setValue(preferences.getFloat(Polymorph.MUSIC_VOLUME));
        musicVolumeSlider.setAnimateDuration(0.05f);
        musicVolumeSlider.setSize(2*Polymorph.WORLD_WIDTH/3, Polymorph.WORLD_HEIGHT/20);
        musicVolumeSlider.setPosition(Polymorph.WORLD_WIDTH / 2 - musicVolumeSlider.getWidth() / 2,
                2 * Polymorph.WORLD_HEIGHT / 3);
        musicVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                preferences.putFloat(Polymorph.MUSIC_VOLUME, musicVolumeSlider.getValue());
                mainMenuMusic.setVolume(preferences.getFloat(Polymorph.MUSIC_VOLUME));
            }
        });

        soundVolumeSlider = new Slider(0f, 1f, 0.1f, false, sliderSkin);
        soundVolumeSlider.setValue(preferences.getFloat(Polymorph.SOUND_VOLUME));
        soundVolumeSlider.setAnimateDuration(0.05f);
        soundVolumeSlider.setSize(2*Polymorph.WORLD_WIDTH/3, Polymorph.WORLD_HEIGHT/20);
        soundVolumeSlider.setPosition(Polymorph.WORLD_WIDTH / 2 - soundVolumeSlider.getWidth() / 2,
                Polymorph.WORLD_HEIGHT / 2);
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
        stage.getBatch().draw(background, 0, 0, Polymorph.WORLD_WIDTH, Polymorph.WORLD_HEIGHT);
        stage.getBatch().draw(musicVolumeText, musicVolumeSlider.getX() + musicVolumeSlider.getWidth()/4,
                musicVolumeSlider.getY() + musicVolumeSlider.getHeight(), musicVolumeSlider.getWidth()/2, musicVolumeSlider.getHeight());
        stage.getBatch().draw(soundVolumeText, soundVolumeSlider.getX() + soundVolumeSlider.getWidth()/4,
                soundVolumeSlider.getY() + soundVolumeSlider.getHeight(), soundVolumeSlider.getWidth()/2, soundVolumeSlider.getHeight());
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
