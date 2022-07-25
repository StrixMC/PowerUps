package com.strixmc.powerups.listeners;

import com.strixmc.acid.commons.service.Service;
import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.hooks.HookManager;
import com.strixmc.powerups.hooks.holograms.decentholograms.DecentHologramsListener;
import com.strixmc.powerups.services.PluginService;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

public class ListenersInitializer implements Service {

    private boolean initialized;

    private final PowerUps main;
    private final HookManager hookManager;

    private final PluginService pluginService;

    public ListenersInitializer(PluginService pluginService) {
        this.pluginService = pluginService;
        this.main = pluginService.getMain();
        this.hookManager = pluginService.getHookManager();
        start();
    }

    @Override
    public void start() {
        setInitialized(true);

        PluginManager pluginManager = main.getServer().getPluginManager();
        pluginManager.registerEvents(new PowerUpsListener(), main);

        if (hookManager.isInitialized() && hookManager.getHoloHook().isInitialized()) {
            if (pluginManager.isPluginEnabled("DecentHolograms")) {
                pluginManager.registerEvents(new DecentHologramsListener(pluginService), main);
            }
        }
    }

    @Override
    public void finish() {
        if (!isInitialized()) return;
        HandlerList.unregisterAll(main);
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
