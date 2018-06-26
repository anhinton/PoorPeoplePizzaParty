package nz.co.canadia.poorpeoplepizzaparty.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Constants.GAME_NAME;
		config.width = Constants.DESKTOP_WIDTH;
		config.height = Constants.DESKTOP_HEIGHT;
		config.resizable = false;
		new LwjglApplication(
		        new PoorPeoplePizzaParty(new DesktopScreenshot(),
                        new DesktopUiFont()),
				config);
	}
}
