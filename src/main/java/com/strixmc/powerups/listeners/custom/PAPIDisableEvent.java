package com.strixmc.powerups.listeners.custom;

import com.strixmc.powerups.hooks.PlaceholderAPIHook;
import com.strixmc.powerups.utils.BaseEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PAPIDisableEvent extends BaseEvent {

    private final PlaceholderAPIHook placeholderAPIHook;

}
