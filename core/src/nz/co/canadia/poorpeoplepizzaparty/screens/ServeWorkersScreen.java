package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

import nz.co.canadia.poorpeoplepizzaparty.FlyingPizza;
import nz.co.canadia.poorpeoplepizzaparty.LunchPhoto;
import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * The ServerWorksersScreen is displayed after choosing WORKERS on CookPizzaScreen. Plays
 * a party animation, which is interrupted by the boss, who fires everyone.
 */

public class ServeWorkersScreen implements InputProcessor, Screen {

    private final PoorPeoplePizzaParty game;
    private final Pizza pizza;
    private final LunchPhoto lunchPhoto;
    private final OrthographicCamera camera;
    private final FitViewport viewport;
    private final Sprite boss;
    private final Array<FlyingPizza> flyingPizzaArray;
    private final Pixmap pizzaPixmap;
    private final Texture pizzaTexture;

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

        lunchPhoto = new LunchPhoto(game.assets.get("graphics/lunch.png", Texture.class),
                game.assets.get("graphics/lunch_grey.png", Texture.class));

        boss = new Sprite(
                game.assets.get("graphics/boss.png", Texture.class));
        boss.setCenterX(Constants.GAME_WIDTH * 3f / 4);
        boss.setY(0 - boss.getHeight());

        pizzaPixmap = pizza.getPixmap();
        pizzaTexture = new Texture(pizzaPixmap);

        flyingPizzaArray = new Array<FlyingPizza>();
        for (int i = 0; i < 20; i++) {
            flyingPizzaArray.add(new FlyingPizza(pizzaTexture));
        }

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
//        if (boss.getY() < 0) {
//            boss.setY(boss.getY() + delta * 400);
//        } else {
//            boss.setY(0);
//        }

        Gdx.gl.glClearColor(Constants.WORKERS_BG_COLOUR.r, Constants.WORKERS_BG_COLOUR.g,
                Constants.WORKERS_BG_COLOUR.b, Constants.WORKERS_BG_COLOUR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for (FlyingPizza fp: flyingPizzaArray) {
            fp.update(delta);
        }

        camera.update();
        game.batch.setProjectionMatrix(viewport.getCamera().combined);
        game.batch.begin();
        lunchPhoto.draw(game.batch);
//        boss.draw(game.batch);
        for (FlyingPizza fp: flyingPizzaArray) {
            fp.draw(game.batch);
        }
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
        // TODO: dispose of assets
//        lunchPhoto.dispose();
//        boss.dispose();
        pizzaPixmap.dispose();
        pizzaTexture.dispose();
        for (FlyingPizza fp: flyingPizzaArray) {
            fp.dispose();
        }
    }
}
