package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class DoomDrips {
    private final Sprite sprite;
    private boolean isActive;

    public DoomDrips(Texture texture) {
        isActive = false;
        sprite = new Sprite(texture);
        sprite.setPosition(0, Constants.GAME_HEIGHT);
        sprite.setColor(1, 1, 1, .5f);
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void draw(SpriteBatch batch) {
        if (isActive) {
            sprite.draw(batch);
        }
    }

    public void update(float delta) {
        if (isActive) {
            if (sprite.getY() > Constants.GAME_HEIGHT - sprite.getHeight()) {
                sprite.setY(sprite.getY() - delta * Constants.DOOM_DRIPS_SPEED);
            } else {
                sprite.setY(Constants.GAME_HEIGHT - sprite.getHeight());
            }
        }
    }
}
