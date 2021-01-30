package contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorOfPhoneNumber {

    public static Pattern patternForFirstElementTable = Pattern.compile("\\+?(([0-9a-zA-Z]+)|(\\([0-9a-zA-Z]+\\)))");
    public static Pattern patternForSecondElementTable = Pattern.compile("([0-9a-zA-Z]+){2,}|(\\([0-9a-zA-Z]{2,}+\\))");
    public static Pattern patternForNextElementTable = Pattern.compile("[0-9a-zA-Z]{2,}");

    public static boolean isNumberOk(String phoneNumber) {
        String[] table = phoneNumber.split("[\\s-]");
        if (table.length == 1) {
            Matcher matcher = patternForFirstElementTable.matcher(table[0]);
            return matcher.matches();
        } else {
            if (!isNumberOfParenthesesOk(table)) {
                return false;
            }
            Matcher matcher0 = patternForFirstElementTable.matcher(table[0]);
            if (!matcher0.matches()) {
                return false;
            }
            Matcher matcher1 = patternForSecondElementTable.matcher(table[1]);
            if (!matcher1.matches()) {
                return false;
            }
            if (table.length > 2) {
                for (int i = 2; i < table.length; i++) {
                    Matcher matcherNext = patternForNextElementTable.matcher(table[i]);
                    if (!matcherNext.matches()) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static boolean isNumberOfParenthesesOk(String[] table) {
        String regex0 = "\\+?\\([0-9a-zA-Z]+\\)";
        String regex1 = "\\([0-9a-zA-Z]{2,}\\)";
        return (table[0].matches(regex0) ^ table[1].matches(regex1))
                || !(table[0].matches(regex0) && table[1].matches(regex1));
    }
}
