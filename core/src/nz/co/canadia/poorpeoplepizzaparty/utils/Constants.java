package nz.co.canadia.poorpeoplepizzaparty.utils;


import com.badlogic.gdx.graphics.Color;

/**
 * Game constants
 */

public class Constants {
    public static final String GAME_NAME = "Poor People Pizza Party";

    public static final boolean DEBUG_GRAPHICS = false;

    // Preferences and save files
    public static final String autosavePath = "poorpeoplepizzaparty";
    public static final String autosaveFile = "autosave.xml";

    // Colours
    public static final Color BG_COLOUR =
            new Color(48/255f, 184/255f, 73/255f, 1); // green, it's green
    public static final Color BOSS_BG_COLOUR =
            new Color(0.235f, 0.247f, 0.255f, 1); // a bit grey really
    public static final Color WORKERS_BG_COLOUR =
            new Color(0, 0, 0, 1);

    public static final int GAME_WIDTH = 960;
    public static final int GAME_HEIGHT = 600;

    public static final float GAME_ASPECT_RATIO =
            (float)GAME_WIDTH / GAME_HEIGHT;

    public static final int DESKTOP_WIDTH = 960;
    public static final int DESKTOP_HEIGHT = 600;

    public static final int HTML_WIDTH = 960;
    public static final int HTML_HEIGHT = 600;

    // Available menus in TitleUi.class
    public enum CurrentTitleMenu { MAIN, SETTINGS }

    // Available menus in PizzaUi.class
    public enum CurrentPizzaMenu { MAIN, TOPPING }

    // Topping levels for the pizza base
    public enum ToppingName {
        BASE, SAUCE, CHEESE, CHICKEN, SALAMI, BACON, SAUSAGE, APRICOT,
        BARBECUE
    }

    // everything is blocked out in terms of this unit
    static final int UNIT = 20;

    // UI button icons are 50px
    public static final float UI_ICON_RATIO = 50f / GAME_HEIGHT;

    // Pizza base x, y
    public static final int BASE_X = UNIT;
    public static final int BASE_Y = UNIT;

    // Pizza base dimensions
    public static final int BASE_WIDTH = GAME_HEIGHT - 2 * UNIT;
    public static final int BASE_HEIGHT = GAME_HEIGHT - 2 * UNIT;

    // Base center position
    public static final int BASE_CENTER_X = BASE_X + BASE_WIDTH / 2;
    public static final int BASE_CENTER_Y = BASE_Y + BASE_HEIGHT / 2;

    // Pizza plotting area
    public static final int PIZZA_LEFT = BASE_X;
    public static final int PIZZA_RIGHT = BASE_X + BASE_WIDTH;
    public static final int PIZZA_BOTTOM = BASE_Y;
    public static final int PIZZA_TOP = BASE_X + BASE_HEIGHT;

    // Base selected sprite scale
    public static final float BASE_SCALE = 1 / 5f;

    // Base serve scale
    public static final float BASE_SERVE_SCALE = 2 / 5f;

    // Sauce topping sprite alpha
    public static final Color SAUCE_COLOR =
            new Color(1,1,1, (float)3 / 5);

    // Points change rates
    public static final float POINTS_MOVEMENT_SPEED = 100f;
    public static final float POINTS_FADE_RATE = 1.5f;

    // Cook timer
    public static final float COOK_TIME_TOTAL = 3f;
    public static final float COOK_TIME_INCREMENT = 1 / 30f;

    // Screenshot filename
    public static final String CAPTURE_PREFIX = "pizza";
    public static final String CAPTURE_SUFFX = ".png";

    // Options for serving cooked pizza
    public enum ServeOption { BOSS, WORKERS }

    // states for ServeWorkersScreen
    public enum ServerWorkersState { PARTY, BOSS, FINISHED }

    // length of party in ServerWorkersScreen in seconds
    // TODO: set length to Pizza Party song clip time
    public static final float PARTY_TIME = 5;

    // Options for party background texture
    public enum PartySprite { NORMAL, INVERSE }

    // number of Flying Pizzas to spawn initially
    public static final int FLYING_PIZZA_INITIAL_SPAWN_COUNT = 20;

    // range of FlyingPizzas to spawn
    public static final int FLYING_PIZZA_SPAWN_COUNT_MIN = 1;
    public static final int FLYING_PIZZA_SPAWN_COUNT_MAX = 10;

    // FlyingPizza spawn rate
    public static final float FLYING_PIZZA_SPAWN_WAIT_MIN = 0.2f;
    public static final float FLYING_PIZZA_SPAWN_WAIT_MAX = 1.2f;

    // FlyingPizza scale
    public static final float FLYING_PIZZA_SCALE = 1 / 3f;

    // FlyingPizza speed range
    public static final float FLYING_PIZZA_SPEED_MIN = 600;
    public static final float FLYING_PIZZA_SPEED_MAX = 1600;

    // time taken by PartyBoss in seconds
    public static final float PARTY_BOSS_TIME = 1;
    // time taken by DoomDrips in seconds
    public static final float DOOM_DRIPS_TIME = 2;

    public static final float PIZZA_PARTY_FRAME_DURATION = 0.9f;
}
