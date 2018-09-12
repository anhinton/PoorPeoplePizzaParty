package nz.co.canadia.poorpeoplepizzaparty;

import android.content.pm.PackageManager;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
    AndroidCaptureIO captureIO;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useGyroscope = false;
		config.useImmersiveMode = false;

		captureIO = new AndroidCaptureIO(this);

		initialize(new PoorPeoplePizzaParty(
				captureIO,
				new AndroidUiFont()), config);
	}

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case AndroidPermissions.REQUEST_SAVE_PIZZA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // do the thing
                    Gdx.app.log("AndroidLauncher",
                            "permission to write files granted");
                    captureIO.writePostcardPNG();
                } else {
                    // permission denied, boo!
                    Gdx.app.log("AndroidLauncher",
                            "permission to write files denied");
                }
            }
        }
    }
}
