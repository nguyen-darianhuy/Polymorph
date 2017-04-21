package durianhln.polymorph.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import durianhln.polymorph.Polymorph;
import durianhln.polymorph.util.Dimension;

/**
 *
 * @author Evan
 */
public class MainMenu implements Screen {
    private Polymorph polymorph;

    private Music mainMenuMusic;
    private TextureRegion background;

    private Stage stage;

    public MainMenu(Polymorph polymorph) {
        AssetManager assetManager = polymorph.getAssetManager();
        TextureAtlas textureAtlas = assetManager.get(Polymorph.MASTER_PATH, TextureAtlas.class);
        this.polymorph = polymorph;

        mainMenuMusic = assetManager.get(Polymorph.MAIN_MENU_MUSIC_PATH);
        mainMenuMusic.setLooping(true);

        background = textureAtlas.findRegion("mainmenu");

        stage = new Stage(new StretchViewport(Polymorph.WORLD_WIDTH, Polymorph.WORLD_HEIGHT));
        stage.clear();
        initButtons(textureAtlas);
        Gdx.input.setInputProcessor(stage);
    }

    private void initButtons(TextureAtlas textureAtlas) {
        Skin buttonSkin = new Skin();
        buttonSkin.addRegions(textureAtlas);

        ImageButton playButton = new ImageButton(buttonSkin.getDrawable("buttontemplate"),
                buttonSkin.getDrawable("downbuttontemplate"));
        playButton.setSize(4 * Polymorph.WORLD_WIDTH / 5, Polymorph.WORLD_HEIGHT / 8);
        playButton.setPosition(Polymorph.WORLD_WIDTH / 2 - playButton.getWidth() / 2, Polymorph.WORLD_HEIGHT / 2);
        playButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                polymorph.setScreen(new GameScreen(polymorph));
                mainMenuMusic.stop();
            }
        });

        ImageButton settingsButton = new ImageButton(buttonSkin.getDrawable("buttontemplate"),
                buttonSkin.getDrawable("downbuttontemplate"));
        settingsButton.setSize(4 * Polymorph.WORLD_WIDTH / 5, Polymorph.WORLD_HEIGHT / 8);
        settingsButton.setPosition(playButton.getX(), playButton.getY() - 5 * playButton.getHeight() / 4);
        settingsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                polymorph.setScreen(new SettingsScreen(polymorph));
            }
        });

        // change to another button
        ImageButton otherButton = new ImageButton(buttonSkin.getDrawable("buttontemplate"),
                buttonSkin.getDrawable("downbuttontemplate"));
        otherButton.setSize(4 * Polymorph.WORLD_WIDTH / 5, Polymorph.WORLD_HEIGHT / 8);
        otherButton.setPosition(settingsButton.getX(), settingsButton.getY() - 5 * settingsButton.getHeight() / 4);
        otherButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return false;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });

        ImageButton creditsButton = new ImageButton(buttonSkin.getDrawable("creditsbutton"),
                buttonSkin.getDrawable("creditsbutton"));
        creditsButton.setPosition(0, 0);
        creditsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return false;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });

        stage.addActor(playButton);
        stage.addActor(settingsButton);
        stage.addActor(otherButton);
        stage.addActor(creditsButton);
    }

    @Override
    public void show() {
        mainMenuMusic.setVolume(polymorph.getPreferences().getFloat(Polymorph.MUSIC_VOLUME));
        mainMenuMusic.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, Polymorph.WORLD_WIDTH, Polymorph.WORLD_HEIGHT);
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }
}
