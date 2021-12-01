package com.strixmc.powerups.hooks;

import com.strixmc.acid.commons.service.Service;
import com.strixmc.powerups.PowerUps;
import lombok.Getter;
import lombok.Setter;

import java.util.logging.Level;

public class HolographicDisplaysHook implements Service {

    @Getter
    @Setter
    private boolean initialized;

    private final PowerUps main;

    public HolographicDisplaysHook(PowerUps main) {
        this.main = main;
        start();
    }

    @Override
    public void start() {
        if (!main.getServer().getPluginManager().isPluginEnabled("HolographicDisplays")) {
            main.getLogger().warning("HolographicDisplays has been NOT found!");
            main.getLogger().log(Level.SEVERE, "PowerUps will NOT work without HolographicDisplays!");
            main.getLogger().log(Level.SEVERE, "https://dev.bukkit.org/projects/holographic-displays");
            return;
        }

        setInitialized(true);
        main.getLogger().info("HolographicDisplays has been found, using it.");
    }
}
