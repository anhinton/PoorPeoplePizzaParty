package nz.co.canadia.poorpeoplepizzaparty.utils;

/**
 * calculate sizes for UI elements to handle being displayed on screens of various dimensions
 */

public class UiSize {
    public static int getPadding(int screenHeight) {
        return Math.round((float)Constants.UNIT / Constants.GAME_HEIGHT
                * screenHeight);
    }

    public static int getBaseX(int screenHeight) {
        return getPadding(screenHeight);
    }

    public static int getBaseY(int screenHeight) {
        return getBaseX(screenHeight);
    }

    public static int getBaseWidth(int screenWidth) {
        return Math.round((float)Constants.BASE_WIDTH / Constants.GAME_WIDTH
                * screenWidth);
    }

    public static int getBaseHeight(int screenHeight) {
        return Math.round((float)Constants.BASE_HEIGHT / Constants.GAME_HEIGHT
                * screenHeight);
    }

    public static int getButtonWidthFull(int screenWidth, int screenHeight) {
        return Math.round(screenWidth - getBaseWidth(screenWidth)
                - getPadding(screenHeight) * 3);
    }

    public static int getButtonWidthHalf(int screenWidth, int screenHeight) {
        return (getButtonWidthFull(screenWidth, screenHeight)
                - getPadding(screenHeight)) / 2;
    }

    public static int getButtonHeight(int screenHeight) {
        return getPadding(screenHeight) * 4;
    }

    public static float getImageHeight(float imageHeight, int screenHeight) {
        return imageHeight / Constants.GAME_HEIGHT * screenHeight;
    }

    public static float getImageWidth(float imageWidth, int screenWidth) {
        return imageWidth / Constants.GAME_WIDTH * screenWidth;
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
        int viewportHeight = screenHeight;
        if (screenWidth / screenHeight <= Constants.GAME_ASPECT_RATIO) {
            viewportHeight = Math.round(screenWidth / Constants.GAME_ASPECT_RATIO);
        }
        return clampViewportHeight(viewportHeight);
    }

    public static int getViewportWidth(int screenWidth, int screenHeight) {
        int viewportHeight = getViewportHeight(screenWidth, screenHeight);
        return Math.round(viewportHeight * Constants.GAME_ASPECT_RATIO);
    }

}
