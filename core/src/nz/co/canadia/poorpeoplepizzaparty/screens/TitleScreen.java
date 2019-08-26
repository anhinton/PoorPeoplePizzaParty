package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiSize;

public class TitleScreen implements InputProcessor, Screen {

    private final PoorPeoplePizzaParty game;
    private final FitViewport viewport;
    private final Stage stage;
    private final Table table;
    private final int buttonWidthFull;
    private final int buttonWidthHalf;
    private final int buttonSize;
    private final int padding;

    // Title menu assets
    private final Image header;
    private final TextButton playButton;
    private final ImageButton settingsButton;
    private final ImageButton quitButton;

    // Settings menu assets
    private final Label soundVolumeValueLabel;
    private final Label soundVolumeLabel;
    private final Slider soundVolumeSlider;
    private final Label settingsTitleLabel;
    private final Image soundVolumeMuteImage;
    private final Image soundVolumeFullImage;
    private final Label musicVolumeLabel;
    private final Label musicVolumeValueLabel;
    private final Slider musicVolumeSlider;
    private final Image musicVolumeMuteImage;
    private final Image musicVolumeFullImage;
    private final TextButton settingsCreditsButton;
    private final ImageButton settingsBackButton;

    public TitleScreen(final PoorPeoplePizzaParty game) {
        this.game = game;

        game.assets.loadTitleScreenAssets();

        OrthographicCamera camera = new OrthographicCamera();
        int screenWidth = Gdx.graphics.getBackBufferWidth();
        int screenHeight = Gdx.graphics.getBackBufferHeight();
        viewport = new FitViewport(
                UiSize.getViewportWidth(screenWidth, screenHeight),
                UiSize.getViewportHeight(screenWidth, screenHeight),
                camera);
        camera.setToOrtho(false, viewport.getScreenHeight(),
                viewport.getScreenHeight());

        stage = new Stage(viewport, game.batch);
        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);

        buttonWidthFull = UiSize.getButtonWidthFull(viewport.getScreenWidth(), viewport.getScreenHeight());
        buttonWidthHalf = UiSize.getButtonWidthHalf(viewport.getScreenWidth(), viewport.getScreenHeight());
        buttonSize = UiSize.getButtonHeight(viewport.getScreenHeight());
        padding = UiSize.getPadding(viewport.getScreenHeight());

        // Title menu assets

        // create header Image
        header = new Image(game.assets.get("graphics/headers/titleScreen.png",
                Texture.class));

