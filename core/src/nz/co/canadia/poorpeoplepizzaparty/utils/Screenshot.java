package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.graphics.Pixmap;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;

public interface Screenshot {
    Pixmap capturePizza(Pizza pizza);

    void saveCapture(Pixmap pixmap);
}
