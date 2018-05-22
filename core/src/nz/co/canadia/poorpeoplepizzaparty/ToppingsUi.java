package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * Creates the toppings menu UI for selecting a topping on the PizzaScreen
 */

public class ToppingsUi {

    private Texture sauceMenuTexture;
    private Sprite sauceMenuSprite;
    private Texture cheeseMenuTexture;
    private Sprite cheeseMenuSprite;

    private Table table;
    private Skin skin;

    public ToppingsUi(Stage stage) {

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
        table.top().right();
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        ImageButton.ImageButtonStyle sauceButtonStyle =
                new ImageButton.ImageButtonStyle(
                        skin.get("default", Button.ButtonStyle.class));
        sauceButtonStyle.imageUp = new SpriteDrawable(sauceMenuSprite);
        ImageButton sauceButton = new ImageButton(sauceButtonStyle);
        table.add(sauceButton);

        ImageButton.ImageButtonStyle cheeseButtonStyle =
                new ImageButton.ImageButtonStyle(
                        skin.get("default", Button.ButtonStyle.class));
        cheeseButtonStyle.imageUp = new SpriteDrawable(cheeseMenuSprite);
        ImageButton cheeseButton = new ImageButton(cheeseButtonStyle);
        table.add(cheeseButton);


//        Label nameLabel = new Label("Name:", skin);
//        TextField nameText = new TextField("", skin);
//        Label addressLabel = new Label("Address:", skin);
//        TextField addressText = new TextField("", skin);
//
//        table.add(nameLabel);
//        table.add(nameText).width(100);
//        table.row();
//        table.add(addressLabel);
//        table.add(addressText).width(100);

        // DEBUG UI
        table.setDebug(true);
    }
}
