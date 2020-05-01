package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * The FlyingPizza class is a scaled-down Sprite created from a Pizza.class object,
 * which starts in a random position off-screen, and flies across the screen. Used in the
 * ServeWorkersScreen party scene.
 */

public class FlyingPizza {

    private boolean isActive;
    private final Sprite sprite;
    private final float speed;
    private final float changeX;
    private final float changeY;

    public FlyingPizza (Texture texture) {
        sprite = new Sprite(texture);
        sprite.setSize(sprite.getWidth() * Constants.FLYING_PIZZA_SCALE,
                sprite.getHeight() * Constants.FLYING_PIZZA_SCALE);

        float direction = 0;
        // randomise sprite start position and direction
        switch (MathUtils.random(1, 4)) {
            case 1:
                // bottom
                sprite.setX(MathUtils.randomTriangular(-sprite.getWidth(), Constants.GAME_WIDTH));
                sprite.setY(-sprite.getHeight());
                direction = MathUtils.randomTriangular(0f, MathUtils.PI);
                break;
            case 2:
                // left
                sprite.setX(-sprite.getWidth());
                sprite.setY(MathUtils.randomTriangular(-sprite.getHeight(), Constants.GAME_HEIGHT));
                direction = MathUtils.randomTriangular(-MathUtils.PI / 2, MathUtils.PI / 2);
                break;
            case 3:
                // top
                sprite.setX(MathUtils.randomTriangular(-sprite.getWidth(), Constants.GAME_WIDTH));
                sprite.setY(Constants.GAME_HEIGHT);
                direction = MathUtils.randomTriangular(MathUtils.PI, MathUtils.PI2);
                break;
            case 4:
                // right
                sprite.setX(Constants.GAME_WIDTH);
                sprite.setY(MathUtils.randomTriangular(-sprite.getHeight(), Constants.GAME_HEIGHT));
                direction = MathUtils.randomTriangular(MathUtils.PI / 2, 3 * MathUtils.PI / 2);
                break;
        }

        // randomise sprite rotation
        float rotation = MathUtils.random(0f, 360f);
        sprite.setOriginCenter();
        sprite.setRotation(rotation);

        speed = MathUtils.randomTriangular(Constants.FLYING_PIZZA_SPEED_MIN,
                Constants.FLYING_PIZZA_SPEED_MAX);
        changeX = MathUtils.cos(direction);
        changeY = MathUtils.sin(direction);

        isActive = true;
    }

    public boolean isActive() {
        return isActive;
    }

    public void draw (SpriteBatch batch) {
        if (isActive) {
            sprite.draw(batch);
        }
    }

    public void update(float delta) {
        if (isActive) {
            sprite.setX(sprite.getX() + changeX * speed * delta);
            sprite.setY(sprite.getY() + changeY * speed * delta);

            if (sprite.getX() < -sprite.getWidth()
                    | sprite.getX() > Constants.GAME_WIDTH
                    | sprite.getY() < -sprite.getHeight()
                    | sprite.getY() > Constants.GAME_HEIGHT) {
                isActive = false;
            }
        }
    }

}
