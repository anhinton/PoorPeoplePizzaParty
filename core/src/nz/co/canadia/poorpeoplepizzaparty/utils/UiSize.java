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

    public static int getButtonWidthFull() {
        return Math.round(Constants.GAME_WIDTH - getBaseWidth()
                - getPadding() * 3);
    }

    public static int getButtonWidthHalf() {
        return (getButtonWidthFull() - getPadding()) / 2;
    }

    public static int getButtonHeight() {
        return getPadding() * 4;
    }

    public static float getImageHeight(float imageHeight) {
        return imageHeight;
    }

    public static float getImageWidth(float imageWidth) {
        return imageWidth;
    }

    /**
     * Clamp viewportHeight to one of the pre-determined screen sizes: 1080, 720, 600.
     * @param viewportHeight
     * @return
     */
    private static int clampViewportHeight(int viewportHeight) {
        int clampedHeight = viewportHeight;
        if (clampedHeight >= 1080) {
            clampedHeight = 1080;
        } else if (clampedHeight >= 720) {
            clampedHeight = 720;
        } else {
            clampedHeight = Constants.GAME_HEIGHT;
        }
        return clampedHeight;
    }

    public static int getViewportHeight(int screenWidth, int screenHeight) {
//        int viewportHeight = screenHeight;
//        if (screenWidth / screenHeight <= Constants.GAME_ASPECT_RATIO) {
//            viewportHeight = Math.round(screenWidth / Constants.GAME_ASPECT_RATIO);
//        }
//        return clampViewportHeight(viewportHeight);
        return Constants.GAME_HEIGHT;
    }

    public static int getViewportWidth(int screenWidth, int screenHeight) {
//        int viewportHeight = getViewportHeight(screenWidth, screenHeight);
//        return Math.round(viewportHeight * Constants.GAME_ASPECT_RATIO);
        return Constants.GAME_WIDTH;
    }

}
