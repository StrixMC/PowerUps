package com.strixmc.powerups.services;

import com.strixmc.acid.commons.cache.Cache;
import com.strixmc.acid.commons.storage.Storage;
import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.powerup.PowerUp;

import java.util.Optional;

public class PowerUpStorage implements Storage<PowerUp> {

    private final PowerUps main;
    private Cache<String, PowerUp> powerUpCache;

    public PowerUpStorage(PowerUps main) {
        this.main = main;
        this.powerUpCache = main.getPluginService().getCacheManager().getPowerUpsCache();
    }

    @Override
    public Optional<PowerUp> find(String id) {
        return Optional.empty();
    }

    @Override
    public void loadAll() {

    }

    @Override
    public boolean exists(String unique) {
        return false;
    }

    @Override
    public void save(PowerUp object) {

    }

    @Override
    public void saveAll() {
        if (powerUpCache.get().isEmpty()) return;
        powerUpCache.get().values().forEach(this::save);
    }

    @Override
    public void delete(String id) {

    }
}
