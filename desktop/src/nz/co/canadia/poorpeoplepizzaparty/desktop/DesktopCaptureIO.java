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

    @Override
    public void savePizza(Pizza pizza, Assets assets, Locale locale) {
        int pizzaX = Constants.GAME_WIDTH - Constants.BASE_WIDTH
                - Constants.BASE_X;
        int pizzaY = Constants.BASE_Y;
        Pixmap pizzaPixmap = Capture.capturePizza(pizza);
        Pixmap postcardPixmap = assets.get("graphics/postcard.png",
                Pixmap.class);
        postcardPixmap.drawPixmap(pizzaPixmap, pizzaX, pizzaY);

        FileHandle filePath;
        if (Gdx.files.external("Pictures").exists()) {
            filePath = Gdx.files.external(
                    "Pictures/" + Capture.fileName(locale));
        } else if (Gdx.files.external("Documents").exists()) {
            filePath = Gdx.files.external(
                    "Documents/" + Capture.fileName(locale));
        } else {
            filePath = Gdx.files.external(
                    Capture.fileName(locale));
        }

        PixmapIO.writePNG(filePath, postcardPixmap);
        pizzaPixmap.dispose();
    }
}
