 package nz.co.canadia.poorpeoplepizzaparty;

import android.os.Environment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import java.io.File;
import java.security.Timestamp;

import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.Capture;
import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class AndroidCaptureIO implements CaptureIO {

    @Override
    public void savePizza(Pizza pizza, Assets assets) {
        int pizzaX = Constants.GAME_WIDTH - Constants.BASE_WIDTH
                - Constants.BASE_X;
        int pizzaY = Constants.BASE_Y;
        Pixmap pizzaPixmap = Capture.capturePizza(pizza);
        Pixmap postcardPixmap = assets.get("graphics/postcard.png",
                Pixmap.class);
        postcardPixmap.drawPixmap(pizzaPixmap, pizzaX, pizzaY);

        File filePath = new File(Environment.DIRECTORY_PICTURES,
                "Pizza");
        filePath = new File(filePath, "mypixmap.png");

        PixmapIO.writePNG(Gdx.files.external(filePath.toString()),
                postcardPixmap);
        pizzaPixmap.dispose();
    }
}
