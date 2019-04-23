package nz.co.canadia.poorpeoplepizzaparty.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiFont;

public class HtmlUiFont implements UiFont {
    @Override
    public BitmapFont getUiFont(FileHandle fontFile) {
        String fontFileName = fontFile.name();
        if (fontFileName.equals("Inconsolata-Regular.ttf")) {
            fontFile = Gdx.files.internal("fonts/Inconsolata/Inconsolata-Regular.fnt");
        } else if (fontFileName.equals("Podkova-Regular.ttf")) {
            fontFile = Gdx.files.internal("fonts/Podkova/Podkova-Regular.fnt");
        }
        return new BitmapFont(fontFile);
    }
}
