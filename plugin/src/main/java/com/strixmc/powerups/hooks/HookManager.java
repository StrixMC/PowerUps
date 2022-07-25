package com.strixmc.powerups.hooks;

import com.strixmc.acid.commons.service.Service;
import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.services.PluginService;

public class HookManager implements Service {

    private boolean initialized;

    private HologramHook holoHook;
    private PlaceholderAPIHook papiHook;

    private final PowerUps main;
    private final PluginService pluginService;

    public HookManager(PluginService pluginService) {
        this.pluginService = pluginService;
        this.main = pluginService.getMain();
        this.initialized = false;
        start();
    }

    @Override
    public void start() {
        this.holoHook = new HologramHook(this);
        this.papiHook = new PlaceholderAPIHook(this);

        setInitialized(true);
    }

    @Override
    public void finish() {
        if (!initialized) return;

        this.papiHook.finish();
        this.holoHook.finish();
    }

    public PluginService getPluginService() {
        return pluginService;
    }

    public PlaceholderAPIHook getPapiHook() {
        return papiHook;
    }

    public HologramHook getHoloHook() {
        return holoHook;
    }

    public PowerUps getMain() {
        return main;
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
