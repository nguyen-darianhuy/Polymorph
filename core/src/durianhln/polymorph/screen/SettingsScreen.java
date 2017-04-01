package durianhln.polymorph.screen;

import java.awt.Dimension;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

import durianhln.polymorph.gameobject.Polymorph;

/**
*
* @author Evan
*/
public class SettingsScreen implements Screen {
    
    private Polymorph game;
    private Dimension screenSize;
    
    private AssetManager assetManager;
    private Music mainMenuMusic;
    private Texture background;
    private Stage stage;

    public SettingsScreen(Polymorph game) {
        this.game = game;
        assetManager = game.getAssetManager();
        
        mainMenuMusic = assetManager.get(Polymorph.MAIN_MENU_MUSIC_PATH, Music.class);
        background = assetManager.get(Polymorph.SETTINGS_SCREEN_BACKGROUND_PATH);
        screenSize = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        stage = new Stage();
        stage.clear();
        initButtons();
        Gdx.input.setInputProcessor(stage);
    }
    
    public void initButtons() {
        TextureAtlas buttonAtlas = assetManager.get(Polymorph.BUTTONS_PATH);
        
        Skin buttonSkin = new Skin();
        buttonSkin.addRegions(buttonAtlas);
        
        ImageButtonStyle backButtonStyle = new ImageButtonStyle();
        backButtonStyle.up = buttonSkin.getDrawable("backbutton"); //change to back button texture
        backButtonStyle.down = buttonSkin.getDrawable("backbutton");
        ImageButton backButton = new ImageButton(backButtonStyle);
        backButton.setSize(48, 48);
        backButton.setPosition(0f, screenSize.height-backButton.getHeight());
        backButton.addListener(new BackButtonListener());
        
        Skin sliderSkin = new Skin(Gdx.files.internal("uiskin.json"));
        
        Slider musicVolumeSlider = new Slider(0, 10, 1, false, sliderSkin);
        musicVolumeSlider.setAnimateDuration(0.3f);
        musicVolumeSlider.setPosition(100,100);
        musicVolumeSlider.addListener(new MusicVolumeSliderListener());
        
        stage.addActor(backButton);
        stage.addActor(musicVolumeSlider);
    }
    
    @Override
    public void show() {
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
        mainMenuMusic.stop();
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }
    
    private class Option1ButtonListener extends InputListener {
        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            return false;
        }
    }
    
    private class Option2ButtonListener extends InputListener {
        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            return false;
        }
    }
    
    private class BackButtonListener extends InputListener {
        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            game.setScreen(new MainMenu(game));
            return false;
        }
    }
    
    private class MusicVolumeSliderListener extends InputListener {
        public void changed (ChangeEvent event, Actor actor) {
            Gdx.app.log("UITest", "slider: ");
        }
    }

}
