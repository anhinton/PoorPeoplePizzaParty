package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import nz.co.canadia.poorpeoplepizzaparty.DoomDrips;
import nz.co.canadia.poorpeoplepizzaparty.FlyingPizza;
import nz.co.canadia.poorpeoplepizzaparty.PartyBoss;
import nz.co.canadia.poorpeoplepizzaparty.PartyScene;
import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.PizzaPartyAnimation;
import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiSize;

/**
 * The ServerWorksersScreen is displayed after choosing WORKERS on CookPizzaScreen. Plays
 * a party animation, which is interrupted by the partyBoss, who fires everyone.
 */

public class ServeWorkersScreen implements InputProcessor, Screen {

    private final PoorPeoplePizzaParty game;
    private final Pizza pizza;
    private final OrthographicCamera gameCamera;
    private final FitViewport gameViewport;
    private final Stage uiStage;
    private final int screenWidth;
    private final int screenHeight;
    private final int padding;
    private final PizzaPartyAnimation pizzaPartyAnimation;
    private float timeElapsed;
    private float nextSpawn;
    private PartyScene partyScene;
    private PartyBoss partyBoss;
    private DoomDrips doomDrips;
    private Array<FlyingPizza> flyingPizzaArray;
    private Pixmap pizzaPixmap;
    private Texture pizzaTexture;
    private Constants.ServerWorkersState state;

    public ServeWorkersScreen(PoorPeoplePizzaParty game, Pizza pizza) {
        this.game = game;
        this.pizza = pizza;

        screenWidth = Gdx.graphics.getBackBufferWidth();
        screenHeight = Gdx.graphics.getBackBufferHeight();
        padding = UiSize.getPadding(screenHeight);

        game.assets.loadServeWorkersScreenAssets();

        gameCamera = new OrthographicCamera();
        gameViewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT,
                gameCamera);
        gameCamera.setToOrtho(false, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);

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
        uiStage = new Stage(uiViewport);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(uiStage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

        state = Constants.ServerWorkersState.PARTY;
        timeElapsed = 0;

        // set time to spawn new pizzas
        nextSpawn = MathUtils.randomTriangular(Constants.FLYING_PIZZA_SPAWN_WAIT_MIN,
                Constants.FLYING_PIZZA_SPAWN_WAIT_MAX);

        partyScene = new PartyScene(game.assets.get("graphics/lunch_1.png", Texture.class),
                game.assets.get("graphics/lunch_2.png", Texture.class));

        pizzaPixmap = pizza.getPixmap();
        pizzaTexture = new Texture(pizzaPixmap);

        flyingPizzaArray = new Array<FlyingPizza>();
        for (int i = 0; i < Constants.FLYING_PIZZA_INITIAL_SPAWN_COUNT; i++) {
            flyingPizzaArray.add(new FlyingPizza(pizzaTexture));
        }

        pizzaPartyAnimation = new PizzaPartyAnimation(
                game.assets.get("graphics/pizzaparty_0.png", Texture.class),
                game.assets.get("graphics/pizzaparty_1.png", Texture.class));

        partyBoss = new PartyBoss (game.assets.get("graphics/boss.png", Texture.class),
                this);

        doomDrips = new DoomDrips(
                game.assets.get("graphics/doomdrips.png", Texture.class),
                this);
    }

    public void bossSpeaks() {
        Label label = new Label(game.bundle.get("serveworkersName") + ": \n\""
                + game.bundle.get("serveworkersStatement") + "\"", game.uiSkin,
                "default");
        label.setAlignment(Align.topLeft);
        label.setPosition(padding,
                screenHeight - padding - label.getHeight());
        label.setWidth(Constants.GAME_WIDTH * 2 / 3f);
        uiStage.addActor(label);
    }

    private void newPizzaScreen() {
        game.setScreen(new PizzaScreen(game, false));
        dispose();
    }

