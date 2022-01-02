package com.strixmc.powerups.powerup;

import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface PowerUpRegistry {

    @Nullable PowerUp getPowerUp(String name);

    boolean registerPowerUp(PowerUp powerUp);

    boolean removePowerUp(String name);

    Collection<PowerUp> getPowerUps();

}
