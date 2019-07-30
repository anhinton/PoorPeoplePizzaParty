package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Points {
    private final BitmapFont font;
    private final String scoreDisplay;
    private float x;
    private float y;
    private boolean visible;
    private float timeElapsed;

    public Points(float x, float y, BitmapFont font, String scoreDisplay) {
        this.x = x;
        this.y = y;
        this.font = font;
        this.scoreDisplay = scoreDisplay;
        visible = true;
        timeElapsed = 0.0f;
    }

    public void draw(SpriteBatch batch) {
        if (visible) {
            font.draw(batch, scoreDisplay, x, y);
        }
    }

    public void update(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
