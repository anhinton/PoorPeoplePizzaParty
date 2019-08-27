package nz.co.canadia.poorpeoplepizzaparty.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiSize;

public class PizzaMessage extends Container {

    private final Skin skin;
    private TextButton messageLabel;

    public PizzaMessage(final Skin skin) {

        this.skin = skin;

        super.setPosition(UiSize.getBaseX(),
                UiSize.getBaseY());
        super.setSize(UiSize.getBaseWidth(),
                UiSize.getBaseHeight());

        messageLabel = new TextButton("", skin, "default");
    }

    public void clearMessage() {
        super.clear();
    }

    public void showMessage(String s) {
        clearMessage();
        messageLabel = new TextButton(s, skin, "default");
        messageLabel.setSize(UiSize.getBaseWidth(),
                Constants.BUTTON_HEIGHT);
        messageLabel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clearMessage();
            }
        });
        super.prefSize(Constants.BUTTON_WIDTH_FULL,
                Constants.BUTTON_HEIGHT);

        // TODO: work out what the IDE warning here means, and how to fix it
        super.setActor(messageLabel);
    }
}
