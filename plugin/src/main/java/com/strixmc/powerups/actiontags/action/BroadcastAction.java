package com.strixmc.powerups.actiontags.action;

import com.strixmc.powerups.action.Action;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BroadcastAction implements Action {

    @Override
    public String getIdentifier() {
        return "BROADCAST";
    }

    @Override
    public void execute(Plugin plugin, Player player, String content) {
        Bukkit.broadcastMessage(content);
    }
}
