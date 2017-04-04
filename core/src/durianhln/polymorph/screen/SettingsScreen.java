package durianhln.polymorph.screen;

import java.awt.Dimension;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

import durianhln.polymorph.game.Match;
import durianhln.polymorph.gameobject.Polymorph;

/**
*
* @author Evan
*/
public class SettingsScreen implements Screen {
    
    private Polymorph polymorph;
    private Dimension screenSize;
    
    private AssetManager assetManager;
    private Music mainMenuMusic;

    private Texture background;
    private Texture musicVolumeTexture;
    private Texture soundVolumeTexture;
    private Stage stage;

    private Slider musicVolumeSlider;
    private Slider soundVolumeSlider;
    
    public SettingsScreen(Polymorph polymorph) {
        this.polymorph = polymorph;
        assetManager = polymorph.getAssetManager();
        
        screenSize = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        backButton.addListener(new InputListener(){
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        	    polymorph.setScreen(new MainMenu(polymorph));
        	    return false;
            }
        });
        
        Skin sliderSkin = new Skin(Gdx.files.internal("uiskin.json"));
        
        musicVolumeTexture = new Texture(Gdx.files.internal("raw/musicvolume.png"));
        musicVolumeSlider = new Slider(0f, 1f, 0.1f, false, sliderSkin);
        musicVolumeSlider.setValue(polymorph.getMusicVolume());
        System.out.println("INIT: " + musicVolumeSlider.getValue());
        musicVolumeSlider.setAnimateDuration(0.05f);
        musicVolumeSlider.setPosition(screenSize.width/2-musicVolumeSlider.getWidth()/2, 2*screenSize.height/3);
        musicVolumeSlider.addListener(new ChangeListener(){
        	public void changed (ChangeEvent event, Actor actor) {
        	    polymorph.setMusicVolume(musicVolumeSlider.getValue());
        	    mainMenuMusic.setVolume(polymorph.getMusicVolume());
            }
        });
        
        soundVolumeTexture = new Texture(Gdx.files.internal("raw/soundvolume.png"));
        soundVolumeSlider = new Slider(0f, 1f, 0.1f, false, sliderSkin);
        soundVolumeSlider.setValue(polymorph.getMusicVolume());
        System.out.println("INIT: " + soundVolumeSlider.getValue());
        soundVolumeSlider.setAnimateDuration(0.05f);
        soundVolumeSlider.setPosition(screenSize.width/2-soundVolumeSlider.getWidth()/2, screenSize.height/2 - 2*soundVolumeTexture.getHeight());
        soundVolumeSlider.addListener(new ChangeListener(){
        	public void changed (ChangeEvent event, Actor actor) {
                polymorph.setSoundVolume(soundVolumeSlider.getValue());
                for(Match match : Match.values()) {
                    match.getSound().setVolume(match.getSound().play(0.0f), polymorph.getSoundVolume());
                }
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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, screenSize.width, screenSize.height);
        stage.getBatch().draw(musicVolumeTexture, musicVolumeSlider.getX()-25, musicVolumeSlider.getY()+musicVolumeSlider.getHeight(), 200, 50);
        stage.getBatch().draw(soundVolumeTexture, soundVolumeSlider.getX()-25, soundVolumeSlider.getY()+soundVolumeSlider.getHeight(), 200, 50);
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
    /*
    private class BackButtonListener extends InputListener {
        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            polymorph.setScreen(new MainMenu(polymorph));
            return false;
        }
    }
    
    private class MusicVolumeSliderListener extends ChangeListener {
        public void changed (ChangeEvent event, Actor actor) {
            polymorph.setMusicVolume(musicVolumeSlider.getValue());
            mainMenuMusic.setVolume(polymorph.getMusicVolume());
        }
    }
    
    private class SoundVolumeSliderListener extends ChangeListener {
        public void changed (ChangeEvent event, Actor actor) {
            polymorph.setSoundVolume(soundVolumeSlider.getValue());
            for(Match match : Match.values()) {
                match.getSound().setVolume(polymorph.getSoundVolume());
            }
        }
    }
    */
}
