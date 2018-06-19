package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MySkin extends Skin {

    private final FreeTypeFontGenerator generator;

    public MySkin() {

        // load texture atlas
        super.addRegions(new TextureAtlas(Gdx.files.internal("data/uiskin.atlas")));

        // load default font
        generator = new FreeTypeFontGenerator(
                Gdx.files.internal("fonts/Cagliostro-Regular/Cagliostro-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Math.round(0.053f * Gdx.graphics.getBackBufferHeight());
        super.add("default-font", generator.generateFont(parameter));

        // load all my handy JSON things
        super.load(Gdx.files.internal("data/uiskin.json"));
    }

    @Override
    public void dispose() {
        generator.dispose();
        super.dispose();
    }

}
