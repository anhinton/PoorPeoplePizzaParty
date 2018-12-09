package nz.co.canadia.poorpeoplepizzaparty.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.I18NBundle;

import nz.co.canadia.poorpeoplepizzaparty.screens.CookScreen;
import nz.co.canadia.poorpeoplepizzaparty.utils.Constants;
import nz.co.canadia.poorpeoplepizzaparty.utils.UiSize;

public class CookUi extends Table {

    private final Skin skin;
    private final AssetManager assets;
    private final I18NBundle bundle;
    private ProgressBar progressBar;
    private Label remainingLabel;
    private final CookScreen cookScreen;
    private int screenWidth;
    private int screenHeight;
    private float timeElapsed;
    private int padding;
    private boolean cooking;

    public CookUi(boolean countdown, int screenWidth, int screenHeight,
                  final CookScreen cookScreen, Skin skin, AssetManager assets,
                  I18NBundle bundle) {

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.cookScreen = cookScreen;
        this.skin = skin;
        this.assets = assets;
        this.bundle = bundle;

        padding = UiSize.getPadding(screenHeight);
        cooking = false;

        super.setFillParent(true);

        if (countdown) {
            showTimer();
        } else {
            showDecision();
        }
    }

    private void showTimer() {

        super.clear();
        super.pad(padding);

        cooking = true;
        timeElapsed = 0;

        ProgressBar.ProgressBarStyle progressBarStyle =
                skin.get("default-horizontal",
                        ProgressBar.ProgressBarStyle.class);
        progressBar = new ProgressBar(0, Constants.COOK_TIME_TOTAL,
                Constants.COOK_TIME_INCREMENT,
                false, progressBarStyle);
        progressBar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (progressBar.getValue() < progressBar.getMaxValue()) {
                    float timeRemaining = progressBar.getMaxValue()
                            - progressBar.getValue();
                    remainingLabel.setText(Integer.toString(MathUtils.ceil(timeRemaining)));
                } else {
                    cooking = false;
                    showDecision();
                }
            }
        });

        float timeRemaining = progressBar.getMaxValue()
                - progressBar.getValue();
        remainingLabel =
                new Label(Integer.toString(MathUtils.ceil(timeRemaining)), skin,
                        "default");

        Label timerLabel = new Label(bundle.get("timerLabel"), skin,
                "default");
        super.add(timerLabel).space(padding);
        super.row();

        super.add(progressBar).space(padding).center().prefWidth(400);
        super.row();

        super.add(remainingLabel).space(padding);
    }

    private void showDecision() {

        super.clear();
        super.pad(padding);

        Image headerImage =
                new Image(assets.get("graphics/headers/cookScreenPizza.png",
                        Texture.class));
        super.add(headerImage)
                .colspan(2)
                .prefSize(
                        UiSize.getImageWidth(headerImage.getPrefWidth(),
                                screenWidth),
                        UiSize.getImageHeight(headerImage.getPrefHeight(),
                                screenHeight))
                .space(padding);
        super.row();

        Label serveLabel = new Label(bundle.get("serveLabel"), skin,
                "default");
        super.add(serveLabel).space(padding).colspan(2);
        super.row();

        TextButton bossButton = new TextButton(bundle.get("serveBossButton"),
                skin, "default");
        bossButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                cookScreen.serve(Constants.ServeOption.BOSS);
                Gdx.app.log("CookUi", "BOSSES selected");
            }
        });
        super.add(bossButton)
                .prefSize(UiSize.getButtonWidthFull(screenWidth, screenHeight),
                        UiSize.getButtonHeight(screenHeight))
                .space(padding);

        TextButton workersButton = new TextButton(bundle.get("serveworkersButton"),
                skin, "default");
        workersButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: send to WorkerScreen
                Gdx.app.log("CookUi", "WORKERS selected");
            }
        });
        super.add(workersButton)
                .prefSize(UiSize.getButtonWidthFull(screenWidth, screenHeight),
                        UiSize.getButtonHeight(screenHeight))
                .space(padding);
    }

    public void update(float delta) {
        if (cooking) {
            timeElapsed += delta;
            progressBar.setValue(timeElapsed);
        }
    }
}