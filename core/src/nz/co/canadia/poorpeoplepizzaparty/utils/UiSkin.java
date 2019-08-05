package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class UiSkin extends Skin {

    public UiSkin () {

        super.add("button-font",
                new BitmapFont(
                        Gdx.files.internal("fonts/Inconsolata/Inconsolata-Regular-36.fnt")),
                BitmapFont.class);

        super.add("label-font",
                new BitmapFont(
                        Gdx.files.internal("fonts/Podkova/Podkova-Regular-36.fnt")),
                BitmapFont.class);

        // load uiskin atlas
        super.addRegions(new TextureAtlas(
                Gdx.files.internal("data/uiskin.atlas")));

        // load uiskin json
        super.load(Gdx.files.internal("data/uiskin.json"));
    }

}
