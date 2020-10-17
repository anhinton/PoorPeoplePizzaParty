package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.uikit.UIActivityViewController;
import org.robovm.apple.uikit.UIImage;
import org.robovm.apple.uikit.UIModalPresentationStyle;
import org.robovm.apple.uikit.UIPopoverArrowDirection;
import org.robovm.apple.uikit.UIView;

import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class IOSCaptureIO implements CaptureIO {
    private FileHandle postcardFilePath;

    @Override
    public void savePostcardImage(Postcard postcard) {
        Pixmap postcardPixmap = postcard.getPixmap();
        postcardFilePath = Gdx.files.local("postcards/" + postcard.fileName());
        writePostcardPNG(postcardPixmap);
        sharePostcardPNG();
    }

    private void writePostcardPNG(Pixmap postcardPixmap) {
        PixmapIO.writePNG(postcardFilePath, postcardPixmap);
    }

    private void sharePostcardPNG() {
        if(postcardFilePath.exists()) {
            UIImage image = new UIImage(postcardFilePath.file());
            NSArray share = new NSArray(image);
            UIActivityViewController uiActivityViewController =
                    new UIActivityViewController(share, null);

            uiActivityViewController.setModalPresentationStyle(UIModalPresentationStyle.Popover);
            UIView view = ((IOSApplication) Gdx.app).getUIViewController().getView();
            uiActivityViewController.getPopoverPresentationController().setSourceView(view);
            uiActivityViewController.getPopoverPresentationController().setSourceRect(
                    new CGRect(view.getFrame().getX(), view.getFrame().getY(),
                            view.getFrame().getWidth(), view.getFrame().getHeight()));
            uiActivityViewController.getPopoverPresentationController().setPermittedArrowDirections(UIPopoverArrowDirection.None);

            ((IOSApplication) Gdx.app).getUIViewController().presentViewController(
                    uiActivityViewController, true, null);
        }
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
