package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * This class contains the pizza party photo displayed in the background of ServerWorkersScreen.
 * The image has two states: colour, initially, and then greyscale. switchState() to
 * change.
 */

public class PartyScene {
    private Sprite normalSprite;
    private Sprite inverseSprite;
    private Constants.PartySprite currentState;

    public PartyScene(TextureRegion normalTexture, TextureRegion inverseTexture) {
        normalSprite = new Sprite(normalTexture);
        normalSprite.setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        normalSprite.setPosition(0, 0);
        inverseSprite = new Sprite(inverseTexture);
        inverseSprite.setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        inverseSprite.setPosition(0, 0);
        currentState = Constants.PartySprite.NORMAL;
    }

    private void setCurrentState(Constants.PartySprite colour) {
        currentState = colour;
    }

    public void switchState() {
        switch (currentState) {
            case NORMAL:
                setCurrentState(Constants.PartySprite.INVERSE);
                break;
            case INVERSE:
                setCurrentState(Constants.PartySprite.NORMAL);
                break;
        }
    }

    public void draw (SpriteBatch batch) {
        switch (currentState) {
            case NORMAL:
                normalSprite.draw(batch);
                break;
            case INVERSE:
                inverseSprite.draw(batch);
                break;
        }
    }
}
