package com.strixmc.powerups.services;

import com.strixmc.acid.commons.service.Service;
import com.strixmc.powerups.DefaultPowerUpHandler;
import com.strixmc.powerups.PowerUpHandler;
import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.actiontags.ActionManager;
import com.strixmc.powerups.commands.PowerUpCommandExecutor;
import com.strixmc.powerups.hooks.HookManager;
import com.strixmc.powerups.listeners.ListenersInitializer;
import com.strixmc.powerups.powerup.DefaultPowerUpRegistry;
import com.strixmc.powerups.powerup.PowerUpRegistry;

public class PluginService implements Service {

    private boolean initialized;

    private HookManager hookManager;
    private ActionManager actionManager;
    private PowerUpHandler powerUpHandler;
    private PowerUpService powerUpService;
    private PowerUpRegistry powerUpRegistry;
    private HologramsService hologramsService;
    private ListenersInitializer listenersInitializer;

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

        this.powerUpRegistry = new DefaultPowerUpRegistry();
        this.actionManager = new ActionManager(this);
        this.powerUpService = new PowerUpService(this);
        this.hologramsService = new HologramsService(this);
        this.powerUpHandler = new DefaultPowerUpHandler(this);
        this.listenersInitializer = new ListenersInitializer(this);

        new PowerUpCommandExecutor(this).registerMainCommand(this.main, "powerups");
    }

    @Override
    public void finish() {
        if (!isInitialized()) return;

        shutdownService(this.hookManager);
        shutdownService(this.powerUpService);
        shutdownService(this.hologramsService);
        shutdownService(this.listenersInitializer);
    }

    private void shutdownService(Service service) {
        if (service != null) service.finish();
    }

    public PowerUps getMain() {
        return main;
    }

    public HookManager getHookManager() {
        return hookManager;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public PowerUpHandler getPowerUpHandler() {
        return powerUpHandler;
    }

    public PowerUpService getPowerUpService() {
        return powerUpService;
    }

    public PowerUpRegistry getPowerUpRegistry() {
        return powerUpRegistry;
    }

    public HologramsService getHologramsService() {
        return hologramsService;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
}
