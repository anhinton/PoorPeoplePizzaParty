package nz.co.canadia.poorpeoplepizzaparty.ui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
    private final Label soundVolumeValueLabel;
    private final TitleScreen titleScreen;
    private final Label musicVolumeValueLabel;
    private final ImageButton backButton;
    private final I18NBundle bundle;
    private final Label settingsTitleLabel;
    private final TextButton playButton;
    private final TextButton settingsButton;
    private final TextButton quitButton;
    private Constants.CurrentTitleMenu currentMenu;

    public TitleUi(int viewportWidth, int viewportHeight,
                   final TitleScreen titleScreen, final Skin skin,
                   final I18NBundle bundle,
                   final AssetManager assets) {

        super.setFillParent(true);

        this.titleScreen = titleScreen;
        this.bundle = bundle;

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
        settingsButton = new TextButton(bundle.get("settingsButton"), skin, "default");
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setCurrentMenu(Constants.CurrentTitleMenu.SETTINGS);
            }
        });

        // create Quit Button
        quitButton = new TextButton(bundle.get("quitButton"), skin, "default");
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

        // Sound Volume idgets
        // Label
        soundVolumeLabel = new Label(bundle.get("soundVolumeLabel") + ":", skin, "default");
        // Slider
        soundVolumeSlider = new Slider(0, 1, .05f, false, sliderStyle);
        soundVolumeSlider.setValue(titleScreen.getSoundVolume());
        soundVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setSoundVolume(soundVolumeSlider.getValue());
            }
        });
        // Value Label
        soundVolumeValueLabel = new Label(printVolume(getSoundVolume()), skin, "default");

        // Music Volume widgets
        // Label
        musicVolumeLabel = new Label(bundle.get("musicVolumeLabel") + ":", skin, "default");
        // Slider
        musicVolumeSlider = new Slider(0, 1, .05f, false, sliderStyle);
        musicVolumeSlider.setValue(titleScreen.getMusicVolume());
        musicVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setMusicVolume(musicVolumeSlider.getValue());
            }
        });
        // Value Label
        musicVolumeValueLabel = new Label(printVolume(getMusicVolume()), skin, "default");

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
        musicVolumeValueLabel.setText(printVolume(getMusicVolume()));
    }

    private float getSoundVolume() {
        return titleScreen.getSoundVolume();
    }

    private String printVolume(float volume) {
        return Integer.toString(MathUtils.round(volume * 100));
    }

    private void setSoundVolume(float soundVolume) {
        titleScreen.setSoundVolume(soundVolume);
        soundVolumeValueLabel.setText(printVolume(getSoundVolume()));
    }

    private void showMainMenu() {

        // Main Menu table
        Table mainMenuTable = new Table();
        mainMenuTable.add(playButton)
                .prefSize(buttonWidthFull, buttonHeight)
                .right()
                .space(padding);
        mainMenuTable.add(settingsButton)
                .prefSize(buttonWidthHalf, buttonHeight)
                .left()
                .space(padding);
        mainMenuTable.add(quitButton)
                .prefSize(buttonWidthHalf, buttonHeight)
                .space(padding);

        super.clear();
        super.bottom()
                .pad(padding);
        super.add(header)
                .space(padding);
        super.row();
        super.add(mainMenuTable);
    }

    private void  showSettingsMenu() {
        // TODO: remove table debugging
//        super.setDebug(true);
        super.clear();
        super.center()
                .pad(padding);
        super.add(settingsTitleLabel)
                .colspan(3)
                .space(padding);
        super.row();
        super.add(soundVolumeLabel)
                .space(padding);
        super.add(soundVolumeSlider)
                .space(padding)
                .prefWidth(buttonWidthFull);
        super.add(soundVolumeValueLabel)
                .space(padding)
                .prefWidth(soundVolumeValueLabel.getPrefWidth());
        super.row();
        super.add(musicVolumeLabel)
                .space(padding);
        super.add(musicVolumeSlider)
                .space(padding)
                .prefWidth(buttonWidthFull);
        super.add(musicVolumeValueLabel)
                .space(padding)
                .prefWidth(musicVolumeValueLabel.getPrefWidth());
        super.row();
        super.add(backButton)
                .colspan(3)
                .space(padding)
                .prefSize(buttonWidthHalf, buttonHeight);
    }
}
