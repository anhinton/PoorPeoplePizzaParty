package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * Topping class to represent toppings which can be placed on our Pizza.
 */

public class Topping {
    private Constants.ToppingName toppingName;
    private Sprite sprite;
    private boolean isVisible;

    public Topping(float x, float y, float rotation,
                   Constants.ToppingName toppingName,
                   ObjectMap<Constants.ToppingName, Texture> textureObjectMap,
                   boolean isVisible) {
        this.toppingName = toppingName;
        this.isVisible = isVisible;

        if (toppingName == Constants.ToppingName.NONE) {
            this.sprite = new Sprite();
            this.sprite.setSize(0, 0);
        } else {
            this.sprite = new Sprite(textureObjectMap.get(toppingName));
            this.sprite.setX(x);
            this.sprite.setY(y);
            this.sprite.setRotation(rotation);
        }
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public Constants.ToppingName getToppingName() {
        return toppingName;
    }

    public void draw(SpriteBatch batch) {
        if (isVisible) {
            sprite.draw(batch);
        }
    }

    public void update(float x, float y) {
        sprite.setCenter(x, y);
        if (toppingName != Constants.ToppingName.NONE) {
            isVisible = !(x < Constants.PIZZA_LEFT |
                    x > Constants.PIZZA_RIGHT |
                    y < Constants.PIZZA_BOTTOM |
                    y > Constants.PIZZA_TOP);
        }
    }
}
