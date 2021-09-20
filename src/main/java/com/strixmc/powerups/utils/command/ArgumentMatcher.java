package com.strixmc.powerups.utils.command;

import java.util.List;

public interface ArgumentMatcher {

    /**
     * Filters tabCompletions based on argument string.
     *
     * @param tabCompletions The tabCompletions to filter.
     * @param argument       The argument string.
     * @return The filtered tabCompletions.
     */
    public List<String> filter(List<String> tabCompletions, String argument);
}