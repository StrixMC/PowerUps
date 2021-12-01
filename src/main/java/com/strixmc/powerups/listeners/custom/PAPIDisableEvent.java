package com.strixmc.powerups.listeners.custom;

import com.strixmc.acid.custom_events.BaseEvent;
import com.strixmc.powerups.hooks.PlaceholderAPIHook;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PAPIDisableEvent extends BaseEvent {

    private final PlaceholderAPIHook placeholderAPIHook;

}
