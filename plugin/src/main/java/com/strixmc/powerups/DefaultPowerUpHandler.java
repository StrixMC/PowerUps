package com.strixmc.powerups;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.VisibilityManager;
import com.gmail.filoghost.holographicdisplays.api.line.ItemLine;
import com.strixmc.acid.location.LocationUtils;
import com.strixmc.acid.messages.MessageUtils;
import com.strixmc.powerups.actiontags.ActionManager;
import com.strixmc.powerups.hologram.HologramHandler;
import com.strixmc.powerups.powerup.PowerUp;
import com.strixmc.powerups.services.PluginService;
import com.strixmc.powerups.utils.ItemStackBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class DefaultPowerUpHandler implements PowerUpHandler {

    private final PowerUps main;
    private final ActionManager actionManager;
    private final HologramHandler hologramHandler;

    public DefaultPowerUpHandler(PluginService pluginService) {
        this.main = pluginService.getMain();
        this.actionManager = pluginService.getActionManager();
        this.hologramHandler = pluginService.getHookManager().getHoloHook().getHandler();
    }

    @Override
    public boolean isWorldDenied(World world) {
        List<String> deniedWorlds = main.getConfiguration().getStringList("DENIED_WORLDS");
        return deniedWorlds.contains(world.getName());
    }

    @Override
    public void executeActions(Player player, PowerUp powerUp) {
        actionManager.executeActions(player, powerUp.getActions());
    }

    @Override
    public void spawnPowerUp(PowerUp powerUp, Location location) {
        final double i = 0.25 * powerUp.getHologram().size();
        location.setY(getGroundLocation(location).getY() + 0.67 + i);
        hologramHandler.createHologram(this, powerUp, location);
    }

    public static Location getGroundLocation(Location location) {
        World world = location.getWorld();

        Block highest = world != null ? world.getHighestBlockAt(location).getRelative(BlockFace.UP) : null;
        Block block = highest != null && highest.getY() < location.getY() ? highest : location.getBlock();

        while (!block.getType().isSolid() && block.getLocation().getY() >= 0 && block.getLocation().getY() <= 255) {
            block = block.getRelative(BlockFace.DOWN);
        }

        return new Location(location.getWorld(), location.getX(), block.getY() >= 0 ? block.getY() + 1 : location.getY(), location.getZ());
    }
}
