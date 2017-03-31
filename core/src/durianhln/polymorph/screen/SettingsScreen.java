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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import durianhln.polymorph.gameobject.Polymorph;

/**
*
* @author Evan
*/
public class SettingsScreen implements Screen {
    
    private Polymorph game;
    private Dimension screenSize;
    
    private AssetManager assetManager;
    private TextureAtlas buttonAtlas;
    private Music mainMenuMusic;
    private BitmapFont font;
    private Texture background;
    
    private Stage stage;
    private Skin sliderSkin;
    private TextButton option1Button;
    private TextButton option2Button;
    private TextButton backButton;    

    public SettingsScreen(Polymorph game) {
        this.game = game;
        assetManager = game.getAssetManager();
        
        mainMenuMusic = assetManager.get(Polymorph.MAIN_MENU_MUSIC_PATH, Music.class);
        background = assetManager.get("raw/background.png"); //change to different background
        font = new BitmapFont(false);
        screenSize = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        stage = new Stage();
        stage.clear();
        initButtons();
        Gdx.input.setInputProcessor(stage);
    }
    
    public void initButtons() {
        buttonAtlas = assetManager.get(Polymorph.BUTTONS_PATH);
        
        sliderSkin = new Skin();
        sliderSkin.add("slider", new Texture(Polymorph.SLIDER_PATH));
        
        Slider musicVolumeSlider = new Slider(0, 10, 1, false, sliderSkin);
        musicVolumeSlider.setPosition(100,100);
        
        /*TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.up = buttonSkin.getDrawable("settingsbutton");
        buttonStyle.down = buttonSkin.getDrawable("settingsbutton");
        buttonStyle.font = font;
        
        option1Button = new TextButton("OPTION1", buttonStyle);
        option1Button.setSize(128, 32);
        option1Button.setPosition(screenSize.width/2-64, screenSize.height/2-16);
        option1Button.addListener(new Option1ButtonListener());
        
        option2Button = new TextButton("OPTION2", buttonStyle);
        option2Button.setSize(128, 32);
        option2Button.setPosition(option1Button.getX(), option1Button.getY()-option2Button.getHeight()-16);
        option2Button.addListener(new Option2ButtonListener());
        
        backButton = new TextButton("BACK", buttonStyle);
        backButton.setSize(128, 32);
        backButton.setPosition(0, screenSize.height-backButton.getHeight());
        backButton.addListener(new backButtonListener());
        
        stage.addActor(option1Button);
        stage.addActor(option2Button);
        stage.addActor(backButton);*/
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
    
    private class backButtonListener extends InputListener {
        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            game.setScreen(new MainMenu(game));
            return false;
        }
    }

}
