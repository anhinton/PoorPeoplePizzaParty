package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

import nz.co.canadia.poorpeoplepizzaparty.screens.LoadingScreen;
import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

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
    private Music music;
    private float musicVolume;
    private float soundVolume;

    public PoorPeoplePizzaParty(CaptureIO captureIO) {
        this.captureIO = captureIO;
        musicVolume = Constants.MUSIC_VOLUME_DEFAULT;
        soundVolume = Constants.SOUND_VOLUME_DEFAULT;
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

    public void setMusic(String fileHandle) {
        music = assets.get(fileHandle, Music.class);
    }

    public void playMusic() {
        setMusicVolume(musicVolume);
        music.setLooping(false);
        music.play();
    }

    public void playMusicLooping() {
        setMusicVolume(musicVolume);
        music.setLooping(true);
        music.play();
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = MathUtils.clamp(musicVolume, 0, 1);
        music.setVolume(MathUtils.clamp(musicVolume, 0, 1));
    }

    public void stopMusic() { music.stop(); }

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
        music.dispose();
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
