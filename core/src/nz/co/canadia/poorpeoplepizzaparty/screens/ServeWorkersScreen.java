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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

import nz.co.canadia.poorpeoplepizzaparty.DoomDrips;
import nz.co.canadia.poorpeoplepizzaparty.FlyingPizza;
import nz.co.canadia.poorpeoplepizzaparty.PartyBoss;
import nz.co.canadia.poorpeoplepizzaparty.PartyScene;
import nz.co.canadia.poorpeoplepizzaparty.Pizza;
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
    private final OrthographicCamera camera;
    private final FitViewport viewport;
    private final Stage stage;
    private final int screenWidth;
    private final int screenHeight;
    private final int padding;
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

        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT,
                camera);
        camera.setToOrtho(false, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);

        stage = new Stage(viewport);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

        state = Constants.ServerWorkersState.PARTY;
        timeElapsed = 0;

        // set time to spawn new pizzas
        nextSpawn = MathUtils.randomTriangular(.5f, 1.5f);

        partyScene = new PartyScene(game.assets.get("graphics/lunch.png", Texture.class),
                game.assets.get("graphics/lunch_grey.png", Texture.class));

        pizzaPixmap = pizza.getPixmap();
        pizzaTexture = new Texture(pizzaPixmap);

        flyingPizzaArray = new Array<FlyingPizza>();
        for (int i = 0; i < 20; i++) {
            flyingPizzaArray.add(new FlyingPizza(pizzaTexture));
        }

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
        label.setWrap(true);
        stage.addActor(label);
    }

    public void showFiredButton() {
        TextButton firedButton = new TextButton(game.bundle.get("serveworkersFiredButton"),
                game.uiSkin,"default");
        firedButton.setPosition(padding, padding);
        firedButton.setSize(UiSize.getButtonWidthFull(screenWidth, screenHeight),
                UiSize.getButtonHeight(screenHeight));
        firedButton.pad(padding);
        firedButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getFired();
            }
        });
        stage.addActor(firedButton);
    }

    private void getFired() {
        game.setScreen(new PizzaScreen(game));
        dispose();
    }

    private void goBack() {
        game.setScreen(new CookScreen(game, pizza, false));
        dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
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
                            .2f, 1.2f);

                    // spawn between 1 and 10 new pizzas
                    for (int i = 1; i <= MathUtils.random(1, 10); i++) {
                        flyingPizzaArray.add(new FlyingPizza(pizzaTexture));
                    }
                }

                // update flying pizzas
                for (int i = 0; i < flyingPizzaArray.size; i++) {
                    flyingPizzaArray.get(i).update(delta);
                    if (!flyingPizzaArray.get(i).isActive()) {
                        // remove if inactive
                        flyingPizzaArray.removeIndex(i);
                    }
                }

                if (timeElapsed > Constants.PARTY_TIME) {
                    flyingPizzaArray.clear();
                    partyScene.switchColour();
                    doomDrips.start();
                    partyBoss.start();
                    state = Constants.ServerWorkersState.BOSS;
                }
                break;
            case BOSS:
                doomDrips.update(delta);
                partyBoss.update(delta);
                break;
        }


        camera.update();
        game.batch.setProjectionMatrix(viewport.getCamera().combined);
        game.batch.begin();
        partyScene.draw(game.batch);
        for (FlyingPizza fp: flyingPizzaArray) {
            fp.draw(game.batch);
        }
        doomDrips.draw(game.batch);
        partyBoss.draw(game.batch);
        game.batch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        // TODO: dispose of assets
//        partyScene.dispose();
//        partyBoss.dispose();
        pizzaPixmap.dispose();
        pizzaTexture.dispose();
        for (FlyingPizza fp: flyingPizzaArray) {
            fp.dispose();
        }
    }
}
