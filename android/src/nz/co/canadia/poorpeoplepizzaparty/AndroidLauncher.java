package nz.co.canadia.poorpeoplepizzaparty;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import nz.co.canadia.poorpeoplepizzaparty.utils.Screenshot;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useGyroscope = false;
		config.useImmersiveMode = false;
		initialize(new PoorPeoplePizzaParty(new AndroidScreenshot(),
				new AndroidUiFont()), config);
	}
}
