package nz.co.canadia.poorpeoplepizzaparty.ui;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * This class implements the UI layout for the ServeBossScreen.class
 */

public class ServeBossUi extends Table {

    private final ServeBossScreen serveBossScreen;
    private final Skin uiSkin;
    private final TextureAtlas atlas;
    private final I18NBundle bundle;
    private final int padding;
    private final Pizza pizza;
    private Texture pizzaTexture;
    private Pixmap pizzaPixmap;
    private TextButton firedButton;

    public ServeBossUi(ServeBossScreen serveBossScreen,
                       Skin uiSkin, TextureAtlas atlas, I18NBundle bundle,
                       Pizza pizza) {

        this.serveBossScreen = serveBossScreen;
        this.uiSkin = uiSkin;
        this.atlas = atlas;
        this.bundle = bundle;
        this.pizza = pizza;
        padding = Constants.UNIT;

        super.setFillParent(true);

        showBoss();
    }

    private void showBoss() {

        super.clear();
        super.pad(padding);

        Table leftColumn = new Table(uiSkin);
        super.add(leftColumn);

        String firedString = "";
        switch(MathUtils.random(1, 2)) {
            case 1:
                firedString = bundle.get("servebossStatement1");
                break;
            case 2:
                firedString = bundle.get("servebossStatement2");
                break;
        }

        Image bossImage = new Image(atlas.findRegion("boss"));

        pizzaPixmap = pizza.getPixmap();
        pizzaTexture = new Texture(pizzaPixmap);
        pizzaTexture.setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        Image pizzaImage = new Image(pizzaTexture);
        leftColumn.add(pizzaImage)
                .prefSize( // this Image is a full Base image, needs to be
                           // scaled
                        pizzaImage.getPrefWidth() * Constants.BASE_SERVE_SCALE,
                        pizzaImage.getPrefHeight() * Constants.BASE_SERVE_SCALE)
                .space(padding);
        leftColumn.row();

        Label bossDialog = new Label(bundle.get("servebossName") + ": \n\""
                + firedString + "\"", uiSkin,
                "default");
        bossDialog.setAlignment(Align.center);
        leftColumn.add(bossDialog)
                .prefWidth(Constants.GAME_WIDTH / 2f)
                .space(padding);
        firedButton = new TextButton(bundle.get("servebossFiredButton"), uiSkin,
                "default");
//        firedButton.getLabel().setWrap(true);
        firedButton.getLabel().setText(firedButton.getText());
        firedButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                serveBossScreen.getFired();
            }
        });
        firedButton.setVisible(false);
        leftColumn.row();
        // add button but it's invisible
        leftColumn.add(firedButton)
                .prefSize(Constants.GAME_WIDTH / 2f,
                        Constants.BUTTON_HEIGHT)
                .space(padding);

        super.add(bossImage)
                .space(padding);
    }

    public void showFiredButton() {
        firedButton.setVisible(true);
    }

    public void dispose() {
        pizzaPixmap.dispose();
        pizzaTexture.dispose();
    }

}
