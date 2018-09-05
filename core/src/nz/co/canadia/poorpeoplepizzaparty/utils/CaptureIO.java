package nz.co.canadia.poorpeoplepizzaparty.utils;

import java.util.Locale;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;

public interface CaptureIO {
    void savePizza(Pizza pizza, Assets assets, Locale locale);
}
