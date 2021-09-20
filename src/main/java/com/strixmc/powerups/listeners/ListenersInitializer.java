package com.strixmc.powerups.listeners;

import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.utils.commons.service.Initializer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

public class ListenersInitializer implements Initializer {

    @Getter
    @Setter
    private boolean initialized;

    private final PowerUps main;

    public ListenersInitializer(PowerUps main) {
        this.main = main;
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
