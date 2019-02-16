package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class PizzaPartyAnimation {
    private final Animation<TextureRegion> animation;
    private final TextureRegion[] animationFrames;
    private float stateTime;
    private int x;
    private int y;
    private boolean isActive;
    private boolean isVisible;

    public PizzaPartyAnimation(Texture texture0, Texture texture1) {
        animationFrames = new TextureRegion[2];
        animationFrames[0] = new TextureRegion(texture0);
        animationFrames[1] = new TextureRegion(texture1);
        animation = new Animation<TextureRegion>(Constants.PIZZA_PARTY_FRAME_DURATION,
                animationFrames);
        stateTime = 0;
        x = Constants.GAME_WIDTH / 2 - texture0.getWidth() / 2;
        y = Constants.GAME_HEIGHT / 2 - texture1.getHeight() / 2;
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
