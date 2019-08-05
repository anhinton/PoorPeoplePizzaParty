package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

import nz.co.canadia.poorpeoplepizzaparty.screens.PizzaScreen;
import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiSkin;

/**
 * The PoorPeoplePizzaParty class is the main class for the game Poor People Pizza Party.
 * It initiates a PizzaScreen and let's the party begin.
 */

public class PoorPeoplePizzaParty extends Game {
    public Assets assets;
    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    public UiSkin uiSkin;
    public I18NBundle bundle;
    public CaptureIO captureIO;

    public PoorPeoplePizzaParty(CaptureIO captureIO) {
        this.captureIO = captureIO;
    }

    @Override
    public void create() {

        // TODO: stop debugging for release
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        Gdx.input.setCatchBackKey(true);

        assets = new Assets();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        uiSkin = new UiSkin(Gdx.graphics.getBackBufferHeight());

        I18NBundle.setSimpleFormatter(true);
        FileHandle bundleFileHandle =
                Gdx.files.internal("i18n/StringBundle");
        Locale locale = new Locale("en", "GB");
        bundle = I18NBundle.createBundle(bundleFileHandle, locale);

        this.setScreen(new PizzaScreen(this, true));
    }

    @Override
    public void render () {
        super.render(); //important!
    }

    @Override
    public void dispose () {
        assets.dispose();
        batch.dispose();
        shapeRenderer.dispose();
        uiSkin.dispose();

        // clean up shared postcard files, if they exist
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            if (Gdx.files.local("postcards/").exists()) {
                FileHandle[] postcardFiles = Gdx.files.local("postcards/").list();
                for (FileHandle file : postcardFiles) {
                    file.delete();
                }
            }
        }
    }
}
