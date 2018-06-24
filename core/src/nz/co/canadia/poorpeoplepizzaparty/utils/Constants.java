package nz.co.canadia.poorpeoplepizzaparty.utils;


import com.badlogic.gdx.graphics.Color;

/**
 * Game constants
 */

public class Constants {
    public static final String GAME_NAME = "Poor People Pizza Party";

    public static final boolean DEBUG_GRAPHICS = false;

    // Colours
    public static final Color BG_COLOUR =
            new Color(48/255f, 184/255f, 73/255f, 1); // green, it's green

    public static final int GAME_WIDTH = 960;
    public static final int GAME_HEIGHT = 600;

    public static final float GAME_ASPECT_RATIO =
            (float)GAME_WIDTH / GAME_HEIGHT;

    public static final int DESKTOP_WIDTH = 1152;
    public static final int DESKTOP_HEIGHT = 720;

    public static final int HTML_WIDTH = 960;
    public static final int HTML_HEIGHT = 600;

    // Topping levels for the pizza base
    public enum ToppingName {
        BASE, SAUCE, CHEESE, CHICKEN, SALAMI, BACON, SAUSAGE, APRICOT,
        BARBECUE
    }

    public static final int UNIT = 20;

    public static final float UI_FONT_RATIO = 32f / GAME_HEIGHT;
    public static final float UI_SHADOW_X = 2f/ GAME_HEIGHT;
    public static final float UI_SHADOW_Y = 2f/ GAME_HEIGHT;
    public static final Color UI_SHADOW_COLOR =
            new Color(0, 0, 0, 0.6f);

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
    public static final float BASE_SCALE = (float)1 / 5;

    // Sauce topping sprite alpha
    public static final Color SAUCE_COLOR =
            new Color(1,1,1, (float)3 / 5);

    // display offset for topping on touch displays
    public static final int TOUCH_OFFSET = 50;
}
