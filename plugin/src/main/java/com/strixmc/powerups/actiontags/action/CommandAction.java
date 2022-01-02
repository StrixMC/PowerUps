package com.strixmc.powerups.actiontags.action;

import com.strixmc.powerups.action.Action;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CommandAction implements Action {

    @Override
    public String getIdentifier() {
        return "COMMAND";
    }

    /*
    Using Player#chat instead of Player#performCommand so "custom" commands can be executed by the player.
     */
    @Override
    public void execute(Plugin plugin, Player player, String content) {
        String command = content.startsWith("/") ? content : "/" + content;
        player.chat(command);
    }
}
