package com.strixmc.powerups.utils.command.argumentmatcher;


import com.strixmc.powerups.utils.command.ArgumentMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Filters to leave all the strings that start with the argument string.
 * Example: kill, kick, looking | ki -> kick, kill
 */
public class StartingWithStringArgumentMatcher implements ArgumentMatcher {

    @Override
    public List<String> filter(List<String> tabCompletions, String argument) {
        List<String> result = new ArrayList<>();

        copyPartialMatches(argument, tabCompletions, result);

        return result;
    }

    private <T extends Collection<? super String>> T copyPartialMatches(String token, Iterable<String> originals, T collection) throws UnsupportedOperationException, IllegalArgumentException {
        notNull(token, "Search token cannot be null");
        notNull(collection, "Collection cannot be null");
        notNull(originals, "Originals cannot be null");
        Iterator var3 = originals.iterator();

        while(var3.hasNext()) {
            String string = (String)var3.next();
            if (startsWithIgnoreCase(string, token)) {
                collection.add(string);
            }
        }

        return collection;
    }

    private boolean startsWithIgnoreCase(String string, String prefix) throws IllegalArgumentException, NullPointerException {
        notNull(string, "Cannot check a null string for a match");
        return string.length() < prefix.length() ? false : string.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    private void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}