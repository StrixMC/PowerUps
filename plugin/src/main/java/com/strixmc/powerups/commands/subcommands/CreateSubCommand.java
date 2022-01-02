package com.strixmc.powerups.commands.subcommands;

import com.strixmc.acid.commands.SubCommand;
import com.strixmc.acid.messages.Placeholder;
import com.strixmc.powerups.powerup.DefaultPowerUpRegistry;
import com.strixmc.powerups.powerup.PowerUp;
import com.strixmc.powerups.powerup.PowerUpRegistry;
import com.strixmc.powerups.utils.Messages;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class CreateSubCommand implements SubCommand {

    private final PowerUpRegistry registry;

    public CreateSubCommand(PowerUpRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length > 0) {
            List<Placeholder> placeholderList = new ArrayList<>();
            placeholderList.add(new Placeholder("$command", commandLabel));

            StringJoiner name = new StringJoiner("_");
            Arrays.stream(args).forEach(name::add);
            if (name.toString().length() > 16) {
                sender.sendMessage("Name arguments are too long.");
                return;
            }
            PowerUp powerUp = new PowerUp(name.toString());
            placeholderList.add(new Placeholder("$powerup_name", powerUp.getName()));
            placeholderList.add(new Placeholder("$powerup_id", powerUp.getID()));

            if (!registry.registerPowerUp(powerUp)) {
                Messages.ALREADY_EXISTS.sendMessage(sender, placeholderList);
                return;
            }

            Messages.CREATED.sendMessage(sender, placeholderList);
        }
    }

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public int getArgsCount() {
        return 2;
    }

    @Override
    public String getSyntax() {
        return Messages.CREATE_HELP.getMessage();
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
        return "powerups.command.create";
    }
}
