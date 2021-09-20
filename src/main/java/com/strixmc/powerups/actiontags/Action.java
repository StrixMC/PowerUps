package com.strixmc.powerups.actiontags;

import com.strixmc.powerups.PowerUps;
import org.bukkit.entity.Player;

public interface Action {

    String getIdentifier();

    void execute(PowerUps main, Player player, String content);

}
