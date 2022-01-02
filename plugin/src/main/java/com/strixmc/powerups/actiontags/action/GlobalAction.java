package com.strixmc.powerups.actiontags.action;

import com.strixmc.powerups.action.Action;
import com.strixmc.powerups.actiontags.ActionManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class GlobalAction implements Action {

    private final ActionManager manager;

    public GlobalAction(ActionManager manager) {
        this.manager = manager;
    }

    @Override
    public String getIdentifier() {
        return "GLOBAL";
    }

    @Override
    public void execute(Plugin plugin, Player player, String content) {
        String actionName = StringUtils.substringBetween(content, "[", "]").toUpperCase();

        //main.getLogger().info("Global content " + content);
        //main.getLogger().info("Global actionname" + actionName);
        Action action = actionName.isEmpty() ? null : manager.getActionMap().get(actionName);
        if (action == null) {
            plugin.getLogger().warning("Action with the name of " + actionName + " was not found.");
            plugin.getLogger().info("Global action needs another action to execute to the player!");
            return;
        }

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            String finalContent = manager.parseContent(player, content, actionName);
            //main.getLogger().info("Global final content" + finalContent);
            action.execute(plugin, onlinePlayer, finalContent);
        }
    }
}
