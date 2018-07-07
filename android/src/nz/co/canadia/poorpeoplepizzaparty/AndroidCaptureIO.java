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
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class AndroidCaptureIO implements CaptureIO {

    @Override
    public void savePizza(Pizza pizza, Assets assets, Locale locale) {
        int pizzaX = Constants.GAME_WIDTH - Constants.BASE_WIDTH
                - Constants.BASE_X;
        int pizzaY = Constants.BASE_Y;
        Pixmap pizzaPixmap = Capture.capturePizza(pizza);
        Pixmap postcardPixmap = assets.get("graphics/postcard.png",
                Pixmap.class);
        postcardPixmap.drawPixmap(pizzaPixmap, pizzaX, pizzaY);

        FileHandle filePath =
                Gdx.files.external(Environment.DIRECTORY_PICTURES + "/"
                        + Capture.fileName(locale));

        PixmapIO.writePNG(filePath, postcardPixmap);
        pizzaPixmap.dispose();
    }
}
