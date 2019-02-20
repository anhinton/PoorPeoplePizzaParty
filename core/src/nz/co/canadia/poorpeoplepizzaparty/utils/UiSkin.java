package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class UiSkin extends Skin {

    public UiSkin (UiFont uiFont) {

        super.add("ui-font",
                uiFont.getUiFont(Gdx.files.internal(
                        "fonts/Cagliostro-Regular/Cagliostro-Regular.ttf")),
                BitmapFont.class);

        // load uiskin atlas
        super.addRegions(new TextureAtlas(
                Gdx.files.internal("data/uiskin.atlas")));

        // load uiskin json
        super.load(Gdx.files.internal("data/uiskin.json"));
    }

}
