package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.Random;

import nz.co.canadia.poorpeoplepizzaparty.screens.PizzaScreen;

public class PoorPeoplePizzaParty extends Game {
    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    public BitmapFont font;
    public Random random;
    public Skin skin;

    @Override
    public void create () {

        boolean debugGraphics = true;

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        random = new Random();
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        this.setScreen(new PizzaScreen(this, debugGraphics));
    }

    @Override
    public void render () {
        super.render(); //important!
    }

    @Override
    public void dispose () {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }
}
