package nz.co.canadia.poorpeoplepizzaparty;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import java.util.Locale;

import nz.co.canadia.poorpeoplepizzaparty.utils.Assets;
import nz.co.canadia.poorpeoplepizzaparty.utils.Capture;
import nz.co.canadia.poorpeoplepizzaparty.utils.CaptureIO;

public class AndroidCaptureIO implements CaptureIO {

    private AndroidLauncher activity;
    private Pixmap postcardPixmap;
    private FileHandle postcardFilePath;

    AndroidCaptureIO(AndroidLauncher activity) {
        this.activity = activity;
    }

    @Override
    public void savePizza(Pizza pizza, Assets assets, Locale locale) {
        postcardPixmap = Capture.postcardPixmap(pizza, assets);

        postcardFilePath = Gdx.files.local("postcards/" + Capture.fileName(locale));

        writePostcardPNG();

        sharePostcardPNG();

        deletePostcardPNG();
    }

    private void deletePostcardPNG() {
        Gdx.app.log("AndroidCaptureIO",
                "deleting postcard PNG " + postcardFilePath.toString());
        postcardFilePath.delete();
    }

    private void sharePostcardPNG() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
//        shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
//        sendIntent.setType("image/png");
//        activity.startActivity(Intent.createChooser(sendIntent, activity.getResources().getText(R.string.send_to)));
    }

    public void writePostcardPNG() {
        Gdx.app.log("AndroidCaptureIO",
                "writing postcard PNG to " + postcardFilePath.toString());
        PixmapIO.writePNG(postcardFilePath, postcardPixmap);
    }

    void dispose() {
        postcardPixmap.dispose();
    }

}
