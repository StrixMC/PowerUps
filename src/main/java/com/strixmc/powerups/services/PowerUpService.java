package com.strixmc.powerups.services;

import com.strixmc.acid.commons.service.Service;
import com.strixmc.powerups.PowerUps;
import lombok.Getter;
import lombok.Setter;

public class PowerUpService implements Service {

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
