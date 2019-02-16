package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import nz.co.canadia.poorpeoplepizzaparty.screens.ServeWorkersScreen;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class DoomDrips {
    private final Sprite sprite;
    private final ServeWorkersScreen serveWorkersScreen;
    private boolean isVisible;
    private boolean isActive;
    private boolean hasFired;

    public DoomDrips(Texture texture, ServeWorkersScreen serveWorkersScreen) {
        this.serveWorkersScreen = serveWorkersScreen;
        isActive = false;
        isVisible = false;
        hasFired = false;
        sprite = new Sprite(texture);
        sprite.setPosition(0, Constants.GAME_HEIGHT);
        sprite.setColor(1, 1, 1, .25f);
    }

    public void start() {
        isActive = true;
        isVisible = true;
    }

    public void draw(SpriteBatch batch) {
        if (isVisible) {
            sprite.draw(batch);
        }
    }

    public void update(float delta) {
        if (isActive) {
            if (sprite.getY() > Constants.GAME_HEIGHT - sprite.getHeight()) {
                sprite.setY(sprite.getY() - delta * Constants.DOOM_DRIPS_SPEED);
            } else {
                sprite.setY(Constants.GAME_HEIGHT - sprite.getHeight());
                isActive = false;
            }
        } else {
            if (!hasFired) {
                serveWorkersScreen.showFiredButton();
                hasFired = true;
            }
        }
    }
}
