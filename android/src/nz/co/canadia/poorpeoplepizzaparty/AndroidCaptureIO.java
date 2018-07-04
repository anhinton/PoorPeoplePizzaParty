package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;

import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;

public class AndroidCaptureIO implements CaptureIO {
    @Override
    public void savePizza(Pizza pizza, Assets assets) {
        Gdx.app.log("AndroidCaptureIO",
                "would have saved pizza postcard to disk");
    }
}
