package com.strixmc.powerups.hooks.holograms.decentholograms;

import com.strixmc.powerups.PowerUpHandler;
import com.strixmc.powerups.powerup.PowerUp;
import com.strixmc.powerups.powerup.PowerUpRegistry;
import com.strixmc.powerups.services.PluginService;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import eu.decentsoftware.holograms.event.HologramClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DecentHologramsListener implements Listener {

    private final PowerUpHandler powerUpHandler;
    private final PowerUpRegistry powerUpRegistry;

    public DecentHologramsListener(PluginService service) {
        this.powerUpHandler = service.getPowerUpHandler();
        this.powerUpRegistry = service.getPowerUpRegistry();

        Bukkit.getLogger().info("Registered DecentHolograms listener!");
    }

    @EventHandler
    public void onHologramClick(HologramClickEvent event) {
        Player player = event.getPlayer();
        Hologram hologram = event.getHologram();

        String holoName = hologram.getName().split("\\|")[1];

        PowerUp powerUp = powerUpRegistry.getPowerUp(holoName);
        if (powerUp == null) return;

        powerUpHandler.executeActions(player, powerUp);
        hologram.destroy();
    }
}
