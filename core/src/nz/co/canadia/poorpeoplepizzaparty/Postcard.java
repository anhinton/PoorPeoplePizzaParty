package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * The Postcard class is used to capture a Pixmap from the game.
 */

public class Postcard {
    private final Pixmap pixmap;
    private final Sprite sprite;
    private final Texture texture;

    public Postcard(Pizza pizza, TextureAtlas atlas) {
        pixmap = initPixmap(pizza, atlas);
        texture = new Texture(pixmap);
        sprite = new Sprite(texture);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void dispose() {
        pixmap.dispose();
        texture.dispose();
    }

    /**
     * Returns a timestamped filename string
     * @return String with timestamp
     */
    public String fileName() {
        return Constants.CAPTURE_PREFIX + TimeUtils.millis() + Constants.CAPTURE_SUFFX;
    }

    /**
     * Returns a pixmap of a Pizza postcard
     * @return pixmap
     */
    public Pixmap getPixmap() {
        return pixmap;
    }

    private Pixmap initPixmap(Pizza pizza, TextureAtlas atlas) {
        int pizzaX = Constants.GAME_WIDTH - Constants.BASE_WIDTH
                - Constants.BASE_X;
        int pizzaY = Constants.BASE_Y;

        // create temporary Pixmap from Pizza
        Pixmap pizzaPixmap = pizza.getPixmap();

        String[] postcardRegions = {
                "postcards/postcard01",
                "postcards/postcard02",
                "postcards/postcard03"
        };
        TextureRegion backgroundTexture = atlas.findRegion(
                postcardRegions[MathUtils.random(postcardRegions.length - 1)]);

        // create new Pixmap to return as pixmap
        Pixmap postcardPixmap = new Pixmap(backgroundTexture.getRegionWidth(),
                backgroundTexture.getRegionHeight(), Pixmap.Format.RGBA8888);

        SpriteBatch spriteBatch = new SpriteBatch();
        FrameBuffer buffer = new FrameBuffer(Pixmap.Format.RGBA8888,
                backgroundTexture.getRegionWidth(), backgroundTexture.getRegionHeight(),
                false);

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(true, Constants.GAME_WIDTH,
                Constants.GAME_HEIGHT);
        spriteBatch.setProjectionMatrix(camera.combined);

        buffer.begin();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, backgroundTexture.getRegionWidth(), backgroundTexture.getRegionHeight());
        spriteBatch.end();

        // draw background to pixmap
        postcardPixmap.drawPixmap(
                ScreenUtils.getFrameBufferPixmap(
                        0, 0, backgroundTexture.getRegionWidth(),
                        backgroundTexture.getRegionHeight()),
                0, 0);

        buffer.end();

        spriteBatch.dispose();
        buffer.dispose();

        // draw temporary pizzaPixmap to pixmap
        postcardPixmap.drawPixmap(pizzaPixmap, pizzaX, pizzaY);
        // dispose of temporary pizzaPixmap
        pizzaPixmap.dispose();

        return postcardPixmap;
    }
}
