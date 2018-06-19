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

public class PizzaMenu extends Table {

    private Skin skin;
    private PizzaScreen pizzaScreen;
    private Array<TextButton> toppingButtons;
    private ButtonGroup<TextButton> toppingGroup;
    private TextButton toppingSelectButton;
    private Texture cameraButtonTexture;
    private Texture undoButtonTexture;
    private ImageButton cameraButton;
    private ImageButton undoButton;
    private TextButton cookButton;
    private I18NBundle bundle;

    public PizzaMenu(final PizzaScreen pizzaScreen, Skin skin,
                     I18NBundle bundle, final Screenshot screenshot,
                     boolean debugGraphics) {

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
        cameraButtonTexture = new Texture(
                Gdx.files.internal("graphics/icons/camera.png"));
        cameraButtonTexture.setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
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
        undoButtonTexture.setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
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
        
        cookButton = new TextButton(bundle.get("pizzamenuCookButton"), skin,
                "default");
        cookButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("PizzaMenu", "cook button pressed");
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
        super.setDebug(debugGraphics);
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

    public void dispose() {
        skin.dispose();
        cameraButtonTexture.dispose();
        undoButtonTexture.dispose();
    }

}
