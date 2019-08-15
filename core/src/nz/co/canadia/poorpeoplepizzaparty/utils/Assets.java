package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Pixmap;
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
     * Load assets for Postcard screen
     */
    public void loadPostcardAssets() {
        super.load("graphics/icons/back.png", Texture.class,
                param);
        super.load("graphics/icons/save.png", Texture.class,
                param);
        super.load("graphics/icons/share.png", Texture.class,
                param);
        super.load("graphics/postcards/postcard01.png", Pixmap.class);
        super.load("graphics/postcards/postcard02.png", Pixmap.class);
        super.load("graphics/postcards/postcard03.png", Pixmap.class);
        super.finishLoading();
    }

    /**
     * Loads assets for Cook Screen. Blocks until all assets are loaded.
     */
    public void loadCookScreenAssets() {
        super.load("graphics/headers/cookScreenPizza.png",
                Texture.class, param);
        super.finishLoading();
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
        super.load("graphics/icons/close.png", Texture.class,
                param);
        super.load("graphics/icons/undo.png", Texture.class,
                param);
        super.finishLoading();
    }

    /**
     * Loads assets for ServeBossScreen. Blocks until all assets are loaded.
     */
    public void loadServeBossScreenAssets() {
        super.load("graphics/boss.png",
                Texture.class, param);
        super.finishLoading();
    }

    /**
     * Loads assets for ServeWorkersScreen. Blocks until all assets are loaded.
     */
    public void loadServeWorkersScreenAssets() {
        super.load("graphics/boss.png",
                Texture.class, param);
        super.load("graphics/doomdrips.png",
                Texture.class, param);
        super.load("graphics/lunch_1.png",
                Texture.class, param);
        super.load("graphics/lunch_2.png",
                Texture.class, param);
        super.load("graphics/pizzaparty_0.png",
                Texture.class, param);
        super.load("graphics/pizzaparty_1.png",
                Texture.class, param);
        super.finishLoading();
    }

    /**
     * Loads assets for SettingsScreen. Blocks until all assets are loaded.
     */
    public void loadSettingsScreenAssets() {
        super.load("graphics/icons/back.png",
                Texture.class, param);
        super.load("graphics/icons/volume_mute.png",
                Texture.class, param);
        super.load("graphics/icons/volume_up.png",
                Texture.class, param);
        super.finishLoading();
    }

    /**
     * Loads assets for TitleScreen. Blocks until all assets are loaded.
     */
    public void loadTitleScreenAssets() {
        super.load("graphics/headers/titleScreen.png",
                Texture.class, param);
        super.load("graphics/icons/close.png",
                Texture.class, param);
        super.load("graphics/icons/settings.png",
                Texture.class, param);
        super.finishLoading();
    }
}
