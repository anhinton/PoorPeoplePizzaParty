package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;

import javax.xml.soap.Text;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * The pizza which we make
 */

public class Pizza {

    private ObjectMap<String, Texture> baseTextures;
    private Sprite baseSprite;
    private Constants.BaseTopping baseTopping;

    public Pizza() {

        // load base textures
        ObjectMap<String, Texture> baseTextures =
                new ObjectMap<String, Texture>();
        baseTextures.put("base",
                new Texture(Gdx.files.internal("base.png")));
        baseTextures.put("sauce",
                new Texture(Gdx.files.internal("sauce.png")));
        baseTextures.put("cheese",
                new Texture(Gdx.files.internal("cheese.png")));
        for (Texture texture: baseTextures.values()) {
            texture.setFilter(Texture.TextureFilter.Linear,
                    Texture.TextureFilter.Linear);
        }

        // set sprite to base texture
        baseTopping = Constants.BaseTopping.BASE;
        baseSprite = new Sprite(baseTextures.get("base"),
                baseTextures.get("base").getWidth(),
                baseTextures.get("base").getHeight());
        baseSprite.setX(60);
        baseSprite.setY(60);
        
    }

    public void draw (SpriteBatch batch) {
                baseSprite.draw(batch);
    }

    public void dispose() {
        for (Texture texture: baseTextures.values()) {
            texture.dispose();
        }
        baseTextures.clear();
    }

    public void setBaseTopping(Constants.BaseTopping baseTopping) {
        if (this.baseTopping != baseTopping) {
            // update sprite with appropriate topping
            switch(baseTopping) {
                case BASE:
                    baseSprite.setTexture(baseTextures.get("base"));
                    break;
                case SAUCE:
                    baseSprite.setTexture(baseTextures.get("sauce"));
                    break;
                case CHEESE:
                    baseSprite.setTexture(baseTextures.get("cheese"));
                    break;
            }
        }
    }
}
