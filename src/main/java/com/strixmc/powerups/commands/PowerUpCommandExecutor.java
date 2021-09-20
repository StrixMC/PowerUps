package com.strixmc.powerups.commands;

import com.strixmc.powerups.cache.CacheManager;
import com.strixmc.powerups.commands.subcommands.*;
import com.strixmc.powerups.powerup.PowerUp;
import com.strixmc.powerups.utils.Messages;
import com.strixmc.powerups.utils.command.MainCommand;
import com.strixmc.powerups.utils.command.argumentmatcher.StartingWithStringArgumentMatcher;
import com.strixmc.powerups.utils.commons.cache.Cache;

public class PowerUpCommandExecutor extends MainCommand {

    private final Cache<String, PowerUp> powerUpCache;

    public PowerUpCommandExecutor(CacheManager cacheManager) {
        super(Messages.NO_PERMISSION.getMessage(), new StartingWithStringArgumentMatcher());
        this.powerUpCache = cacheManager.getPowerUpsCache();
        registerSubCommands(
                new HelpSubCommand(),
                new ListSubCommand(powerUpCache),
                new CreateSubCommand(powerUpCache),
                new DeleteSubCommand(powerUpCache),
                new EnableSubCommand(powerUpCache),
                new DisableSubCommand(powerUpCache)
        );

    }

}
