package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

class TitleScreen implements InputProcessor, Screen {

    private final PoorPeoplePizzaParty game;
    private final FitViewport viewport;
    private final Stage stage;
    private final Table table;
    private final int buttonWidthFull;
    private final int buttonWidthHalf;
    private final int buttonSize;
    private final int padding;
    private final Sound soundVolumeSound;
    private float oldSoundVolume;
    private Constants.CurrentTitleMenu currentMenu;
    private final Preferences settings;

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
    private final Image soundVolumeDownImage;
    private final Image soundVolumeUpImage;
    private final Label musicVolumeLabel;
    private final Label musicVolumeValueLabel;
    private final Slider musicVolumeSlider;
    private final Image musicVolumeDownImage;
    private final Image musicVolumeUpImage;
    private final TextButton settingsCreditsButton;
    private final ImageButton settingsBackButton;

    // Credits assets
    private final Label creditsTitleLabel;
    private final ScrollPane creditsPane;
    private final ImageButton creditsBackButton;

    // Quit screen assets
    private final Label quitLabel;
    private final TextButton quitYesButton;
    private final TextButton quitNoButton;

    TitleScreen(final PoorPeoplePizzaParty game) {
        this.game = game;

        settings = Gdx.app.getPreferences("nz.co.canadia.poorpeoplepizzaparty.settings");
        game.setSoundVolume(settings.getFloat("soundVolume", Constants.SOUND_VOLUME_DEFAULT));
        oldSoundVolume = getSoundVolume();

        game.assets.loadThemeMusic();
        game.setMusic("music/ThemeMusic.mp3");
        game.setMusicVolume(settings.getFloat("musicVolume", Constants.MUSIC_VOLUME_DEFAULT));
        if(Gdx.app.getType() != Application.ApplicationType.WebGL) {
            game.playMusicLooping();
        }

        game.assets.loadTitleScreenSounds();
        soundVolumeSound = game.assets.get("sounds/toppings/salami.mp3", Sound.class);

        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(
                Constants.GAME_WIDTH,
                Constants.GAME_HEIGHT,
                camera);
        camera.setToOrtho(false, viewport.getScreenHeight(),
                viewport.getScreenHeight());

        stage = new Stage(viewport, game.batch);
        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);

        buttonWidthFull = Constants.BUTTON_WIDTH_FULL;
        buttonWidthHalf = Constants.BUTTON_WIDTH_HALF;
        buttonSize = Constants.BUTTON_HEIGHT;
        padding = Constants.UNIT;

        /* Title menu assets */

        // load TextureAtlas
        TextureAtlas atlas = game.assets.get("graphics/graphics.atlas", TextureAtlas.class);

