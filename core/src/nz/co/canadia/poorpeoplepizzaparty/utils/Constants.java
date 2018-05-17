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
    public enum BaseTopping {
        BASE, SAUCE, CHEESE
    }
}
