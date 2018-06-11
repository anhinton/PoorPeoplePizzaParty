package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;

import nz.co.canadia.poorpeoplepizzaparty.utils.Screenshot;

public class AndroidScreenshot implements Screenshot {
    @Override
    public Pixmap captureScreen() {
        Gdx.app.log("AndroidScreenshot",
                "would have returned a Pixmap.class");
        return null;
    }

    @Override
    public void saveCapture(Pixmap pixmap) {
        Gdx.app.log("AndroidScreenshot",
                "would have saved a Pixmap to disk");
    }
}
