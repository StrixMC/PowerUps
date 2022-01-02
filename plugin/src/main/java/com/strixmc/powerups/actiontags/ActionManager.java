package com.strixmc.powerups.actiontags;

import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.action.Action;
import com.strixmc.powerups.actiontags.action.*;
import com.strixmc.powerups.hooks.PlaceholderAPIHook;
import com.strixmc.powerups.services.PluginService;
import me.clip.placeholderapi.PlaceholderAPI;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionManager {

    private final PowerUps main;
    private final Map<String, Action> actionMap;

    private PlaceholderAPIHook papiHook;

    public ActionManager(PluginService pluginService) {
        this.actionMap = new HashMap<>();
        this.main = pluginService.getMain();
        this.papiHook = pluginService.getHookManager().getPapiHook();

        // todo
        /*
        TITLE, ACTIONBAR, SUBTITLE, TITLE, FULL_TITLE, PARTICLE, EFFECT
         */
        registerAction(
                new SoundAction(),
                new MessageAction(),
                new CommandAction(),
                new BroadcastAction(),
                new GlobalAction(this)
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
            if (action == null) {
                main.getLogger().warning("Action with the name of " + actionName + " was not found.");
                return;
            }

            content = parseContent(player, content, actionName);
            action.execute(main, player, content);
        });
    }

    public String parseContent(Player player, String content, String actionName) {
        content = content.replace("%player_name%", player.getName());
        content = content.contains(" ") ? content.split(" ", 2)[1] : content.replace("[" + actionName + "]", "");

        if (papiHook.isInitialized()) {
            content = PlaceholderAPI.setPlaceholders(player, content);
        }
        return content.trim();
    }

    public Map<String, Action> getActionMap() {
        return this.actionMap;
    }
}
