package com.strixmc.powerups.actiontags.action;

import com.strixmc.acid.messages.MessageUtils;
import com.strixmc.powerups.action.Action;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MessageAction implements Action {

    @Override
    public String getIdentifier() {
        return "MESSAGE";
    }

    @Override
    public void execute(Plugin plugin, Player player, String content) {
        player.sendMessage(MessageUtils.translate(content));
    }
}
