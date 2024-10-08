package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.Points;
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
    private final TextureAtlas atlas;
    private final Pizza pizza;
    private OrthographicCamera gameCamera;
    private Viewport viewport;
    private Stage stage;
    private PizzaUi pizzaUi;
    private PizzaMessage pizzaMessage;
    private Topping selectedTopping;
    private boolean showedToppingTutorial;
    private Array<Points> pointsArray;
    private int pointsCount;
    private ObjectMap<Constants.ToppingName, Sound> toppingSoundMap;

    public PizzaScreen(final PoorPeoplePizzaParty game, boolean loadAutosave) {
        this.game = game;
        atlas = game.assets.get("graphics/graphics.atlas", TextureAtlas.class);
        showedToppingTutorial = false;

        initialise();
        if (loadAutosave) {
            pizza = new Pizza(atlas, game.assets);
            load();
        } else {
            pizza = new Pizza(atlas, game.assets);
        }
    }

    PizzaScreen(PoorPeoplePizzaParty game, Pizza pizza) {
        this.game = game;
        atlas = game.assets.get("graphics/graphics.atlas", TextureAtlas.class);
        showedToppingTutorial = true;
        initialise();
        this.pizza = pizza;
    }

    private void initialise() {

        game.assets.loadThemeMusic();
        game.setMusic("music/ThemeMusic.mp3");
        game.playMusicLooping();

        game.assets.loadToppingsSounds();

        toppingSoundMap = new ObjectMap<Constants.ToppingName, Sound>();
        toppingSoundMap.put(Constants.ToppingName.APRICOT,
                game.assets.get("sounds/toppings/apricot.mp3", Sound.class));
        toppingSoundMap.put(Constants.ToppingName.BACON,
                game.assets.get("sounds/toppings/bacon.mp3", Sound.class));
        toppingSoundMap.put(Constants.ToppingName.BARBECUE,
                game.assets.get("sounds/toppings/barbecue.mp3", Sound.class));
        toppingSoundMap.put(Constants.ToppingName.CHEESE,
                game.assets.get("sounds/toppings/cheese.mp3", Sound.class));
        toppingSoundMap.put(Constants.ToppingName.CHICKEN,
                game.assets.get("sounds/toppings/chicken.mp3", Sound.class));
        toppingSoundMap.put(Constants.ToppingName.SALAMI,
                game.assets.get("sounds/toppings/salami.mp3", Sound.class));
        toppingSoundMap.put(Constants.ToppingName.SAUCE,
                game.assets.get("sounds/toppings/sauce.mp3", Sound.class));
        toppingSoundMap.put(Constants.ToppingName.SAUSAGE,
                game.assets.get("sounds/toppings/sausage.mp3", Sound.class));

        selectedTopping = null;
        pointsArray = new Array<Points>();
        pointsCount = 0;

        gameCamera = new OrthographicCamera();
        viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT,
                gameCamera);
