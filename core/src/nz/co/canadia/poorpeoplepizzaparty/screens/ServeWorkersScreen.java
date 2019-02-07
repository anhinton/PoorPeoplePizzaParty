package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.FitViewport;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class ServeWorkersScreen implements InputProcessor, Screen {

    private final PoorPeoplePizzaParty game;
    private final Pizza pizza;
    private final Sprite lunchPhoto;
    private final OrthographicCamera camera;
    private final FitViewport viewport;
    private final Sprite lunchGreyPhoto;
    private final Sprite boss;

    public ServeWorkersScreen(PoorPeoplePizzaParty game, Pizza pizza) {
        this.game = game;
        this.pizza = pizza;

        game.assets.loadServeWorkersScreenAssets();

        camera = new OrthographicCamera();
        float screenWidth = Gdx.graphics.getBackBufferWidth();
        float screenHeight = Gdx.graphics.getBackBufferHeight();
        viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT,
                camera);
        camera.setToOrtho(false, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);

        lunchPhoto = new Sprite(
                game.assets.get("graphics/lunch.png", Texture.class));
        lunchPhoto.setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        lunchPhoto.setPosition(0, 0);

        lunchGreyPhoto = new Sprite(
                game.assets.get("graphics/lunch_grey.png", Texture.class));
        lunchGreyPhoto.setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        lunchGreyPhoto.setPosition(0, 0);

        boss = new Sprite(
                game.assets.get("graphics/boss.png", Texture.class));
        boss.setCenterX(Constants.GAME_WIDTH * 3f / 4);
        boss.setY(0 - boss.getHeight());

        Gdx.input.setInputProcessor(this);
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
        if (boss.getY() < 0) {
            boss.setY(boss.getY() + delta * 400);
        } else {
            boss.setY(0);
        }

        Gdx.gl.glClearColor(Constants.WORKERS_BG_COLOUR.r, Constants.WORKERS_BG_COLOUR.g,
                Constants.WORKERS_BG_COLOUR.b, Constants.WORKERS_BG_COLOUR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(viewport.getCamera().combined);
        game.batch.begin();
//        lunchPhoto.draw(game.batch);
        lunchGreyPhoto.draw(game.batch);
        boss.draw(game.batch);
        game.batch.end();
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

    }
}
