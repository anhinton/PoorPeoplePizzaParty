package nz.co.canadia.poorpeoplepizzaparty.utils;

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

    public static int getBaseHeight(int screenWidth) {
        return getBaseWidth(screenWidth);
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

    public static float getImageWidth(float imageHeight, int screenWidth) {
        return imageHeight / Constants.GAME_WIDTH * screenWidth;
    }
}
