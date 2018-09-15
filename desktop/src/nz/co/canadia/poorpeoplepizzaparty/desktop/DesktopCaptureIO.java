package nz.co.canadia.poorpeoplepizzaparty.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import java.util.Locale;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.Capture;
import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class DesktopCaptureIO implements CaptureIO {

    private Pixmap postcardPixmap;

    @Override
    public void savePizza(Pizza pizza, Assets assets, Locale locale) {
        postcardPixmap = Capture.postcardPixmap(pizza, assets);

        FileHandle filePath = Gdx.files.external(Constants.CAPTURE_DIR + "/"
                + Capture.fileName());
        if (Gdx.files.external("Pictures").exists()) {
            filePath = Gdx.files.external(
                    "Pictures/" + filePath);
        } else if (Gdx.files.external("Documents").exists()) {
            filePath = Gdx.files.external(
                    "Documents/" + filePath);
        }

        PixmapIO.writePNG(filePath, postcardPixmap);
    }

    @Override
    public void dispose() {
        postcardPixmap.dispose();
    }
}
