package com.strixmc.powerups.actiontags;

import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.actiontags.action.BroadcastAction;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionManager {

    private final PowerUps main;
    private final Map<String, Action> actionMap;

    public ActionManager(PowerUps main) {
        this.main = main;
        this.actionMap = new HashMap<>();

        registerAction(
                new BroadcastAction()
        );
    }

    private void registerAction(Action... actions) {
        for (Action action : actions) {
            this.actionMap.put(action.getIdentifier(), action);
        }
    }

    public void executeActions(Player player, List<String> actions) {
        actions.forEach(content -> {
            String actionName = StringUtils.substringBetween(content, "[", "]").toUpperCase();
            Action action = actionName.isEmpty() ? null : this.actionMap.get(actionName);
            if (action != null) {
                content = content.replace("%player_name%", player.getName());
                content = content.contains(" ") ? content.split(" ", 2)[1] : content.replace("[" + actionName + "]", "");
                // TODO implement placeholderapi.
                action.execute(main, player, content.trim());
            }
        });
    }
}
