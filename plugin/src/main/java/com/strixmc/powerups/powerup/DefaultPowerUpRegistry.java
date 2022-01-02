package com.strixmc.powerups.powerup;

import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DefaultPowerUpRegistry implements PowerUpRegistry {

    private final Map<String, PowerUp> cache;

    public DefaultPowerUpRegistry() {
        this.cache = new HashMap<>();
    }

    @Override
    public @Nullable PowerUp getPowerUp(String name) {
        return cache.get(name);
    }

    @Override
    public boolean registerPowerUp(PowerUp powerUp) {
        String ID = powerUp.getID();
        if (cache.containsKey(ID)) {
            return false;
        }

        cache.put(ID, powerUp);
        return true;
    }

    @Override
    public boolean removePowerUp(String ID) {
        return cache.remove(ID) != null;
    }

    @Override
    public Collection<PowerUp> getPowerUps() {
        return cache.values();
    }
}
