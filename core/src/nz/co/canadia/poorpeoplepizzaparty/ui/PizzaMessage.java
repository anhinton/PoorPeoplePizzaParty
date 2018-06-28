package nz.co.canadia.poorpeoplepizzaparty.ui;

import com.badlogic.gdx.Gdx;
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

        super.setPosition(UiSize.getBaseX(screenHeight),
                UiSize.getBaseY(screenHeight));
        super.setSize(UiSize.getBaseWidth(screenWidth),
                UiSize.getBaseHeight(screenWidth));

        messageLabel = new TextButton("", skin, "default");
    }

    public void clearMessage() {
        super.clear();
    }

    public void showMessage(String s) {
        clearMessage();
        messageLabel = new TextButton(s, skin, "default");
        messageLabel.setSize(UiSize.getBaseWidth(screenWidth),
                UiSize.getButtonHeight(screenHeight));
        messageLabel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clearMessage();
            }
        });
        super.prefSize(UiSize.getButtonWidthFull(screenWidth, screenHeight),
                UiSize.getButtonHeight(screenHeight));

        // TODO: work out what the IDE warning here means, and how to fix it
        super.setActor(messageLabel);
    }
}
