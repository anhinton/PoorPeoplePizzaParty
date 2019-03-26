package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;

/**
 * The Postcard class is used to capture a Pixmap from the game.
 */

public class Postcard {
    private final Pixmap postcardPixmap;
    private final Sprite postcardSprite;
    private final Texture postcardTexture;

    public Postcard(Pizza pizza, Assets assets) {
        assets.loadPostcardAssets();
        postcardPixmap = postcardPixmap(pizza, assets);
        postcardTexture = new Texture(postcardPixmap);
        postcardSprite = new Sprite(postcardTexture);
    }

    public void draw(SpriteBatch batch) {
        postcardSprite.draw(batch);
    }

    public void dispose() {
        postcardPixmap.dispose();
        postcardTexture.dispose();
    }

    /**
     * Returns a timestamped filename string
     * @return String with timestamp
     */
    public static String fileName() {
        return Constants.CAPTURE_PREFIX + TimeUtils.millis() + Constants.CAPTURE_SUFFX;
    }

    /**
     * Returns a pixmap of a Pizza postcard
     * @return pixmap
     */
    public static Pixmap postcardPixmap(Pizza pizza, Assets assets) {
        int pizzaX = Constants.GAME_WIDTH - Constants.BASE_WIDTH
                - Constants.BASE_X;
        int pizzaY = Constants.BASE_Y;

        // create temporary Pixmap from Pizza
        Pixmap pizzaPixmap = pizza.getPixmap();

        // load postcard background Pixmap
        Pixmap backgroundPixmap = assets.get("graphics/postcard.png",
                Pixmap.class);

        // create new Pixmap to return as postcardPixmap
        Pixmap postcardPixmap = new Pixmap(backgroundPixmap.getWidth(),
                backgroundPixmap.getHeight(), backgroundPixmap.getFormat());

        // draw background to postcardPixmap
        postcardPixmap.drawPixmap(backgroundPixmap, 0, 0);

        // draw temporary pizzaPixmap to postcardPixmap
        postcardPixmap.drawPixmap(pizzaPixmap, pizzaX, pizzaY);
        // dispose of temporary pizzaPixmap
        pizzaPixmap.dispose();

        return postcardPixmap;
    }
}
