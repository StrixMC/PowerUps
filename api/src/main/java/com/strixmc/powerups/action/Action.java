package com.strixmc.powerups.action;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public interface Action {

    String getIdentifier();

    void execute(Plugin plugin, Player player, String content);

}
