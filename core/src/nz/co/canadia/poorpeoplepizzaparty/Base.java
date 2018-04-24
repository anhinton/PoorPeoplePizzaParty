package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * The bottom of the pizza is the base
 */

public class Base {
    private Texture baseTexture;
    private Texture sauceTexture;
    private Texture cheeseTexture;
    private Sprite sprite;
    private Constants.BaseTopping topping;

    Base() {

        // load base textures
        baseTexture = new Texture(Gdx.files.internal("base.png"));
        baseTexture.setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        sauceTexture = new Texture(Gdx.files.internal("sauce.png"));
        sauceTexture.setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        cheeseTexture = new Texture(Gdx.files.internal("cheese.png"));
        cheeseTexture.setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);

        // set sprite to base texture
        topping = Constants.BaseTopping.BASE;
        sprite = new Sprite(baseTexture, baseTexture.getWidth(),
                baseTexture.getHeight());
        sprite.setX(60);
        sprite.setY(60);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void dispose() {
    }
}
