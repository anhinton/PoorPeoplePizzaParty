package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import nz.co.canadia.poorpeoplepizzaparty.screens.ServeWorkersScreen;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class PartyBoss {
    private final Sprite sprite;
    private final ServeWorkersScreen serveWorkersScreen;
    private final float speed;

    private boolean isActive;
    private boolean isVisible;
    private boolean hasSpoken;

    public PartyBoss(TextureRegion textureRegion, ServeWorkersScreen serveWorkersScreen) {
        this.serveWorkersScreen = serveWorkersScreen;
        isActive = false;
        isVisible = false;
        hasSpoken = false;
        sprite = new Sprite(textureRegion);
        sprite.setCenterX(Constants.GAME_WIDTH * 3f / 4);
        sprite.setY(0 - sprite.getHeight());
        speed = sprite.getHeight() / Constants.PARTY_BOSS_TIME;
    }

    public boolean isActive() {
        return isActive;
    }

    public void start() {
        isVisible = true;
        isActive = true;
    }

    public void stop() {
        sprite.setY(0);
        isActive = false;
        if(!hasSpoken) {
            serveWorkersScreen.bossSpeaks();
            hasSpoken = true;
        }
    }

    public void draw(SpriteBatch batch) {
        if (isVisible) {
            sprite.draw(batch);
        }
    }

    public void update(float delta) {
        if (isActive) {
            if (sprite.getY() < 0) {
                sprite.setY(sprite.getY() + delta * speed);
            } else {
                stop();
            }
        }
    }
}
