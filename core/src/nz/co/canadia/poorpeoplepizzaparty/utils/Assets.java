package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.audio.Music;
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
     * Loads assets for game art assets.
     */
    public void loadGameAssets() {
        // TitleScreen
        super.load("graphics/headers/titleScreen.png",
                Texture.class, param);
        super.load("graphics/icons/back.png",
                Texture.class, param);
        super.load("graphics/icons/close.png",
                Texture.class, param);
        super.load("graphics/icons/settings.png",
                Texture.class, param);
        super.load("graphics/icons/volume_mute.png",
                Texture.class, param);
        super.load("graphics/icons/volume_up.png",
                Texture.class, param);

        // PizzaScreen
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

        // PostcardScreen
        super.load("graphics/icons/back.png", Texture.class,
                param);
        super.load("graphics/icons/save.png", Texture.class,
                param);
        super.load("graphics/icons/share.png", Texture.class,
                param);
        super.load("graphics/postcards/postcard01.png", Pixmap.class);
        super.load("graphics/postcards/postcard02.png", Pixmap.class);
        super.load("graphics/postcards/postcard03.png", Pixmap.class);

        // CookScreen
        super.load("graphics/headers/cookScreenPizza.png",
                Texture.class, param);

        // ServeBossScreen
        super.load("graphics/boss.png",
                Texture.class, param);

        // ServeWorkersScreen
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
    }

    public void loadThemeMusic() {
        super.load("music/ThemeMusic.mp3", Music.class);
        super.finishLoadingAsset("music/ThemeMusic.mp3");
    }
    
    public void unloadThemeMusic() {
        super.unload("music/ThemeMusic.mp3");
    }

    public void loadBossMusic() {
        super.load("music/BossTheme.mp3", Music.class);
        super.finishLoadingAsset("music/BossTheme.mp3");
    }

    public void unloadBossMusic() {
        super.unload("music/BossTheme.mp3");
    }

    public void loadPartyMusic() {
        super.load("music/PartyTheme.mp3", Music.class);
        super.finishLoadingAsset("music/PartyTheme.mp3");
    }

    public void unloadPartyMusic() {
        super.unload("music/PartyTheme.mp3");
    }
}
