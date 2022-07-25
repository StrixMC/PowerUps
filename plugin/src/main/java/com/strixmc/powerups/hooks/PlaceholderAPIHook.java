package com.strixmc.powerups.hooks;

import com.strixmc.acid.commons.service.Service;
import com.strixmc.powerups.PowerUps;

public class PlaceholderAPIHook implements Service {

    private boolean initialized;

    private PlaceholderAPISupport papiSupport;

    private final PowerUps main;

    public PlaceholderAPIHook(HookManager manager) {
        this.main = manager.getMain();
        this.initialized = false;
        start();
    }

    @Override
    public void start() {
        if (!main.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            main.getLogger().warning("PlaceholderAPI has been NOT found!");
            main.getLogger().warning("PowerUps will NOT support PlaceholderAPI variables!");
            return;
        }

        setInitialized(true);
        main.getLogger().info("PlaceholderAPI has been found, using it.");
        main.getLogger().info("PowerUps now support PlaceholderAPI.");
        this.papiSupport = new PlaceholderAPISupport(main);
        this.papiSupport.register();
    }

    @Override
    public void finish() {
        if (isInitialized()) {
            setInitialized(false);
            this.papiSupport.unregister();
            main.getLogger().warning("PlaceholderAPI has been disabled.");
            main.getLogger().warning("PowerUps will NOT support PlaceholderAPI variables!");
        }
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
