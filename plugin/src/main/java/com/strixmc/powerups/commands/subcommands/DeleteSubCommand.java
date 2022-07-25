package com.strixmc.powerups.commands.subcommands;

import com.strixmc.acid.commands.SubCommand;
import com.strixmc.acid.messages.MessageUtils;
import com.strixmc.acid.messages.Placeholder;
import com.strixmc.powerups.powerup.PowerUp;
import com.strixmc.powerups.powerup.PowerUpRegistry;
import com.strixmc.powerups.utils.Messages;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class DeleteSubCommand implements SubCommand {

    private final PowerUpRegistry registry;

    public DeleteSubCommand(PowerUpRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length > 0) {
            List<Placeholder> placeholderList = new ArrayList<>();
            placeholderList.add(new Placeholder("$command", commandLabel));

            String name = args[0];
            String tmpID = MessageUtils.strip(name.toUpperCase().trim());

            PowerUp powerUp = registry.getPowerUp(tmpID);

            if (powerUp == null) {
                placeholderList.add(new Placeholder("$powerup_name", name));
                placeholderList.add(new Placeholder("$powerup_id", tmpID));
                Messages.NO_EXISTS.sendMessage(sender, placeholderList);
                return;
            }

            placeholderList.add(new Placeholder("$powerup_name", powerUp.getName()));
            placeholderList.add(new Placeholder("$powerup_id", powerUp.getID()));

            registry.removePowerUp(powerUp.getID());
            Messages.DELETED.sendMessage(sender, placeholderList);
        }
    }

    @Override
    public List<String> getTabCompletion(int index, String[] args) {
        if (registry.getPowerUps().isEmpty()) return null;
        if (args.length == 2) {
            List<String> available = new ArrayList<>();
            registry.getPowerUps().forEach(powerUp -> available.add(powerUp.getID()));

            return available;
        }
        return null;
    }

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public int getArgsCount() {
        return 2;
    }

    @Override
    public String getSyntax() {
        return Messages.DELETE_HELP.getMessage();
    }

    @Override
    public boolean requireAdmin() {
        return true;
    }

    @Override
    public boolean requirePlayer() {
        return false;
    }

    @Override
    public String getPermission() {
        return "powerups.command.delete";
    }
}
