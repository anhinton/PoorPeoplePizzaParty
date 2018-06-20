package nz.co.canadia.poorpeoplepizzaparty.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
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

public class PizzaUi extends Table {

    private final Skin skin;
    private final PizzaScreen pizzaScreen;
    private final I18NBundle bundle;
    private final Array<TextButton> toppingButtons;
    private final ButtonGroup<TextButton> toppingGroup;
    private final TextButton toppingSelectButton;
    private final ImageButton cameraButton;
    private final ImageButton undoButton;
    private final TextButton cookButton;

    public PizzaUi(final PizzaScreen pizzaScreen, final Skin skin,
                   final I18NBundle bundle, final Screenshot screenshot,
                   final AssetManager manager) {

        this.pizzaScreen = pizzaScreen;
        super.setFillParent(true);
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
        cameraButtonStyle.imageUp = new SpriteDrawable(
                new Sprite(manager.get("graphics/icons/camera.png",
                        Texture.class)));
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
        undoButtonStyle.imageUp = new SpriteDrawable(
                new Sprite(manager.get("graphics/icons/undo.png",
                        Texture.class)));
        undoButton = new ImageButton(undoButtonStyle);
        undoButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pizzaScreen.clearMessage();
                pizzaScreen.undoLastTopping();
            }
        });
        
        cookButton = new TextButton(bundle.get("pizzamenuCookButton"), skin,
                "default");
        cookButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pizzaScreen.cook();
                Gdx.app.log("PizzaUi", "cook button pressed");
            }
        });

        toppingGroup = new ButtonGroup<TextButton>();
        toppingGroup.setMinCheckCount(0);
        toppingGroup.setMaxCheckCount(1);

        toppingButtons = new Array<TextButton>();
        addToppingButton(Constants.ToppingName.SAUCE,
                bundle.get("toppingmenuSauce"));
        addToppingButton(Constants.ToppingName.CHEESE,
                bundle.get("toppingmenuCheese"));
        super.row();
        addToppingButton(Constants.ToppingName.BACON,
                bundle.get("toppingmenuBacon"));
        addToppingButton(Constants.ToppingName.SAUSAGE,
                bundle.get("toppingmenuSausage"));
        super.row();
        addToppingButton(Constants.ToppingName.SALAMI,
                bundle.get("toppingmenuSalami"));
        addToppingButton(Constants.ToppingName.CHICKEN,
                bundle.get("toppingmenuChicken"));
        super.row();
        addToppingButton(Constants.ToppingName.APRICOT,
                bundle.get("toppingmenuApricot"));
        addToppingButton(Constants.ToppingName.BARBECUE,
                bundle.get("toppingmenuBarbecue"));

        showMainMenu();

        // DEBUG UI
        super.setDebug(Constants.DEBUG_GRAPHICS);
    }

    private void showMainMenu() {
        super.clear();
        super.center().right().pad(Constants.MENU_PADDING);
        super.add(new Label(bundle.get("pizzamenuHeaderLabel"), skin,
                "default"))
                .space(Constants.MENU_PADDING)
                .colspan(2).center();
        super.row();
        super.add(toppingSelectButton).space(Constants.MENU_PADDING)
                .prefSize(Constants.BUTTON_WIDTH_FULL, Constants.BUTTON_HEIGHT)
                .colspan(2)
                .right();
        super.row();
        super.add(cameraButton)
                .prefSize(Constants.BUTTON_WIDTH_HALF, Constants.BUTTON_HEIGHT)
                .space(Constants.MENU_PADDING);
        super.add(undoButton)
                .prefSize(Constants.BUTTON_WIDTH_HALF, Constants.BUTTON_HEIGHT)
                .space(Constants.MENU_PADDING);
        super.row();
        super.add(cookButton)
                .prefSize(Constants.BUTTON_WIDTH_FULL, Constants.BUTTON_HEIGHT)
                .space(Constants.MENU_PADDING)
                .colspan(2);
    }

    private void showToppingMenu() {
        super.clear();
        super.center().top();
        super.add(new Label(bundle.get("toppingmenuSelectLabel"), skin,
                "default"))
                .colspan(2);
        super.row();
        int counter = 0;
        for (TextButton b: toppingButtons) {
            if (counter > 0 & counter % 2 == 0)
                super.row();
            super.add(b).space(Constants.MENU_PADDING)
                    .prefSize(Constants.BUTTON_WIDTH_FULL, Constants.BUTTON_HEIGHT);
            counter++;
        }
    }

    /**
     * Generate a topping button and add to toppingButtons and toppingGroup.
     *
     * @param toppingName topping name enum
     * @param text text to display on button
     */
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

    private void setSelectedTopping(TextButton button) {
        toppingSelectButton.setText(button.getText().toString());
    }
}
