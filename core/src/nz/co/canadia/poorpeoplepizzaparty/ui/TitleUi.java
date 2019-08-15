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
    private final TitleScreen titleScreen;
    private final TextButton playButton;
    private final ImageButton settingsButton;
    private final ImageButton quitButton;
    private final int viewportHeight;
    private final int viewportWidth;
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

    }
}
