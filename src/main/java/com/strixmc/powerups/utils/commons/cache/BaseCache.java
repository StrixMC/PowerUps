package com.strixmc.powerups.utils.commons.cache;

import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class BaseCache<K, V> implements Cache<K, V> {

    private final Map<K, V> cacheMap;

    public BaseCache(Map<K, V> cacheMap) {
        this.cacheMap = cacheMap;
    }

    @Override
    public Map<K, V> get() {
        return cacheMap;
    }
}
