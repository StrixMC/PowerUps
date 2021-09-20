package com.strixmc.powerups.tasks;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.utils.commons.cache.Cache;
import org.bukkit.scheduler.BukkitRunnable;

public class ActiveHologramsTask extends BukkitRunnable {

    private final PowerUps main;
    private final Cache<Long, Hologram> hologramsCache;

    public ActiveHologramsTask(PowerUps main, Cache<Long, Hologram> hologramsCache) {
        this.main = main;
        this.hologramsCache = hologramsCache;
    }

    @Override
    public void run() {
        if (hologramsCache.get().isEmpty()) return;
        final int maxLife = main.getPluginService().getConfig().getInt("LIFE_TIME");

        hologramsCache.get().forEach((savedTime, hologram) -> {
            if ((System.currentTimeMillis() - savedTime) / 1000L >= maxLife) {
                if (!hologram.isDeleted()) {
                    hologram.delete();
                }
                hologramsCache.remove(savedTime);
            }
        });
    }
}
