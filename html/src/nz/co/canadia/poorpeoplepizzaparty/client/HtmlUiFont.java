package nz.co.canadia.poorpeoplepizzaparty.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import nz.co.canadia.poorpeoplepizzaparty.utils.UiFont;

public class HtmlUiFont implements UiFont {
    @Override
    public BitmapFont getUiFont() {

        return new BitmapFont(
                Gdx.files.internal("fonts/Cagliostro-Regular/Cagliostro-Regular-ui.fnt"));
    }
}
