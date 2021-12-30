package nz.co.canadia.poorpeoplepizzaparty.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle(Constants.GAME_NAME);
		config.setWindowedMode(Constants.DESKTOP_WIDTH, Constants.DESKTOP_HEIGHT);
		config.setResizable(false);
		config.setWindowIcon(
				"icon_128.png",
				"icon_32.png",
				"icon_16.png"
		);

		new Lwjgl3Application(new PoorPeoplePizzaParty(new DesktopCaptureIO()), config);
	}
}
