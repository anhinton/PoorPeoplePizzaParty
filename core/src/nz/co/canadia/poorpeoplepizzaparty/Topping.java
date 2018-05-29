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

    public Topping(float x, float y, float rotation,
                   Constants.ToppingName toppingName,
                   ObjectMap<Constants.ToppingName, Texture> textureObjectMap) {
        this.toppingName = toppingName;

        if (toppingName == Constants.ToppingName.NONE) {
            this.sprite = new Sprite();
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
        if (toppingName != Constants.ToppingName.NONE) {
            sprite.draw(batch);
        }
    }

    public void update(float x, float y) {
        sprite.setCenter(x, y);
    }
}
