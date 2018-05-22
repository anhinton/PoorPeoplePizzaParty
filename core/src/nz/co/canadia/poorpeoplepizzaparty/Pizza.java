package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * The pizza which we make
 */

public class Pizza {

    private Texture baseTexture;
    private Texture sauceTexture;
    private Texture cheeseTexture;
    private Sprite baseSprite;
    private Sprite sauceSprite;
    private Sprite cheeseSprite;
    private Sprite currentSprite;
    private Constants.BaseTopping baseTopping;

    public Pizza() {

        // load base textures and create sprites
        baseTexture = new Texture(Gdx.files.internal("base.png"));
        baseTexture.setFilter(Texture.TextureFilter.Linear, 
                Texture.TextureFilter.Linear);
        baseSprite = new Sprite(baseTexture, Constants.BASE_WITDH,
                Constants.BASE_HEIGHT);
        baseSprite.setX(Constants.BASE_X);
        baseSprite.setY(Constants.BASE_Y);
        
        sauceTexture = new Texture(Gdx.files.internal("sauce.png"));
        sauceTexture.setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        sauceSprite = new Sprite(sauceTexture, Constants.BASE_WITDH,
                Constants.BASE_HEIGHT);
        sauceSprite.setX(Constants.BASE_X);
        sauceSprite.setY(Constants.BASE_Y);

        cheeseTexture = new Texture(Gdx.files.internal("cheese.png"));
        cheeseTexture.setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        cheeseSprite = new Sprite(cheeseTexture, Constants.BASE_WITDH,
                Constants.BASE_HEIGHT);
        cheeseSprite.setX(Constants.BASE_X);
        cheeseSprite.setY(Constants.BASE_Y);
        
        // set base to plain
        baseTopping = Constants.BaseTopping.BASE;
        currentSprite = baseSprite;
    }

    public void draw (SpriteBatch batch) {
        currentSprite.draw(batch);
    }

    public void dispose() {
        baseTexture.dispose();
        sauceTexture.dispose();
        cheeseTexture.dispose();
    }

    public void setBaseTopping(Constants.BaseTopping baseTopping) {
        if (getBaseTopping() != baseTopping) {
            // update sprite with appropriate topping
            switch (baseTopping) {
                case BASE:
                    this.baseTopping = Constants.BaseTopping.BASE;
                    currentSprite = baseSprite;
                    break;
                case SAUCE:
                    this.baseTopping = Constants.BaseTopping.SAUCE;
                    currentSprite = sauceSprite;
                    break;
                case CHEESE:
                    this.baseTopping = Constants.BaseTopping.CHEESE;
                    currentSprite = cheeseSprite;
                    break;
            }
        }
    }

    public Constants.BaseTopping getBaseTopping() {
        return baseTopping;
    }
}
