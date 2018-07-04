package nz.co.canadia.poorpeoplepizzaparty;

import android.content.Context;
import android.os.Environment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.Capture;
import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class AndroidCaptureIO implements CaptureIO {

    private final Context context;

    public AndroidCaptureIO(Context context) {

        this.context = context;
    }

    @Override
    public void savePizza(Pizza pizza, Assets assets) {
        int pizzaX = Constants.GAME_WIDTH - Constants.BASE_WIDTH
                - Constants.BASE_X;
        int pizzaY = Constants.BASE_Y;
        Pixmap pizzaPixmap = Capture.capturePizza(pizza);
        Pixmap postcardPixmap = assets.get("graphics/postcard.png",
                Pixmap.class);
        postcardPixmap.drawPixmap(pizzaPixmap, pizzaX, pizzaY);

        if (ContextCompat.checkSelfPermission(thisActivity, Manifest.permission.WRITE_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
        }

        String s = Environment.getExternalStorageState();

        PixmapIO.writePNG(Gdx.files.external("mypixmap.png"),
                postcardPixmap);
        pizzaPixmap.dispose();
        Gdx.app.log("AndroidCaptureIO",
                "would have saved pizza postcard to disk");
    }
}
