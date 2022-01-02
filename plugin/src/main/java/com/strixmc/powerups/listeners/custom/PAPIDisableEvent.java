package com.strixmc.powerups.listeners.custom;

import com.strixmc.acid.custom_events.BaseEvent;
import com.strixmc.powerups.hooks.PlaceholderAPIHook;

public class PAPIDisableEvent extends BaseEvent {

    private final PlaceholderAPIHook placeholderAPIHook;

    public PAPIDisableEvent(PlaceholderAPIHook placeholderAPIHook) {
        this.placeholderAPIHook = placeholderAPIHook;
    }

    public PlaceholderAPIHook getPlaceholderAPIHook() {
        return placeholderAPIHook;
    }
}
