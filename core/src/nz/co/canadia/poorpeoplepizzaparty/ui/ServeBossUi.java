package nz.co.canadia.poorpeoplepizzaparty.ui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.screens.ServeBossScreen;
import nz.co.canadia.poorpeoplepizzaparty.utils.Capture;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiSize;

/**
 * This class implements the UI layout for the ServeBossScreen.class
 */

public class ServeBossUi extends Table {

    private final int screenWidth;
    private final int screenHeight;
    private final ServeBossScreen serveBossScreen;
    private final Skin uiSkin;
    private final AssetManager assets;
    private final I18NBundle bundle;
    private final int padding;
    private final Pizza pizza;
    private Texture pizzaTexture;
    private Pixmap pizzaPixmap;

    public ServeBossUi(int screenWidth, int screenHeight,
                       ServeBossScreen serveBossScreen,
                       Skin uiSkin, AssetManager assets, I18NBundle bundle,
                       Pizza pizza) {

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.serveBossScreen = serveBossScreen;
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

        pizzaPixmap = pizza.getPixmap();
        pizzaTexture = new Texture(pizzaPixmap);
        pizzaTexture.setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        Image pizzaImage = new Image(pizzaTexture);
        leftColumn.add(pizzaImage)
                .prefSize( // this Image is a full Base image, needs to be
                           // scaled
                        UiSize.getImageWidth(pizzaImage.getPrefWidth(),
                                screenWidth) * Constants.BASE_SERVE_SCALE,
                        UiSize.getImageHeight(pizzaImage.getPrefHeight(),
                                screenHeight) * Constants.BASE_SERVE_SCALE)
                .space(padding);
        leftColumn.row();

        Label bossDialog = new Label(bundle.get("servebossName") + ": \n\""
                + firedString + "\"", uiSkin,
                "default");
        bossDialog.setAlignment(Align.center);
        leftColumn.add(bossDialog)
                .space(padding);
        leftColumn.row();

        TextButton firedButton = new TextButton(bundle.get("servebossFiredButton"), uiSkin,
                "default");
        firedButton.getLabel().setWrap(true);
        firedButton.getLabel().setText(firedButton.getText());
        firedButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                serveBossScreen.getFired();
            }
        });
        leftColumn.add(firedButton)
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

    public void dispose() {
        pizzaPixmap.dispose();
        pizzaTexture.dispose();
    }

}
