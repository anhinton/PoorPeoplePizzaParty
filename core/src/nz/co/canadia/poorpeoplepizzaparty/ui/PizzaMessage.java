package nz.co.canadia.poorpeoplepizzaparty.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import nz.co.canadia.poorpeoplepizzaparty.utils.UiSize;

public class PizzaMessage extends Container {

    private final Skin skin;
    private TextButton messageLabel;
    private int screenWidth;
    private int screenHeight;

    public PizzaMessage(int screenWidth, int screenHeight, final Skin skin) {

        this.skin = skin;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

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
                UiSize.getButtonHeight());
        messageLabel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clearMessage();
            }
        });
        super.prefSize(UiSize.getButtonWidthFull(),
                UiSize.getButtonHeight());

        // TODO: work out what the IDE warning here means, and how to fix it
        super.setActor(messageLabel);
    }
}
