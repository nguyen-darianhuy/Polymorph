package durianhln.polymorph.screen;

import java.awt.Dimension;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import durianhln.polymorph.Polymorph;

/**
 *
 * @author Evan
 */
public class MainMenu implements Screen {

    private Polymorph polymorph;
    private Dimension screenSize;

    private AssetManager assetManager;
    private Music mainMenuMusic;
    private Texture background;

    private Stage stage;

    private TextureAtlas buttonAtlas;
    private Skin buttonSkin;

    public MainMenu(Polymorph polymorph) {
        this.polymorph = polymorph;
        assetManager = polymorph.getAssetManager();

        mainMenuMusic = assetManager.get(Polymorph.MAIN_MENU_MUSIC_PATH);
        mainMenuMusic.setLooping(true);

        background = assetManager.get(Polymorph.MAIN_MENU_BACKGROUND_PATH);
        background.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        screenSize = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage();
        stage.clear();
        initButtons();
        Gdx.input.setInputProcessor(stage);
    }

    public void initButtons() {
        buttonAtlas = assetManager.get(Polymorph.BUTTONS_PATH);
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonAtlas);

        //TODO FIX THIS SHIT INDENTATION
        //TODO Long-term fix the magic numbers
        ImageButton playButton = new ImageButton(buttonSkin.getDrawable("playbutton"), buttonSkin.getDrawable("playbutton"));
        playButton.setSize(256, 64);
        playButton.setPosition(screenSize.width/2-playButton.getWidth()/2, screenSize.height/2-playButton.getHeight()/2+50);
        playButton.addListener(new InputListener() {
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                polymorph.setScreen(new GameScreen(polymorph));
                mainMenuMusic.stop();
                return true;
            }
        });

        ImageButton settingsButton = new ImageButton(buttonSkin.getDrawable("settingsbutton"), buttonSkin.getDrawable("settingsbutton"));
        settingsButton.setSize(256, 64);
        settingsButton.setPosition(playButton.getX(), playButton.getY()-settingsButton.getHeight()-playButton.getHeight()/2);
        settingsButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                polymorph.setScreen(new SettingsScreen(polymorph));
                return false;
            }
        });

        //change to another button
        ImageButton otherButton = new ImageButton(buttonSkin.getDrawable("settingsbutton"), buttonSkin.getDrawable("settingsbutton"));
        otherButton.setSize(256, 64);
        otherButton.setPosition(settingsButton.getX(), settingsButton.getY()-settingsButton.getHeight()-settingsButton.getHeight()/2);
        otherButton.addListener(new InputListener() {
        		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		    return false;
        		}
        });

        ImageButton creditsButton = new ImageButton(buttonSkin.getDrawable("creditsbutton"), buttonSkin.getDrawable("creditsbutton"));
        creditsButton.setSize(112.5f, 37.5f);
        creditsButton.setPosition(0, 0);
        creditsButton.addListener(new InputListener() {
    		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return false;
            }
        });

        stage.addActor(playButton);
        stage.addActor(settingsButton);
        stage.addActor(otherButton);
        stage.addActor(creditsButton);
    }


    @Override
    public void show() {
        mainMenuMusic.setVolume(polymorph.getMusicVolume());
        mainMenuMusic.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, screenSize.width, screenSize.height);
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
