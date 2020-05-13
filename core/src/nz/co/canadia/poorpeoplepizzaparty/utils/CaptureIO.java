package nz.co.canadia.poorpeoplepizzaparty.utils;

import nz.co.canadia.poorpeoplepizzaparty.Postcard;

public interface CaptureIO {
    void savePostcardImage(Postcard postcard);

    void savePostcardImage(Postcard postcard, String shareText, String shareHeader);

    void savePizzaXml(String pizzaXml);

    String loadPizzaXml();
}
