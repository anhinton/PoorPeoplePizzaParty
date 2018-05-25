package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * The pizza which we make
 */

public class Pizza {

    private Array<Topping> toppingArray;
    private ObjectMap<Constants.ToppingName, Texture> textureObjectMap;

    public Pizza(ObjectMap<Constants.ToppingName, Texture> textureObjectMap) {

        this.textureObjectMap = textureObjectMap;

        // add the base Topping to the topping array
        toppingArray = new Array<Topping>();
        toppingArray.add(new Topping(Constants.BASE_X, Constants.BASE_Y,
                0, Constants.ToppingName.BASE, textureObjectMap));
    }

    public void draw (SpriteBatch batch) {
//        currentSprite.draw(batch);
        for (Topping topping: toppingArray) {
            topping.draw(batch);
        }
    }


    public void dispose() {
        for (Texture texture: textureObjectMap.values()) {
            texture.dispose();
        }
        textureObjectMap.clear();
    }

    public void addTopping(Topping topping) {
        this.toppingArray.add(topping);
    }
}
