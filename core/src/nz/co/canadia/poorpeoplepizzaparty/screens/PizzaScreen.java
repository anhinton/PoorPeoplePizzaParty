package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * The main game screen where we create our pizza.
 */

public class PizzaScreen implements InputProcessor, Screen {

    private final PoorPeoplePizzaParty game;
    private Pizza pizza;
    private boolean isTouchDown;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Stage stage;
    private Table table;
    private InputMultiplexer inputMultiplexer;

    public PizzaScreen(final PoorPeoplePizzaParty game) {
        this.game = game;

        pizza = new Pizza();
        isTouchDown = false;

        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT,
                camera);
        camera.setToOrtho(false, Constants.APP_WIDTH,
                Constants.APP_HEIGHT);

        stage = new Stage(viewport);
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(this);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // DEBUG UI
        table.setDebug(true);
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
        game.batch.end();

        stage.act(delta);
        stage.draw();
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
        if (!isTouchDown) {
            isTouchDown = true;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (isTouchDown) {
            switch(pizza.getBaseTopping()) {
                case BASE:
                    pizza.setBaseTopping(Constants.BaseTopping.SAUCE);
                    break;
                case SAUCE:
                    pizza.setBaseTopping(Constants.BaseTopping.CHEESE);
                    break;
                case CHEESE:
                    pizza.setBaseTopping(Constants.BaseTopping.BASE);
                    break;
            }
            isTouchDown = false;
        }
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
