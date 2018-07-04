package nz.co.canadia.poorpeoplepizzaparty.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.Capture;
import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class DesktopCaptureIO implements CaptureIO {
    @Override
    public void savePizza(Pizza pizza, Assets assets) {
        int pizzaX = Constants.GAME_WIDTH - Constants.BASE_WIDTH
                - Constants.BASE_X;
        int pizzaY = Constants.BASE_Y;
        Pixmap pizzaPixmap = Capture.capturePizza(pizza);
        Pixmap postcardPixmap = assets.get("graphics/postcard.png",
                Pixmap.class);
        postcardPixmap.drawPixmap(pizzaPixmap, pizzaX, pizzaY);
        PixmapIO.writePNG(Gdx.files.external("mypixmap.png"),
                postcardPixmap);
        pizzaPixmap.dispose();
    }
}
