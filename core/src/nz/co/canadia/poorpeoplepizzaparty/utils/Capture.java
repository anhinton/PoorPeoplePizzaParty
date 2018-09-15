package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Locale;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;

/**
 * The Capture class is used to capture a Pixmap from the game.
 */

public class Capture {
    private static Pixmap capturePizza(Pizza pizza) {

        SpriteBatch batch = new SpriteBatch();
        FrameBuffer buffer = new FrameBuffer(Pixmap.Format.RGBA8888,
                Constants.GAME_WIDTH, Constants.GAME_HEIGHT, false);

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH,
                Constants.GAME_HEIGHT);
        batch.setProjectionMatrix(camera.combined);

        buffer.begin();
        batch.begin();
        pizza.draw(batch);
        batch.end();
        byte[] pixels = ScreenUtils.getFrameBufferPixels(Constants.BASE_X,
                Constants.BASE_Y, Constants.BASE_WIDTH, Constants.BASE_HEIGHT,
                true);
        Pixmap pixmap = new Pixmap(Constants.BASE_WIDTH, Constants.BASE_HEIGHT,
                Pixmap.Format.RGBA8888);
        BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);
        buffer.end();

        batch.dispose();
        buffer.dispose();
        return pixmap;
    }

    /**
     * Returns a timestamped filename string
     * @return String with timestamp
     */
    public static String fileName(Locale locale) {
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
        Pixmap pizzaPixmap = Capture.capturePizza(pizza);

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
