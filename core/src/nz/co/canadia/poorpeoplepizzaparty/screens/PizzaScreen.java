package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.Topping;
import nz.co.canadia.poorpeoplepizzaparty.ui.PizzaMessage;
import nz.co.canadia.poorpeoplepizzaparty.ui.PizzaUi;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * The main game screen where we create our pizza.
 */

public class PizzaScreen implements InputProcessor, Screen {

    private final PoorPeoplePizzaParty game;
    private Pizza pizza;
    private OrthographicCamera gameCamera;
    private Viewport gameViewport;
    private Stage uiStage;
    private PizzaUi pizzaUi;
    private PizzaMessage pizzaMessage;
    private Topping selectedTopping;
    private boolean showedToppingTutorial;

    public PizzaScreen(final PoorPeoplePizzaParty game) {
        this.game = game;
        showedToppingTutorial = false;

        initialise();
        pizza = new Pizza(game.assets);
    }

    public PizzaScreen(PoorPeoplePizzaParty game, Pizza pizza) {
        this.game = game;
        showedToppingTutorial = true;
        initialise();
        this.pizza = pizza;
    }

    private void initialise() {
        selectedTopping = null;

        game.assets.loadPizzaScreenAssets();

        gameCamera = new OrthographicCamera();
        gameViewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT,
                gameCamera);
        gameCamera.setToOrtho(false, Constants.GAME_WIDTH,
                Constants.GAME_HEIGHT);

        OrthographicCamera uiCamera = new OrthographicCamera();
        float screenWidth = Gdx.graphics.getBackBufferWidth();
        float screenHeight = Gdx.graphics.getBackBufferHeight();
        Viewport uiViewport;
        if (screenWidth / screenHeight >= Constants.GAME_ASPECT_RATIO) {
            uiViewport = new FitViewport(
                    Math.round(screenHeight * Constants.GAME_ASPECT_RATIO),
                    screenHeight,
                    uiCamera);
        } else {
            uiViewport = new FitViewport(screenWidth,
                    screenWidth / Constants.GAME_ASPECT_RATIO,
                    uiCamera);
        }
        uiCamera.setToOrtho(false, uiViewport.getScreenHeight(),
                uiViewport.getScreenHeight());

        uiStage = new Stage(uiViewport);
        pizzaUi = new PizzaUi(uiViewport.getScreenWidth(),
                uiViewport.getScreenHeight(), this, game.skin,
                game.bundle, game.screenshot, game.assets);
        uiStage.addActor(pizzaUi);
        pizzaMessage = new PizzaMessage(uiViewport.getScreenWidth(),
                uiViewport.getScreenHeight(), game.skin);
        uiStage.addActor(pizzaMessage);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(uiStage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void cook() {
        game.setScreen(new CookScreen(game, pizza));
        dispose();
    }

    public void undoLastTopping() {
        pizza.undoLastTopping();
    }

    public void clearMessage() {
        pizzaMessage.clearMessage();
    }

    private void showMessage(String s) {
        pizzaMessage.showMessage(s);
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
            x = Constants.GAME_WIDTH / 2;
            y = Constants.GAME_HEIGHT / 2;
        }
        selectedTopping = new Topping(
                x,
                y,
                MathUtils.random(360f),
                toppingName,
                game.assets.get(game.assets.toppingPath(toppingName), Texture.class),
                false);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK
                | keycode == Input.Keys.ESCAPE) {
            if (!pizzaUi.goBack()) {
                Gdx.app.exit();
            }
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
        clearMessage();
        if (hasSelectedTopping()) {
            selectedTopping.setVisible(true);
        }
        // update selectedTopping location to follow mouse
        Vector3 mouseCoords = gameCamera.unproject(
                new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if (hasSelectedTopping()) {
            selectedTopping.update(mouseCoords.x, mouseCoords.y);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 touchCoords = gameCamera.unproject(
                new Vector3(screenX, screenY, 0));
        if (hasSelectedTopping()
                & pizzaUi.getCurrentMenu() == Constants.CurrentPizzaMenu.MAIN) {
            if (touchCoords.x > Constants.PIZZA_LEFT &
                    touchCoords.x < Constants.PIZZA_RIGHT &
                    touchCoords.y > Constants.PIZZA_BOTTOM &
                    touchCoords.y < Constants.PIZZA_TOP) {
                pizza.addTopping(selectedTopping);
            }
            selectedTopping = new Topping(selectedTopping.getX(),
                    selectedTopping.getY(),
                    MathUtils.random(360f),
                    selectedTopping.getToppingName(),
                    game.assets.get(game.assets.toppingPath(selectedTopping.getToppingName()),
                            Texture.class),
                    false);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // update selectedTopping location to follow mouse
        Vector3 mouseCoords = gameCamera.unproject(
                new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if (hasSelectedTopping()) {
            selectedTopping.update(mouseCoords.x, mouseCoords.y);
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Vector3 mouseCoords = gameCamera.unproject(
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

        gameViewport.apply();
        gameCamera.update();
        game.batch.setProjectionMatrix(gameCamera.combined);
        game.shapeRenderer.setProjectionMatrix(gameCamera.combined);

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

        uiStage.getViewport().apply();
        uiStage.getCamera().update();
        uiStage.act(delta);
        uiStage.draw();

        // show topping tutorial the first time a topping is selected
        if (!showedToppingTutorial) {
            if (hasSelectedTopping()) {
                showedToppingTutorial = true;
                String s = "";
                switch (Gdx.app.getType()) {
                    case Android: case iOS:
                        s = game.bundle.get("messageTutorialTouch");
                        break;
                    case Desktop: case WebGL:
                        s = game.bundle.get("messageTutorialMouse");
                        break;
                }
                showMessage(s);
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
        uiStage.getViewport().update(width, height, true);
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
        uiStage.dispose();
    }
}
