package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.graphics.Pixmap;

public interface Screenshot {
    Pixmap captureScreen();

    void saveCapture(Pixmap pixmap);
}
