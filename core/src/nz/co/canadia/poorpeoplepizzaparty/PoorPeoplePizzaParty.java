package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;

import java.security.Key;
import java.util.Locale;

import nz.co.canadia.poorpeoplepizzaparty.screens.LoadingScreen;
import nz.co.canadia.poorpeoplepizzaparty.screens.TitleScreen;
import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;

/**
 * The PoorPeoplePizzaParty class is the main class for the game Poor People Pizza Party.
 * It initiates a PizzaScreen and let's the party begin.
 */

public class PoorPeoplePizzaParty extends Game {
    public Assets assets;
    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    public Skin uiSkin;
    public I18NBundle bundle;
    public CaptureIO captureIO;
    private float soundVolume;
    private float musicVolume;

    public PoorPeoplePizzaParty(CaptureIO captureIO) {
        this.captureIO = captureIO;
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(float soundVolume) {
        this.soundVolume = MathUtils.clamp(soundVolume, 0, 1);
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = MathUtils.clamp(musicVolume, 0, 1);
    }

    @Override
    public void create() {

        // TODO: stop debugging for release
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        Gdx.input.setCatchKey(Input.Keys.BACK, true);

        assets = new Assets();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        uiSkin = new Skin(Gdx.files.internal("data/uiskin.json"));

        I18NBundle.setSimpleFormatter(true);
        FileHandle bundleFileHandle =
                Gdx.files.internal("i18n/StringBundle");
        Locale locale = new Locale("en", "GB");
        bundle = I18NBundle.createBundle(bundleFileHandle, locale);

        soundVolume = 1;
        musicVolume = 1;

        this.setScreen(new LoadingScreen(this));
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
