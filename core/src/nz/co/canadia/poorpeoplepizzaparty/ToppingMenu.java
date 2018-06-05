package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

import nz.co.canadia.poorpeoplepizzaparty.screens.PizzaScreen;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * Creates the toppings menu UI for selecting a topping on the PizzaScreen
 */

public class ToppingMenu extends Table{

    private Skin skin;
    private PizzaScreen pizzaScreen;
    private Array<TextButton> toppingButtons;
    private ButtonGroup<TextButton> toppingGroup;
    private TextButton selectButton;

    public ToppingMenu(final PizzaScreen pizzaScreen, Skin skin,
                       boolean debugGraphics) {

        this.pizzaScreen = pizzaScreen;
        this.setFillParent(true);

        this.skin = skin;

        selectButton = new TextButton("Select topping", skin,
                "default");
        selectButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                showToppingMenu();
            }
        });

        toppingGroup = new ButtonGroup<TextButton>();
        toppingGroup.setMinCheckCount(0);
        toppingGroup.setMaxCheckCount(1);

        toppingButtons = new Array<TextButton>();
        addToppingButton(Constants.ToppingName.SAUCE, "Pizza sauce");
        addToppingButton(Constants.ToppingName.CHEESE, "Cheese");
        this.row();
        addToppingButton(Constants.ToppingName.BACON, "Bacon");
        addToppingButton(Constants.ToppingName.SAUSAGE, "Sausage");
        this.row();
        addToppingButton(Constants.ToppingName.SALAMI, "Salami");
        addToppingButton(Constants.ToppingName.CHICKEN, "Chicken");
        this.row();
        addToppingButton(Constants.ToppingName.APRICOT, "Apricot Swirl");
        addToppingButton(Constants.ToppingName.BARBECUE, "Barbecue Swirl");

        showMainMenu();

        // DEBUG UI
        this.setDebug(debugGraphics);
    }

    private void showToppingMenu() {
        this.clear();
        this.center().top();
        this.add(new Label("Select topping:", skin, "default"))
                .colspan(2);
        this.row();
        int counter = 0;
        for (TextButton b: toppingButtons) {
            if (counter > 0 & counter % 2 == 0)
                this.row();
            this.add(b).space(Constants.MENU_PADDING)
                    .prefSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
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
        this.add(selectButton).space(Constants.MENU_PADDING)
                .prefSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
    }

    private void setSelectedTopping(TextButton button) {
        selectButton.setText(button.getText().toString());
    }

    public void dispose() {
        skin.dispose();
    }

}
