package com.strixmc.powerups.services;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.strixmc.acid.commons.cache.Cache;
import com.strixmc.acid.commons.service.Service;
import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.tasks.ActiveHologramsTask;
import lombok.Getter;
import lombok.Setter;

public class HologramsService implements Service {

    @Getter
    @Setter
    private boolean initialized;

    private final PowerUps main;
    private ActiveHologramsTask task;
    private final Cache<Long, Hologram> hologramCache;

    public HologramsService(PluginService pluginService) {
        this.main = pluginService.getMain();
        this.hologramCache = pluginService.getCacheManager().getHologramCache();
        start();
    }

    @Override
    public void start() {
        setInitialized(true);
        this.task = new ActiveHologramsTask(main, hologramCache);
        this.task.runTaskTimer(this.main, 20L, 20L);
    }

    @Override
    public void finish() {
        if (!isInitialized()) return;

        task.cancel();

        if (hologramCache.get().isEmpty()) return;
        hologramCache.get().forEach((savedTime, hologram) -> {
            if (!hologram.isDeleted()) {
                hologram.delete();
            }
            hologramCache.remove(savedTime);
        });
    }
}
