package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ObjectMap;

public class Assets extends AssetManager {

    private final TextureLoader.TextureParameter param;
    
    public Assets() {
        param = new TextureLoader.TextureParameter();
        param.minFilter = Texture.TextureFilter.Linear;
        param.magFilter = Texture.TextureFilter.Linear;
    }

    /**
     * Loads assets for Cook Screen. Blocks until all assets are loaded.
     */
    public void loadCookScreenAssets() {
        super.load("graphics/headers/cookScreenPizza.png",
                Texture.class, param);
        super.finishLoading();
    }

    public void disposeCookScreenAssets() {
        super.unload("graphics/headers/cookScreenPizza.png");
    }

    /**
     * Loads assets for Pizza Screen. Blocks until all assets are loaded.
     * @param toppingPaths map of ToppingNames to file paths
     */
    public void loadPizzaScreenAssets(
            ObjectMap<Constants.ToppingName, String> toppingPaths) {
        for(String s: toppingPaths.values()) {
            super.load(s, Texture.class, param);
        }
        super.load("graphics/headers/pizzaScreen.png", Texture.class,
                param);
        super.load("graphics/icons/back.png", Texture.class,
                param);
        super.load("graphics/icons/camera.png", Texture.class,
                param);
        super.load("graphics/icons/undo.png", Texture.class,
                param);
        super.finishLoading();
    }

    public void disposePizzaSceenAssets(
            ObjectMap<Constants.ToppingName, String> textureFiles) {
        for (String s: textureFiles.values()) {
            super.unload(s);
        }
        super.unload("graphics/headers/pizzaScreen.png");
        super.unload("graphics/icons/camera.png");
        super.unload("graphics/icons/undo.png");
    }
}
