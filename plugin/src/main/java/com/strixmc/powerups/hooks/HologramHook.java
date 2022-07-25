package com.strixmc.powerups.hooks;

import com.strixmc.acid.commons.service.Service;
import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.hologram.HologramHandler;
import com.strixmc.powerups.hooks.holograms.decentholograms.DecentHologramsHandler;
import com.strixmc.powerups.hooks.holograms.holographicdisplays.HolographicDisplaysHandler;

import java.util.logging.Level;

public class HologramHook implements Service {

    private boolean initialized;

    private final PowerUps main;
    private HologramHandler handler;

    public HologramHook(HookManager hookManager) {
        this.main = hookManager.getMain();
        start();
    }

    @Override
    public void start() {
        if (isPluginEnabled("HolographicDisplays")) {
            this.handler = new HolographicDisplaysHandler(main);
            main.getLogger().log(Level.INFO, "HolographicDisplays has been found, using it.");
            main.getLogger().log(Level.INFO, "PowerUps is now using HolographicDisplays.");
        } else if (isPluginEnabled("DecentHolograms")) {
            this.handler = new DecentHologramsHandler();
            main.getLogger().log(Level.INFO, "DecentHolograms has been found, using it.");
            main.getLogger().log(Level.INFO, "PowerUps is now using DecentHolograms.");
        } else {
            main.getLogger().warning("Neither of HolographicDisplays or DecentHolograms has been NOT found!");
            main.getLogger().log(Level.SEVERE, "PowerUps will NOT work without ONE of them!");
            main.getLogger().log(Level.SEVERE, "HolographicDisplays: https://dev.bukkit.org/projects/holographic-displays");
            main.getLogger().log(Level.SEVERE, "DecentHolograms: https://www.spigotmc.org/resources/96927/");
            return;
        }
        setInitialized(true);
    }

    private boolean isPluginEnabled(String plugin) {
        return main.getServer().getPluginManager().isPluginEnabled(plugin);
    }

    public HologramHandler getHandler() {
        return handler;
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
