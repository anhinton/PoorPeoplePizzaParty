 package nz.co.canadia.poorpeoplepizzaparty;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Environment;
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

     private Activity activity;

     AndroidCaptureIO(Activity activity) {
         this.activity = activity;
     }

     @Override
    public void savePizza(Pizza pizza, Assets assets, Locale locale) {
         Pixmap postcardPixmap = Capture.postcardPixmap(pizza, assets);

         FileHandle filePath =
                 Gdx.files.external(Environment.DIRECTORY_PICTURES + "/"
                         + Capture.fileName(locale));

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Gdx.app.log("AndroidCaptureIO", "no permission to save files");
        } else {
            PixmapIO.writePNG(filePath, postcardPixmap);
            postcardPixmap.dispose();
        }
    }
 }
