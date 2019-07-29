package nz.co.canadia.poorpeoplepizzaparty;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import java.io.File;

import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class AndroidCaptureIO implements CaptureIO {

    private AndroidLauncher activity;
    private FileHandle postcardFilePath;

    AndroidCaptureIO(AndroidLauncher activity) {
        this.activity = activity;
    }

    @Override
    public void savePostcardImage(Postcard postcard) {
        Pixmap postcardPixmap = postcard.getPixmap();

        postcardFilePath = Gdx.files.local("postcards/" + postcard.fileName());

        writePostcardPNG(postcardPixmap);

        sharePostcardPNG();
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

    private void sharePostcardPNG() {
        // get postcard file URI
        File postcardFile = postcardFilePath.file();
        Uri postcardUri = FileProvider.getUriForFile(activity.getContext(),
                "nz.co.canadia.poorpeoplepizzaparty.fileprovider", postcardFile);

        // grant permission for apps to read postcardUri
        activity.getContext().grantUriPermission(
                "nz.co.canadia.poorpeoplepizzaparty.fileprovider",
                postcardUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // share postcard via an Intent
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                activity.getResources().getText(R.string.share_text));
        shareIntent.putExtra(Intent.EXTRA_STREAM, postcardUri);
        shareIntent.setType("image/png");
        shareIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        activity.startActivity(Intent.createChooser(shareIntent,
                activity.getResources().getText(R.string.share_header)));
    }

    private void writePostcardPNG(Pixmap postcardPixmap) {
        Gdx.app.log("AndroidCaptureIO",
                "writing postcard PNG to " + postcardFilePath.toString());
        PixmapIO.writePNG(postcardFilePath, postcardPixmap);
    }

}
