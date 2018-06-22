package nz.co.canadia.poorpeoplepizzaparty.utils;

public class UiSize {
    public static int getPadding(int screenHeight) {
        return Math.round((float)Constants.UNIT / Constants.GAME_HEIGHT
                * screenHeight);
    }

    public static int getButtonWidthFull(int screenWidth, int screenHeight) {
        float baseWidth =
                (float)Constants.BASE_WIDTH / Constants.GAME_WIDTH * screenWidth;
        return Math.round(screenWidth - baseWidth
                - getPadding(screenHeight) * 3);
    }

    public static int getButtonWidthHalf(int screenWidth, int screenHeight) {
        return (getButtonWidthFull(screenWidth, screenHeight)
                - getPadding(screenHeight)) / 2;
    }

    public static int getButtonHeight(int screenHeight) {
        return getPadding(screenHeight) * 4;
    }
}
