package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class PizzaPartyAnimation {
    private final Animation<TextureRegion> animation;
    private float stateTime;
    private final int x;
    private final int y;
    private boolean isActive;
    private boolean isVisible;

    public PizzaPartyAnimation(TextureRegion textureRegion0, TextureRegion textureRegion1) {
        TextureRegion[] animationFrames = new TextureRegion[2];
        animationFrames[0] = new TextureRegion(textureRegion0);
        animationFrames[1] = new TextureRegion(textureRegion1);
        animation = new Animation<TextureRegion>(Constants.PIZZA_PARTY_FRAME_DURATION,
                animationFrames);
        stateTime = 0;
        x = Constants.GAME_WIDTH / 2 - textureRegion0.getRegionWidth() / 2;
        y = Constants.GAME_HEIGHT / 2 - textureRegion1.getRegionHeight() / 2;
        isActive = true;
        isVisible = true;
    }

    public void update(float delta) {
        if (isActive) {
            stateTime += delta;
        }
    }

    public void stop() {
        isActive = false;
        isVisible = false;
    }

    public void draw(SpriteBatch batch) {
        if (isVisible) {
            TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
            batch.draw(currentFrame, x, y);
        }
    }
}
