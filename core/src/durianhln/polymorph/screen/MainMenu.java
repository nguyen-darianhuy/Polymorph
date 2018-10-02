package durianhln.polymorph.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import durianhln.polymorph.Polymorph;
import durianhln.polymorph.gameobject.ShapeColor;

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
        this.polymorph = polymorph;

        initAssets();
        initStage();
    }

    private void initAssets() {
        AssetManager assetManager = polymorph.getAssetManager();
        TextureAtlas textureAtlas = assetManager.get(Polymorph.MASTER_PATH);

        mainMenuMusic = assetManager.get(Polymorph.MAIN_MENU_MUSIC_PATH);
        mainMenuMusic.setLooping(true);

        background = textureAtlas.findRegion("settingsscreen");
    }

    private void initStage() {
        stage = new Stage(new StretchViewport(Polymorph.WORLD_WIDTH, Polymorph.WORLD_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        createTitle();
        createButtons();
    }

    private BitmapFont generateFont(int size, boolean bold) {
        AssetManager assetManager = polymorph.getAssetManager();
        FreeTypeFontGenerator fontGenerator = bold ? assetManager.get(Polymorph.FONT_BOLD_PATH, FreeTypeFontGenerator.class)
                                                   : assetManager.get(Polymorph.FONT_NORMAL_PATH, FreeTypeFontGenerator.class);

        FreeTypeFontParameter fontSettings = new FreeTypeFontParameter();
        fontSettings.size = size;
        fontSettings.minFilter = Texture.TextureFilter.Linear;
        fontSettings.magFilter = Texture.TextureFilter.Linear;

        return fontGenerator.generateFont(fontSettings);
    }

    private void createTitle() {
        BitmapFont titleFont = generateFont(124, true);
        titleFont.getData().markupEnabled = true;
        Label title = new Label(String.format("[#%s]P[] o l y [#%s]m[] o r p [#%s]h[]",
                                    ShapeColor.RED.color, ShapeColor.GREEN.color, ShapeColor.BLUE.color),
                                new LabelStyle(titleFont, Color.WHITE));
        title.setPosition(Polymorph.WORLD_WIDTH/2 - title.getWidth()/2, 4*Polymorph.WORLD_HEIGHT/5.5f);

        stage.addActor(title);
    }

    private void createButtons() {
        BitmapFont buttonFont = generateFont(140, false); //Bug where if font size is > ~140, text is not rendered!
        buttonFont.getData().setScale(1.1f);
        TextureAtlas textureAtlas = polymorph.getAssetManager().get(Polymorph.MASTER_PATH, TextureAtlas.class);
        TextButton.TextButtonStyle buttonStyle = new TextButtonStyle(new TextureRegionDrawable(textureAtlas.findRegion("buttontemplate")),
                                                                     new TextureRegionDrawable(textureAtlas.findRegion("downbuttontemplate")),
                                                                     null, buttonFont);
        buttonStyle.fontColor = Color.GRAY;

        TextButton playButton = new TextButton("Play", buttonStyle);
        playButton.setSize(4 * Polymorph.WORLD_WIDTH / 5, Polymorph.WORLD_HEIGHT / 8);
        playButton.setPosition(Polymorph.WORLD_WIDTH / 2 - playButton.getWidth() / 2, Polymorph.WORLD_HEIGHT / 2);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                polymorph.setScreen(new GameScreen(polymorph));
                mainMenuMusic.stop();
            }
        });
        stage.addActor(playButton);

        TextButton settingsButton = new TextButton("Settings", buttonStyle);
        settingsButton.setSize(4 * Polymorph.WORLD_WIDTH / 5, Polymorph.WORLD_HEIGHT / 8);
        settingsButton.setPosition(playButton.getX(), playButton.getY() - 5 * playButton.getHeight() / 4);
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                polymorph.setScreen(new SettingsScreen(polymorph));
            }
        });
        stage.addActor(settingsButton);

        //TODO change to another button
        TextButton otherButton = new TextButton("Other", buttonStyle);
        otherButton.setSize(4 * Polymorph.WORLD_WIDTH / 5, Polymorph.WORLD_HEIGHT / 8);
        otherButton.setPosition(settingsButton.getX(), settingsButton.getY() - 5 * settingsButton.getHeight() / 4);
        otherButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        stage.addActor(otherButton);

        //TODO scale it up
        ImageButton creditsButton = new ImageButton(new TextureRegionDrawable(textureAtlas.findRegion("creditsbutton")),
                new TextureRegionDrawable(textureAtlas.findRegion("creditsbutton")));
        creditsButton.setPosition(Polymorph.WORLD_WIDTH/25, Polymorph.WORLD_HEIGHT/50);
        creditsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
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

        stage.act(delta);
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
