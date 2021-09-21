package com.strixmc.powerups.actiontags.action;

import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.actiontags.Action;
import com.strixmc.powerups.actiontags.ActionManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GlobalAction implements Action {

    private ActionManager manager;

    public GlobalAction(ActionManager manager) {
        this.manager = manager;
    }

    @Override
    public String getIdentifier() {
        return "GLOBAL";
    }

    @Override
    public void execute(PowerUps main, Player player, String content) {
        String actionName = StringUtils.substringBetween(content, "[", "]").toUpperCase();

        Bukkit.broadcastMessage("Global content " + content);
        Bukkit.broadcastMessage("Global actionname" + actionName);
        Action action = actionName.isEmpty() ? null : manager.getActionMap().get(actionName);
        if (action != null) {
            String finalContent = manager.parseContent(player, content, actionName);
            Bukkit.broadcastMessage("Global final content" + finalContent);
            action.execute(main, player, finalContent);
        }
    }
}
