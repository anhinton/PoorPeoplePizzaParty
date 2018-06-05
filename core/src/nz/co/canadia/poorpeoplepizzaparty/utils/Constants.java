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

    // App dimensions
    public static final int APP_WIDTH = 960;
    public static final int APP_HEIGHT = 600;

    // Topping levels for the pizza base
    public enum ToppingName {
        BASE, SAUCE, CHEESE, CHICKEN, SALAMI, BACON, SAUSAGE, APRICOT,
        BARBECUE
    }

    // Pizza base x, y
    public static final int BASE_X = 60;
    public static final int BASE_Y = 60;

    // Pizza base dimensions
    public static final int BASE_WITDH = 480;
    public static final int BASE_HEIGHT = 480;

    // Base center position
    public static final int BASE_CENTER_X = BASE_X + BASE_WITDH / 2;
    public static final int BASE_CENTER_Y = BASE_Y + BASE_HEIGHT / 2;

    // Pizza plotting area
    public static final int PIZZA_LEFT = BASE_X;
    public static final int PIZZA_RIGHT = BASE_X + BASE_WITDH;
    public static final int PIZZA_BOTTOM = BASE_Y;
    public static final int PIZZA_TOP = BASE_X + BASE_HEIGHT;

    // Topping menu button size
    public static final int BUTTON_WIDTH = 300;
    public static final int BUTTON_HEIGHT = 80;

    // Topping menu UI padding/spacing
    public static final int MENU_PADDING = 20;

    // Base selected sprite scale
    public static final float BASE_SCALE = (float)(1 / 5);

    // Sauce topping sprite alpha
    public static final Color SAUCE_COLOR =
            new Color(1,1,1, (float)(0.6));
}
