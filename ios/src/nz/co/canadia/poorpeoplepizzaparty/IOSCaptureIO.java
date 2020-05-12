package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class IOSCaptureIO implements CaptureIO {
    @Override
    public void savePostcardImage(Postcard postcard) {

    }

    @Override
    public void savePizzaXml(String pizzaXml) {
        FileHandle autosaveFile = Gdx.files.local(Constants.autosaveFile);
        autosaveFile.writeString(pizzaXml, false);
    }

    @Override
    public String loadPizzaXml() {

        if (Gdx.files.local(Constants.autosaveFile).exists()) {
            return Gdx.files.local(Constants.autosaveFile).readString();
        } else {
            return "";
        }
    }
}
