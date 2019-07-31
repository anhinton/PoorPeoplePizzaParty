package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class Points {
    private final Label label;
    private boolean visible;
    private float alpha;

    public Points(float x, float y, Label.LabelStyle labelStyle, String text) {
        label = new Label(text, labelStyle);
        label.setPosition(x - label.getWidth() / 2, y - label.getHeight() / 2);
        visible = true;
        alpha = 1;
    }

    public boolean isVisible() {
        return visible;
    }

    public void draw(SpriteBatch batch) {
        if (visible) {
            label.draw(batch, 1);
        }
    }

    public void update(float delta) {
        if (visible) {
            label.setY(label.getY() + delta * Constants.POINTS_MOVEMENT_SPEED);
            alpha -= delta * Constants.POINTS_FADE_RATE;
            if (alpha <= 0) {
                alpha = 0;
                visible = false;
            }
            label.setColor(new Color(1, 1, 1, alpha));
        }
    }
}
