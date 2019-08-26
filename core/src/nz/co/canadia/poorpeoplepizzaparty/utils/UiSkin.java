package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class UiSkin extends Skin {

    public UiSkin (int height) {

        BitmapFont buttonFont;
//        if (height >= 1080) {
//            buttonFont = new BitmapFont(
//                    Gdx.files.internal("fonts/Inconsolata/Inconsolata-Regular-64.fnt"));
//        } else if (height >= 720) {
//            buttonFont = new BitmapFont(
//                    Gdx.files.internal("fonts/Inconsolata/Inconsolata-Regular-43.fnt"));
//        } else {
//            buttonFont = new BitmapFont(
//                    Gdx.files.internal("fonts/Inconsolata/Inconsolata-Regular-36.fnt"));
//        }
        buttonFont = new BitmapFont(
                Gdx.files.internal("fonts/Inconsolata/Inconsolata-Regular-36.fnt"));

        BitmapFont labelFont;
//        if (height >= 1080) {
//            labelFont = new BitmapFont(
//                    Gdx.files.internal("fonts/Podkova/Podkova-Regular-64.fnt"));
//        } else if (height >= 720) {
//            labelFont = new BitmapFont(
//                    Gdx.files.internal("fonts/Podkova/Podkova-Regular-43.fnt"));
//        } else {
//            labelFont = new BitmapFont(
//                    Gdx.files.internal("fonts/Podkova/Podkova-Regular-36.fnt"));
//        }
        labelFont = new BitmapFont(
                Gdx.files.internal("fonts/Podkova/Podkova-Regular-36.fnt"));

        BitmapFont creditsFont = new BitmapFont(
                Gdx.files.internal("fonts/Inconsolata/Inconsolata-Regular-credits.fnt"));

        super.add("button-font",
                buttonFont,
                BitmapFont.class);

        super.add("label-font",
                labelFont,
                BitmapFont.class);

        super.add("credits-font",
                creditsFont,
                BitmapFont.class);

        // load uiskin atlas
        super.addRegions(new TextureAtlas(
                Gdx.files.internal("data/uiskin.atlas")));

        // load uiskin json
        super.load(Gdx.files.internal("data/uiskin.json"));
    }

}
