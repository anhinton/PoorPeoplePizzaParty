package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiSize;

public class SettingsScreen implements InputProcessor, Screen {

    private final PoorPeoplePizzaParty game;
    private final Stage stage;
    private final Table table;

    public SettingsScreen(final PoorPeoplePizzaParty game) {
        this.game = game;

        game.assets.loadSettingsScreenAssets();

        OrthographicCamera camera = new OrthographicCamera();
        int screenWidth = Gdx.graphics.getBackBufferWidth();
        int screenHeight = Gdx.graphics.getBackBufferHeight();
        FitViewport viewport = new FitViewport(
                UiSize.getViewportWidth(screenWidth, screenHeight),
                UiSize.getViewportHeight(screenWidth, screenHeight),
                camera);
        camera.setToOrtho(false, viewport.getScreenHeight(),
                viewport.getScreenHeight());
        stage = new Stage(viewport, game.batch);
        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);
        initializeTable(viewport.getScreenWidth(), viewport.getScreenHeight());

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
    }

    private void initializeTable(int viewportWidth, int viewportHeight) {

        int buttonWidthHalf = UiSize.getButtonWidthHalf(viewportWidth, viewportHeight);
        int buttonWidthFull = UiSize.getButtonWidthFull(viewportWidth, viewportHeight);
        int buttonHeight = UiSize.getButtonHeight(viewportHeight);
        int padding = UiSize.getPadding(viewportHeight);

        // Settings title Label
        Label settingsTitleLabel = new Label(game.bundle.get("settingsTitleLabel"),
                game.uiSkin, "default");

        // create SliderStlye
        Slider.SliderStyle sliderStyle =
                game.uiSkin.get("default-horizontal",
                        Slider.SliderStyle.class);

        // Sound Volume widgets
        // Slider
        final Slider soundVolumeSlider = new Slider(0, 1, .05f,
                false, sliderStyle);
        soundVolumeSlider.setValue(getSoundVolume());
        soundVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setSoundVolume(soundVolumeSlider.getValue());
            }
        });
        // Label
        Label soundVolumeLabel = new Label(game.bundle.get("soundVolumeLabel") + ":",
                game.uiSkin, "default");
        soundVolumeLabel.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                soundVolumeSlider.setValue(0);
            }
        });
        // Mute Image
        Image soundVolumeMuteImage = new Image(
                game.assets.get("graphics/icons/volume_mute.png",
                Texture.class));
        soundVolumeMuteImage.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                soundVolumeSlider.setValue(0);
            }
        });
        // Full volume image
        Image soundVolumeFullImage = new Image(
                game.assets.get("graphics/icons/volume_up.png",
                Texture.class));
        soundVolumeFullImage.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                soundVolumeSlider.setValue(1);
            }
        });

        // Music Volume widgets
        // Slider
        final Slider musicVolumeSlider = new Slider(0, 1, .05f,
                false, sliderStyle);
        musicVolumeSlider.setValue(getMusicVolume());
        musicVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setMusicVolume(musicVolumeSlider.getValue());
            }
        });
        // Label
        Label musicVolumeLabel = new Label(game.bundle.get("musicVolumeLabel") + ":",
                game.uiSkin,"default");
        musicVolumeLabel.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                musicVolumeSlider.setValue(0);
            }
        });
        // Mute Image
        Image musicVolumeMuteImage = new Image(
                game.assets.get("graphics/icons/volume_mute.png",
                Texture.class));
        musicVolumeMuteImage.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                musicVolumeSlider.setValue(0);
            }
        });
        // Full volume image
        Image musicVolumeFullImage = new Image(
                game.assets.get("graphics/icons/volume_up.png",
                Texture.class));
        musicVolumeFullImage.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                musicVolumeSlider.setValue(1);
            }
        });

        // create Settings Credits Button
        TextButton creditsButton = new TextButton(game.bundle.get("creditsButton"), game.uiSkin, "default");
        creditsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO: load CreditsScreen
            }
        });

        // create Settings Back Button
        ImageButton.ImageButtonStyle backButtonStyle =
                new ImageButton.ImageButtonStyle(
                        game.uiSkin.get("default", Button.ButtonStyle.class));
        Sprite backSprite = new Sprite(
                game.assets.get("graphics/icons/back.png",
                        Texture.class));
        backSprite.setSize(Constants.UI_ICON_RATIO * viewportHeight,
                Constants.UI_ICON_RATIO * viewportHeight);
        backButtonStyle.imageUp = new SpriteDrawable(backSprite);
        ImageButton backButton = new ImageButton(backButtonStyle);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                goBack();
            }
        });
        Table bottomButtons = new Table();
        bottomButtons.add(creditsButton)
                .bottom().left()
                .expand()
                .prefSize(buttonWidthFull, buttonHeight)
                .space(padding);
        bottomButtons.add(backButton)
                .bottom().right()
                .prefSize(buttonWidthHalf, buttonHeight)
                .space(padding);

        table.clear();
        table.left().top()
                .pad(padding);
        table.add(settingsTitleLabel)
                .colspan(4)
                .space(padding);
        table.row();
        table.add(soundVolumeLabel)
                .space(padding);
        table.add(soundVolumeMuteImage)
                .size(Constants.UI_ICON_RATIO * viewportHeight,
                        Constants.UI_ICON_RATIO * viewportHeight)
                .space(padding);
        table.add(soundVolumeSlider)
                .space(padding)
                .prefWidth(viewportWidth);
        table.add(soundVolumeFullImage)
                .size(Constants.UI_ICON_RATIO * viewportHeight)
                .space(padding);
        table.row();
        table.add(musicVolumeLabel)
                .space(padding);
        table.add(musicVolumeMuteImage)
                .size(Constants.UI_ICON_RATIO * viewportHeight)
                .space(padding);
        table.add(musicVolumeSlider)
                .space(padding)
                .fillX();
        table.add(musicVolumeFullImage)
                .size(Constants.UI_ICON_RATIO * viewportHeight)
                .space(padding);
        table.row();
        table. add(bottomButtons)
                .colspan(4)
                .expand()
                .prefSize(viewportWidth, viewportHeight);
    }

    private void goBack() {
        game.setScreen(new TitleScreen(game));
        dispose();
    }

    private float getSoundVolume() {
        return game.getSoundVolume();
    }

    private void setSoundVolume(float soundVolume) {
        game.setSoundVolume(soundVolume);
    }

    private float getMusicVolume() {
        return game.getMusicVolume();
    }

    private void setMusicVolume(float musicVolume) {
        game.setMusicVolume(musicVolume);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // clear screen
        Gdx.gl.glClearColor(Constants.BG_COLOUR.r, Constants.BG_COLOUR.g,
                Constants.BG_COLOUR.b, Constants.BG_COLOUR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // draw sprites
        game.batch.begin();
        game.batch.end();

        // update UI camera
        stage.getViewport().apply();
        stage.getCamera().update();
        stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
        stage.act(delta);

        // draw UI
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK
                | keycode == Input.Keys.ESCAPE) {
            Gdx.app.exit();
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
