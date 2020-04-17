package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class LoadingScreen implements Screen {

    private final PoorPeoplePizzaParty game;
    private final Stage stage;
    private final ProgressBar progressBar;
    private float progress;

    public LoadingScreen(final PoorPeoplePizzaParty game) {
        this.game = game;

        game.assets.loadGameAssets();

        OrthographicCamera camera = new OrthographicCamera();
        FitViewport viewport = new FitViewport(
                Constants.GAME_WIDTH,
                Constants.GAME_HEIGHT,
                camera);
        camera.setToOrtho(false, viewport.getScreenHeight(),
                viewport.getScreenHeight());

        stage = new Stage(viewport, game.batch);
        Table table = new Table();
        stage.addActor(table);
        table.setFillParent(true);

        int padding = Constants.UNIT;

        // create ProgressBarStyle
        ProgressBar.ProgressBarStyle progressBarStyle =
                game.uiSkin.get("default-horizontal",
                        ProgressBar.ProgressBarStyle.class);
        // ProgressBar
        progressBar = new ProgressBar(0, 1, .01f,
                false, progressBarStyle);
        progressBar.setValue(progress);

        Label timerLabel = new Label(game.bundle.get("loadingLabel"), game.uiSkin,
                "default");
        table.add(timerLabel).space(padding);
        table.row();
        table.add(progressBar).prefWidth(Constants.BASE_WIDTH);
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

        // update UI camera
        stage.getViewport().apply();
        stage.getCamera().update();
        stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
        stage.act(delta);

        if (game.assets.update()) {
            game.setScreen(new TitleScreen(game));
//            game.setScreen(new ServeWorkersScreen(game, new Pizza(game.assets)));
        }


        progress = game.assets.getProgress();
        progressBar.setValue(progress);

        // draw UI
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

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

    }
}
