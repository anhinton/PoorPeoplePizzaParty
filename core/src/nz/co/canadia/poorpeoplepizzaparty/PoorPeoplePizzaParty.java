package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;
import java.util.Random;

import nz.co.canadia.poorpeoplepizzaparty.screens.PizzaScreen;
import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.Screenshot;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiSkin;

public class PoorPeoplePizzaParty extends Game {
    public Assets assets;
    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    public Random random;
    public UiSkin skin;
    public I18NBundle bundle;
    public Screenshot screenshot;

    public PoorPeoplePizzaParty(Screenshot screenshot) {
        this.screenshot = screenshot;
    }

    @Override
    public void create() {

        Gdx.input.setCatchBackKey(true);

        assets = new Assets();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        random = new Random();
        skin = new UiSkin();

        I18NBundle.setSimpleFormatter(true);
        FileHandle bundleFileHandle =
                Gdx.files.internal("i18n/StringBundle");
        Locale locale = new Locale("en", "GB");
        bundle = I18NBundle.createBundle(bundleFileHandle, locale);

        this.setScreen(new PizzaScreen(this));
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
        skin.dispose();
    }
}
