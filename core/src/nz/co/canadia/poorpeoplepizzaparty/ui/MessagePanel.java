package nz.co.canadia.poorpeoplepizzaparty.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class MessagePanel extends Container {

    private TextButton messageLabel;
    private Skin skin;

    public MessagePanel(Skin skin) {

        this.skin = skin;

        this.setPosition(Constants.BASE_X, Constants.BASE_Y);
        this.setSize(Constants.BASE_WITDH, Constants.BASE_HEIGHT);

        messageLabel = new TextButton("", skin, "default");
    }

    public void showMessage(String s) {
        clearMessage();
        messageLabel = new TextButton(s, skin, "default");
        messageLabel.setSize(Constants.BASE_WITDH, Constants.BUTTON_HEIGHT);
        messageLabel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clearMessage();
            }
        });
        this.prefSize(Constants.BUTTON_WIDTH_FULL, Constants.BUTTON_HEIGHT);

        // TODO: work out what the IDE warning here means, and how to fix it
        this.setActor(messageLabel);

        // TODO: clean up Timer code if not required
//        Timer.schedule(new Timer.Task() {
//            @Override
//            public void run() {
//                clearMessage();
//            }
//        }, 5);

        Gdx.app.log("PizzaScreen", s);
    }

    public void clearMessage() {
        this.clear();
    }
}
