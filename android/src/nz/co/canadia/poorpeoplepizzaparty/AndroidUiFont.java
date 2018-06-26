package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiFont;

public class AndroidUiFont implements UiFont {

    @Override
    public BitmapFont getUiFont() {

        // create ui-font based on screen size
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal(
                        "fonts/Cagliostro-Regular/Cagliostro-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Math.round(Constants.UI_FONT_RATIO
                * Gdx.graphics.getBackBufferHeight());
        parameter.shadowColor = Constants.UI_SHADOW_COLOR;
        parameter.shadowOffsetX =
                Math.round(Constants.UI_SHADOW_X
                        * Gdx.graphics.getBackBufferHeight());
        parameter.shadowOffsetY =
                Math.round(Constants.UI_SHADOW_Y
                        * Gdx.graphics.getBackBufferHeight());
        BitmapFont uiFont = generator.generateFont(parameter);

        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        return uiFont;
    }
}
