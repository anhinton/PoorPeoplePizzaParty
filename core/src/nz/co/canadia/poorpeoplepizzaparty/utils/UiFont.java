package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * This interface exists so that I can load screen-size appropriate fonts for Desktop and
 * Android while loading fixed sized fonts for Html.
 */

public interface UiFont {
    BitmapFont getUiFont(FileHandle fontFile);
}
