package nz.co.canadia.poorpeoplepizzaparty.utils;

import com.badlogic.gdx.graphics.Pixmap;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;

public interface CaptureIO {
    void savePizza(Pizza pizza, Assets assets);
}
