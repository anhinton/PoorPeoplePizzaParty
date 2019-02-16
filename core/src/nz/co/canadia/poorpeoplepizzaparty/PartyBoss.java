package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class PartyBoss {
    private final Sprite sprite;
    private boolean isActive;

    public PartyBoss(Texture texture) {
        isActive = false;
        sprite = new Sprite(texture);
        sprite.setCenterX(Constants.GAME_WIDTH * 3f / 4);
        sprite.setY(0 - sprite.getHeight());
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
            if (sprite.getY() < 0) {
                sprite.setY(sprite.getY() + delta * Constants.PARTY_BOSS_SPEED);
            } else {
                sprite.setY(0);
            }
        }
    }
}
