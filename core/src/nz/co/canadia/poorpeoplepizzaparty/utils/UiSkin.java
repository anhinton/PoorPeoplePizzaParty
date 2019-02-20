package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class UiSkin extends Skin {

    public UiSkin (UiFont uiFont) {

        super.add("button-font",
                uiFont.getUiFont(Gdx.files.internal(
                        "fonts/Inconsolata/Inconsolata-Regular.ttf")),
                BitmapFont.class);

        super.add("label-font",
                uiFont.getUiFont(Gdx.files.internal(
                        "fonts/Podkova/Podkova-Regular.ttf")),
                BitmapFont.class);

        // load uiskin atlas
        super.addRegions(new TextureAtlas(
                Gdx.files.internal("data/uiskin.atlas")));

        // load uiskin json
        super.load(Gdx.files.internal("data/uiskin.json"));
    }

}
