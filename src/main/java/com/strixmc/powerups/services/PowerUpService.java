package com.strixmc.powerups.services;

import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.utils.commons.service.Initializer;
import lombok.Getter;
import lombok.Setter;

public class PowerUpService implements Initializer {

    @Getter
    @Setter
    private boolean initialized;

    private final PowerUps main;
    private PowerUpStorage storage;

    public PowerUpService(PowerUps main) {
        this.main = main;
        this.initialized = false;

        start();
    }

    @Override
    public void start() {
        storage = new PowerUpStorage(main);
        storage.loadAll();

        setInitialized(true);
    }

    @Override
    public void finish() {
        if (!isInitialized()) return;
        storage.saveAll();
    }
}
