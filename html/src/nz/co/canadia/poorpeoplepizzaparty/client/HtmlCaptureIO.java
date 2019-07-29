package nz.co.canadia.poorpeoplepizzaparty.client;

import com.badlogic.gdx.Gdx;

import nz.co.canadia.poorpeoplepizzaparty.Postcard;
import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;

public class HtmlCaptureIO implements CaptureIO {

    @Override
    public void savePostcardImage(Postcard postcard) {

    }

    @Override
    public void savePizzaXml(String pizzaXml) {
        //TODO: remove debugging code
        Gdx.app.log("HtmlCaptureIO", "No save/load on WebGl");
    }

    @Override
    public String loadPizzaXml() {
        //TODO: remove debugging code
        Gdx.app.log("HtmlCaptureIO", "No save/load on WebGl");
        return "";
    }
}
