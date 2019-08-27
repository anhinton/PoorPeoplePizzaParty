package nz.co.canadia.poorpeoplepizzaparty.utils;

/**
 * calculate sizes for UI elements to handle being displayed on screens of various dimensions
 */

public class UiSize {
    public static int getPadding() {
        return Constants.UNIT;
    }

    public static int getIconSize() {
        return Constants.UI_ICON_SIZE;
    }

    public static int getBaseX() {
        return getPadding();
    }

    public static int getBaseY() {
        return getBaseX();
    }

    public static int getBaseWidth() {
        return Constants.BASE_WIDTH;
    }

    public static int getBaseHeight() {
        return Constants.BASE_HEIGHT;
    }

}
