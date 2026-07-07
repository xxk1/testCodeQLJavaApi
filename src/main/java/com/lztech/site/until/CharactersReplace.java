package com.lztech.site.until;


public class CharactersReplace {
    private static final String[] CHARACTER_ATTR = {"%", "_", "'"};

    public static String replaceCharacters(String s) {
        if (s != null) {
            for (String character : CHARACTER_ATTR) {
                if (s.contains(character)) {
                    s = s.replace(character, "\\" + character);
                }
            }
        }
        return s;
    }
}
