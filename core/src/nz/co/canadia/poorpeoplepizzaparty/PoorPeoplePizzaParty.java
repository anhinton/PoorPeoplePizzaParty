package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import nz.co.canadia.poorpeoplepizzaparty.screens.PizzaScreen;

/**
 * this is a cool game about have a pizza party, but for poor people
 */

public class PoorPeoplePizzaParty extends Game {
	public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;

    @Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		this.setScreen(new PizzaScreen(this));
	}

	@Override
	public void render () {
        super.render(); //important!
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
	}

    public void resize(int width, int height) {
    }
}
