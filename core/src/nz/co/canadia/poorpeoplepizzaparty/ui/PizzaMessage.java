package nz.co.canadia.poorpeoplepizzaparty.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class PizzaMessage extends Container {

    private final Skin skin;
    private TextButton messageLabel;

    public PizzaMessage(final Skin skin) {

        this.skin = skin;

        super.setPosition(Constants.BASE_X,
                Constants.BASE_Y);
        super.setSize(Constants.BASE_WIDTH,
                Constants.BASE_HEIGHT);

        messageLabel = new TextButton("", skin, "default");
    }

    public void clearMessage() {
        super.clear();
    }

    @SuppressWarnings("unchecked")
    public void showMessage(String s) {
        clearMessage();
        messageLabel = new TextButton(s, skin, "default");
        messageLabel.setSize(Constants.BASE_WIDTH,
                Constants.BUTTON_HEIGHT);
        messageLabel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clearMessage();
            }
        });
        super.prefSize(Constants.BUTTON_WIDTH_FULL,
                Constants.BUTTON_HEIGHT);

        super.setActor(messageLabel);
    }
}
