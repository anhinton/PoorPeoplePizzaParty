package nz.co.canadia.poorpeoplepizzaparty.client;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;

import java.util.Locale;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.Capture;
import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;

public class HtmlCaptureIO implements CaptureIO {

    @Override
    public void savePizza(Pizza pizza, Assets assets, Locale locale) {
        Pixmap postcardPixmap = Capture.postcardPixmap(pizza, assets);
        postcardPixmap.dispose();

        // TODO: remove debug logging
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.log("HtmlCaptureIO",
                "would have saved a pizza postcard to disk");
        Gdx.app.log("HtmlCaptureIO",
                "I did make a pixmap and chuck it out, though");
    }
}
