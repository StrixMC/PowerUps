package com.strixmc.powerups.actiontags.action;

import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.actiontags.Action;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BroadcastAction implements Action {

    @Override
    public String getIdentifier() {
        return "BROADCAST";
    }

    @Override
    public void execute(PowerUps main, Player player, String content) {
        Bukkit.broadcastMessage(content);
    }
}
