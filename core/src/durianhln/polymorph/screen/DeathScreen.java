package durianhln.polymorph.screen;

import java.awt.Dimension;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import durianhln.polymorph.Polymorph;

/**
 * 
 * @author Jason
 *
 */


public class DeathScreen implements Screen {
    	
	private Polymorph polymorph;
    private Dimension screenSize; //TODO remove this
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private TextureAtlas buttonAtlas; //TODO repack all raw and remove this
    private Music DeathScreenMusic;//TODO find death music
    private Texture background;//TODO make a unique background
    private Stage stage;
    private int score;
    
    public DeathScreen(Polymorph polymorph,int playerscore) {
        this.polymorph = polymorph;
        score=playerscore;
        AssetManager assetManager = polymorph.getAssetManager();

        DeathScreenMusic = assetManager.get(Polymorph.MAIN_MENU_MUSIC_PATH);
        DeathScreenMusic.setLooping(true);
        
        background = assetManager.get(Polymorph.MAIN_MENU_BACKGROUND_PATH); //TODO make a unique background for the death screen
        background.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        screenSize = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenSize.width, screenSize.height); //change this
        batch=new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        
        stage = new Stage();
        stage.clear();
        font = new BitmapFont(false);

        buttonAtlas = assetManager.get(Polymorph.MASTER_PATH, TextureAtlas.class);
        initButtons(score,buttonAtlas);
        Gdx.input.setInputProcessor(stage);
        
    }

    public void initButtons(int score,TextureAtlas buttonAtlas) {
        Skin buttonSkin = new Skin();
        buttonSkin.addRegions(buttonAtlas);

        //TODO FIX THIS SHIT INDENTATION
        //TODO Long-term fix the magic numbers
        ImageButton playButton = new ImageButton(buttonSkin.getDrawable("playbutton"),
        		                                                        buttonSkin.getDrawable("playbutton"));
        playButton.setSize(256, 64);
        playButton.setPosition(screenSize.width/2-playButton.getWidth()/2,
        		               screenSize.height/2-playButton.getHeight()/2+50);
        playButton.addListener(new InputListener() {
        	
        	@Override
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		polymorph.setScreen(new GameScreen(polymorph));
        		DeathScreenMusic.stop();
        		return true;
        	}
        });

        stage.addActor(playButton);
        
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, screenSize.width, screenSize.height);
        font.draw(stage.getBatch(), String.format("Score: %d\n", score), screenSize.width - 100, screenSize.height-10);
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
    
    
    
}
