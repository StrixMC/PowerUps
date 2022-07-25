package com.strixmc.powerups.hooks.holograms.holographicdisplays;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.VisibilityManager;
import com.gmail.filoghost.holographicdisplays.api.line.ItemLine;
import com.strixmc.acid.messages.MessageUtils;
import com.strixmc.powerups.PowerUpHandler;
import com.strixmc.powerups.hologram.HologramHandler;
import com.strixmc.powerups.powerup.PowerUp;
import com.strixmc.powerups.utils.ItemStackBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class HolographicDisplaysHandler implements HologramHandler {

    private final Plugin main;
    private final Map<Long, Hologram> cache;

    public HolographicDisplaysHandler(Plugin plugin) {
        this.main = plugin;
        this.cache = new HashMap<>();
    }

    @Override
    public boolean createHologram(PowerUpHandler handler, PowerUp powerUp, Location location) {
        long holoCurrentTime = System.currentTimeMillis();
        if (cache.containsKey(holoCurrentTime)) {
            return false;
        }

        Hologram hologram = HologramsAPI.createHologram(main, location);

        VisibilityManager visibilityManager = hologram.getVisibilityManager();
        visibilityManager.setVisibleByDefault(false);
        powerUp.getHologram().forEach(text -> hologram.appendTextLine(MessageUtils.translate(text)));
        ItemLine itemLine = hologram.appendItemLine(getItem(powerUp));
        visibilityManager.setVisibleByDefault(true);

        itemLine.setTouchHandler(player -> {
            handler.executeActions(player, powerUp);
            hologram.delete();
        });

        cache.put(holoCurrentTime, hologram);
        return true;
    }

    @Override
    public boolean removeHologram(Block block) {
        return false;
    }

    @Override
    public void removeAllHolograms() {

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
