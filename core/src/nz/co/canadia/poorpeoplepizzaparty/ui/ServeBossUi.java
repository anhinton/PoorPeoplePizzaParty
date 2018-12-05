package nz.co.canadia.poorpeoplepizzaparty.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.utils.Capture;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiSize;

/**
 * This class implements the UI layout for the ServeBossScreen.class
 */

public class ServeBossUi extends Table {

    private final int screenWidth;
    private final int screenHeight;
    private final Skin uiSkin;
    private final AssetManager assets;
    private final I18NBundle bundle;
    private final int padding;
    private final Pizza pizza;

    public ServeBossUi(int screenWidth, int screenHeight,
                       Skin uiSkin, AssetManager assets, I18NBundle bundle,
                       Pizza pizza) {

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.uiSkin = uiSkin;
        this.assets = assets;
        this.bundle = bundle;
        this.pizza = pizza;
        padding = UiSize.getPadding(screenHeight);

        super.setFillParent(true);

        showBoss();
    }

    private void showBoss() {

        super.clear();
        super.pad(padding);

        Table leftColumn = new Table(uiSkin);
        super.add(leftColumn)
//                .expand()
        ;

        String firedString = "";
        switch(MathUtils.random(1, 2)) {
            case 1:
                firedString = bundle.get("servebossStatement1");
                break;
            case 2:
                firedString = bundle.get("servebossStatement2");
                break;
        }

        Texture pizzaTexture = new Texture(Capture.capturePizza(pizza));
        Image pizzaImage = new Image(pizzaTexture);
        leftColumn.add(pizzaImage)
                .prefSize(UiSize.getBaseServeWidth(screenWidth),
                        UiSize.getBaseServeHeight(screenWidth))
                .space(padding);
        // TODO: remove pizza size debugging code
        Gdx.app.log("ServeBossUi", "pizzaImage width: " + pizzaImage.getWidth());
        Gdx.app.log("ServeBossUi", "pizzaImage height: " + pizzaImage.getWidth());
        Gdx.app.log("ServeBossUi", "pizzaImage cell width: " + leftColumn.getCell(pizzaImage).getPrefWidth());
        Gdx.app.log("ServeBossUi", "pizzaImage cell height: " + leftColumn.getCell(pizzaImage).getPrefHeight());
        leftColumn.row();

        Label bossDialog = new Label(bundle.get("servebossName") + ": \n\""
                + firedString + "\"", uiSkin,
                "default");
        bossDialog.setAlignment(Align.center);
        leftColumn.add(bossDialog)
                .space(padding);
        leftColumn.row();

        TextButton textButton = new TextButton(bundle.get("servebossButton"), uiSkin,
                "default");
        textButton.getLabel().setWrap(true);
        textButton.getLabel().setText(textButton.getText());
        leftColumn.add(textButton)
                .prefSize(UiSize.getButtonWidthFull(screenWidth, screenHeight),
                UiSize.getButtonHeight(screenHeight))
                .space(padding);

        Image bossImage = new Image(assets.get("graphics/boss.png", Texture.class));
        super.add(bossImage)
                .prefSize(
                        UiSize.getImageWidth(bossImage.getPrefWidth(),
                                screenWidth),
                        UiSize.getImageHeight(bossImage.getPrefHeight(),
                                screenHeight))
//                .expand()
                .space(padding);
    }

}
