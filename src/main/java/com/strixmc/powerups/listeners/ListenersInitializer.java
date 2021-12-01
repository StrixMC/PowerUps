package com.strixmc.powerups.listeners;

import com.strixmc.acid.commons.service.Service;
import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.services.PluginService;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

public class ListenersInitializer implements Service {

    @Getter
    @Setter
    private boolean initialized;

    private final PowerUps main;

    public ListenersInitializer(PluginService pluginService) {
        this.main = pluginService.getMain();
        start();
    }

    @Override
    public void start() {
        setInitialized(true);

        PluginManager pluginManager = main.getServer().getPluginManager();
        pluginManager.registerEvents(new PowerUpsListener(), main);
    }

    @Override
    public void finish() {
        if (!isInitialized()) return;
        HandlerList.unregisterAll(main);
    }
}
