package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import nz.co.canadia.poorpeoplepizzaparty.screens.PizzaScreen;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * Creates the toppings menu UI for selecting a topping on the PizzaScreen
 */

public class ToppingMenu extends Table{

    private Skin skin;
    private PizzaScreen pizzaScreen;
    public ButtonGroup<TextButton> buttonGroup;

    public ToppingMenu(final PizzaScreen pizzaScreen, Skin skin) {

        this.pizzaScreen = pizzaScreen;
        this.setFillParent(true);
        this.top().right().pad(Constants.MENU_PADDING);

        this.skin = skin;

        buttonGroup = new ButtonGroup<TextButton>();
        buttonGroup.setMinCheckCount(0);
        buttonGroup.setMaxCheckCount(1);

        addMenuItem(Constants.ToppingName.SAUCE, "Pizza sauce");
        addMenuItem(Constants.ToppingName.CHEESE, "Cheese");
        this.row();

        addMenuItem(Constants.ToppingName.BACON, "Bacon");
        addMenuItem(Constants.ToppingName.SAUSAGE, "Sasuage");

        // DEBUG UI
        this.setDebug(true);
    }

    private void addMenuItem (final Constants.ToppingName toppingName, String text) {
        TextButton textButton = new TextButton(
                text, skin, "toggle");
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pizzaScreen.setSelectedTopping(toppingName);
            }
        });
        this.add(textButton).space(Constants.MENU_PADDING)
                .prefSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        buttonGroup.add(textButton);
    }

    public void dispose() {
        skin.dispose();
    }
}
