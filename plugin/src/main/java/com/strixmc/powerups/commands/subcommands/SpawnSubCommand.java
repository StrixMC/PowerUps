package com.strixmc.powerups.commands.subcommands;

import com.strixmc.acid.commands.SubCommand;
import com.strixmc.acid.messages.MessageUtils;
import com.strixmc.acid.messages.Placeholder;
import com.strixmc.powerups.PowerUpHandler;
import com.strixmc.powerups.powerup.PowerUp;
import com.strixmc.powerups.powerup.PowerUpRegistry;
import com.strixmc.powerups.utils.Messages;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class SpawnSubCommand implements SubCommand {

    private final PowerUpRegistry registry;
    private final PowerUpHandler powerUpHandler;

    public SpawnSubCommand(PowerUpRegistry registry, PowerUpHandler powerUpHandler) {
        this.registry = registry;
        this.powerUpHandler = powerUpHandler;
    }

    @Override
    public void execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length > 0) {
            Player player = (Player) sender;
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

            Location lookingLocation = player.getTargetBlock(null, 20).getLocation();
            powerUpHandler.spawnPowerUp(powerUp, lookingLocation);
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
        return "spawn";
    }

    @Override
    public int getArgsCount() {
        return 2;
    }

    @Override
    public String getSyntax() {
        return Messages.ENABLE_HELP.getMessage();
    }

    @Override
    public boolean requireAdmin() {
        return true;
    }

    @Override
    public String getPermission() {
        return "powerups.command.spawn";
    }
}