        // create header Image
        header = new Image(atlas.findRegion("headers/titleScreen"));

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
        Sprite settingsSprite = new Sprite(atlas.findRegion("icons/settings"));
        settingsSprite.setSize(Constants.UI_ICON_SIZE,
                Constants.UI_ICON_SIZE);
        settingsButtonStyle.imageUp = new SpriteDrawable(settingsSprite);
        settingsButton = new ImageButton(settingsButtonStyle);
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setCurrentMenu(Constants.CurrentTitleMenu.SETTINGS);
                game.playMusicLooping();
            }
        });

        // create Quit Button
        ImageButton.ImageButtonStyle quitButtonStyle =
                new ImageButton.ImageButtonStyle(
                        game.uiSkin.get("default", Button.ButtonStyle.class));
        Sprite quitSprite = new Sprite(atlas.findRegion("icons/close"));
        quitSprite.setSize(Constants.UI_ICON_SIZE,
                Constants.UI_ICON_SIZE);
        quitButtonStyle.imageUp = new SpriteDrawable(quitSprite);
        quitButton = new ImageButton(quitButtonStyle);
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setCurrentMenu(Constants.CurrentTitleMenu.QUIT);
            }
        });

        /* Settings menu assets */

        // Settings title Label
        settingsTitleLabel = new Label(game.bundle.get("settingsTitleLabel"),
                game.uiSkin, "default");

        // create SliderStlye
        Slider.SliderStyle sliderStyle =
                game.uiSkin.get("default-horizontal",
                        Slider.SliderStyle.class);

        /* Sound Volume widgets */
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
                oldSoundVolume = getSoundVolume();
                setSoundVolume(soundVolumeSlider.getValue());
            }
        });
        // Mute Image
        soundVolumeDownImage = new Image(atlas.findRegion("icons/volume_mute"));
        soundVolumeDownImage.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                decreaseSoundVolume();
                soundVolumeSound.play(getSoundVolume());
            }
        });
        soundVolumeDownImage.addListener(new ActorGestureListener() {
            public boolean longPress(Actor actor, float x, float y) {
                muteSoundVolume();
                return true;
            }
        });
        // Full volume image
        soundVolumeUpImage = new Image(atlas.findRegion("icons/volume_up"));
        soundVolumeUpImage.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                increaseSoundVolume();
                soundVolumeSound.play(getSoundVolume());
            }
        });
        soundVolumeUpImage.addListener(new ActorGestureListener() {
           public boolean longPress(Actor actor, float x, float y) {
               fullSoundVolume();
               return true;
           }
        });

        /* Music Volume widgets */
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
        musicVolumeDownImage = new Image(atlas.findRegion("icons/volume_mute"));
        musicVolumeDownImage.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                decreaseMusicVolume();
            }
        });
        musicVolumeDownImage.addListener(new ActorGestureListener() {
            public boolean longPress(Actor actor, float x, float y) {
                muteMusicVolume();
                return true;
            }
        });
        // Full volume image
        musicVolumeUpImage = new Image(atlas.findRegion("icons/volume_up"));
        musicVolumeUpImage.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                increaseMusicVolume();
            }
        });
        musicVolumeUpImage.addListener(new ActorGestureListener() {
            public boolean longPress(Actor actor, float x, float y) {
                fullMusicVolume();
                return true;
            }
        });

        // create Settings Credits Button
        settingsCreditsButton = new TextButton(game.bundle.get("creditsButton"), game.uiSkin, "default");
        settingsCreditsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setCurrentMenu(Constants.CurrentTitleMenu.CREDITS);
            }
        });

        // create Settings Back Button
        ImageButton.ImageButtonStyle backButtonStyle =
                new ImageButton.ImageButtonStyle(
                        game.uiSkin.get("default", Button.ButtonStyle.class));
        Sprite backSprite = new Sprite(atlas.findRegion("icons/back"));
        backSprite.setSize(Constants.UI_ICON_SIZE,
                Constants.UI_ICON_SIZE);
        backButtonStyle.imageUp = new SpriteDrawable(backSprite);
        settingsBackButton = new ImageButton(backButtonStyle);
        settingsBackButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                settings.flush();
                setCurrentMenu(Constants.CurrentTitleMenu.TITLE);
            }
        });

        /* Credits assets */

        // Credits title Label
        creditsTitleLabel = new Label(game.bundle.get("creditsTitleLabel"), game.uiSkin,
                "default");

        // credits ScrollPane
        FileHandle file = Gdx.files.internal("i18n/credits.txt");
        String creditsText = file.readString();
        Label creditsLabel = new Label(creditsText, game.uiSkin, "credits");
        creditsLabel.setWrap(true);
        ScrollPane.ScrollPaneStyle creditsPaneStyle =
                new ScrollPane.ScrollPaneStyle(
                        game.uiSkin.get("credits", ScrollPane.ScrollPaneStyle.class));
        creditsPane = new ScrollPane(creditsLabel, creditsPaneStyle);
        creditsPane.setFadeScrollBars(false);

        // credits Back Button
        creditsBackButton = new ImageButton(backButtonStyle);
        creditsBackButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                goBack();
            }
        });

        /* Quit screen assets */

        // Quit Label
        quitLabel = new Label(game.bundle.get("quitLabel"), game.uiSkin,
                "default");

        // Quit Yes button
        quitYesButton = new TextButton(game.bundle.get("quitYes"), game.uiSkin,
                "default");
        quitYesButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                quit();
            }
        });

        // Quit No button
        quitNoButton = new TextButton(game.bundle.get("quitNo"), game.uiSkin,
                "default");
        quitNoButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setCurrentMenu(Constants.CurrentTitleMenu.TITLE);
            }
        });

        setCurrentMenu(Constants.CurrentTitleMenu.TITLE);

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
        if (Gdx.app.getType().equals(Application.ApplicationType.Desktop)) {
            mainMenuTable.add(quitButton)
                    .prefSize(buttonSize)
                    .space(padding);
        }

        table.clear();
        table.bottom()
                .pad(padding);
        table.add(header)
                .space(padding);
        table.row();
        table.add(mainMenuTable);
    }

    private void showSettingsMenu() {

        // Volumes table
        Table volumesTable = new Table();
        volumesTable.add(soundVolumeLabel)
                .space(padding);
        volumesTable.add(soundVolumeDownImage)
                .size(Constants.UI_ICON_SIZE)
                .space(padding);
        volumesTable.add(soundVolumeSlider)
                .prefWidth(viewport.getScreenWidth())
                .fillX()
                .space(padding);
        volumesTable.add(soundVolumeUpImage)
                .size(Constants.UI_ICON_SIZE)
                .space(padding);
        volumesTable.add(soundVolumeValueLabel)
                .width(buttonSize)
                .space(padding);
        volumesTable.row();
        volumesTable.add(musicVolumeLabel)
                .space(padding);
        volumesTable.add(musicVolumeDownImage)
                .size(Constants.UI_ICON_SIZE)
                .space(padding);
        volumesTable.add(musicVolumeSlider)
                .fillX()
                .space(padding);
        volumesTable.add(musicVolumeUpImage)
                .size(Constants.UI_ICON_SIZE)
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

    private void showCredits() {
        table.clear();
        table.pad(padding);
        table.add(creditsTitleLabel)
                .left()
                .space(padding);
        table.row();
        table.add(creditsPane)
                .prefWidth(viewport.getScreenWidth())
                .space(padding);
        table.add(creditsBackButton)
                .bottom()
                .height(buttonSize)
                .width(buttonWidthHalf)
                .space(padding);
    }

    private void showQuit() {
        table.clear();
        table.center();
        table.pad(padding);
        table.add(quitLabel)
                .colspan(2)
                .space(padding);
        table.row();
        table.add(quitYesButton)
                .prefSize(buttonWidthHalf, buttonSize)
                .space(padding);
        table.add(quitNoButton)
                .prefSize(buttonWidthHalf, buttonSize)
                .space(padding);
    }

    private void goBack() {
        switch (currentMenu) {
            case TITLE:
                if (Gdx.app.getType() == Application.ApplicationType.Desktop
                        | Gdx.app.getType() == Application.ApplicationType.Android) {
                    setCurrentMenu(Constants.CurrentTitleMenu.QUIT);
                }
                break;
            case SETTINGS:
                setCurrentMenu(Constants.CurrentTitleMenu.TITLE);
                break;
            case CREDITS:
                setCurrentMenu(Constants.CurrentTitleMenu.SETTINGS);
                break;
            case QUIT:
                quit();
                break;
        }
    }

    private void setCurrentMenu(Constants.CurrentTitleMenu menu) {
        currentMenu = menu;
        switch (menu) {
            case TITLE:
                showTitleMenu();
                break;
            case SETTINGS:
                showSettingsMenu();
                break;
            case CREDITS:
                showCredits();
                break;
            case QUIT:
                showQuit();
                break;
        }
    }

    private void play() {
        game.setScreen(new PizzaScreen(game, true));
        dispose();
    }

    private void quit() {
        Gdx.app.exit();
    }

    private String printVolume(float volume) {
        return Integer.toString(MathUtils.round(volume * 100));
    }

    private void decreaseMusicVolume() {
        setMusicVolume(getMusicVolume() - Constants.VOLUME_STEP);
    }

    private void increaseMusicVolume() {
        setMusicVolume(getMusicVolume() + Constants.VOLUME_STEP);
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
        settings.putFloat("soundVolume", getSoundVolume());
    }

    private void fullSoundVolume() {
        setSoundVolume(1);
    }

    private void muteSoundVolume() {
        setSoundVolume(0);
    }

    private float getMusicVolume() {
        return game.getMusicVolume();
    }

    private void setMusicVolume(float musicVolume) {
        game.setMusicVolume(musicVolume);
        musicVolumeValueLabel.setText(printVolume(getMusicVolume()));
        musicVolumeSlider.setValue(getMusicVolume());
        settings.putFloat("musicVolume", getMusicVolume());
    }

    private void fullMusicVolume() {
        setMusicVolume(1);
    }

    private void muteMusicVolume() {
        setMusicVolume(0);
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

        if (oldSoundVolume != getSoundVolume()) {
            oldSoundVolume = getSoundVolume();
            soundVolumeSound.play(getSoundVolume());
        }
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
            goBack();
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
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
