package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The pizza which we make
 */

public class Pizza {
    private Texture baseTexture;
    private Sprite baseSprite;

    public Pizza() {
        baseTexture = new Texture(Gdx.files.internal("base.png"));
        baseTexture.setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        baseSprite = new Sprite(baseTexture, baseTexture.getWidth(),
                baseTexture.getHeight());
        baseSprite.setX(60);
        baseSprite.setY(60);
    }

    public void draw(SpriteBatch batch) {
        baseSprite.draw(batch);
    }

    public void dispose() {
        baseTexture.dispose();
    }
}
