package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;

import java.io.IOException;
import java.io.StringWriter;

import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * The pizza which we make
 */

public class Pizza {

    private final TextureAtlas atlas;
    private final Assets assets;
    private final Array<Topping> toppings;
    private final Array<Constants.ToppingName> toppingOrder;
    private final Array<Constants.ToppingName> baseToppingOrder;

    public Pizza(final TextureAtlas atlas, Assets assets) {
        this.atlas = atlas;
        this.assets = assets;

        toppings = new Array<Topping>();
        toppingOrder = new Array<Constants.ToppingName>();
        baseToppingOrder = new Array<Constants.ToppingName>();

        initialize();
    }

    private void initialize() {
        // add the base Topping to the topping array
        toppings.add(new Topping(Constants.BASE_X, Constants.BASE_Y,
                0, Constants.ToppingName.BASE,
                atlas.findRegion(assets.toppingPath(Constants.ToppingName.BASE)),
                true));

        // initialise toppingOrder and baseToppingOrder arrays
        baseToppingOrder.add(Constants.ToppingName.BASE);
    }

    public void addTopping(Topping topping) {
        if (topping.getVisible()) {
            if (topping.getToppingName() == Constants.ToppingName.BASE) {
                setBaseTopping(topping.getToppingName());
            } else if (topping.getToppingName() == Constants.ToppingName.SAUCE
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
     * Remove all toppings from the pizza: the nuclear option
     */
    public void removeAllToppings() {
        toppings.clear();
        toppingOrder.clear();
        baseToppingOrder.clear();
        initialize();
    }

    /**
     * Remove the last topping added to the pizza
     */
    public void undoLastTopping() {
        if (toppingOrder.size > 0) {
            switch (toppingOrder.peek()) {
                case BASE:
                    //setBaseTopping(Constants.ToppingName.BASE);
                    break;
                case SAUCE:
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
        camera.setToOrtho(true, Constants.GAME_WIDTH,
                Constants.GAME_HEIGHT);
        batch.setProjectionMatrix(camera.combined);

        buffer.begin();

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        batch.begin();
        draw(batch);
        batch.end();

        Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(Constants.BASE_X,
                Constants.BASE_Y, Constants.BASE_WIDTH, Constants.BASE_HEIGHT);

        buffer.end();

        batch.dispose();
        buffer.dispose();
        return pixmap;
    }

    public String serialize() {
        StringWriter writer = new StringWriter();
        XmlWriter xml = new XmlWriter(writer);
        try {
            xml.element("pizza");
            for(Topping t: toppings) {
                xml.element("topping")
                        .attribute("x", t.getX())
                        .attribute("y", t.getY())
                        .attribute("rotation", t.getRotation())
                        .attribute("toppingName", t.getToppingName())
                        .attribute("visible", t.getVisible())
                        .pop();
            }
            xml.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    public void deserialize(String xmlString) {
        XmlReader xml = new XmlReader();
        XmlReader.Element rootElement = xml.parse(xmlString);
        if (rootElement != null) {
            Array<XmlReader.Element> toppingElements = rootElement.getChildrenByName("topping");
            for (XmlReader.Element e : toppingElements) {
                float x = e.getFloat("x");
                float y = e.getFloat("y");
                float rotation = e.getFloat("rotation");
                Constants.ToppingName toppingName = Constants.ToppingName.valueOf(e.get("toppingName"));
                boolean visible = e.getBoolean("visible");
                addTopping(new Topping(x, y, rotation, toppingName,
                        atlas.findRegion(assets.toppingPath(toppingName)), visible));
            }
        }
    }

    private void setBaseTopping(Constants.ToppingName toppingName) {
        toppings.set(0, new Topping(Constants.BASE_X, Constants.BASE_Y,
                0, toppingName,
                atlas.findRegion(assets.toppingPath(toppingName)),
                true));
    }

    public void draw (SpriteBatch batch) {
        for (Topping topping: toppings) {
            topping.draw(batch);
        }
    }
}
