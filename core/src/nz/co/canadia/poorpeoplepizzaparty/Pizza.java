package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * The pizza which we make
 */

public class Pizza {

    private final Assets assets;
    private final Array<Topping> toppings;
    private final Array<Constants.ToppingName> toppingOrder;
    private final Array<Constants.ToppingName> baseToppingOrder;

    public Pizza(final Assets assets) {
        this.assets = assets;

        // add the base Topping to the topping array
        toppings = new Array<Topping>();
        toppings.add(new Topping(Constants.BASE_X, Constants.BASE_Y,
                0, Constants.ToppingName.BASE,
                assets.get(assets.toppingPath(Constants.ToppingName.BASE),
                        Texture.class),
                true));

        // initialise toppingOrder and baseToppingOrder arrays
        toppingOrder = new Array<Constants.ToppingName>();
        baseToppingOrder = new Array<Constants.ToppingName>();
        baseToppingOrder.add(Constants.ToppingName.BASE);
    }

    public void addTopping(Topping topping) {
        if (topping.getVisible()) {
            if (topping.getToppingName() == Constants.ToppingName.SAUCE
                    | topping.getToppingName() == Constants.ToppingName.CHEESE) {
                if (topping.getToppingName() != baseToppingOrder.peek()) {
                    setBaseTopping(topping.getToppingName());
                    toppingOrder.add(topping.getToppingName());
                    baseToppingOrder.add(topping.getToppingName());
                }
            } else {
                this.toppings.add(topping);
                toppingOrder.add(topping.getToppingName());
            }
        }
    }

    /**
     * Remove the last topping added to the pizza
     */
    public void undoLastTopping() {
        if (toppingOrder.size > 0) {
            switch (toppingOrder.peek()) {
                case BASE:
                    setBaseTopping(Constants.ToppingName.BASE);
                    break;
                case SAUCE:
                    baseToppingOrder.pop();
                    setBaseTopping(baseToppingOrder.peek());
                    toppingOrder.pop();
                    break;
                case CHEESE:
                    baseToppingOrder.pop();
                    setBaseTopping(baseToppingOrder.peek());
                    toppingOrder.pop();
                    break;
                default:
                    toppingOrder.pop();
                    toppings.pop();
                    break;
            }
        }
    }

    /**
     * return a Pixmap of the pizza and toppings
     */
    public Pixmap getPixmap() {

        SpriteBatch batch = new SpriteBatch();
        FrameBuffer buffer = new FrameBuffer(Pixmap.Format.RGBA8888,
                Constants.GAME_WIDTH, Constants.GAME_HEIGHT, false);

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH,
                Constants.GAME_HEIGHT);
        batch.setProjectionMatrix(camera.combined);

        buffer.begin();

        batch.begin();
        draw(batch);
        batch.end();

        Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(Constants.BASE_X,
                Constants.BASE_Y, Constants.BASE_WIDTH, Constants.BASE_HEIGHT);

        batch.dispose();
        buffer.dispose();
        return pixmap;
    }

    private void setBaseTopping(Constants.ToppingName toppingName) {
        toppings.set(0, new Topping(Constants.BASE_X, Constants.BASE_Y,
                0, toppingName,
                assets.get(assets.toppingPath(toppingName), Texture.class),
                true));
    }

    public Array<Topping> getToppings() {
        return toppings;
    }

    public void draw (SpriteBatch batch) {
        for (Topping topping: toppings) {
            topping.draw(batch);
        }
    }
}
