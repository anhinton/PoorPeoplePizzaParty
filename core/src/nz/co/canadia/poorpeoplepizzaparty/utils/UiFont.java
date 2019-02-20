package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public interface UiFont {
    BitmapFont getUiFont(FileHandle fontFile);
}
