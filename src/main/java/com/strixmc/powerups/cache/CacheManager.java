package com.strixmc.powerups.cache;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.strixmc.powerups.powerup.PowerUp;
import com.strixmc.powerups.utils.commons.cache.BaseCache;
import com.strixmc.powerups.utils.commons.cache.Cache;
import com.strixmc.powerups.utils.commons.service.Initializer;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
public class CacheManager implements Initializer {

    @Getter
    @Setter
    private boolean initialized;

    @Getter
    private Cache<String, PowerUp> powerUpsCache;

    @Getter
    private Cache<Long, Hologram> hologramCache;

    public CacheManager() {
        this.initialized = false;
        start();
    }

    @Override
    public void start() {
        setInitialized(true);
        initPowerUpsCache();
        initHologramsCache();
    }

    private void initHologramsCache() {
        this.hologramCache = new BaseCache<>(new HashMap<>());
    }

    private void initPowerUpsCache() {
        this.powerUpsCache = new BaseCache<>(new HashMap<>());
    }
}
