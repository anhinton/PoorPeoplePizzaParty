package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

import nz.co.canadia.poorpeoplepizzaparty.screens.ServeBossScreen;
import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiFont;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiSkin;

public class PoorPeoplePizzaParty extends Game {
    public Assets assets;
    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    public UiSkin uiSkin;
    public I18NBundle bundle;
    public CaptureIO captureIO;
    private UiFont uiFont;

    public PoorPeoplePizzaParty(CaptureIO captureIO, UiFont uiFont) {
        this.captureIO = captureIO;
        this.uiFont = uiFont;
    }

    @Override
    public void create() {

        // TODO: stop debugging for release
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        Gdx.input.setCatchBackKey(true);

        assets = new Assets();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        uiSkin = new UiSkin(uiFont);

        I18NBundle.setSimpleFormatter(true);
        FileHandle bundleFileHandle =
                Gdx.files.internal("i18n/StringBundle");
        Locale locale = new Locale("en", "GB");
        bundle = I18NBundle.createBundle(bundleFileHandle, locale);

        // TODO: stop loading assets once finished debugging ServeBossScreen
        assets.loadPizzaScreenAssets();
        // TODO: change back to PizzaScreen
//        this.setScreen(new CookScreen(this, new Pizza(assets)));
        this.setScreen(new ServeBossScreen(this, new Pizza(assets)));
//        this.setScreen(new PizzaScreen(this));
    }

    @Override
    public void render () {
        super.render(); //important!
    }

    @Override
    public void dispose () {
        assets.dispose();
        batch.dispose();
        captureIO.dispose();
        shapeRenderer.dispose();
        uiSkin.dispose();

        // clean up shared postcard files, if they exist
        switch (Gdx.app.getType()) {
            case Android:
                if (Gdx.files.local("postcards/").exists()) {
                    Gdx.app.log("PoorPeoplePizzaParty", "deleting postcards");
                    FileHandle[] postcardFiles = Gdx.files.local("postcards/").list();
                    for (FileHandle file: postcardFiles) {
                        file.delete();
                        Gdx.app.log("PoorPeoplePizzaParty",
                                file.toString() + " deleted");
                    }
                }
        }
    }
}
