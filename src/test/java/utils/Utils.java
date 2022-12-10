package utils;

import java.text.NumberFormat;
import java.util.Locale;

public class Utils {

    public String roundFormatNumberAndConvertToString(double number) {
        double roundValue = Math.round(number);
        NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.ENGLISH);

        return numberFormatter.format(roundValue);
    }
}
