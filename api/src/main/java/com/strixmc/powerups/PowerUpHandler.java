package com.strixmc.powerups;

import com.strixmc.powerups.powerup.PowerUp;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public interface PowerUpHandler {

    boolean isWorldDenied(World world);

    void executeActions(Player player, PowerUp powerUp);

    void spawnPowerUp(PowerUp powerUp, Location location);

}
