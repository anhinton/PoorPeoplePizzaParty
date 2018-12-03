package nz.co.canadia.poorpeoplepizzaparty.ui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.I18NBundle;

import nz.co.canadia.poorpeoplepizzaparty.utils.UiSize;

/**
 * This class implements the UI layout for the ServeBossScreen.class
 */

public class BossUi extends Table {

    private final int screenWidth;
    private final int screenHeight;
    private final Skin skin;
    private final AssetManager assets;
    private final I18NBundle bundle;
    private final int padding;

    public BossUi(int screenWidth, int screenHeight,
                  Skin skin, AssetManager assets, I18NBundle bundle) {

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.skin = skin;
        this.assets = assets;
        this.bundle = bundle;
        padding = UiSize.getPadding(screenHeight);

        super.setFillParent(true);

        showBoss();
    }

    private void showBoss() {

        super.clear();
        super.pad(padding);

        Image bossImage = new Image(assets.get("graphics/boss.png", Texture.class));
        super.add(bossImage)
                .prefSize(
                        UiSize.getImageWidth(bossImage.getPrefWidth(),
                                screenWidth),
                        UiSize.getImageHeight(bossImage.getPrefHeight(),
                                screenHeight))
                .space(padding);


    }

}
