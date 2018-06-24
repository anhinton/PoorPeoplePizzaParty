package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class UiSkin extends Skin {

    public void loadPizzaScreen() {

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
        super.add("ui-font", generator.generateFont(parameter),
                BitmapFont.class);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        // load uiskin atlas
        super.addRegions(new TextureAtlas(
                Gdx.files.internal("data/uiskin.atlas")));

        // load uiskin json
        super.load(Gdx.files.internal("data/uiskin.json"));        
    }
}
