package com.strixmc.powerups.hologram;

import com.strixmc.powerups.PowerUpHandler;
import com.strixmc.powerups.powerup.PowerUp;
import org.bukkit.Location;
import org.bukkit.block.Block;

public interface HologramHandler {

    boolean createHologram(PowerUpHandler powerUpHandler, PowerUp powerUp, Location location);

    boolean removeHologram(Block block);

    void removeAllHolograms();

}
