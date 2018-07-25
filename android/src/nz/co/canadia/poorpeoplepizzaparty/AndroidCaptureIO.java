 package nz.co.canadia.poorpeoplepizzaparty;

import android.os.Environment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import java.util.Locale;

import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.Capture;
import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;

 public class AndroidCaptureIO implements CaptureIO {

    @Override
    public void savePizza(Pizza pizza, Assets assets, Locale locale) {
        Pixmap postcardPixmap = Capture.postcardPixmap(pizza, assets);

        FileHandle filePath =
                Gdx.files.external(Environment.DIRECTORY_PICTURES + "/"
                        + Capture.fileName(locale));

        PixmapIO.writePNG(filePath, postcardPixmap);
        postcardPixmap.dispose();
    }
}
