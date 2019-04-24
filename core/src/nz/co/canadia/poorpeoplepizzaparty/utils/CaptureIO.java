package nz.co.canadia.poorpeoplepizzaparty.utils;

import java.util.Locale;

import nz.co.canadia.poorpeoplepizzaparty.Pizza;

public interface CaptureIO {
    void savePostcardImage(Pizza pizza, Assets assets);

    void dispose();
}
