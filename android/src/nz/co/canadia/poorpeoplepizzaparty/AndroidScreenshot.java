package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;

import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.Screenshot;

public class AndroidScreenshot implements Screenshot {
    @Override
    public Pixmap capturePizza(Pizza pizza) {
        Gdx.app.log("AndroidScreenshot",
                "would have returned a Pixmap.class");
        return new Pixmap(0, 0, Pixmap.Format.RGBA8888);
    }

    @Override
    public void savePizza(Pizza pizza, Assets assets) {
        Gdx.app.log("AndroidScreenshot",
                "would have saved pizza postcard to disk");
    }
}
