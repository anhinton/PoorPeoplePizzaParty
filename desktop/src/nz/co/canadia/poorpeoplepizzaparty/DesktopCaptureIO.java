package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import java.io.File;
import java.util.zip.Deflater;

import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class DesktopCaptureIO implements CaptureIO {

    @Override
    public void savePostcardImage(Postcard postcard) {
        Pixmap postcardPixmap = postcard.getPixmap();

        FileHandle filePath;
        if (Gdx.files.external(Constants.DESKTOP_CAPTURE_PATH).exists()) {
            FileHandle captureDir = Gdx.files.external(
                    Constants.DESKTOP_CAPTURE_PATH + "/" + Constants.GAME_NAME);
            captureDir.mkdirs();
            filePath = Gdx.files.external(
                    captureDir + "/" + postcard.fileName());
        } else {
            filePath = Gdx.files.external(postcard.fileName());
        }
        PixmapIO.writePNG(filePath, postcardPixmap, Deflater.BEST_COMPRESSION, false);
    }

    @Override
    public void savePizzaXml(String pizzaXml) {
        FileHandle autosaveFile = autosaveFile();
        boolean dirExists = autosaveFile.parent().exists();
        if (!dirExists) {
            dirExists = autosaveFile.parent().file().mkdir();
        }
        if (dirExists) {
            autosaveFile.writeString(pizzaXml, false);
        }
    }

    @Override
    public String loadPizzaXml() {
        if (autosaveFile().exists()) {
            return autosaveFile().readString();
        } else {
            return "";
        }
    }

    private FileHandle autosaveFile() {
        String osName = System.getProperty("os.name").toLowerCase();
        FileHandle saveDir;
        if (osName.contains("windows")) {
            saveDir = Gdx.files.external("AppData/Roaming/" + Constants.autosavePath);
        } else if (osName.contains("linux")) {
            saveDir = Gdx.files.external(".local/share/" + Constants.autosavePath);
        } else if (osName.contains("mac")) {
            saveDir = Gdx.files.external("Library/Application Support/" + Constants.autosavePath);
        } else {
            saveDir = Gdx.files.external("." + Constants.autosavePath);
        }
        return Gdx.files.external(saveDir + "/" + Constants.autosaveFile);
    }
}
