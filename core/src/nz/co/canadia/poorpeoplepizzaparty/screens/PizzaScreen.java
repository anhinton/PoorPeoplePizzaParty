package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.Topping;
import nz.co.canadia.poorpeoplepizzaparty.ui.MessageUi;
import nz.co.canadia.poorpeoplepizzaparty.ui.PizzaUi;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * The main game screen where we create our pizza.
 */

public class PizzaScreen implements InputProcessor, Screen {

    private final PoorPeoplePizzaParty game;
    private final ObjectMap<Constants.ToppingName, String> textureFiles;
    private final Pizza pizza;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final Stage stage;
    private final PizzaUi pizzaUi;
    private final MessageUi messageUi;
    private Topping selectedTopping;

    public PizzaScreen(final PoorPeoplePizzaParty game) {
        this.game = game;

        // match topping names to image asset paths
        textureFiles = new ObjectMap<Constants.ToppingName, String>();
        textureFiles.put(Constants.ToppingName.APRICOT,
                "graphics/toppings/apricot-topping.png");
        textureFiles.put(Constants.ToppingName.BACON,
                "graphics/toppings/bacon-topping.png");
        textureFiles.put(Constants.ToppingName.BARBECUE,
                "graphics/toppings/barbecue-topping.png");
        textureFiles.put(Constants.ToppingName.BASE,
                "graphics/toppings/base-topping.png");
        textureFiles.put(Constants.ToppingName.CHEESE,
                "graphics/toppings/cheese-topping.png");
        textureFiles.put(Constants.ToppingName.CHICKEN,
                "graphics/toppings/chicken-topping.png");
        textureFiles.put(Constants.ToppingName.SALAMI,
                "graphics/toppings/salami-topping.png");
        textureFiles.put(Constants.ToppingName.SAUCE,
                "graphics/toppings/sauce-topping.png");
        textureFiles.put(Constants.ToppingName.SAUSAGE,
                "graphics/toppings/sausage-topping.png");
        game.assets.loadPizzaScreenAssets(textureFiles);
        game.assets.finishLoading();

        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT,
                camera);
        camera.setToOrtho(false, Constants.APP_WIDTH,
                Constants.APP_HEIGHT);

        stage = new Stage(viewport);
        pizzaUi = new PizzaUi(this, game.skin, game.bundle,
                game.screenshot, game.assets);
        stage.addActor(pizzaUi);
        messageUi = new MessageUi(game.skin);
        stage.addActor(messageUi);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

        pizza = new Pizza(textureFiles, game.assets, game.bundle,
                this);
        selectedTopping = null;
    }

    public void cook() {
        dispose();
        game.setScreen(new CookScreen(game));
    }

    public void undoLastTopping() {
        pizza.undoLastTopping();
    }

    public void clearMessage() {
        messageUi.clearMessage();
    }

    public void showMessage(String s) {
        messageUi.showMessage(s);
    }

    private boolean hasSelectedTopping() {
        return selectedTopping != null;
    }

    public void setSelectedTopping(Constants.ToppingName toppingName) {
        float x;
        float y;
        if (hasSelectedTopping()) {
            x = selectedTopping.getX();
            y = selectedTopping.getY();
        } else {
            x = Constants.APP_WIDTH / 2;
            y = Constants.APP_HEIGHT / 2;
        }
        selectedTopping = new Topping(
                x,
                y,
                game.random.nextFloat() * 360,
                toppingName,
                game.assets.get(textureFiles.get(toppingName), Texture.class),
                false);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        clearMessage();
        if (hasSelectedTopping()) {
            selectedTopping.setVisible(true);
        }
        // update selectedTopping location to follow mouse
        Vector3 mouseCoords = camera.unproject(
                new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if (hasSelectedTopping()) {
            selectedTopping.update(mouseCoords.x, mouseCoords.y);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 touchCoords = camera.unproject(
                new Vector3(screenX, screenY, 0));
        if (hasSelectedTopping()) {
            if (touchCoords.x > Constants.PIZZA_LEFT &
                    touchCoords.x < Constants.PIZZA_RIGHT &
                    touchCoords.y > Constants.PIZZA_BOTTOM &
                    touchCoords.y < Constants.PIZZA_TOP) {
                pizza.addTopping(selectedTopping);
            }
            selectedTopping = new Topping(selectedTopping.getX(),
                    selectedTopping.getY(),
                    game.random.nextFloat() * 360,
                    selectedTopping.getToppingName(),
                    game.assets.get(textureFiles.get(selectedTopping.getToppingName()),
                            Texture.class),
                    false);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // update selectedTopping location to follow mouse
        Vector3 mouseCoords = camera.unproject(
                new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if (hasSelectedTopping()) {
            switch(Gdx.app.getType()) {
                case Android: case iOS:
                    selectedTopping.update(mouseCoords.x,
                            mouseCoords.y + Constants.TOUCH_OFFSET);
                    break;
                default:
                    selectedTopping.update(mouseCoords.x, mouseCoords.y);
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Vector3 mouseCoords = camera.unproject(
                new Vector3(screenX, screenY, 0));
        if (hasSelectedTopping()) {
            switch (Gdx.app.getType()) {
                case Desktop: case WebGL:
                    if (mouseCoords.x > Constants.PIZZA_LEFT &
                            mouseCoords.x < Constants.PIZZA_RIGHT &
                            mouseCoords.y > Constants.PIZZA_BOTTOM &
                            mouseCoords.y < Constants.PIZZA_TOP) {
                        selectedTopping.setVisible(true);
                    } else {
                        selectedTopping.setVisible(false);
                    }
                    break;
            }
            selectedTopping.update(mouseCoords.x, mouseCoords.y);
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Constants.BG_COLOUR.r, Constants.BG_COLOUR.g,
                Constants.BG_COLOUR.b, Constants.BG_COLOUR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.shapeRenderer.setProjectionMatrix(camera.combined);

        game.batch.begin();
        pizza.draw(game.batch);
        if (hasSelectedTopping()) {
            selectedTopping.drawSelected(game.batch);
        }
        game.batch.end();

        if (Constants.DEBUG_GRAPHICS) {
            game.shapeRenderer.setColor(1,1,1,1);
            game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            for (Topping t: pizza.getToppings()) {
                Rectangle r = t.getBoundingRectangle();
                game.shapeRenderer.rect(r.x, r.y, r.width, r.height);
            }
            if (hasSelectedTopping()) {
                game.shapeRenderer.rect(
                        selectedTopping.getBoundingRectangle().x,
                        selectedTopping.getBoundingRectangle().y,
                        selectedTopping.getBoundingRectangle().width,
                        selectedTopping.getBoundingRectangle().height);
            }
            game.shapeRenderer.end();
        }

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.assets.disposePizzaSceenAssets(textureFiles);
        stage.dispose();
    }
}
