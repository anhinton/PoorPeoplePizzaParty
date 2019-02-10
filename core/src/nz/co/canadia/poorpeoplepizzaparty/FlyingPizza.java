package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

import static java.lang.Math.PI;

/**
 * The FlyingPizza class is a scaled-down Sprite created from a Pizza.class object,
 * which starts in a random position off-screen, and flies across the screen. Used in the
 * ServeWorkersScreen party scene.
 */

public class FlyingPizza {
    private Sprite sprite;
    private float speed;
    private float changeX;
    private float changeY;

    public FlyingPizza (Texture texture) {
        sprite = new Sprite(texture);
        sprite.setSize(sprite.getWidth() * Constants.FLYING_PIZZA_SCALE,
                sprite.getHeight() * Constants.FLYING_PIZZA_SCALE);
        sprite.setCenter(Constants.GAME_WIDTH / 2f, Constants.GAME_HEIGHT / 2f);

        speed = MathUtils.random(Constants.FLYING_PIZZA_SPEED_LOWER,
                Constants.FLYING_PIZZA_SPEED_UPPER);
//        speed = 1600;
        float direction = MathUtils.random(0f, (float)(2 * Math.PI));
        changeX = MathUtils.cos(direction);
        changeY = MathUtils.sin(direction);
    }

    public void draw (SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void update(float delta) {
        sprite.setX(sprite.getX() + changeX * speed * delta);
        sprite.setY(sprite.getY() + changeY * speed * delta);
    }

    public void dispose() {

    }
}
