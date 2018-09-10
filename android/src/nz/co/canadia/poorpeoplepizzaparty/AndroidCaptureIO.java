 package nz.co.canadia.poorpeoplepizzaparty;

import android.Manifest;
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

         postcardFilePath =
                 Gdx.files.external(Environment.DIRECTORY_PICTURES + "/"
                         + Capture.fileName(locale));

         if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                 == PackageManager.PERMISSION_GRANTED) {
             Gdx.app.log("AndroidCaptureIO",  "permission to save files granted");
             writePostcardPNG();
         } else {
             Gdx.app.log("AndroidCaptureIO", "no permission to save files");
             requestSavePizza();
         }
    }

     private void requestSavePizza() {
         ActivityCompat.requestPermissions(activity,
                 new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                 AndroidPermissions.REQUEST_SAVE_PIZZA);
     }

     public void writePostcardPNG() {
         Gdx.app.log("AndroidCaptureIO",
                 "writing postcard PNG to EXTERNAL_FILES");
         PixmapIO.writePNG(postcardFilePath, postcardPixmap);
     }

     void dispose() {
         postcardPixmap.dispose();
     }

 }
