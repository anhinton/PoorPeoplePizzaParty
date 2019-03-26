package nz.co.canadia.poorpeoplepizzaparty;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import java.io.File;

import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.Postcard;
import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;

public class AndroidCaptureIO implements CaptureIO {

    private AndroidLauncher activity;
    private Pixmap postcardPixmap;
    private FileHandle postcardFilePath;

    AndroidCaptureIO(AndroidLauncher activity) {
        this.activity = activity;
        this.postcardPixmap = new Pixmap(0, 0, Pixmap.Format.RGBA8888);
    }

    @Override
    public void savePostcardImage(Pizza pizza, Assets assets) {
        postcardPixmap = Postcard.postcardPixmap(pizza, assets);

        postcardFilePath = Gdx.files.local("postcards/" + Postcard.fileName());

        writePostcardPNG();

        sharePostcardPNG();
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

    private void writePostcardPNG() {
        Gdx.app.log("AndroidCaptureIO",
                "writing postcard PNG to " + postcardFilePath.toString());
        PixmapIO.writePNG(postcardFilePath, postcardPixmap);
    }

    public void dispose() {
        postcardPixmap.dispose();
    }

}
