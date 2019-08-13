package nz.co.canadia.poorpeoplepizzaparty.ui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.I18NBundle;

import nz.co.canadia.poorpeoplepizzaparty.screens.TitleScreen;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiSize;

public class TitleUi extends Table {

    private final Image header;
    private final int buttonWidthHalf;
    private final int buttonWidthFull;
    private final int buttonHeight;
    private final int padding;
    private final Label soundVolumeLabel;
    private final Slider soundVolumeSlider;
    private final Label musicVolumeLabel;
    private final Slider musicVolumeSlider;
    private final TitleScreen titleScreen;
    private final ImageButton backButton;
    private final Label settingsTitleLabel;
    private final TextButton playButton;
    private final ImageButton settingsButton;
    private final ImageButton quitButton;
    private final int viewportHeight;
    private final int viewportWidth;
    private final Image musicVolumeMuteImage;
    private final Image musicVolumeFullImage;
    private final Image soundVolumeMuteImage;
    private final Image soundVolumeFullImage;
    private Constants.CurrentTitleMenu currentMenu;

    public TitleUi(int viewportWidth, int viewportHeight,
                   final TitleScreen titleScreen, final Skin skin,
                   final I18NBundle bundle,
                   final AssetManager assets) {

        super.setFillParent(true);

        this.titleScreen = titleScreen;
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;

        buttonWidthHalf = UiSize.getButtonWidthHalf(viewportWidth, viewportHeight);
        buttonWidthFull = UiSize.getButtonWidthFull(viewportWidth, viewportHeight);
        buttonHeight = UiSize.getButtonHeight(viewportHeight);
        padding = UiSize.getPadding(viewportHeight);

        header = new Image(assets.get("graphics/headers/titleScreen.png",
                Texture.class));

        // create Play Button
        playButton = new TextButton(bundle.get("playButton"), skin, "default");
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                titleScreen.play();
            }
        });

        // create Settings button
        ImageButton.ImageButtonStyle settingsButtonStyle =
                new ImageButton.ImageButtonStyle(
                        skin.get("default", Button.ButtonStyle.class));
        Sprite settingsSprite = new Sprite(
                assets.get("graphics/icons/settings.png",
                        Texture.class));
        settingsSprite.setSize(Constants.UI_ICON_RATIO * viewportHeight,
                Constants.UI_ICON_RATIO * viewportHeight);
        settingsButtonStyle.imageUp = new SpriteDrawable(settingsSprite);
        settingsButton = new ImageButton(settingsButtonStyle);
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setCurrentMenu(Constants.CurrentTitleMenu.SETTINGS);
            }
        });

        // create Quit Button
        ImageButton.ImageButtonStyle quitButtonStyle =
                new ImageButton.ImageButtonStyle(
                        skin.get("default", Button.ButtonStyle.class));
        Sprite quitSprite = new Sprite(
                assets.get("graphics/icons/close.png",
                        Texture.class));
        quitSprite.setSize(Constants.UI_ICON_RATIO * viewportHeight,
                Constants.UI_ICON_RATIO * viewportHeight);
        quitButtonStyle.imageUp = new SpriteDrawable(quitSprite);
        quitButton = new ImageButton(quitButtonStyle);
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                titleScreen.quit();
            }
        });

        // Settings title Label
        settingsTitleLabel = new Label(bundle.get("settingsTitleLabel"), skin, "default");

        // create SliderStlye
        Slider.SliderStyle sliderStyle =
                skin.get("default-horizontal",
                        Slider.SliderStyle.class);

        // Sound Volume widgets
        // Label
        soundVolumeLabel = new Label(bundle.get("soundVolumeLabel") + ":", skin, "default");
        soundVolumeLabel.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                soundVolumeSlider.setValue(0);
            }
        });
        // Mute Image
        soundVolumeMuteImage = new Image(assets.get("graphics/icons/volume_mute.png",
                Texture.class));
        soundVolumeMuteImage.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                soundVolumeSlider.setValue(0);
            }
        });
        // Slider
        soundVolumeSlider = new Slider(0, 1, .05f, false, sliderStyle);
        soundVolumeSlider.setValue(titleScreen.getSoundVolume());
        soundVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setSoundVolume(soundVolumeSlider.getValue());
            }
        });
        // Full volume image
        soundVolumeFullImage = new Image(assets.get("graphics/icons/volume_up.png",
                Texture.class));
        soundVolumeFullImage.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                soundVolumeSlider.setValue(1);
            }
        });

        // Music Volume widgets
        // Label
        musicVolumeLabel = new Label(bundle.get("musicVolumeLabel") + ":", skin,
                "default");
        musicVolumeLabel.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                musicVolumeSlider.setValue(0);
            }
        });
        // Mute Image
        musicVolumeMuteImage = new Image(assets.get("graphics/icons/volume_mute.png",
                Texture.class));
        musicVolumeMuteImage.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                musicVolumeSlider.setValue(0);
            }
        });
        // Slider
        musicVolumeSlider = new Slider(0, 1, .05f, false, sliderStyle);
        musicVolumeSlider.setValue(titleScreen.getMusicVolume());
        musicVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setMusicVolume(musicVolumeSlider.getValue());
            }
        });
        // Full volume image
        musicVolumeFullImage = new Image(assets.get("graphics/icons/volume_up.png",
                Texture.class));
        musicVolumeFullImage.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                musicVolumeSlider.setValue(1);
            }
        });

        // create Settings Back Button
        ImageButton.ImageButtonStyle backButtonStyle =
                new ImageButton.ImageButtonStyle(
                        skin.get("default", Button.ButtonStyle.class));
        Sprite backSprite = new Sprite(
                assets.get("graphics/icons/back.png",
                        Texture.class));
        backSprite.setSize(Constants.UI_ICON_RATIO * viewportHeight,
                Constants.UI_ICON_RATIO * viewportHeight);
        backButtonStyle.imageUp = new SpriteDrawable(backSprite);
        backButton = new ImageButton(backButtonStyle);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setCurrentMenu(Constants.CurrentTitleMenu.MAIN);
            }
        });

        setCurrentMenu(Constants.CurrentTitleMenu.MAIN);
    }

    public boolean goBack() {
        if (currentMenu == Constants.CurrentTitleMenu.SETTINGS) {
            setCurrentMenu(Constants.CurrentTitleMenu.MAIN);
            return true;
        } else {
            return false;
        }
    }

    private void setCurrentMenu(Constants.CurrentTitleMenu currentMenu) {
        this.currentMenu = currentMenu;
        switch (currentMenu) {
            case MAIN:
                showMainMenu();
                break;
            case SETTINGS:
                showSettingsMenu();
        }
    }

    private float getMusicVolume() {
        return titleScreen.getMusicVolume();
    }

    private void setMusicVolume(float MusicVolume) {
        titleScreen.setMusicVolume(MusicVolume);
    }

    private float getSoundVolume() {
        return titleScreen.getSoundVolume();
    }

    private void setSoundVolume(float soundVolume) {
        titleScreen.setSoundVolume(soundVolume);
    }

    private void showMainMenu() {

        // Main Menu table
        Table mainMenuTable = new Table();
        mainMenuTable.add(playButton)
                .prefSize(buttonWidthHalf, buttonHeight)
                .right()
                .space(padding);
        mainMenuTable.add(settingsButton)
                .prefSize(buttonHeight)
                .left()
                .space(padding);
        mainMenuTable.add(quitButton)
                .prefSize(buttonHeight)
                .space(padding);

        super.clear();
        super.bottom()
                .pad(padding);
        super.add(header)
                .prefSize(
                        UiSize.getImageWidth(header.getPrefWidth(),
                                viewportWidth),
                        UiSize.getImageHeight(header.getPrefHeight(),
                                viewportHeight))
                .space(padding);
        super.row();
        super.add(mainMenuTable);
    }

    private void  showSettingsMenu() {
        // TODO: remove table debugging
//        super.setDebug(true);
        super.clear();
        super.left().top()
                .pad(padding);
        super.add(settingsTitleLabel)
                .colspan(4)
                .space(padding);
        super.row();
        super.add(soundVolumeLabel)
                .space(padding);
        super.add(soundVolumeMuteImage)
                .size(Constants.UI_ICON_RATIO * viewportHeight,
                        Constants.UI_ICON_RATIO * viewportHeight)
                .space(padding);
        super.add(soundVolumeSlider)
                .space(padding)
                .prefWidth(viewportWidth);
        super.add(soundVolumeFullImage)
                .size(Constants.UI_ICON_RATIO * viewportHeight)
                .space(padding);
        super.row();
        super.add(musicVolumeLabel)
                .space(padding);
        super.add(musicVolumeMuteImage)
                .size(Constants.UI_ICON_RATIO * viewportHeight)
                .space(padding);
        super.add(musicVolumeSlider)
                .space(padding)
                .fillX();
        super.add(musicVolumeFullImage)
                .size(Constants.UI_ICON_RATIO * viewportHeight)
                .space(padding);
        super.row();
        super.add(backButton)
                .bottom()
                .colspan(4)
                .expandY()
                .prefSize(buttonWidthHalf, buttonHeight)
                .right()
                .space(padding);
        super.row();
    }
}
