package nz.co.canadia.poorpeoplepizzaparty.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

import nz.co.canadia.poorpeoplepizzaparty.Constants;
import nz.co.canadia.poorpeoplepizzaparty.PoorPeoplePizzaParty;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(Constants.APP_WIDTH, Constants.APP_HEIGHT);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new PoorPeoplePizzaParty();
        }
}