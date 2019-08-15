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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiSize;

public class TitleScreen implements InputProcessor, Screen {

    private final PoorPeoplePizzaParty game;
    private final Stage stage;
    private final Table table;

    public TitleScreen(final PoorPeoplePizzaParty game) {
        this.game = game;

        game.assets.loadTitleScreenAssets();

        OrthographicCamera camera = new OrthographicCamera();
        int screenWidth = Gdx.graphics.getBackBufferWidth();
        int screenHeight = Gdx.graphics.getBackBufferHeight();
        Viewport viewport = new FitViewport(
                UiSize.getViewportWidth(screenWidth, screenHeight),
                UiSize.getViewportHeight(screenWidth, screenHeight),
                camera);
        stage = new Stage(viewport, game.batch);
        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);
        initializeTable(viewport.getScreenWidth(), viewport.getScreenHeight());
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
    }

    private void initializeTable(int viewportWidth, int viewportHeight) {

        int buttonWidthFull = UiSize.getButtonWidthFull(viewportWidth, viewportHeight);
        int buttonHeight = UiSize.getButtonHeight(viewportHeight);
        int padding = UiSize.getPadding(viewportHeight);

        Image header = new Image(game.assets.get("graphics/headers/titleScreen.png",
                Texture.class));

        // create Play Button
        TextButton playButton = new TextButton(game.bundle.get("playButton"), game.uiSkin, "default");
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                play();
            }
        });

        // create Settings button
        ImageButton.ImageButtonStyle settingsButtonStyle =
                new ImageButton.ImageButtonStyle(
                        game.uiSkin.get("default", Button.ButtonStyle.class));
        Sprite settingsSprite = new Sprite(
                game.assets.get("graphics/icons/settings.png",
                        Texture.class));
        settingsSprite.setSize(Constants.UI_ICON_RATIO * viewportHeight,
                Constants.UI_ICON_RATIO * viewportHeight);
        settingsButtonStyle.imageUp = new SpriteDrawable(settingsSprite);
        ImageButton settingsButton = new ImageButton(settingsButtonStyle);
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new SettingsScreen(game));
                dispose();
            }
        });

        // create Quit Button
        ImageButton.ImageButtonStyle quitButtonStyle =
                new ImageButton.ImageButtonStyle(
                        game.uiSkin.get("default", Button.ButtonStyle.class));
        Sprite quitSprite = new Sprite(
                game.assets.get("graphics/icons/close.png",
                        Texture.class));
        quitSprite.setSize(Constants.UI_ICON_RATIO * viewportHeight,
                Constants.UI_ICON_RATIO * viewportHeight);
        quitButtonStyle.imageUp = new SpriteDrawable(quitSprite);
        ImageButton quitButton = new ImageButton(quitButtonStyle);
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                quit();
            }
        });

        // Main Menu table
        Table mainMenuTable = new Table();
        mainMenuTable.add(playButton)
                .prefSize(buttonWidthFull, buttonHeight)
                .right()
                .space(padding);
        mainMenuTable.add(settingsButton)
                .prefSize(buttonHeight)
                .left()
                .space(padding);
        mainMenuTable.add(quitButton)
                .prefSize(buttonHeight)
                .space(padding);

        table.clear();
        table.bottom()
                .pad(padding);
        table.add(header)
                .prefSize(
                        UiSize.getImageWidth(header.getPrefWidth(),
                                viewportWidth),
                        UiSize.getImageHeight(header.getPrefHeight(),
                                viewportHeight))
                .space(padding);
        table.row();
        table.add(mainMenuTable);
    }

    private void play() {
        game.setScreen(new PizzaScreen(game, true));
        dispose();
    }

    private void quit() {
        Gdx.app.exit();
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

        // draw sprites
        game.batch.begin();
        game.batch.end();

        // update UI camera
        stage.getViewport().apply();
        stage.getCamera().update();
        stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
        stage.act(delta);

        // draw UI
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
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

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK
                | keycode == Input.Keys.ESCAPE) {
            quit();
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
