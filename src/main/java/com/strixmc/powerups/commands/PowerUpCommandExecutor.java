package com.strixmc.powerups.commands;

import com.strixmc.powerups.cache.CacheManager;
import com.strixmc.powerups.commands.subcommands.*;
import com.strixmc.powerups.powerup.PowerUp;
import com.strixmc.powerups.powerup.PowerUpManager;
import com.strixmc.powerups.services.PluginService;
import com.strixmc.powerups.utils.Messages;
import com.strixmc.powerups.utils.command.MainCommand;
import com.strixmc.powerups.utils.command.argumentmatcher.StartingWithStringArgumentMatcher;
import com.strixmc.powerups.utils.commons.cache.Cache;

public class PowerUpCommandExecutor extends MainCommand {

    private final PowerUpManager manager;
    private final Cache<String, PowerUp> powerUpCache;
    private final PluginService pluginService;

    public PowerUpCommandExecutor(PluginService pluginService) {
        super(Messages.NO_PERMISSION.getMessage(), new StartingWithStringArgumentMatcher());
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
