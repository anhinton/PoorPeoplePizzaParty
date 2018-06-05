package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ObjectMap;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * Topping class to represent toppings which can be placed on our Pizza.
 */

public class Topping {
    private Constants.ToppingName toppingName;
    private Sprite sprite;
    private Sprite selectedSprite;
    private boolean visible;

    public Topping(float x, float y, float rotation,
                   Constants.ToppingName toppingName,
                   ObjectMap<Constants.ToppingName, Texture> textureObjectMap,
                   boolean visible) {
        this.toppingName = toppingName;
        this.visible = visible;

        sprite = new Sprite(textureObjectMap.get(toppingName));
        if (toppingName == Constants.ToppingName.SAUCE |
                toppingName == Constants.ToppingName.CHEESE) {
//            sprite.setX(Constants.BASE_X);
//            sprite.setY(Constants.BASE_Y);
            sprite.setCenter(Constants.BASE_CENTER_X, Constants.BASE_CENTER_Y);
            sprite.setSize(Constants.BASE_WITDH, Constants.BASE_HEIGHT);
            selectedSprite = new Sprite(sprite);
            selectedSprite.setScale((float)1/5);
        } else if (toppingName == Constants.ToppingName.APRICOT |
                toppingName == Constants.ToppingName.BARBECUE) {
            sprite.setCenter(Constants.BASE_CENTER_X, Constants.BASE_CENTER_Y);
            sprite.setRotation(rotation);
            sprite.setColor(1,1,1, (float)0.5);
            selectedSprite = new Sprite(sprite);
            selectedSprite.setScale((float)1/5);
        } else {
            sprite.setX(x);
            sprite.setY(y);
            sprite.setRotation(rotation);
            selectedSprite = sprite;
        }
    }

    public float getX() {
        return selectedSprite.getX();
    }

    public float getY() {
        return selectedSprite.getY();
    }

    public Rectangle getBoundingRectangle() {
        return selectedSprite.getBoundingRectangle();
    }

    public Constants.ToppingName getToppingName() {
        return toppingName;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void drawSelected(SpriteBatch batch) {
        if (visible) {
            selectedSprite.draw(batch);
        }
    }

    public void update(float x, float y) {
        selectedSprite.setCenter(x, y);
    }
}
