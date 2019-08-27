package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiSize;

public class CookScreen implements InputProcessor, Screen {

    private final PoorPeoplePizzaParty game;
    private final Stage stage;
    private final Pizza pizza;
    private final Table table;
    private final int padding;
    private boolean cooking;
    private float timeElapsed;
    private ProgressBar progressBar;
    private Label remainingLabel;

    CookScreen(final PoorPeoplePizzaParty game, Pizza pizza,
               boolean countdown) {

        this.game = game;
        this.pizza = pizza;

        game.assets.loadCookScreenAssets();

        OrthographicCamera camera = new OrthographicCamera();
        Viewport viewport = new FitViewport(
                Constants.GAME_WIDTH,
                Constants.GAME_HEIGHT,
                camera);
        camera.setToOrtho(false, viewport.getScreenHeight(),
                viewport.getScreenHeight());
        stage = new Stage(viewport, game.batch);
        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);

        padding = UiSize.getPadding();

        if (countdown) {
            showTimer();
        } else {
            showDecision();
        }

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
    }

    private void showTimer() {

        table.clear();
        table.pad(padding);

        cooking = true;
        timeElapsed = 0;

        ProgressBar.ProgressBarStyle progressBarStyle =
                game.uiSkin.get("default-horizontal",
                        ProgressBar.ProgressBarStyle.class);
        progressBar = new ProgressBar(0, Constants.COOK_TIME_TOTAL,
                Constants.COOK_TIME_INCREMENT,
                false, progressBarStyle);
        float timeRemaining = progressBar.getMaxValue()
                - progressBar.getValue();
        remainingLabel = new Label(Integer.toString(MathUtils.ceil(timeRemaining)),
                game.uiSkin,"default");
        progressBar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (progressBar.getValue() < progressBar.getMaxValue()) {
                    float timeRemaining = progressBar.getMaxValue()
                            - progressBar.getValue();
                    remainingLabel.setText(Integer.toString(MathUtils.ceil(timeRemaining)));
                } else {
                    cooking = false;
                    showDecision();
                }
            }
        });

        Label timerLabel = new Label(game.bundle.get("timerLabel"), game.uiSkin,
                "default");
        table.add(timerLabel).space(padding);
        table.row();

        table.add(progressBar).space(padding).center().prefWidth(400);
        table.row();

        table.add(remainingLabel).space(padding);        
    }

    private void showDecision() {

        table.clear();
        table.pad(padding);

        Image headerImage =
                new Image(game.assets.get("graphics/headers/cookScreenPizza.png",
                        Texture.class));
        table.add(headerImage)
                .colspan(2)
                .space(padding);
        table.row();

        Label serveLabel = new Label(game.bundle.get("serveLabel"), game.uiSkin,
                "default");
        table.add(serveLabel).space(padding).colspan(2);
        table.row();

        TextButton bossButton = new TextButton(game.bundle.get("serveBossButton"),
                game.uiSkin, "default");
        bossButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                serve(Constants.ServeOption.BOSS);
            }
        });
        table.add(bossButton)
                .prefSize(Constants.BUTTON_WIDTH_FULL,
                        Constants.BUTTON_HEIGHT)
                .space(padding);

        TextButton workersButton = new TextButton(game.bundle.get("serveworkersButton"),
                game.uiSkin, "default");
        workersButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                serve(Constants.ServeOption.WORKERS);
            }
        });
        table.add(workersButton)
                .prefSize(Constants.BUTTON_WIDTH_FULL,
                        Constants.BUTTON_HEIGHT)
                .space(padding);
    }

    private void goBack() {
        game.setScreen(new PizzaScreen(game, pizza));
        dispose();
    }

    private void serve(Constants.ServeOption serveOption) {
        switch (serveOption) {
            case BOSS:
                game.setScreen(new ServeBossScreen(game, pizza));
                break;
            case WORKERS:
                game.setScreen(new ServeWorkersScreen(game, pizza));
                break;
        }
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

        stage.getCamera().update();
        stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
        stage.act(delta);
        stage.draw();

        if (cooking) {
            timeElapsed += delta;
            progressBar.setValue(timeElapsed);
        }
    }

    @Override
    public void resize(int width, int height) {
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
        stage.dispose();
    }
}
