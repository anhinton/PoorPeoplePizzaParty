package nz.co.canadia.poorpeoplepizzaparty.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new PoorPeoplePizzaParty(), config);
	}
}
