package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
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
import nz.co.canadia.poorpeoplepizzaparty.ToppingMenu;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * The main game screen where we create our pizza.
 */

public class PizzaScreen implements InputProcessor, Screen {

    private final PoorPeoplePizzaParty game;
    private final ObjectMap<Constants.ToppingName, Texture> textureObjectMap;
    private Pizza pizza;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Stage stage;
    private ToppingMenu toppingMenu;
    private Topping selectedTopping;
    private boolean debugGraphics;

    public PizzaScreen(final PoorPeoplePizzaParty game, boolean debugGraphics) {
        this.game = game;
        this.debugGraphics = debugGraphics;

        // load textures and create sprites
        // TODO: replace textureObjectMap with asset manager probably
        textureObjectMap = new ObjectMap<Constants.ToppingName, Texture>();
        textureObjectMap.put(
                Constants.ToppingName.BASE,
                new Texture(Gdx.files.internal("graphics/toppings/base.png")));
        textureObjectMap.put(
                Constants.ToppingName.SAUCE,
                new Texture(Gdx.files.internal("graphics/toppings/sauce.png")));
        textureObjectMap.put(
                Constants.ToppingName.CHEESE,
                new Texture(Gdx.files.internal("graphics/toppings/cheese.png")));
        textureObjectMap.put(
                Constants.ToppingName.BACON,
                new Texture(Gdx.files.internal("graphics/toppings/bacon-topping.png")));
        textureObjectMap.put(
                Constants.ToppingName.SAUSAGE,
                new Texture(Gdx.files.internal("graphics/toppings/sausage-topping.png")));
        textureObjectMap.put(
                Constants.ToppingName.SALAMI,
                new Texture(Gdx.files.internal("graphics/toppings/salami-topping.png")));
        textureObjectMap.put(
                Constants.ToppingName.CHICKEN,
                new Texture(Gdx.files.internal("graphics/toppings/chicken-topping.png")));
        for (Texture texture: textureObjectMap.values()) {
            texture.setFilter(Texture.TextureFilter.Linear,
                    Texture.TextureFilter.Linear);
        }

        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT,
                camera);
        camera.setToOrtho(false, Constants.APP_WIDTH,
                Constants.APP_HEIGHT);

        stage = new Stage(viewport);
        toppingMenu = new ToppingMenu(this, game.skin, debugGraphics);
        stage.addActor(toppingMenu);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

        pizza = new Pizza(textureObjectMap);
        selectedTopping = null;
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
                textureObjectMap,
                false);
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

        stage.act(delta);
        stage.draw();

        if (debugGraphics) {
            game.shapeRenderer.setColor(1,1,1,1);
            game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            for (Topping t: pizza.getToppingArray()) {
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

        // update selectedTopping location to follow mouse
        Vector3 mouseCoords = camera.unproject(
                new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if (hasSelectedTopping()) {
            selectedTopping.update(mouseCoords.x, mouseCoords.y);
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height, true);
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
        toppingMenu.dispose();
        stage.dispose();
        for (Texture texture: textureObjectMap.values()) {
            texture.dispose();
        }
        textureObjectMap.clear();
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
        if (hasSelectedTopping()) {
            selectedTopping.setVisible(true);
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
                    textureObjectMap,
                    false);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Vector3 mouseCoords = camera.unproject(
            new Vector3(screenX, screenY, 0));
        if (hasSelectedTopping()) {
            switch (Gdx.app.getType()) {
                case Desktop:
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
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
