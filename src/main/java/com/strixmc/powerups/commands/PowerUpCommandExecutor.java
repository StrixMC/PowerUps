package com.strixmc.powerups.commands;

import com.strixmc.acid.commands.MainCommand;
import com.strixmc.acid.commons.cache.Cache;
import com.strixmc.powerups.commands.subcommands.*;
import com.strixmc.powerups.powerup.PowerUp;
import com.strixmc.powerups.powerup.PowerUpManager;
import com.strixmc.powerups.services.PluginService;

public class PowerUpCommandExecutor extends MainCommand {

    private final PowerUpManager manager;
    private final Cache<String, PowerUp> powerUpCache;
    private final PluginService pluginService;

    public PowerUpCommandExecutor(PluginService pluginService) {
        this.pluginService = pluginService;
        this.manager = pluginService.getPowerUpManager();
        this.powerUpCache = pluginService.getCacheManager().getPowerUpsCache();
        registerSubCommands(
                new HelpSubCommand(),
                new ListSubCommand(powerUpCache),
                new CreateSubCommand(powerUpCache),
                new DeleteSubCommand(powerUpCache),
                new EnableSubCommand(powerUpCache),
                new DisableSubCommand(powerUpCache),
                new ReloadSubCommand(pluginService),
                new SpawnSubCommand(manager, powerUpCache)
        );

    }

}
