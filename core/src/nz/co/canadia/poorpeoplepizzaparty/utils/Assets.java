package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ObjectMap;

public class Assets extends AssetManager {

    private final ObjectMap<Constants.ToppingName, String> toppingPaths;
    
    public Assets() {
        toppingPaths = new ObjectMap<Constants.ToppingName, String>();
        toppingPaths.put(Constants.ToppingName.APRICOT,
                "toppings/apricot-topping");
        toppingPaths.put(Constants.ToppingName.BACON,
                "toppings/bacon-topping");
        toppingPaths.put(Constants.ToppingName.BARBECUE,
                "toppings/barbecue-topping");
        toppingPaths.put(Constants.ToppingName.BASE,
                "toppings/base-topping");
        toppingPaths.put(Constants.ToppingName.CHEESE,
                "toppings/cheese-topping");
        toppingPaths.put(Constants.ToppingName.CHICKEN,
                "toppings/chicken-topping");
        toppingPaths.put(Constants.ToppingName.SALAMI,
                "toppings/salami-topping");
        toppingPaths.put(Constants.ToppingName.SAUCE,
                "toppings/sauce-topping");
        toppingPaths.put(Constants.ToppingName.SAUSAGE,
                "toppings/sausage-topping");
    }

    public String toppingPath(Constants.ToppingName toppingName) {
        return toppingPaths.get(toppingName);
    }

    /**
     * Loads assets for game art assets.
     */
    public void loadTextures() {
        super.load("graphics/graphics.atlas", TextureAtlas.class);
    }

    public void loadTitleScreenSounds() {
            super.load("sounds/toppings/salami.mp3", Sound.class);
            super.finishLoading();
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
