package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

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

        // load random postcard background Pixmap
        FileHandle postcardsDir = Gdx.files.internal("graphics/postcards");

        String[] postcardFiles = {
                "graphics/postcards/postcard01.png",
                "graphics/postcards/postcard02.png",
                "graphics/postcards/postcard03.png"
        };
        Pixmap backgroundPixmap = assets.get(
                postcardFiles[MathUtils.random(postcardFiles.length - 1)]
        );

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
