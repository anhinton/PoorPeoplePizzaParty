package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.ObjectMap;

import nz.co.canadia.poorpeoplepizzaparty.screens.PizzaScreen;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * The pizza which we make
 */

public class Pizza {

    private I18NBundle bundle;
    private Array<Topping> toppings;
    private Array<Constants.ToppingName> toppingOrder;
    private ObjectMap<Constants.ToppingName, String> toppingStrings;
    private AssetManager manager;
    private PizzaScreen pizzaScreen;

    public Pizza(ObjectMap<Constants.ToppingName, String> toppingStrings,
                 AssetManager manager, I18NBundle bundle, PizzaScreen pizzaScreen) {
        this.toppingStrings = toppingStrings;
        this.bundle = bundle;
        this.manager = manager;
        this.pizzaScreen = pizzaScreen;

        // add the base Topping to the topping array
        toppings = new Array<Topping>();
        toppings.add(new Topping(Constants.BASE_X, Constants.BASE_Y,
                0, Constants.ToppingName.BASE,
                manager.get(toppingStrings.get(Constants.ToppingName.BASE),
                        Texture.class),
                true));

        // initialise toppingOrder array
        toppingOrder = new Array<Constants.ToppingName>();
        toppingOrder.add(Constants.ToppingName.BASE);
    }

    public void addTopping(Topping topping) {
        if (topping.getVisible()) {
            if (topping.getToppingName() == Constants.ToppingName.SAUCE) {
                if (toppingOrder.peek() != Constants.ToppingName.BASE) {
                    pizzaScreen.showMessage(bundle.get("messagePizzaWarning"));
                } else {
                    setBaseTopping(topping.getToppingName());
                    toppingOrder.add(topping.getToppingName());
                }
            } else if (topping.getToppingName() == Constants.ToppingName.CHEESE) {
                if (toppingOrder.peek() != Constants.ToppingName.SAUCE) {
                    pizzaScreen.showMessage(bundle.get("messagePizzaWarning"));
                } else {
                    setBaseTopping(topping.getToppingName());
                    toppingOrder.add(topping.getToppingName());
                }
            } else {
                this.toppings.add(topping);
                toppingOrder.add(topping.getToppingName());
            }
        }
    }

    /**
     * Remove the last topping added to the pizza
     */
    public void undoLastTopping() {
        if (toppingOrder.size > 0) {
            switch (toppingOrder.peek()) {
                case BASE:
                    setBaseTopping(Constants.ToppingName.BASE);
                    break;
                case SAUCE:
                    setBaseTopping(Constants.ToppingName.BASE);
                    toppingOrder.pop();
                    break;
                case CHEESE:
                    setBaseTopping(Constants.ToppingName.SAUCE);
                    toppingOrder.pop();
                    break;
                default:
                    toppingOrder.pop();
                    toppings.pop();
                    break;
            }
        }
    }

    private Topping getBaseTopping() {
        return toppings.get(0);
    }

    private void setBaseTopping(Constants.ToppingName toppingName) {
        toppings.set(0, new Topping(Constants.BASE_X, Constants.BASE_Y,
                0, toppingName,
                manager.get(toppingStrings.get(toppingName), Texture.class),
                true));
    }

    public Array<Topping> getToppings() {
        return toppings;
    }

    public void draw (SpriteBatch batch) {
        for (Topping topping: toppings) {
            topping.draw(batch);
        }
    }
}
