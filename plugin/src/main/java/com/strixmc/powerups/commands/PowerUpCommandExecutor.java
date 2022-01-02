package com.strixmc.powerups.commands;

import com.strixmc.acid.commands.MainCommand;
import com.strixmc.powerups.PowerUpHandler;
import com.strixmc.powerups.commands.subcommands.*;
import com.strixmc.powerups.powerup.PowerUpRegistry;
import com.strixmc.powerups.services.PluginService;

public class PowerUpCommandExecutor extends MainCommand {

    public PowerUpCommandExecutor(PluginService pluginService) {
        PowerUpHandler handler = pluginService.getPowerUpHandler();
        PowerUpRegistry registry = pluginService.getPowerUpService().getRegistry();

        registerSubCommands(
                new HelpSubCommand(),
                new ListSubCommand(registry),
                new CreateSubCommand(registry),
                new DeleteSubCommand(registry),
                new EnableSubCommand(registry),
                new DisableSubCommand(registry),
                new ReloadSubCommand(pluginService),
                new SpawnSubCommand(registry, handler)
        );
    }
}
