package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.ui.TitleUi;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiSize;

public class TitleScreen implements InputProcessor, Screen {

    private final PoorPeoplePizzaParty game;
    private final OrthographicCamera gameCamera;
    private final FitViewport gameViewport;
    private final Stage uiStage;
    private final TitleUi titleUi;

    public TitleScreen (final PoorPeoplePizzaParty game) {
        this.game = game;

        game.assets.loadTitleScreenAssets();

        gameCamera = new OrthographicCamera();
        gameViewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT,
                gameCamera);
        gameCamera.setToOrtho(false, Constants.GAME_WIDTH,
                Constants.GAME_HEIGHT);

        OrthographicCamera uiCamera = new OrthographicCamera();
        int screenWidth = Gdx.graphics.getBackBufferWidth();
        int screenHeight = Gdx.graphics.getBackBufferHeight();
        Viewport uiViewport = new FitViewport(
                UiSize.getViewportWidth(screenWidth, screenHeight),
                UiSize.getViewportHeight(screenWidth, screenHeight),
                uiCamera);
        uiStage = new Stage(uiViewport, game.batch);
        titleUi = new TitleUi(uiViewport.getScreenWidth(),
                uiViewport.getScreenHeight(), this, game.uiSkin,
                game.bundle, game.assets);
        uiStage.addActor(titleUi);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(uiStage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void play() {
        game.setScreen(new PizzaScreen(game, true));
        dispose();
    }

    public void quit() {
        Gdx.app.exit();
    }

    public float getSoundVolume() {
        return game.getSoundVolume();
    }

    public void setSoundVolume(float soundVolume) {
        game.setSoundVolume(soundVolume);
    }

    public float getMusicVolume() {
        return game.getMusicVolume();
    }

    public void setMusicVolume(float musicVolume) {
        game.setMusicVolume(musicVolume);
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
        gameViewport.apply();
        gameCamera.update();
        game.batch.setProjectionMatrix(gameCamera.combined);
        game.shapeRenderer.setProjectionMatrix(gameCamera.combined);

        // draw sprites
        game.batch.begin();
        game.batch.end();

        // update UI camera
        uiStage.getViewport().apply();
        uiStage.getCamera().update();
        uiStage.getBatch().setProjectionMatrix(uiStage.getCamera().combined);
        uiStage.act(delta);

        // draw UI
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
        uiStage.getViewport().update(width, height);
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

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK
                | keycode == Input.Keys.ESCAPE) {
            if (!titleUi.goBack()) {
                quit();
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
}
