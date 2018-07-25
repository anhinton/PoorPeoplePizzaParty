package nz.co.canadia.poorpeoplepizzaparty.client;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;

public class HtmlCaptureIO implements CaptureIO {
    @Override
    public void savePizza(Pizza pizza, Assets assets) {
        // TODO: remove debug logging
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.log("HtmlCaptureIO",
                "would have saved a pizza postcard to disk");
    }
}
