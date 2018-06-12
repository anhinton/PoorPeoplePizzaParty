package nz.co.canadia.poorpeoplepizzaparty.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;

import nz.co.canadia.poorpeoplepizzaparty.screens.PizzaScreen;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;
import nz.co.canadia.poorpeoplepizzaparty.utils.Screenshot;

/**
 * Creates the toppings menu UI for selecting a topping on the PizzaScreen
 */

public class ToppingMenu extends Table {

    private Skin skin;
    private PizzaScreen pizzaScreen;
    private Array<TextButton> toppingButtons;
    private ButtonGroup<TextButton> toppingGroup;
    private TextButton toppingSelectButton;
    private Texture cameraButtonTexture;
    private Texture undoButtonTexture;
    private ImageButton cameraButton;
    private ImageButton undoButton;
    private I18NBundle bundle;

    public ToppingMenu(final PizzaScreen pizzaScreen, Skin skin,
                       I18NBundle bundle, final Screenshot screenshot,
                       boolean debugGraphics) {

        this.pizzaScreen = pizzaScreen;
        this.setFillParent(true);
        this.skin = skin;
        this.bundle = bundle;

        toppingSelectButton = new TextButton(bundle.get("pizzamenuSelectButton"), skin,
                "default");
        toppingSelectButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pizzaScreen.clearMessage();
                showToppingMenu();
            }
        });

        ImageButton.ImageButtonStyle cameraButtonStyle =
                new ImageButton.ImageButtonStyle(
                        skin.get("default", Button.ButtonStyle.class));
        cameraButtonTexture = new Texture(
                Gdx.files.internal("graphics/icons/camera.png"));
        cameraButtonStyle.imageUp = new SpriteDrawable(
                new Sprite(cameraButtonTexture));
        cameraButton = new ImageButton(cameraButtonStyle);
        cameraButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pizzaScreen.clearMessage();
                Pixmap pixmap = screenshot.captureScreen();
                screenshot.saveCapture(pixmap);
            }
        });

        ImageButton.ImageButtonStyle undoButtonStyle =
                new ImageButton.ImageButtonStyle(
                        skin.get("default", Button.ButtonStyle.class));
        undoButtonTexture = new Texture(
                Gdx.files.internal("graphics/icons/undo.png"));
        undoButtonStyle.imageUp = new SpriteDrawable(
                new Sprite(undoButtonTexture));
        undoButton = new ImageButton(undoButtonStyle);
        undoButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pizzaScreen.clearMessage();
                pizzaScreen.undoLastTopping();
            }
        });

        toppingGroup = new ButtonGroup<TextButton>();
        toppingGroup.setMinCheckCount(0);
        toppingGroup.setMaxCheckCount(1);

        toppingButtons = new Array<TextButton>();
        addToppingButton(Constants.ToppingName.SAUCE,
                bundle.get("toppingSauce"));
        addToppingButton(Constants.ToppingName.CHEESE,
                bundle.get("toppingCheese"));
        this.row();
        addToppingButton(Constants.ToppingName.BACON,
                bundle.get("toppingBacon"));
        addToppingButton(Constants.ToppingName.SAUSAGE,
                bundle.get("toppingSausage"));
        this.row();
        addToppingButton(Constants.ToppingName.SALAMI,
                bundle.get("toppingSalami"));
        addToppingButton(Constants.ToppingName.CHICKEN,
                bundle.get("toppingChicken"));
        this.row();
        addToppingButton(Constants.ToppingName.APRICOT,
                bundle.get("toppingApricot"));
        addToppingButton(Constants.ToppingName.BARBECUE,
                bundle.get("toppingBarbecue"));

        showMainMenu();

        // DEBUG UI
        this.setDebug(debugGraphics);
    }

    private void showToppingMenu() {
        this.clear();
        this.center().top();
        this.add(new Label(bundle.get("toppingmenuSelectLabel"), skin,
                "default"))
                .colspan(2);
        this.row();
        int counter = 0;
        for (TextButton b: toppingButtons) {
            if (counter > 0 & counter % 2 == 0)
                this.row();
            this.add(b).space(Constants.MENU_PADDING)
                    .prefSize(Constants.BUTTON_WIDTH_FULL, Constants.BUTTON_HEIGHT);
            counter++;
        }
    }

    private void addToppingButton(final Constants.ToppingName toppingName, String text) {
        final TextButton textButton = new TextButton(
                text, skin, "default");
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pizzaScreen.setSelectedTopping(toppingName);
                setSelectedTopping(textButton);
                showMainMenu();
            }
        });
        toppingButtons.add(textButton);
        toppingGroup.add(textButton);
    }

    private void showMainMenu() {
        this.clear();
        this.center().right().pad(Constants.MENU_PADDING);
        this.add(new Label(bundle.get("pizzamenuHeaderLabel"), skin,
                "default"))
                .space(Constants.MENU_PADDING)
                .colspan(2).center();
        this.row();
        this.add(toppingSelectButton).space(Constants.MENU_PADDING)
                .prefSize(Constants.BUTTON_WIDTH_FULL, Constants.BUTTON_HEIGHT)
                .colspan(2)
                .right();
        this.row();
        this.add(cameraButton)
                .prefSize(Constants.BUTTON_WIDTH_HALF, Constants.BUTTON_HEIGHT)
                .space(Constants.MENU_PADDING);
        this.add(undoButton)
                .prefSize(Constants.BUTTON_WIDTH_HALF, Constants.BUTTON_HEIGHT)
                .space(Constants.MENU_PADDING);
    }

    private void setSelectedTopping(TextButton button) {
        toppingSelectButton.setText(button.getText().toString());
    }

    public void dispose() {
        skin.dispose();
        cameraButtonTexture.dispose();
    }

}
