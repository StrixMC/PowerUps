package com.strixmc.powerups.commands.subcommands;

import com.strixmc.acid.commands.SubCommand;
import com.strixmc.acid.commons.cache.Cache;
import com.strixmc.acid.messages.MessageUtils;
import com.strixmc.acid.messages.Placeholder;
import com.strixmc.powerups.powerup.PowerUp;
import com.strixmc.powerups.utils.Messages;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class ListSubCommand implements SubCommand {

    private final Cache<String, PowerUp> powerUpCache;

    public ListSubCommand(Cache<String, PowerUp> powerUpCache) {
        this.powerUpCache = powerUpCache;
    }

    @Override
    public void execute(CommandSender sender, String commandLabel, String[] args) {
        if (powerUpCache.get().isEmpty()) {
            Messages.NO_AVAILABLE.sendMessage(sender);
            return;
        }

        List<String> availableList = powerUpCache.get().values().stream().map(powerUp -> (powerUp.isEnabled() ? "&a" : "&c") + powerUp.getID()).collect(Collectors.toList());

        StringJoiner list = new StringJoiner("&7, &r");
        availableList.forEach(list::add);

        Messages.AVAILABLE_LIST.sendMessage(sender, Arrays.asList(new Placeholder("$amount", availableList.size())));
        MessageUtils.sendMessage(sender, list.toString());
    }

    @Override
    public boolean requirePlayer() {
        return false;
    }

    @Override
    public String getName() {
        return "list";
    }
}
