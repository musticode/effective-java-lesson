package org.example.item6;

import java.util.regex.Pattern;

public class RegExample {

    private static final Pattern ROMAN = Pattern.compile("asdfff sdadsa");
    public static boolean isRomanNumeral(String value){
        return value.matches("asdasd asd asdsa asdsa");
    }

    static boolean isNonNumeral(String value){
        return ROMAN.matcher(value).matches();
    }

}
