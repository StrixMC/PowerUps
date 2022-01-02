package com.strixmc.powerups.commands.subcommands;

import com.strixmc.acid.commands.SubCommand;
import com.strixmc.acid.files.FileCreator;
import com.strixmc.acid.messages.MessageUtils;
import com.strixmc.powerups.services.PluginService;
import com.strixmc.powerups.utils.YamlCreator;
import org.bukkit.command.CommandSender;

import java.io.IOException;

public class ReloadSubCommand implements SubCommand {

    private final FileCreator lang;
    private final YamlCreator config;

    public ReloadSubCommand(PluginService pluginService) {
        this.lang = pluginService.getMain().getLang();
        this.config = pluginService.getMain().getConfiguration();
    }

    @Override
    public void execute(CommandSender sender, String commandLabel, String[] args) {

        lang.reload();
        config.reload(true);
        try {
            config.save();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
