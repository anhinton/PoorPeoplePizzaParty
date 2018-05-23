package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * Creates the toppings menu UI for selecting a topping on the PizzaScreen
 */

public class ToppingMenu {

    private Texture sauceMenuTexture;
    private Sprite sauceMenuSprite;
    private Texture cheeseMenuTexture;
    private Sprite cheeseMenuSprite;

    private Table table;
    private Skin skin;

    public ToppingMenu(Stage stage) {

        // create button sprites
        sauceMenuTexture = new Texture(Gdx.files.internal("sauce.png"));
        sauceMenuSprite = new Sprite(sauceMenuTexture);
        sauceMenuSprite.setSize(Constants.BUTTON_IMAGE_WIDTH,
                Constants.BUTTON_IMAGE_HEIGHT);
        cheeseMenuTexture = new Texture(Gdx.files.internal("cheese.png"));
        cheeseMenuSprite = new Sprite(cheeseMenuTexture);
        cheeseMenuSprite.setSize(Constants.BUTTON_IMAGE_WIDTH,
                Constants.BUTTON_IMAGE_HEIGHT);

        table = new Table();
        table.setFillParent(true);
        table.top().right().pad(Constants.MENU_PADDING);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        ImageButton.ImageButtonStyle sauceButtonStyle =
                new ImageButton.ImageButtonStyle(
                        skin.get("toggle", Button.ButtonStyle.class));
        sauceButtonStyle.imageUp = new SpriteDrawable(sauceMenuSprite);
        ImageButton sauceButton = new ImageButton(sauceButtonStyle);
        table.add(sauceButton).space(Constants.MENU_PADDING)
                .prefSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);

        ImageButton.ImageButtonStyle cheeseButtonStyle =
                new ImageButton.ImageButtonStyle(
                        skin.get("toggle", Button.ButtonStyle.class));
        cheeseButtonStyle.imageUp = new SpriteDrawable(cheeseMenuSprite);
        final ImageButton cheeseButton = new ImageButton(cheeseButtonStyle);
        table.add(cheeseButton).space(Constants.MENU_PADDING)
                .prefSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);

        ButtonGroup<ImageButton> toppingsGroup = new ButtonGroup<ImageButton>();
        toppingsGroup.setMinCheckCount(0);
        toppingsGroup.setMaxCheckCount(1);
        toppingsGroup.add(sauceButton, cheeseButton);

        // DEBUG UI
        table.setDebug(true);
    }

    public void dispose() {
        sauceMenuTexture.dispose();
        cheeseMenuTexture.dispose();
        skin.dispose();
    }
}