//        gameCamera.setToOrtho(false, Constants.GAME_WIDTH,
//                Constants.GAME_HEIGHT);

        stage = new Stage(viewport, game.batch);
        pizzaUi = new PizzaUi(this, game.uiSkin,
                game.bundle, atlas);
        stage.addActor(pizzaUi);
        pizzaMessage = new PizzaMessage(game.uiSkin);
        stage.addActor(pizzaMessage);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void createPostcard(){
        game.assets.loadPostcardSounds();
        game.setScreen(new PostcardScreen(game, pizza));
        dispose();
    }

    public void cook() {
        game.setScreen(new CookScreen(game, pizza, true));
        dispose();
    }

    public void goBack() {
        save();
        game.setScreen(new TitleScreen(game));
        dispose();
    }

    public void randomPizza() {
        pizza.random();
        playToppingSound(Constants.ToppingName.SALAMI);
    }

    private void addTopping(Topping topping, float x, float y) {
        pizza.addTopping(topping);
        playToppingSound(topping.getToppingName());
        scorePoints(x, y);
    }

    private void playToppingSound(Constants.ToppingName toppingName) {
        switch(toppingName) {
            case APRICOT:
            case BACON:
            case BARBECUE:
            case CHEESE:
            case CHICKEN:
            case SALAMI:
            case SAUCE:
            case SAUSAGE:
                toppingSoundMap.get(toppingName).play(game.getSoundVolume());
                break;
        }
    }

    public void removeAllToppings() {
        pizza.removeAllToppings();
        pointsCount = 0;
    }

    private void save() {
        String pizzaXml = pizza.serialize();
        game.captureIO.savePizzaXml(pizzaXml);
    }

    private void load() {
        String pizzaXml = game.captureIO.loadPizzaXml();
        pizza.deserialize(pizzaXml);
    }

    public void undoLastTopping() {
        pizza.undoLastTopping();
    }

    public void clearMessage() {
        pizzaMessage.clearMessage();
    }

    private void scorePoints(float x, float y) {
        pointsCount++;
        String text;
        if (MathUtils.random(1, 420) == 420) {
            text = game.bundle.get("scorePointsRandom");
        } else if (pointsCount % 69 == 0) {
            text = game.bundle.get("scorePointsSixtyNine");
        } else if (pointsCount % 3 == 0) {
            text = game.bundle.get("scorePointsThree");
        } else {
            text = game.bundle.get("scorePointsBasic");
        }
        pointsArray.add(new Points(x, y, game.uiSkin.getFont("label-font"), text));
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
            x = Constants.GAME_WIDTH / 2f;
            y = Constants.GAME_HEIGHT / 2f;
        }
        selectedTopping = new Topping(
                x,
                y,
                MathUtils.random(360f),
                toppingName,
                atlas.findRegion(game.assets.toppingPath(toppingName)),
                false);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK
                | keycode == Input.Keys.ESCAPE) {
            return pizzaUi.goBack();
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
        Vector3 touchCoords = gameCamera.unproject(
                new Vector3(screenX, screenY, 0),
                viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(),
                viewport.getScreenHeight());
        if (hasSelectedTopping()) {
            selectedTopping.update(touchCoords.x, touchCoords.y);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 touchCoords = gameCamera.unproject(
                new Vector3(screenX, screenY, 0),
                viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(),
                viewport.getScreenHeight());
        if (hasSelectedTopping()
                & pizzaUi.getCurrentMenu() == Constants.CurrentPizzaMenu.MAIN) {
            if (touchCoords.x > Constants.PIZZA_LEFT &
                    touchCoords.x < Constants.PIZZA_RIGHT &
                    touchCoords.y > Constants.PIZZA_BOTTOM &
                    touchCoords.y < Constants.PIZZA_TOP) {
                addTopping(selectedTopping, touchCoords.x, touchCoords.y);
            }
            selectedTopping = new Topping(selectedTopping.getX(),
                    selectedTopping.getY(),
                    MathUtils.random(360f),
                    selectedTopping.getToppingName(),
                    atlas.findRegion(game.assets.toppingPath(selectedTopping.getToppingName())),
                    false);
        }
        return true;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // update selectedTopping location to follow mouse
        Vector3 touchCoords = gameCamera.unproject(
                new Vector3(screenX, screenY, 0),
                viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(),
                viewport.getScreenHeight());
        if (hasSelectedTopping()) {
            selectedTopping.update(touchCoords.x, touchCoords.y);
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Vector3 mouseCoords = gameCamera.unproject(
                new Vector3(screenX, screenY, 0),
                viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(),
                viewport.getScreenHeight());
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
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // clear screen
        Gdx.gl.glClearColor(Constants.BG_COLOUR.r, Constants.BG_COLOUR.g,
                Constants.BG_COLOUR.b, Constants.BG_COLOUR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // update game camera
        viewport.apply();
        gameCamera.update();
        game.batch.setProjectionMatrix(gameCamera.combined);
        game.shapeRenderer.setProjectionMatrix(gameCamera.combined);

        // update Points array
        for (int i = 0; i < pointsArray.size; i++) {
            if (pointsArray.get(i).isVisible()) {
                pointsArray.get(i).update(delta);
            } else {
                pointsArray.removeIndex(i);
            }
        }

        // draw pizza sprites
        game.batch.begin();
        pizza.draw(game.batch);
        if (hasSelectedTopping()) {
            selectedTopping.drawSelected(game.batch);
        }
        game.batch.end();

        // update UI camera
        stage.getViewport().apply();
        stage.getCamera().update();
        stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
        stage.act(delta);

        // draw Points in UI layer
        stage.getBatch().begin();
        for (Points p: pointsArray) {
            p.draw(stage.getBatch());
        }
        stage.getBatch().end();

        // draw UI
        stage.draw();

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
        viewport.update(width, height);
    }

    @Override
    public void pause() {
        save();
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
        game.assets.unloadToppingsSounds();
    }
}
