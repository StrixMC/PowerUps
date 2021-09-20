package com.strixmc.powerups.utils;

import java.util.List;

public class StringUtils {

    public static String replace(String string, final Placeholder... placeholders) {
        for (final Placeholder placeholder : placeholders) {
            string = string.replace(placeholder.getReplaced(), placeholder.getReplacement());
        }

        return string;
    }

    public static List<String> replace(final List<String> strings, final Placeholder... placeholders) {
        for (int i = 0; i < strings.size(); i++) {
            strings.set(i, replace(strings.get(i), placeholders));
        }

        return strings;
    }

}
