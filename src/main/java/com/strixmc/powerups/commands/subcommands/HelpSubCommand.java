package com.strixmc.powerups.commands.subcommands;

import com.strixmc.powerups.utils.Messages;
import com.strixmc.powerups.utils.Placeholder;
import com.strixmc.powerups.utils.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class HelpSubCommand implements SubCommand {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public void execute(CommandSender sender, String commandLabel, String[] args) {
        List<Placeholder> placeholderList = Collections.singletonList(new Placeholder("$command", commandLabel));
        if (sender instanceof Player) {
            Messages.MAIN_HELP.sendMessage((Player) sender, placeholderList);
            return;
        }

        Messages.MAIN_HELP.sendMessage(sender, placeholderList);
    }

    @Override
    public boolean requirePlayer() {
        return false;
    }

}
