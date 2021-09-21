package com.strixmc.powerups.powerup;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.VisibilityManager;
import com.gmail.filoghost.holographicdisplays.api.line.ItemLine;
import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.actiontags.ActionManager;
import com.strixmc.powerups.services.PluginService;
import com.strixmc.powerups.utils.LocationUtils;
import com.strixmc.powerups.utils.MessageUtils;
import com.strixmc.powerups.utils.commons.cache.Cache;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PowerUpManager {

    private final PowerUps main;
    private final ActionManager actionManager;
    private Cache<Long, Hologram> hologramCache;

    public PowerUpManager(PluginService pluginService) {
        this.main = pluginService.getMain();
        this.actionManager = pluginService.getActionManager();
        this.hologramCache = pluginService.getCacheManager().getHologramCache();
    }

    public void powerUpActions(Player player, PowerUp powerUp) {
        if (!powerUp.isEnabled()) return;
        actionManager.executeActions(player, powerUp.getActions());
    }

    public void spawnPowerUp(PowerUp powerUp, Location location) {
        final double i = 0.25 * powerUp.getHologram().size();
        final double fixedY = LocationUtils.getGroundLocation(location).getY() + 0.67 + i;
        location.setY(fixedY);
        Hologram hologram = HologramsAPI.createHologram(main, location);
        VisibilityManager visibilityManager = hologram.getVisibilityManager();
        visibilityManager.setVisibleByDefault(false);
        powerUp.getHologram().forEach(text -> hologram.appendTextLine(MessageUtils.translate(text)));
        ItemLine itemLine = hologram.appendItemLine(powerUp.getItem());
        visibilityManager.setVisibleByDefault(true);
        long savedHologramLong = System.currentTimeMillis();
        hologramCache.add(savedHologramLong, hologram);

        //todo add types.

        itemLine.setTouchHandler(player -> {
            powerUpActions(player, powerUp);
            hologram.delete();
        });

        itemLine.setPickupHandler(player -> {
            powerUpActions(player, powerUp);
            hologram.delete();
        });

        hologramCache.find(savedHologramLong).ifPresent(ignored -> hologramCache.remove(savedHologramLong));
    }

    /*public void powerUpActions(Player player, List<String> actions, PowerUp powerUp) {
        actions.forEach(action -> {

            final Pattern pattern = Pattern.compile("(?<=\\[).+?(?=\\])");
            final Matcher matcher = pattern.matcher(action);
            String actionType = null;

            while (matcher.find()) {
                actionType = matcher.group();
            }

            if (actionType == null) return;
            //action = action.replace("$id", powerUp.getID()).replace("$name", powerUp.getName()).replace("$player", player.getName()).replaceFirst("(!?)" + Pattern.quote("[" + actionType + "]"), "").trim();
            //Bukkit.getLogger().info("ACTION " + action);
            //Bukkit.getLogger().info("ACTION TYPE " + actionType);
*//*
            switch (actionType.toUpperCase()) {
                case "COMMAND": {
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, action);
                    break;
                }
                case "MESSAGE": {
                    player.sendMessage(utils.translate(action));
                    break;
                }
                case "TITLE": {
                    Titles.sendTitle(player, utils.translate(action), "");
                    break;
                }
                case "ACTIONBAR": {
                    ActionBar.sendActionBar(player, utils.translate(action));
                    break;
                }
                case "SUBTITLE": {
                    Titles.sendTitle(player, "", utils.translate(action));
                    break;
                }
                case "FULLTITLE": {
                    Titles.sendTitle(player, utils.translate(action.split(";")[0]), utils.translate(action.split(";")[1]));
                    break;
                }
                case "SOUND": {
                    try {
                        Sound sound = Sound.valueOf(action.split(";")[0]);
                        float volume = Float.parseFloat(action.split(";")[1]);
                        float pitch = Float.parseFloat(action.split(";")[2]);

                        XSound xSound = XSound.matchXSound(sound);

                        if (xSound.isSupported()) {
                            xSound.play(player.getEyeLocation(), volume, pitch);
                        }
                    } catch (IllegalArgumentException e) {
                        for (int i = 0; i < 3; i++) {
                            Bukkit.getLogger().warning(powerUp.getID() + " have a wrong named sound (\"" + action.split(";")[0] + "\")");
                        }
                    }
                    break;
                }
                case "PARTICLE": {
                    try {
                        Effect effect = Effect.valueOf(action.split(";")[0]);

                        player.getWorld().playEffect(player.getLocation(), effect, null);
                    } catch (IllegalArgumentException e) {
                        for (int i = 0; i < 3; i++) {
                            Bukkit.getLogger().warning(powerUp.getID() + " have a wrong named particle (\"" + action.split(";")[0] + "\")");
                        }
                    }
                    break;
                }
                case "EFFECT": {
                    try {
                        XPotion effect = XPotion.matchXPotion(PotionEffectType.getByName(action.split(";")[0]));
                        int duration = Integer.parseInt(action.split(";")[1]);
                        int amplifier = Integer.parseInt(action.split(";")[2]);

                        if (effect.isSupported()) {
                            effect.parsePotion(duration, amplifier).apply(player);
                        }
                    } catch (IllegalArgumentException e) {
                        for (int i = 0; i < 3; i++) {
                            Bukkit.getLogger().warning(powerUp.getID() + " have a wrong named effect (\"" + action.split(";")[0] + "\")");
                        }
                    }
                    break;
                }
            }
*//*
        });
    }*/
}
