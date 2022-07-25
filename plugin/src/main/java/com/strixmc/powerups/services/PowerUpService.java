package com.strixmc.powerups.services;

import com.strixmc.acid.commons.service.Service;
import com.strixmc.acid.files.FileCreator;
import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.powerup.PowerUp;
import com.strixmc.powerups.powerup.PowerUpRegistry;

import java.io.File;

public class PowerUpService implements Service {

    private boolean initialized;

    private final PowerUps main;
    private final File storageFolder;
    private final PowerUpRegistry registry;

    public PowerUpService(PluginService pluginService) {
        this.main = pluginService.getMain();
        this.registry = pluginService.getPowerUpRegistry();
        this.storageFolder = new File(main.getDataFolder() + File.separator + "powerups");

        this.initialized = false;
        start();
    }

    @Override
    public void start() {
        if (!storageFolder.exists()) return;

        File[] files = storageFolder.listFiles();
        if (files == null || files.length == 0) return;

        for (File file : files) {
            String ID = file.getName().split("\\.")[0];

            FileCreator storageFile = new FileCreator(main, ID, storageFolder);

            if (storageFile.getKeys(false).isEmpty()) {
                main.getLogger().warning(ID + " is malformed.");
                continue;
            }

            if (registry.registerPowerUp(new PowerUp(storageFile.getValues(true)))) {
                main.getLogger().info(ID + " has been loaded.");
            } else {
                main.getLogger().warning("Something went wrong while loading powerup " + ID);
            }
        }

        setInitialized(true);
    }

    @Override
    public void finish() {
        if (!isInitialized()) return;

        if (registry.getPowerUps().isEmpty()) return;
        if (!storageFolder.exists()) {
            storageFolder.mkdir();
        }

        registry.getPowerUps().forEach(powerUp -> {
            FileCreator storageFile = new FileCreator(main, powerUp.getID(), storageFolder);

            storageFile.clearFile();
            storageFile.addDefaults(powerUp.serialize());
            storageFile.options().copyDefaults(true);
            storageFile.save();
        });
        registry.getPowerUps().clear();
    }

    public PowerUpRegistry getRegistry() {
        return this.registry;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
}
