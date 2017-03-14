package durianhln.polymorph.screen;

import java.awt.Dimension;
import java.util.EventListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import durianhln.polymorph.game.PolyGame;
import durianhln.polymorph.gameobject.Polymorph;

/**
 * 
 * @author Evan
 */
public class MainMenu implements Screen {

    private Polymorph game;
    private Dimension screenSize;
    
    private TextureAtlas buttonAtlas;
    private BitmapFont font;
    private Texture background;
    
    private Stage stage;
    private Skin buttonSkin;
    private TextButtonStyle buttonStyle;
    private TextButton playButton;
    private TextButton settingsButton;

    public MainMenu(Polymorph game) {
        this.game = game;
        
        background = new Texture("raw/background.png");
        font = new BitmapFont(false);
        screenSize = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        stage = new Stage();
        stage.clear();
        initButtons();
        Gdx.input.setInputProcessor(stage);
    }

    public void initButtons() {
        buttonAtlas = new TextureAtlas("buttons/buttons.pack");
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonAtlas);
        
        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.up = buttonSkin.getDrawable("ButtonUp");
        buttonStyle.down = buttonSkin.getDrawable("ButtonDown");
        buttonStyle.font = font;
        
        playButton = new TextButton("PLAY", buttonStyle);
        playButton.setSize(128, 32);
        playButton.setPosition(screenSize.width/2-64, screenSize.height/2-16);
        playButton.addListener(new PlayButtonListener());
        
        settingsButton = new TextButton("SETTINGS", buttonStyle);
        settingsButton.setSize(128, 32);
        settingsButton.setPosition(playButton.getX(), playButton.getY()-settingsButton.getHeight());
       
        stage.addActor(playButton);
        stage.addActor(settingsButton);
    }
    
    
    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }
    
    private class PlayButtonListener extends InputListener {
        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            game.setScreen(new GameScreen(game));
            return false;
        }
    }
    
    /*private class SettingsButtonListener extends InputListener {
        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            game.setScreen(new SettingsScreen(game));
            return false;
        }
    }*/
}
