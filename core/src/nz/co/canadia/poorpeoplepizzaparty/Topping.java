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
    private float x;
    private float y;
    private float rotation;
    private Constants.ToppingName toppingName;
    private Sprite sprite;

    public Topping(float x, float y, float rotation,
                   Constants.ToppingName toppingName,
                   ObjectMap<Constants.ToppingName, Texture> textureObjectMap) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.toppingName = toppingName;

        if (toppingName == Constants.ToppingName.NONE) {
            this.sprite = null;
        } else {
            this.sprite = new Sprite(textureObjectMap.get(toppingName));
            this.sprite.setX(x);
            this.sprite.setY(y);
            this.sprite.setRotation(rotation);
        }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Constants.ToppingName getToppingName() {
        return toppingName;
    }

    public void update() {
        sprite.setX(getX());
        sprite.setY(getY());
        sprite.setRotation(getRotation());
    }

    public void draw(SpriteBatch batch) {
        if (toppingName != Constants.ToppingName.NONE) {
            sprite.draw(batch);
        }
    }
}
