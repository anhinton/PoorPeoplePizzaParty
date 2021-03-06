package nz.co.canadia.poorpeoplepizzaparty.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(Constants.HTML_WIDTH,
                        Constants.HTML_HEIGHT);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new PoorPeoplePizzaParty(new HtmlCaptureIO());
        }
}