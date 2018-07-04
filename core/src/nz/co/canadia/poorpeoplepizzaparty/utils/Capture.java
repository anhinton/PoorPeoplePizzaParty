package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;

/**
 * The Capture class is used to capture a Pixmap from the game.
 */

public class Capture {
    public static Pixmap capturePizza(Pizza pizza) {

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
}
