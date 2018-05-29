package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
    private InputMultiplexer multiplexer;
    private ToppingMenu toppingMenu;
    private Topping selectedTopping;

    public PizzaScreen(final PoorPeoplePizzaParty game) {
        this.game = game;

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
        for (Texture texture: textureObjectMap.values()) {
            texture.setFilter(Texture.TextureFilter.Linear,
                    Texture.TextureFilter.Linear);
        }

        pizza = new Pizza(textureObjectMap);
        selectedTopping = new Topping(0, 0, game.random.nextFloat() * 360,
                Constants.ToppingName.BACON, textureObjectMap);

        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT,
                camera);
        camera.setToOrtho(false, Constants.APP_WIDTH,
                Constants.APP_HEIGHT);

        stage = new Stage(viewport);
        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

        toppingMenu = new ToppingMenu(stage);
    }

    public void setSelectedTopping(float x, float y, float rotation,
                                   Constants.ToppingName toppingName) {
        this.selectedTopping = new Topping(x, y, rotation, toppingName,
                textureObjectMap);
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
        selectedTopping.draw(game.batch);
        game.batch.end();

        stage.act(delta);
        stage.draw();

        // update selectedTopping location to follow mouse
        Vector3 mouseCoords = camera.unproject(
                new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        selectedTopping.update(mouseCoords.x, mouseCoords.y);
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
        pizza.dispose();
        toppingMenu.dispose();
        stage.dispose();
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        pizza.addTopping(selectedTopping);
        selectedTopping = new Topping(selectedTopping.getX(),
                selectedTopping.getY(),
                game.random.nextFloat() * 360,
                selectedTopping.getToppingName(),
                textureObjectMap);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
