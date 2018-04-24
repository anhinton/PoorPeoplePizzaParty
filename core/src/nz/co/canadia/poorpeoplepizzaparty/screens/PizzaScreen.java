package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class PizzaScreen implements Screen {
    private final PoorPeoplePizzaParty game;

    private OrthographicCamera camera;

    private Texture img;
    private Viewport viewport;

    public PizzaScreen(PoorPeoplePizzaParty game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false,
                Constants.APP_WIDTH,
                Constants.APP_HEIGHT);

        img = new Texture("badlogic.jpg");
        camera = new OrthographicCamera();
        camera.setToOrtho(false,
                Constants.APP_WIDTH,
                Constants.APP_HEIGHT);
        viewport = new FitViewport(Constants.APP_WIDTH,
                Constants.APP_HEIGHT, camera);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Constants.BORDER_COLOUR.r, Constants.BORDER_COLOUR.g,
                Constants.BORDER_COLOUR.b, Constants.BORDER_COLOUR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.shapeRenderer.setProjectionMatrix(camera.combined);

        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.setColor(Constants.BG_COLOUR.r, Constants.BG_COLOUR.g,
                Constants.BG_COLOUR.b, Constants.BG_COLOUR.a);
        game.shapeRenderer.rect(0, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
        game.shapeRenderer.end();

        game.batch.begin();
        game.batch.draw(img, 0, 0);
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
        img.dispose();
    }
}
