package nz.co.canadia.poorpeoplepizzaparty.ui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
import nz.co.canadia.poorpeoplepizzaparty.utils.UiSize;

/**
 * Creates the toppings menu UI for selecting a topping on the PizzaScreen
 */

public class PizzaUi extends Table {

    private final Skin skin;
    private final PizzaScreen pizzaScreen;
    private final I18NBundle bundle;
    private final Array<TextButton> toppingButtons;
    private final ButtonGroup<TextButton> toppingGroup;
    private final Image headerImage;
    private final TextButton toppingSelectButton;
    private final ImageButton cameraButton;
    private final ImageButton undoButton;
    private final TextButton cookButton;
    private final ImageButton backButton;
    private final ImageButton closeButton;
    private float buttonWidthFull;
    private float buttonWidthHalf;
    private float buttonHeight;
    private float padding;
    private Constants.CurrentPizzaMenu currentMenu;

    public PizzaUi(final PizzaScreen pizzaScreen, final Skin skin,
                   final I18NBundle bundle,
                   final AssetManager assets) {

        this.pizzaScreen = pizzaScreen;
        super.setFillParent(true);
        this.skin = skin;
        this.bundle = bundle;

        buttonWidthHalf = Constants.BUTTON_WIDTH_HALF;
        buttonWidthFull = Constants.BUTTON_WIDTH_FULL;
        buttonHeight = Constants.BUTTON_HEIGHT;
        padding = UiSize.getPadding();

        ImageButton.ImageButtonStyle closeButtonStyle =
                new ImageButton.ImageButtonStyle(
                        skin.get("default", Button.ButtonStyle.class));
        Sprite closeSprite = new Sprite(
                assets.get("graphics/icons/close.png",
                        Texture.class));
        closeSprite.setSize(UiSize.getIconSize(),
                UiSize.getIconSize());
        closeButtonStyle.imageUp = new SpriteDrawable(closeSprite);
        closeButton = new ImageButton(closeButtonStyle);
        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                goBack();
            }
        });

        headerImage = new Image(
                assets.get("graphics/headers/pizzaScreen.png",
                        Texture.class));

        toppingSelectButton = new TextButton(bundle.get("pizzamenuSelectButton"), skin,
                "default");
        toppingSelectButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pizzaScreen.clearMessage();
                setCurrentMenu(Constants.CurrentPizzaMenu.TOPPING);
            }
        });

        ImageButton.ImageButtonStyle cameraButtonStyle =
                new ImageButton.ImageButtonStyle(
                        skin.get("default", Button.ButtonStyle.class));
        Sprite cameraSprite = new Sprite(
                assets.get("graphics/icons/camera.png",
                        Texture.class));
        cameraSprite.setSize(UiSize.getIconSize(),
                UiSize.getIconSize());
        cameraButtonStyle.imageUp = new SpriteDrawable(cameraSprite);
        cameraButton = new ImageButton(cameraButtonStyle);
        cameraButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pizzaScreen.createPostcard();
            }
        });

        ImageButton.ImageButtonStyle undoButtonStyle =
                new ImageButton.ImageButtonStyle(
                        skin.get("default", Button.ButtonStyle.class));
        Sprite undoSprite = new Sprite(
                assets.get("graphics/icons/undo.png",
                        Texture.class));
        undoSprite.setSize(UiSize.getIconSize(),
                UiSize.getIconSize());
        undoButtonStyle.imageUp = new SpriteDrawable(undoSprite);
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
            }
        });

        ImageButton.ImageButtonStyle backButtonStyle =
                new ImageButton.ImageButtonStyle(
                        skin.get("default", Button.ButtonStyle.class));
        Sprite backSprite = new Sprite(
                assets.get("graphics/icons/back.png",
                        Texture.class));
        backSprite.setSize(UiSize.getIconSize(),
                UiSize.getIconSize());
        backButtonStyle.imageUp = new SpriteDrawable(backSprite);
        backButton = new ImageButton(backButtonStyle);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                goBack();
                setCurrentMenu(Constants.CurrentPizzaMenu.MAIN);
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

        setCurrentMenu(Constants.CurrentPizzaMenu.MAIN);

        // DEBUG UI
        super.setDebug(Constants.DEBUG_GRAPHICS);
    }

    /**
     * Handle a back button press. Returns true if handled by PizzaUi, false
     * otherwise.
     * @return boolean true if handled
     */
    public boolean goBack() {
        boolean result;
        if (currentMenu == Constants.CurrentPizzaMenu.TOPPING) {
            setCurrentMenu(Constants.CurrentPizzaMenu.MAIN);
            result = true;
        } else {
            pizzaScreen.goBack();
            result = false;
        }
        return result;
    }

    public Constants.CurrentPizzaMenu getCurrentMenu() {
        return currentMenu;
    }

    private void setCurrentMenu(Constants.CurrentPizzaMenu menu) {
        currentMenu = menu;
        switch(menu) {
            case MAIN:
                showMainMenu();
                break;
            case TOPPING:
                showToppingMenu();
                break;
        }
    }

    private void showMainMenu() {
        // close button column (appears above pizza)
        Table closeColumn = new Table();
        closeColumn.left()
                .top();
        closeColumn.add(closeButton)
                .prefSize(buttonHeight)
                .space(padding);

        // UI column: the main UI for pizza creation
        Table uiColumn = new Table();
        uiColumn.add(headerImage)
                .space(padding)
                .colspan(2).center();
        uiColumn.row();
        uiColumn.add(toppingSelectButton).space(padding)
                .prefSize(buttonWidthFull,
                        buttonHeight)
                .colspan(2)
                .right();
        uiColumn.row();
        uiColumn.add(cameraButton)
                .prefSize(buttonWidthHalf,
                        buttonHeight)
                .space(padding);
        uiColumn.add(undoButton)
                .prefSize(buttonWidthHalf,
                        buttonHeight)
                .space(padding);
        uiColumn.row();
        uiColumn.add(cookButton)
                .prefSize(buttonWidthFull,
                        buttonHeight)
                .space(padding)
                .colspan(2);

        super.clear();
        super.pad(padding);
        super.add(closeColumn)
                .prefSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT)
                .space(padding);
        super.add(uiColumn)
                .space(padding);
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
            super.add(b).space(padding)
                    .prefSize(Constants.BUTTON_WIDTH_FULL,
                            buttonHeight);
            counter++;
        }
        super.row();
        super.add(backButton)
                .colspan(2)
                .prefSize(buttonWidthHalf,
                        buttonHeight)
                .right()
                .space(padding);
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
                setCurrentMenu(Constants.CurrentPizzaMenu.MAIN);
                showMainMenu();
            }
        });
        toppingButtons.add(textButton);
        toppingGroup.add(textButton);
    }

    private void setSelectedTopping(TextButton button) {
        toppingSelectButton.setText(button.getText().toString());
    }

    public boolean undoButtonPressed() {
        return undoButton.isPressed();
    }
}
