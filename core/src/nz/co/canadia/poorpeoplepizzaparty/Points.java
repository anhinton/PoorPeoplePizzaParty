package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class Points {
    private final BitmapFontCache fontCache;
    private final float speed;
    private boolean visible;
    private float alpha;

    public Points(float x, float y, BitmapFont font, String text) {
        fontCache = font.newFontCache();
        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        fontCache.setText(glyphLayout, x - glyphLayout.width / 2, y + glyphLayout.height / 2);
        speed = Constants.POINTS_MOVEMENT_SPEED / Constants.GAME_HEIGHT
                * Gdx.graphics.getBackBufferHeight();
        visible = true;
        alpha = 1;
    }

    public boolean isVisible() {
        return visible;
    }

    public void draw(Batch batch) {
        if (visible) {
            fontCache.draw(batch);
        }
    }

    public void update(float delta) {
        if (visible) {
            fontCache.translate(0, delta * speed);
            alpha -= delta * Constants.POINTS_FADE_RATE;
            if (alpha <= 0) {
                alpha = 0;
                visible = false;
            }
            fontCache.setAlphas(alpha);
        }
    }
}
