package nz.co.canadia.poorpeoplepizzaparty.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;
import nz.co.canadia.poorpeoplepizzaparty.Postcard;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiSize;

public class PostcardScreen implements InputProcessor, Screen {
    private final PoorPeoplePizzaParty game;
    private final Pizza pizza;
    private final Postcard postcard;
    private final OrthographicCamera gameCamera;
    private final FitViewport viewport;
    private final Stage stage;

    PostcardScreen(final PoorPeoplePizzaParty game, final Pizza pizza) {
        this.game = game;
        this.pizza = pizza;
        int padding = UiSize.getPadding();
        int buttonWidth = Constants.BUTTON_WIDTH_HALF;
        int buttonHeight = Constants.BUTTON_HEIGHT;

        game.assets.loadPostcardAssets();

        gameCamera = new OrthographicCamera();
        viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT,
                gameCamera);
        gameCamera.setToOrtho(false, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        
        stage = new Stage(viewport, game.batch);
        Table table = new Table();
        table.setFillParent(true);
        table.bottom().right().pad(padding);
        stage.addActor(table);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

        postcard = new Postcard(pizza, game.assets);

        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            // add share button
            ImageButton.ImageButtonStyle shareButtonStyle =
                    new ImageButton.ImageButtonStyle(
                            game.uiSkin.get("default", Button.ButtonStyle.class));
            Sprite shareSprite = new Sprite(
                    game.assets.get("graphics/icons/share.png",
                            Texture.class));
            shareSprite.setSize(UiSize.getIconSize(),
                    UiSize.getIconSize());
            shareButtonStyle.imageUp = new SpriteDrawable(shareSprite);
            ImageButton shareButton = new ImageButton(shareButtonStyle);
            shareButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    game.captureIO.savePostcardImage(postcard);
                }
            });
            table.add(shareButton)
                    .space(padding)
                    .prefSize(buttonWidth, buttonHeight);
        }

        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            // add serialize button
            ImageButton.ImageButtonStyle saveButtonStyle =
                    new ImageButton.ImageButtonStyle(
                            game.uiSkin.get("default", Button.ButtonStyle.class));
            Sprite saveSprite = new Sprite(
                    game.assets.get("graphics/icons/save.png",
                            Texture.class));
            saveSprite.setSize(UiSize.getIconSize(),
                    UiSize.getIconSize());
            saveButtonStyle.imageUp = new SpriteDrawable(saveSprite);
            ImageButton saveButton = new ImageButton(saveButtonStyle);
            saveButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    game.captureIO.savePostcardImage(postcard);
                }
            });
            table.add(saveButton)
                    .space(padding)
                    .prefSize(buttonWidth, buttonHeight);
        }

        // add back button
        ImageButton.ImageButtonStyle backButtonStyle =
                new ImageButton.ImageButtonStyle(
                        game.uiSkin.get("default", Button.ButtonStyle.class));
        Sprite backSprite = new Sprite(
                game.assets.get("graphics/icons/back.png",
                        Texture.class));
        backSprite.setSize(UiSize.getIconSize(),
                UiSize.getIconSize());
        backButtonStyle.imageUp = new SpriteDrawable(backSprite);
        ImageButton backButton = new ImageButton(backButtonStyle);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                goBack();
            }
        });
        table.add(backButton)
                .space(padding)
                .prefSize(buttonWidth, buttonHeight);
    }

    private void goBack() {
        game.setScreen(new PizzaScreen(game, pizza));
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

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        gameCamera.update();
        game.batch.setProjectionMatrix(gameCamera.combined);

        game.batch.begin();
        postcard.draw(game.batch);
        game.batch.end();

        stage.getViewport().apply();
        stage.getCamera().update();
        stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
        stage.act(delta);
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
        postcard.dispose();
    }
}
