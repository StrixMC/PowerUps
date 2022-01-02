package com.strixmc.powerups.hooks;

import com.strixmc.acid.commons.service.Service;
import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.services.PluginService;

public class HookManager implements Service {

    private boolean initialized;

    private PlaceholderAPIHook papiHook;
    private HolographicDisplaysHook holoHook;

    private final PowerUps main;

    public HookManager(PluginService pluginService) {
        this.main = pluginService.getMain();
        this.initialized = false;
        start();
    }

    @Override
    public void start() {
        setInitialized(true);

        this.papiHook = new PlaceholderAPIHook(main);
        this.holoHook = new HolographicDisplaysHook(main);
    }

    @Override
    public void finish() {
        if (!initialized) return;

        this.papiHook.finish();
        this.holoHook.finish();
    }

    public PlaceholderAPIHook getPapiHook() {
        return papiHook;
    }

    public HolographicDisplaysHook getHoloHook() {
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
