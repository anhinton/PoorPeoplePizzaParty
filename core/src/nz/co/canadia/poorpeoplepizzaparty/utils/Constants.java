package nz.co.canadia.poorpeoplepizzaparty.utils;


import com.badlogic.gdx.Gdx;
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

    public static final int APP_WIDTH = 960;
    public static final int APP_HEIGHT = 600;

    // Topping levels for the pizza base
    public enum ToppingName {
        BASE, SAUCE, CHEESE, CHICKEN, SALAMI, BACON, SAUSAGE, APRICOT,
        BARBECUE
    }

    private static final int UNIT = 20;

    // Pizza base x, y
    public static final int BASE_X = UNIT;
    public static final int BASE_Y = UNIT;

    // Pizza base dimensions
    public static final int BASE_WIDTH = APP_HEIGHT - 2 * UNIT;
    public static final int BASE_HEIGHT = APP_HEIGHT - 2 * UNIT;

    // Base center position
    public static final int BASE_CENTER_X = BASE_X + BASE_WIDTH / 2;
    public static final int BASE_CENTER_Y = BASE_Y + BASE_HEIGHT / 2;

    // Pizza plotting area
    public static final int PIZZA_LEFT = BASE_X;
    public static final int PIZZA_RIGHT = BASE_X + BASE_WIDTH;
    public static final int PIZZA_BOTTOM = BASE_Y;
    public static final int PIZZA_TOP = BASE_X + BASE_HEIGHT;

    // Topping menu UI padding/spacing
    public static final int MENU_PADDING = UNIT;

    // Topping menu button size
    public static final int BUTTON_WIDTH_FULL =
            APP_WIDTH - BASE_WIDTH - MENU_PADDING * 3;
    public static final float BUTTON_WIDTH_HALF =
            (float)(BUTTON_WIDTH_FULL - MENU_PADDING) / 2;
    public static final int BUTTON_HEIGHT = 4 * UNIT;

    // Base selected sprite scale
    public static final float BASE_SCALE = (float)1 / 5;

    // Sauce topping sprite alpha
    public static final Color SAUCE_COLOR =
            new Color(1,1,1, (float)3 / 5);

    // display offset for topping on touch displays
    public static final int TOUCH_OFFSET = 50;
}
