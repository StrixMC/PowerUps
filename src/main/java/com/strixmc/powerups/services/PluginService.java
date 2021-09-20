package com.strixmc.powerups.services;

import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.cache.CacheManager;
import com.strixmc.powerups.commands.PowerUpCommandExecutor;
import com.strixmc.powerups.hooks.HookManager;
import com.strixmc.powerups.listeners.ListenersInitializer;
import com.strixmc.powerups.utils.FileCreator;
import com.strixmc.powerups.utils.Messages;
import com.strixmc.powerups.utils.commons.service.Initializer;
import lombok.Getter;
import lombok.Setter;

import static org.bukkit.Bukkit.getServer;

public class PluginService implements Initializer {

    @Getter
    private HookManager hookManager;
    @Getter
    private CacheManager cacheManager;
    private ListenersInitializer listenersInitializer;
    @Getter
    private FileCreator lang;
    @Getter
    private FileCreator config;
    private HologramsService hologramsService;

    @Getter
    @Setter
    private boolean initialized;

    private final PowerUps main;

    public PluginService(PowerUps main) {
        this.main = main;
        this.initialized = false;
        start();
    }

    @Override
    public void start() {
        setInitialized(true);
        this.lang = new FileCreator(this.main, "lang");
        this.config = new FileCreator(this.main, "config");
        Messages.setLang(this.lang);

        this.hookManager = new HookManager(this.main);
        if (!this.hookManager.getHoloHook().isInitialized()) {
            setInitialized(false);
            return;
        }

        this.cacheManager = new CacheManager();
        this.hologramsService = new HologramsService(this.main, this.cacheManager);
        this.listenersInitializer = new ListenersInitializer(this.main);

        new PowerUpCommandExecutor(cacheManager).registerMainCommand(this.main, "powerups");
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
