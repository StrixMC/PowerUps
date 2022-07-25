package com.strixmc.powerups.hooks.holograms.decentholograms;

import com.strixmc.powerups.PowerUpHandler;
import com.strixmc.powerups.hologram.HologramHandler;
import com.strixmc.powerups.powerup.PowerUp;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DecentHologramsHandler implements HologramHandler {

    private final Map<Long, Hologram> cache;

    public DecentHologramsHandler() {
        this.cache = new HashMap<>();
    }

    @Override
    public boolean createHologram(PowerUpHandler powerUpHandler, PowerUp powerUp, Location location) {
        long holoCurrentTime = System.currentTimeMillis();
        if (cache.containsKey(holoCurrentTime)) {
            return false;
        }

        Hologram hologram = DHAPI.createHologram(holoCurrentTime + "|" + powerUp.getID(), location, powerUp.getHologram());
        hologram.hideAll();

        DHAPI.addHologramLine(hologram, "#ICON: " + powerUp.getMaterial().toUpperCase(Locale.ROOT) + ":" + powerUp.getData());

        hologram.updateAll();
        hologram.showAll();

        cache.put(holoCurrentTime, hologram);
        return true;
    }

    @Override
    public boolean removeHologram(Block block) {
        return false;
    }

    @Override
    public void removeAllHolograms() {
        if (cache.values().isEmpty()) {
            return;
        }

        cache.forEach(((aLong, hologram) -> {
            hologram.destroy();
            cache.remove(aLong);
        }));
    }

}
