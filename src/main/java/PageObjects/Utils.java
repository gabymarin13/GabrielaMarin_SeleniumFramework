package PageObjects;

import java.util.Random;

public class Utils {
    public static String generadorDeEmailRandom() {
        int inferior = 97; // inicio mínusculas 'a'
        int superior = 122; // inicio mayusculas 'z'
        int lenght = 10;
        Random random = new Random();

        String generatedString = random.ints(inferior, superior + 1)
                .limit(lenght)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString + "@gmail.com";
    }
}
