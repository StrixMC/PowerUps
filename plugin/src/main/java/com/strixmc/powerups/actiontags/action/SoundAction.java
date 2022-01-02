package com.strixmc.powerups.actiontags.action;

import com.strixmc.powerups.action.Action;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SoundAction implements Action {

    @Override
    public String getIdentifier() {
        return "SOUND";
    }

    @Override
    public void execute(Plugin plugin, Player player, String content) {
        String[] args = content.split(";");
        try {
            Sound sound = null;
            float volume = 1.0F;
            float pitch = 1.0F;

            switch (args.length) {
                case 1: {
                    sound = Sound.valueOf(args[0]);
                    break;
                }
                case 2: {
                    sound = Sound.valueOf(args[0]);
                    volume = Float.parseFloat(args[1]);
                    break;
                }
                case 3: {
                    sound = Sound.valueOf(args[0]);
                    volume = Float.parseFloat(args[1]);
                    pitch = Float.parseFloat(args[2]);
                    break;
                }
            }

            if (sound != null) {
                player.playSound(player.getEyeLocation(), sound, volume, pitch);
            } else {
                plugin.getLogger().warning("[Action] Sound not supported: " + args[0]);
            }

        } catch (IllegalArgumentException e) {
            plugin.getLogger().warning("[Action] Invalid sound: " + content);
        }
    }
}
