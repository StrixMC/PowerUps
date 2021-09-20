package com.strixmc.powerups.hooks;

import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.utils.commons.service.Initializer;
import lombok.Getter;
import lombok.Setter;

import java.util.logging.Level;

public class HolographicDisplaysHook implements Initializer {

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
            main.getLogger().log(Level.SEVERE, "PowerUps will cannot work without HolographicDisplays!");
            return;
        }

        setInitialized(true);
        main.getLogger().info("HolographicDisplays has been found, using it.");
    }
}