    public void showFiredButton() {
        partyScene.switchState();
        TextButton firedButton = new TextButton(game.bundle.get("serveworkersFiredButton"),
                game.uiSkin,"default");
        firedButton.setSize(screenWidth * 2 / 3f,
                UiSize.getButtonHeight(screenHeight) * 2);
        firedButton.setPosition(screenWidth / 2f - firedButton.getWidth() / 2,
                screenHeight / 2f - firedButton.getHeight() / 2);
        firedButton.pad(padding);
        firedButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                newPizzaScreen();
            }
        });
        uiStage.addActor(firedButton);
    }

    private void stopBoss() {
        partyBoss.stop();
        doomDrips.stop();
        state = Constants.ServerWorkersState.FINISHED;
    }

    private void stopParty() {
        pizzaPartyAnimation.stop();
        doomDrips.start();
        partyBoss.start();
        state = Constants.ServerWorkersState.BOSS;
    }

    private void goBack() {
        game.setScreen(new CookScreen(game, pizza, false));
        dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        // back button goes back one screen
        if (keycode == Input.Keys.BACK
                | keycode == Input.Keys.ESCAPE) {
            goBack();
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
        switch(state) {
            case PARTY:
                stopParty();
                return true;
            case BOSS:
                stopBoss();
                return true;
            case FINISHED:
                break;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }

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

        Gdx.gl.glClearColor(Constants.WORKERS_BG_COLOUR.r, Constants.WORKERS_BG_COLOUR.g,
                Constants.WORKERS_BG_COLOUR.b, Constants.WORKERS_BG_COLOUR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        uiStage.act();

        switch (state) {
            case PARTY:
                timeElapsed += delta;
                if (timeElapsed >= nextSpawn) {
                    // set time of next spawn
                    nextSpawn = timeElapsed + MathUtils.randomTriangular(
                            Constants.FLYING_PIZZA_SPAWN_WAIT_MIN,
                            Constants.FLYING_PIZZA_SPAWN_WAIT_MAX);

                    // spawn new pizzas
                    for (int i = 1;
                         i <= MathUtils.random(Constants.FLYING_PIZZA_SPAWN_COUNT_MIN,
                                 Constants.FLYING_PIZZA_SPAWN_COUNT_MAX);
                         i++) {
                        flyingPizzaArray.add(new FlyingPizza(pizzaTexture));
                    }
                }

                // update pizza party animation
                pizzaPartyAnimation.update(delta);

                if (timeElapsed > Constants.PARTY_TIME) {
                    stopParty();
                }
                break;
            case BOSS:
                doomDrips.update(delta);
                partyBoss.update(delta);
                if (!doomDrips.isActive() & !partyBoss.isActive()) {
                    stopBoss();
                }
                break;
        }

        // update flying pizzas
        if (flyingPizzaArray.size > 0) {
            for (int i = 0; i < flyingPizzaArray.size; i++) {
                flyingPizzaArray.get(i).update(delta);
                if (!flyingPizzaArray.get(i).isActive()) {
                    // remove if inactive
                    flyingPizzaArray.removeIndex(i);
                }
            }
        }

        gameCamera.update();
        game.batch.setProjectionMatrix(gameViewport.getCamera().combined);
        game.batch.begin();
        partyScene.draw(game.batch);
        for (FlyingPizza fp: flyingPizzaArray) {
            fp.draw(game.batch);
        }
        pizzaPartyAnimation.draw(game.batch);
        doomDrips.draw(game.batch);
        partyBoss.draw(game.batch);
        game.batch.end();

        // TODO: remove debugging before release
        if (Constants.DEBUG_GRAPHICS) {
            game.shapeRenderer.setColor(1,0,0,1);
            game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            for (FlyingPizza fp: flyingPizzaArray) {
                game.shapeRenderer.rect(fp.getBoundingRectangle().x,
                        fp.getBoundingRectangle().y,
                        fp.getBoundingRectangle().width,
                        fp.getBoundingRectangle().height);
            }
            game.shapeRenderer.end();
        }

        uiStage.getViewport().apply();
        uiStage.getCamera().update();
        uiStage.act(delta);
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height, true);
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
        pizzaPixmap.dispose();
        pizzaTexture.dispose();
        for (FlyingPizza fp: flyingPizzaArray) {
            fp.dispose();
        }
    }
}
