package com.strixmc.powerups.commands.subcommands;

import com.strixmc.powerups.services.PluginService;
import com.strixmc.powerups.utils.FileCreator;
import com.strixmc.powerups.utils.MessageUtils;
import com.strixmc.powerups.utils.command.SubCommand;
import org.bukkit.command.CommandSender;

public class ReloadSubCommand implements SubCommand {

    private final FileCreator lang;
    private final FileCreator config;

    public ReloadSubCommand(PluginService pluginService) {
        this.lang = pluginService.getLang();
        this.config = pluginService.getConfig();
    }

    @Override
    public void execute(CommandSender sender, String commandLabel, String[] args) {

        if (args.length != 1) {
            return;
        }

        lang.reload();
        config.reload();

        MessageUtils.sendMessage(sender, "Config & Lang files have been reloaded.");
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public boolean requireAdmin() {
        return true;
    }

    @Override
    public String getPermission() {
        return "powerups.command.reload";
    }

    @Override
    public boolean requirePlayer() {
        return false;
    }

    @Override
    public int getArgsCount() {
        return 1;
    }
}
