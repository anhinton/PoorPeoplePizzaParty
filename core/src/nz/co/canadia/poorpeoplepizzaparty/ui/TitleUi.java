package nz.co.canadia.poorpeoplepizzaparty.ui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

import nz.co.canadia.poorpeoplepizzaparty.screens.TitleScreen;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiSize;

public class TitleUi extends Table {

    private final Image header;
    private final int buttonWidthHalf;
    private final int buttonWidthFull;
    private final int buttonHeight;
    private final int padding;
    private final TextButton playButton;
    private final TextButton settingsButton;
    private final TextButton quitButton;

    public TitleUi(int viewportWidth, int viewportHeight,
                   final TitleScreen titleScreen, final Skin skin,
                   final I18NBundle bundle,
                   final AssetManager assets) {

        super.setFillParent(true);

        buttonWidthHalf = UiSize.getButtonWidthHalf(viewportWidth, viewportHeight);
        buttonWidthFull = UiSize.getButtonWidthFull(viewportWidth, viewportHeight);
        buttonHeight = UiSize.getButtonHeight(viewportHeight);
        padding = UiSize.getPadding(viewportHeight);

        header = new Image(assets.get("graphics/headers/titleScreen.png",
                Texture.class));

        playButton = new TextButton(bundle.get("playButton"), skin, "default");
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                titleScreen.play();
            }
        });

        settingsButton = new TextButton(bundle.get("settingsButton"), skin, "default");

        quitButton = new TextButton(bundle.get("quitButton"), skin, "default");
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                titleScreen.quit();
            }
        });

        Slider.SliderStyle sliderStyle =
                skin.get("default-horizontal",
                        Slider.SliderStyle.class);

        Label soundVolumeLabel = new Label("Sound volume:", skin, "default");
        soundVolumeLabel.setAlignment(Align.top);

        Slider soundVolumeSlider = new Slider(0, 1, .05f, false, sliderStyle);
        soundVolumeSlider.setValue(titleScreen.getSoundVolume());

        Label musicVolumeLabel = new Label("Music volume:", skin, "default");
        musicVolumeLabel.setAlignment(Align.top);

        Slider musicVolumeSlider = new Slider(0, 1, .05f, false, sliderStyle);
        musicVolumeSlider.setValue(titleScreen.getMusicVolume());

        super.setDebug(true);
        super.clear();
        super.pad(padding);
        super.add(soundVolumeLabel).pad(padding);
        super.add(soundVolumeSlider).pad(padding).prefWidth(buttonWidthFull);
        super.row();
        super.add(musicVolumeLabel).pad(padding);
        super.add(musicVolumeSlider).pad(padding).prefWidth(buttonWidthFull);

//        showMainMenu();
    }

    private void showMainMenu() {
        super.clear();
        super.pad(padding);
        super.add(header)
                .space(padding)
                .colspan(2);
        super.row();
        super.add(playButton).prefSize(buttonWidthFull, buttonHeight).space(padding).colspan(2);
        super.row();
        super.add(settingsButton).prefSize(buttonWidthHalf, buttonHeight).space(padding).right();
        super.add(quitButton).prefSize(buttonWidthHalf, buttonHeight).space(padding).left();
    }

    private void showSettings() {
    }
}
