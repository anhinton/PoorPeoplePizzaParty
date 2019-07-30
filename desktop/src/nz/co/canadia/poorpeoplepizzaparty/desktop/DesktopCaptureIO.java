package nz.co.canadia.poorpeoplepizzaparty.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;

import nz.co.canadia.poorpeoplepizzaparty.Postcard;
import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class DesktopCaptureIO implements CaptureIO {
    private File captureDir;

    @Override
    public void savePostcardImage(Postcard postcard) {
        Pixmap postcardPixmap = postcard.getPixmap();

        final JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setDialogTitle("Choose location to save postcard");
        if (captureDir != null) {
            fc.setCurrentDirectory(captureDir);
        }
        int returnVal = fc.showSaveDialog(new Component() {});

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            captureDir = fc.getSelectedFile();
            FileHandle filePath =
                    Gdx.files.absolute(captureDir + "/" + postcard.fileName());
            PixmapIO.writePNG(filePath, postcardPixmap);
        }
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
