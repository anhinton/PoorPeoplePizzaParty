package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.ui.CookUi;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class CookScreen implements InputProcessor, Screen {

    private final FitViewport viewport;
    private final PoorPeoplePizzaParty game;
    private final Stage stage;
    private final Pizza pizza;
    private CookUi cookUi;

    CookScreen(final PoorPeoplePizzaParty game, Pizza pizza) {

        this.game = game;
        this.pizza = pizza;

        game.assets.loadCookScreenAssets();

        OrthographicCamera uiCamera = new OrthographicCamera();
        float screenWidth = Gdx.graphics.getBackBufferWidth();
        float screenHeight = Gdx.graphics.getBackBufferHeight();
        if (screenWidth / screenHeight >= Constants.GAME_ASPECT_RATIO) {
            viewport = new FitViewport(
                    Math.round(screenHeight * Constants.GAME_ASPECT_RATIO),
                    screenHeight,
                    uiCamera);
        } else {
            viewport = new FitViewport(screenWidth,
                    screenWidth / Constants.GAME_ASPECT_RATIO,
                    uiCamera);
        }
        uiCamera.setToOrtho(false, viewport.getScreenHeight(),
                viewport.getScreenHeight());

        stage = new Stage(viewport);
        cookUi = new CookUi(viewport.getScreenWidth(),
                viewport.getScreenHeight(), game.skin,
                game.assets, game.bundle);
        stage.addActor(cookUi);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
    }

    private void goBack(PoorPeoplePizzaParty game, Pizza pizza) {
        game.setScreen(new PizzaScreen(game, pizza));
        dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK
                | keycode == Input.Keys.ESCAPE) {
            goBack(game, pizza);
            return true;
        }
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
        return false;
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

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Constants.BG_COLOUR.r, Constants.BG_COLOUR.g,
                Constants.BG_COLOUR.b, Constants.BG_COLOUR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cookUi.update(delta);

        stage.getCamera().update();
        game.batch.setProjectionMatrix(stage.getCamera().combined);
        game.shapeRenderer.setProjectionMatrix(stage.getCamera().combined);
        
        stage.getCamera().update();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
        stage.dispose();
        game.assets.disposeCookScreenAssets();
    }
}
