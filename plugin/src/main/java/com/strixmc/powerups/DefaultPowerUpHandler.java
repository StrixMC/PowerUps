package com.strixmc.powerups;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.VisibilityManager;
import com.gmail.filoghost.holographicdisplays.api.line.ItemLine;
import com.strixmc.acid.messages.MessageUtils;
import com.strixmc.powerups.actiontags.ActionManager;
import com.strixmc.powerups.powerup.PowerUp;
import com.strixmc.powerups.powerup.PowerUpRegistry;
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

    public DefaultPowerUpHandler(PluginService pluginService) {
        this.main = pluginService.getMain();
        this.actionManager = pluginService.getActionManager();
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
        final double fixedY = getGroundLocation(location).getY() + 0.67 + i;
        location.setY(fixedY);
        Hologram hologram = HologramsAPI.createHologram(main, location);
        VisibilityManager visibilityManager = hologram.getVisibilityManager();
        visibilityManager.setVisibleByDefault(false);
        powerUp.getHologram().forEach(text -> hologram.appendTextLine(MessageUtils.translate(text)));
        ItemLine itemLine = hologram.appendItemLine(getItem(powerUp));
        visibilityManager.setVisibleByDefault(true);
        long savedHologramLong = System.currentTimeMillis();
        //todo fix-me
        //hologramCache.add(savedHologramLong, hologram);

        //todo add types.

        itemLine.setTouchHandler(player -> {
            executeActions(player, powerUp);
            hologram.delete();
        });

        itemLine.setPickupHandler(player -> {
            executeActions(player, powerUp);
            hologram.delete();
        });
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

    public ItemStack getItem(PowerUp powerUp) {
        try {
            return new ItemStackBuilder(Material.matchMaterial(powerUp.getMaterial())).data(powerUp.getData());
        } catch (NullPointerException e) {
            for (int i = 0; i < 3; i++) {
                Bukkit.getLogger().warning(powerUp.getName() + " powerup have a wrong named material (\"" + powerUp.getID() + "\")");
            }
            return new ItemStack(Material.COAL_BLOCK);
        }
    }
}