        // create Play Button
        playButton = new TextButton(game.bundle.get("playButton"), game.uiSkin, "default");
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                play();
            }
        });

        // create Settings button
        ImageButton.ImageButtonStyle settingsButtonStyle =
                new ImageButton.ImageButtonStyle(
                        game.uiSkin.get("default", Button.ButtonStyle.class));
        Sprite settingsSprite = new Sprite(
                game.assets.get("graphics/icons/settings.png",
                        Texture.class));
        settingsSprite.setSize(Constants.UI_ICON_RATIO * viewport.getScreenHeight(),
                Constants.UI_ICON_RATIO * viewport.getScreenHeight());
        settingsButtonStyle.imageUp = new SpriteDrawable(settingsSprite);
        settingsButton = new ImageButton(settingsButtonStyle);
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                showSettingsMenu();
            }
        });

        // create Quit Button
        ImageButton.ImageButtonStyle quitButtonStyle =
                new ImageButton.ImageButtonStyle(
                        game.uiSkin.get("default", Button.ButtonStyle.class));
        Sprite quitSprite = new Sprite(
                game.assets.get("graphics/icons/close.png",
                        Texture.class));
        quitSprite.setSize(Constants.UI_ICON_RATIO * viewport.getScreenHeight(),
                Constants.UI_ICON_RATIO * viewport.getScreenHeight());
        quitButtonStyle.imageUp = new SpriteDrawable(quitSprite);
        quitButton = new ImageButton(quitButtonStyle);
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                quit();
            }
        });

        // Settings menu assets

        // Settings title Label
        settingsTitleLabel = new Label(game.bundle.get("settingsTitleLabel"),
                game.uiSkin, "default");

        // create SliderStlye
        Slider.SliderStyle sliderStyle =
                game.uiSkin.get("default-horizontal",
                        Slider.SliderStyle.class);

        // Sound Volume widgets
        // Label
        soundVolumeLabel = new Label(game.bundle.get("soundVolumeLabel") + ":",
                game.uiSkin, "default");
        // Volume Level Label
        soundVolumeValueLabel = new Label(printVolume(getSoundVolume()),
                game.uiSkin, "default");
        soundVolumeValueLabel.setAlignment(Align.center);
        // Slider
        soundVolumeSlider = new Slider(0, 1, .05f,
                false, sliderStyle);
        soundVolumeSlider.setValue(getSoundVolume());
        soundVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setSoundVolume(soundVolumeSlider.getValue());
            }
        });
        // Mute Image
        soundVolumeMuteImage = new Image(
                game.assets.get("graphics/icons/volume_mute.png",
                        Texture.class));
        soundVolumeMuteImage.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                decreaseSoundVolume();
            }
        });
        // Full volume image
        soundVolumeFullImage = new Image(
                game.assets.get("graphics/icons/volume_up.png",
                        Texture.class));
        soundVolumeFullImage.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                increaseSoundVolume();
            }
        });

        // Music Volume widgets
        // Label
        musicVolumeLabel = new Label(game.bundle.get("musicVolumeLabel") + ":",
                game.uiSkin,"default");
        // Volume Level Label
        musicVolumeValueLabel = new Label(printVolume(getMusicVolume()),
                game.uiSkin, "default");
        musicVolumeValueLabel.setAlignment(Align.center);
        // Slider
        musicVolumeSlider = new Slider(0, 1, .05f,
                false, sliderStyle);
        musicVolumeSlider.setValue(getMusicVolume());
        musicVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setMusicVolume(musicVolumeSlider.getValue());
            }
        });
        // Mute Image
        musicVolumeMuteImage = new Image(
                game.assets.get("graphics/icons/volume_mute.png",
                        Texture.class));
        musicVolumeMuteImage.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                decreaseMusicVolume();
            }
        });
        // Full volume image
        musicVolumeFullImage = new Image(
                game.assets.get("graphics/icons/volume_up.png",
                        Texture.class));
        musicVolumeFullImage.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                increaseMusicVolume();
            }
        });

        // create Settings Credits Button
        settingsCreditsButton = new TextButton(game.bundle.get("creditsButton"), game.uiSkin, "default");
        settingsCreditsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO: show credits
            }
        });

        // create Settings Back Button
        ImageButton.ImageButtonStyle backButtonStyle =
                new ImageButton.ImageButtonStyle(
                        game.uiSkin.get("default", Button.ButtonStyle.class));
        Sprite backSprite = new Sprite(
                game.assets.get("graphics/icons/back.png",
                        Texture.class));
        backSprite.setSize(Constants.UI_ICON_RATIO * viewport.getScreenHeight(),
                Constants.UI_ICON_RATIO * viewport.getScreenHeight());
        backButtonStyle.imageUp = new SpriteDrawable(backSprite);
        settingsBackButton = new ImageButton(backButtonStyle);
        settingsBackButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                showTitleMenu();
            }
        });

        showTitleMenu();
        
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
    }

    private void showTitleMenu() {

        // Main Menu table
        Table mainMenuTable = new Table();
        mainMenuTable.add(playButton)
                .prefSize(buttonWidthFull, buttonSize)
                .right()
                .space(padding);
        mainMenuTable.add(settingsButton)
                .prefSize(buttonSize)
                .left()
                .space(padding);
        if (!Gdx.app.getType().equals(Application.ApplicationType.WebGL)) {
            mainMenuTable.add(quitButton)
                    .prefSize(buttonSize)
                    .space(padding);
        }

        table.clear();
        table.bottom()
                .pad(padding);
        table.add(header)
                .prefSize(
                        UiSize.getImageWidth(header.getPrefWidth(),
                                viewport.getScreenWidth()),
                        UiSize.getImageHeight(header.getPrefHeight(),
                                viewport.getScreenHeight()))
                .space(padding);
        table.row();
        table.add(mainMenuTable);
    }

    private void showSettingsMenu() {

        // Volumes table
        Table volumesTable = new Table();
        volumesTable.add(soundVolumeLabel)
                .space(padding);
        volumesTable.add(soundVolumeMuteImage)
                .size(Constants.UI_ICON_RATIO * viewport.getScreenWidth())
                .space(padding);
        volumesTable.add(soundVolumeSlider)
                .prefWidth(viewport.getScreenWidth())
                .fillX()
                .space(padding);
        volumesTable.add(soundVolumeFullImage)
                .size(Constants.UI_ICON_RATIO * viewport.getScreenWidth())
                .space(padding);
        volumesTable.add(soundVolumeValueLabel)
                .width(buttonSize)
                .space(padding);
        volumesTable.row();
        volumesTable.add(musicVolumeLabel)
                .space(padding);
        volumesTable.add(musicVolumeMuteImage)
                .size(Constants.UI_ICON_RATIO * viewport.getScreenWidth())
                .space(padding);
        volumesTable.add(musicVolumeSlider)
                .fillX()
                .space(padding);
        volumesTable.add(musicVolumeFullImage)
                .size(Constants.UI_ICON_RATIO * viewport.getScreenWidth())
                .space(padding);
        volumesTable.add(musicVolumeValueLabel)
                .width(buttonSize)
                .space(padding);

        Table bottomButtons = new Table();
        bottomButtons.add(settingsCreditsButton)
                .bottom().left()
                .expand()
                .prefSize(buttonWidthFull, buttonSize)
                .space(padding);
        bottomButtons.add(settingsBackButton)
                .bottom().right()
                .prefSize(buttonWidthHalf, buttonSize)
                .space(padding);

        table.clear();
        table.top()
                .pad(padding);
        table.add(settingsTitleLabel)
                .space(padding);
        table.row();
        table.add(volumesTable).prefWidth(viewport.getScreenWidth());
        table.row();
        table.add(bottomButtons)
                .expand()
                .prefSize(viewport.getScreenWidth(), viewport.getScreenHeight());
    }
    
    private void goBack() {
        
    }

    private void play() {
        game.setScreen(new PizzaScreen(game, true));
        dispose();
    }

    private void quit() {
        Gdx.app.exit();
    }

    private void decreaseMusicVolume() {
        setMusicVolume(getMusicVolume() - Constants.VOLUME_STEP);
    }

    private void increaseMusicVolume() {
        setMusicVolume(getMusicVolume() + Constants.VOLUME_STEP);
    }

    private String printVolume(float volume) {
        return Integer.toString(MathUtils.round(volume * 100));
    }

    private void increaseSoundVolume() {
        setSoundVolume(getSoundVolume() + Constants.VOLUME_STEP);
    }

    private void decreaseSoundVolume() {
        setSoundVolume(getSoundVolume() - Constants.VOLUME_STEP);
    }

    private float getSoundVolume() {
        return game.getSoundVolume();
    }

    private void setSoundVolume(float soundVolume) {
        game.setSoundVolume(soundVolume);
        soundVolumeValueLabel.setText(printVolume(getSoundVolume()));
        soundVolumeSlider.setValue(getSoundVolume());
    }

    private float getMusicVolume() {
        return game.getMusicVolume();
    }

    private void setMusicVolume(float musicVolume) {
        game.setMusicVolume(musicVolume);
        musicVolumeValueLabel.setText(printVolume(getMusicVolume()));
        musicVolumeSlider.setValue(getMusicVolume());
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
            quit();
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
