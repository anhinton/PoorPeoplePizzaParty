package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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

    public void loadToppingsSounds() {
        super.load("sounds/toppings/apricot.mp3", Sound.class);
        super.load("sounds/toppings/bacon.mp3", Sound.class);
        super.load("sounds/toppings/barbecue.mp3", Sound.class);
        super.load("sounds/toppings/cheese.mp3", Sound.class);
        super.load("sounds/toppings/chicken.mp3", Sound.class);
        super.load("sounds/toppings/salami.mp3", Sound.class);
        super.load("sounds/toppings/sauce.mp3", Sound.class);
        super.load("sounds/toppings/sausage.mp3", Sound.class);
        super.finishLoading();
    }

    public void unloadToppingsSounds() {
        super.unload("sounds/toppings/apricot.mp3");
        super.unload("sounds/toppings/bacon.mp3");
        super.unload("sounds/toppings/barbecue.mp3");
        super.unload("sounds/toppings/cheese.mp3");
        super.unload("sounds/toppings/chicken.mp3");
        super.unload("sounds/toppings/salami.mp3");
        super.unload("sounds/toppings/sauce.mp3");
        super.unload("sounds/toppings/sausage.mp3");
    }

    public void loadThemeMusic() {
        super.load("music/ThemeMusic.mp3", Music.class);
        super.finishLoadingAsset("music/ThemeMusic.mp3");
    }
    
    public void unloadThemeMusic() {
        super.unload("music/ThemeMusic.mp3");
    }

    public void loadServeBossSounds() {
        super.load("music/BossTheme.mp3", Music.class);
        super.finishLoadingAsset("music/BossTheme.mp3");
    }

    public void unloadServeBossSounds() {
        super.unload("music/BossTheme.mp3");
    }

    public void loadServeWorkersSounds() {
        super.load("music/PartyTheme.mp3", Music.class);
        super.load("sounds/PickScrape.mp3", Sound.class);
        super.finishLoadingAsset("music/PartyTheme.mp3");
        super.finishLoadingAsset("sounds/PickScrape.mp3");
    }

    public void unloadServeWorkersSounds() {
        super.unload("music/PartyTheme.mp3");
        super.unload("sounds/PickScrape.mp3");
    }

    public void loadPostcardSounds() {
        super.load("sounds/camera.mp3", Sound.class);
        super.finishLoadingAsset("sounds/camera.mp3");
    }

    public void unloadPostcardSounds() {
        super.unload("sounds/camera.mp3");
    }
}
