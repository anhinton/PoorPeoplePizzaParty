package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * Topping class to represent toppings which can be placed on our Pizza.
 */

public class Topping {
    private final Constants.ToppingName toppingName;
    private final Sprite sprite;
    private final Sprite selectedSprite;
    private boolean visible;

    public Topping(float x, float y, float rotation,
                   Constants.ToppingName toppingName,
                   Texture texture,
                   boolean visible) {
        this.toppingName = toppingName;
        this.visible = visible;

        sprite = new Sprite(texture);
        if (toppingName == Constants.ToppingName.BASE |
                toppingName == Constants.ToppingName.SAUCE |
                toppingName == Constants.ToppingName.CHEESE) {
            sprite.setSize(Constants.BASE_WIDTH, Constants.BASE_HEIGHT);
            sprite.setCenter(Constants.BASE_CENTER_X, Constants.BASE_CENTER_Y);
            selectedSprite = new Sprite(sprite);
            selectedSprite.setScale(Constants.BASE_SCALE);
        } else if (toppingName == Constants.ToppingName.APRICOT |
                toppingName == Constants.ToppingName.BARBECUE) {
            sprite.setCenter(Constants.BASE_CENTER_X, Constants.BASE_CENTER_Y);
            sprite.setRotation(rotation);
            sprite.setColor(Constants.SAUCE_COLOR);
            selectedSprite = new Sprite(sprite);
            selectedSprite.setScale(Constants.BASE_SCALE);
        } else {
            sprite.setX(x);
            sprite.setY(y);
            sprite.setRotation(rotation);
            selectedSprite = sprite;
        }
    }

    float getRotation() {
        return selectedSprite.getRotation();
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

    boolean getVisible() {
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
