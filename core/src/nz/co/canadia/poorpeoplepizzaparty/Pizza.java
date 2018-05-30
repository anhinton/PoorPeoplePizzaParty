package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * The pizza which we make
 */

public class Pizza {

    private Array<Topping> toppingArray;

    public Pizza(ObjectMap<Constants.ToppingName, Texture> textureObjectMap) {

        // add the base Topping to the topping array
        toppingArray = new Array<Topping>();
        toppingArray.add(new Topping(Constants.BASE_X, Constants.BASE_Y,
                0, Constants.ToppingName.BASE, textureObjectMap,
                true));
    }

    public void addTopping(Topping topping) {
        if (topping.getToppingName() != Constants.ToppingName.NONE &
                topping.getVisible()) {
            if (topping.getToppingName() == Constants.ToppingName.SAUCE |
                    topping.getToppingName() == Constants.ToppingName.CHEESE) {
                toppingArray.set(0, topping);
            } else {
                this.toppingArray.add(topping);
            }
        }
    }

    public Array<Topping> getToppingArray() {
        return toppingArray;
    }

    public void draw (SpriteBatch batch) {
        for (Topping topping: toppingArray) {
            topping.draw(batch);
        }
    }
}
