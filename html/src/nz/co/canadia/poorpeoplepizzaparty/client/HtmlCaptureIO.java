package nz.co.canadia.poorpeoplepizzaparty.client;

import com.badlogic.gdx.Gdx;

import nz.co.canadia.poorpeoplepizzaparty.Postcard;
import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;

public class HtmlCaptureIO implements CaptureIO {

    @Override
    public void savePostcardImage(Postcard postcard) {
        // No postcard export on WebGl
    }

    @Override
    public void savePizzaXml(String pizzaXml) {
        // No save/load on WebGl
    }

    @Override
    public String loadPizzaXml() {
        // No save/load on WebGl
        return "";
    }
}
