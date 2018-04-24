package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The pizza which we make
 */

public class Pizza {
    private Base base;

    public Pizza() {
        base = new Base();

    }

    public void draw(SpriteBatch batch) {
        base.draw(batch);
    }

    public void dispose() {
        base.dispose();
    }
}
