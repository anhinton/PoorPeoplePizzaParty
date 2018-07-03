package nz.co.canadia.poorpeoplepizzaparty.client;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;

import nz.co.canadia.poorpeoplepizzaparty.utils.Screenshot;

public class HtmlScreenshot implements Screenshot {
    @Override
    public Pixmap capturePizza() {
        // TODO: remove debug logging
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.log("HtmlScreenshot",
                "would have returned a Pixmap.class");
        return null;
    }

    @Override
    public void saveCapture(Pixmap pixmap) {
        // TODO: remove debug logging
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.log("HtmlScreenshot",
                "would have saved a Pixmap to disk");
    }
}
