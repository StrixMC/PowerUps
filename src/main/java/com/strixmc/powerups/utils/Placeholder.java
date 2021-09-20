package com.strixmc.powerups.utils;

import lombok.Getter;

public class Placeholder {

    @Getter
    private final String replaced;
    @Getter
    private final String replacement;

    public Placeholder(final String replaced, final String replacement) {
        this.replaced = replaced;
        this.replacement = replacement;
    }

    public Placeholder(final String replaced, final int amount) {
        this.replaced = replaced;
        this.replacement = String.valueOf(amount);
    }
}
