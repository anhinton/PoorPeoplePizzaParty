package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

public class LunchPhoto {
    private Sprite colourSprite;
    private Sprite greySprite;
    private Constants.PartySprite currentColour;

    public LunchPhoto(Texture colourTexture, Texture greyTexture) {
        colourSprite = new Sprite(colourTexture);
        colourSprite.setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        colourSprite.setPosition(0, 0);
        greySprite = new Sprite(greyTexture);
        greySprite.setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        greySprite.setPosition(0, 0);
        currentColour = Constants.PartySprite.COLOUR;
    }

    private void setCurrentColour(Constants.PartySprite colour) {
        currentColour = colour;
    }

    public void switchColour() {
        switch (currentColour) {
            case COLOUR:
                setCurrentColour(Constants.PartySprite.GREY);
                break;
            case GREY:
                setCurrentColour(Constants.PartySprite.COLOUR);
                break;
        }
    }

    public void draw (SpriteBatch batch) {
        switch (currentColour) {
            case COLOUR:
                colourSprite.draw(batch);
                break;
            case GREY:
                greySprite.draw(batch);
                break;
        }
    }
}
