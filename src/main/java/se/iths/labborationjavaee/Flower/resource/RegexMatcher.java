package se.iths.labborationjavaee.Flower.resource;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.regex.Pattern;

@ApplicationScoped
public class RegexMatcher {
    public static String numbers = "[1-9]";
    public static String letters = "[a-zA-Z]";

    public static boolean isNumber(String search) {
        return (compileNumber(search) && !compileLetter(search));
    }

    public static boolean isLetter(String search) {
        return (!compileNumber(search) && compileLetter(search));
    }

    public static boolean compileNumber(String search) {
        return Pattern.compile(numbers).matcher(search).find();
    }

    public static boolean compileLetter(String search) {
        return Pattern.compile(letters).matcher(search).find();
    }
}
