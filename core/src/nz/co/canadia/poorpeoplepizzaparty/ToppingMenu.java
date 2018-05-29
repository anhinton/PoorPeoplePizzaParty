package nz.co.canadia.poorpeoplepizzaparty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import nz.co.canadia.poorpeoplepizzaparty.screens.PizzaScreen;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;

/**
 * Creates the toppings menu UI for selecting a topping on the PizzaScreen
 */

public class ToppingMenu {

    private Table table;
    private Skin skin;

    public ToppingMenu(Stage stage, final PizzaScreen pizzaScreen) {

        table = new Table();
        table.setFillParent(true);
        table.top().right().pad(Constants.MENU_PADDING);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        TextButton sauceButton = new TextButton(
                "Sauce", skin, "toggle");
        table.add(sauceButton).space(Constants.MENU_PADDING)
                .prefSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        TextButton cheeseButton = new TextButton(
                "Cheese", skin, "toggle");
        table.add(cheeseButton).space(Constants.MENU_PADDING)
                .prefSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        table.row();

        TextButton baconButton = new TextButton(
                "Bacon", skin, "toggle");
        baconButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pizzaScreen.toggleSelectedTopping(Constants.ToppingName.BACON);
            }
        });
        table.add(baconButton).space(Constants.MENU_PADDING)
                .prefSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        TextButton sausageButton = new TextButton(
                "Sausage", skin, "toggle");
        table.add(sausageButton).space(Constants.MENU_PADDING)
                .prefSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);

        ButtonGroup<TextButton> toppingsGroup = new ButtonGroup<TextButton>();
        toppingsGroup.setMinCheckCount(0);
        toppingsGroup.setMaxCheckCount(1);
        toppingsGroup.add(sauceButton, cheeseButton, baconButton,
                sausageButton);

        // DEBUG UI
        table.setDebug(true);
    }

    public void dispose() {
        skin.dispose();
    }
}
