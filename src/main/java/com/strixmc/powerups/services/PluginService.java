package com.strixmc.powerups.services;

import com.strixmc.acid.commons.service.Service;
import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.actiontags.ActionManager;
import com.strixmc.powerups.cache.CacheManager;
import com.strixmc.powerups.commands.PowerUpCommandExecutor;
import com.strixmc.powerups.hooks.HookManager;
import com.strixmc.powerups.listeners.ListenersInitializer;
import com.strixmc.powerups.powerup.PowerUpManager;
import lombok.Getter;
import lombok.Setter;

public class PluginService implements Service {

    @Getter
    @Setter
    private boolean initialized;

    @Getter
    private HookManager hookManager;
    @Getter
    private CacheManager cacheManager;
    @Getter
    private PowerUpManager powerUpManager;
    @Getter
    private ActionManager actionManager;
    private HologramsService hologramsService;
    private ListenersInitializer listenersInitializer;

    @Getter
    private final PowerUps main;

    public PluginService(PowerUps main) {
        this.main = main;
        this.initialized = false;
        start();
    }

    @Override
    public void start() {
        setInitialized(true);

        this.hookManager = new HookManager(this);
        if (!this.hookManager.getHoloHook().isInitialized()) {
            setInitialized(false);
            return;
        }

        this.cacheManager = new CacheManager();
        this.actionManager = new ActionManager(this);
        this.powerUpManager = new PowerUpManager(this);
        this.hologramsService = new HologramsService(this);
        this.listenersInitializer = new ListenersInitializer(this);

        new PowerUpCommandExecutor(this).registerMainCommand(this.main, "powerups");
    }

    @Override
    public void finish() {
        if (!isInitialized()) return;

        this.hookManager.finish();
        this.cacheManager.finish();
        this.hologramsService.finish();
        this.listenersInitializer.finish();
    }
}
