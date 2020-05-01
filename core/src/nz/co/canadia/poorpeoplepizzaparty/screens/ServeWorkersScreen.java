package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
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

/**
 * The ServerWorksersScreen is displayed after choosing WORKERS on CookPizzaScreen. Plays
 * a party animation, which is interrupted by the partyBoss, who fires everyone.
 */

public class ServeWorkersScreen implements InputProcessor, Screen {

    private final PoorPeoplePizzaParty game;
    private final Pizza pizza;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final Stage stage;
    private final int padding;
    private final PizzaPartyAnimation pizzaPartyAnimation;
    private final Sound pickScrape;
    private float timeElapsed;
    private float nextSpawn;
    private PartyScene partyScene;
    private PartyBoss partyBoss;
    private DoomDrips doomDrips;
    private Array<FlyingPizza> flyingPizzaArray;
    private Pixmap pizzaPixmap;
    private Texture pizzaTexture;
    private Constants.ServeWorkersState state;

    public ServeWorkersScreen(PoorPeoplePizzaParty game, Pizza pizza) {
        this.game = game;
        this.pizza = pizza;

        // load Music and Sounds
        game.assets.loadServeWorkersSounds();
        game.setMusic("music/PartyTheme.mp3");
        game.assets.loadServeBossSounds();
        pickScrape = game.assets.get("sounds/PickScrape.mp3", Sound.class);
        // start music
        game.playMusic();

        padding = Constants.UNIT;

        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT,
                camera);
        camera.setToOrtho(false, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        
        stage = new Stage(viewport, game.batch);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

        state = Constants.ServeWorkersState.PARTY;
        timeElapsed = 0;

        // set time to spawn new pizzas
        nextSpawn = MathUtils.randomTriangular(Constants.FLYING_PIZZA_SPAWN_WAIT_MIN,
                Constants.FLYING_PIZZA_SPAWN_WAIT_MAX);

        // load TextureAtlas
        TextureAtlas atlas = game.assets.get("graphics/graphics.atlas", TextureAtlas.class);

        partyScene = new PartyScene(atlas.findRegion("lunch01"),
                atlas.findRegion("lunch02"));

        pizzaPixmap = pizza.getPixmap();
        pizzaTexture = new Texture(pizzaPixmap);

        flyingPizzaArray = new Array<FlyingPizza>();
        for (int i = 0; i < Constants.FLYING_PIZZA_INITIAL_SPAWN_COUNT; i++) {
            flyingPizzaArray.add(new FlyingPizza(pizzaTexture));
        }

        pizzaPartyAnimation = new PizzaPartyAnimation(
                atlas.findRegion("pizzaparty00"),
                atlas.findRegion("pizzaparty01"));

        partyBoss = new PartyBoss (atlas.findRegion("boss"),
                this);

        doomDrips = new DoomDrips(
                atlas.findRegion("doomdrips")
        );
    }

    public void bossSpeaks() {
        Label label = new Label(game.bundle.get("serveworkersName") + ": \n\""
                + game.bundle.get("serveworkersStatement") + "\"", game.uiSkin,
                "default");
        label.setAlignment(Align.topLeft);
        label.setPosition(padding,
                Constants.GAME_HEIGHT - padding - label.getHeight());
        label.setWidth(Constants.GAME_WIDTH * 2 / 3f);
        stage.addActor(label);
    }

    private void newPizzaScreen() {
        game.stopMusic();
        game.setScreen(new PizzaScreen(game, false));
        dispose();
    }

    private void showFiredButton() {
        TextButton firedButton = new TextButton(game.bundle.get("serveworkersFiredButton"),
                game.uiSkin,"default");
        firedButton.setSize(Constants.GAME_WIDTH * 2 / 3f,
                Constants.BUTTON_HEIGHT * 2);
        firedButton.setPosition(Constants.GAME_WIDTH/ 2f - firedButton.getWidth() / 2,
                Constants.GAME_HEIGHT / 2f - firedButton.getHeight() / 2);
        firedButton.pad(padding);
        firedButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                newPizzaScreen();
            }
        });
        stage.addActor(firedButton);
    }

    private void stopBoss() {
        partyScene.switchState();
        game.setMusic("music/BossTheme.mp3");
        game.playMusicLooping();
        partyBoss.stop();
        doomDrips.stop();
        pickScrape.stop();
        timeElapsed = 0;
        state = Constants.ServeWorkersState.RUBBISH;
    }

    private void stopRubbish() {
        showFiredButton();
        state = Constants.ServeWorkersState.FIRED;
    }

    private void stopParty() {
        game.stopMusic();
        pizzaPartyAnimation.stop();
        doomDrips.start();
        partyBoss.start();
        pickScrape.play(game.getMusicVolume());
        state = Constants.ServeWorkersState.BOSS;
    }

    private void goBack() {
        game.stopMusic();
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
            case RUBBISH:
                stopRubbish();
                return true;
            case FIRED:
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

        stage.act();

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
            case RUBBISH:
                timeElapsed += delta;
                if (timeElapsed > Constants.FIRED_TIME) {
                    stopRubbish();
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

        camera.update();
        game.batch.setProjectionMatrix(viewport.getCamera().combined);
        game.batch.begin();
        partyScene.draw(game.batch);
        for (FlyingPizza fp: flyingPizzaArray) {
            fp.draw(game.batch);
        }
        pizzaPartyAnimation.draw(game.batch);
        doomDrips.draw(game.batch);
        partyBoss.draw(game.batch);
        game.batch.end();

        stage.getViewport().apply();
        stage.getCamera().update();
        stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
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
        pizzaPixmap.dispose();
        pizzaTexture.dispose();
        for (FlyingPizza fp: flyingPizzaArray) {
            fp.dispose();
        }
        game.assets.unloadServeBossSounds();
        game.assets.unloadServeWorkersSounds();
    }
}
