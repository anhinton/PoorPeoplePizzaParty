package nz.co.canadia.poorpeoplepizzaparty.utils;


import com.badlogic.gdx.graphics.Color;

/**
 * Game constants
 */

public class Constants {
    public static final String GAME_NAME = "Poor People Pizza Party";

    // Colours
    public static final Color BG_COLOUR =
            new Color(48/255f, 184/255f, 73/255f, 1); // green, it's green

    // App dimensions
    public static final int APP_WIDTH = 960;
    public static final int APP_HEIGHT = 600;

    // Topping levels for the pizza base
    public enum ToppingName {
        NONE, BASE, SAUCE, CHEESE, CHICKEN, PEPPERONI, BACON, HAM, APRICOT,
        BARBECUE
    }

    // Pizza base dimensions
    public static final int BASE_WITDH = 480;
    public static final int BASE_HEIGHT = 480;

    // Pizza base x, y
    public static final int BASE_X = 60;
    public static final int BASE_Y = 60;

    // Topping menu button size
    public static final int BUTTON_WIDTH = 100;
    public static final int BUTTON_HEIGHT = 100;

    // Topping menu button image size
    public static final int BUTTON_IMAGE_WIDTH = 90;
    public static final int BUTTON_IMAGE_HEIGHT = 90;

    // Topping menu UI padding/spacing
    public static final int MENU_PADDING = 20;
}
