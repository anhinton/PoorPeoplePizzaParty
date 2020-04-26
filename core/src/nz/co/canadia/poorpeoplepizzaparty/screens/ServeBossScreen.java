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
import com.badlogic.gdx.utils.viewport.Viewport;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.ui.ServeBossUi;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * The ServeBossScreen, where you give your boss the pizza you made and he decides your fate
 */

public class ServeBossScreen implements InputProcessor, Screen {

    private final Stage stage;
    private final PoorPeoplePizzaParty game;
    private final Pizza pizza;
    private final ServeBossUi serveBossUi;
    private float timeElapsed;
    private Constants.ServeBossState state;

    ServeBossScreen(final PoorPeoplePizzaParty game, Pizza pizza) {

        this.game = game;
        this.pizza = pizza;

        game.assets.loadServeBossSounds();
        game.setMusic("music/BossTheme.mp3");
        game.playMusicLooping();

        OrthographicCamera camera = new OrthographicCamera();
        Viewport uiViewport = new FitViewport(
                Constants.GAME_WIDTH,
                Constants.GAME_HEIGHT,
                camera);
        camera.setToOrtho(false, uiViewport.getScreenHeight(),
                uiViewport.getScreenHeight());

        stage = new Stage(uiViewport, game.batch);
        serveBossUi = new ServeBossUi(this, game.uiSkin,
                game.assets, game.bundle, pizza);
        stage.addActor(serveBossUi);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

        state = Constants.ServeBossState.SERVE;
        timeElapsed = 0f;
    }

    public void getFired() {
        game.stopMusic();
        game.setScreen(new PizzaScreen(game, false));
        dispose();
    }

    private void showFiredButton() {
        state = Constants.ServeBossState.FIRED;
        Gdx.app.log("ServeBossScreen", "show fired button");
        serveBossUi.showFiredButton();
    }

    private void goBack() {
        game.stopMusic();
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
        Gdx.gl.glClearColor(Constants.BOSS_BG_COLOUR.r, Constants.BOSS_BG_COLOUR.g,
                Constants.BOSS_BG_COLOUR.b, Constants.BOSS_BG_COLOUR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getCamera().update();
        stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
        stage.act(delta);
        stage.draw();

        switch(state) {
            case SERVE:
                timeElapsed += delta;
                if (timeElapsed > Constants.FIRED_TIME) {
                    showFiredButton();
                }
                break;
            case FIRED:
                break;
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
        serveBossUi.dispose();
        game.assets.unloadServeBossSounds();
    }
}
