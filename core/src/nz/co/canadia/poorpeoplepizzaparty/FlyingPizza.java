package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * The FlyingPizza class is a scaled-down Sprite created from a Pizza.class object,
 * which starts in a random position off-screen, and flies across the screen. Used in the
 * ServeWorkersScreen party scene.
 */

public class FlyingPizza {
    private final Pixmap pixmap;
    private final Texture texture;
    private Sprite sprite;

    public FlyingPizza (Pizza pizza) {
        pixmap = pizza.getPixmap();
        texture = new Texture(pixmap);
        texture.setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        sprite = new Sprite(texture);
        //TODO: set scaling in Constants.class
        sprite.setSize(Constants.GAME_HEIGHT / 3f, Constants.GAME_HEIGHT / 3f);
        sprite.setCenter(Constants.GAME_WIDTH / 2f, Constants.GAME_HEIGHT / 2f);
    }

    public void draw (SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void dispose() {
        pixmap.dispose();
        texture.dispose();
    }
}
