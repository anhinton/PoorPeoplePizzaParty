package nz.co.canadia.poorpeoplepizzaparty.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;
import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.Postcard;
import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;

public class DesktopCaptureIO implements CaptureIO {
    private File captureDir;

    @Override
    public void savePostcardImage(Postcard postcard) {
        Pixmap postcardPixmap = postcard.getPixmap();

        final JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setDialogTitle("Choose save location");
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
}
