package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ObjectMap;

public class Assets extends AssetManager {

    private final TextureLoader.TextureParameter param;
    private ObjectMap<Constants.ToppingName, String> toppingPaths;
    
    public Assets() {
        toppingPaths = new ObjectMap<Constants.ToppingName, String>();
        toppingPaths.put(Constants.ToppingName.APRICOT,
                "graphics/toppings/apricot-topping.png");
        toppingPaths.put(Constants.ToppingName.BACON,
                "graphics/toppings/bacon-topping.png");
        toppingPaths.put(Constants.ToppingName.BARBECUE,
                "graphics/toppings/barbecue-topping.png");
        toppingPaths.put(Constants.ToppingName.BASE,
                "graphics/toppings/base-topping.png");
        toppingPaths.put(Constants.ToppingName.CHEESE,
                "graphics/toppings/cheese-topping.png");
        toppingPaths.put(Constants.ToppingName.CHICKEN,
                "graphics/toppings/chicken-topping.png");
        toppingPaths.put(Constants.ToppingName.SALAMI,
                "graphics/toppings/salami-topping.png");
        toppingPaths.put(Constants.ToppingName.SAUCE,
                "graphics/toppings/sauce-topping.png");
        toppingPaths.put(Constants.ToppingName.SAUSAGE,
                "graphics/toppings/sausage-topping.png");

        param = new TextureLoader.TextureParameter();
        param.minFilter = Texture.TextureFilter.Linear;
        param.magFilter = Texture.TextureFilter.Linear;
    }

    public String toppingPath(Constants.ToppingName toppingName) {
        return toppingPaths.get(toppingName);
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
     */
    public void loadPizzaScreenAssets() {
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
