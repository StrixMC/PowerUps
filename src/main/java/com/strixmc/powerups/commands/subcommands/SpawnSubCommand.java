package com.strixmc.powerups.commands.subcommands;

import com.strixmc.powerups.powerup.PowerUp;
import com.strixmc.powerups.powerup.PowerUpManager;
import com.strixmc.powerups.utils.MessageUtils;
import com.strixmc.powerups.utils.Messages;
import com.strixmc.powerups.utils.Placeholder;
import com.strixmc.powerups.utils.command.SubCommand;
import com.strixmc.powerups.utils.commons.cache.Cache;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class SpawnSubCommand implements SubCommand {

    private final PowerUpManager powerUpManager;
    private final Cache<String, PowerUp> powerUpCache;

    public SpawnSubCommand(PowerUpManager powerUpManager, Cache<String, PowerUp> powerUpCache) {
        this.powerUpManager = powerUpManager;
        this.powerUpCache = powerUpCache;
    }

    @Override
    public void execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length > 0) {
            Player player = (Player) sender;
            List<Placeholder> placeholderList = new ArrayList<>();
            placeholderList.add(new Placeholder("$command", commandLabel));

            StringJoiner name = new StringJoiner("_");
            Arrays.stream(args).forEach(name::add);

            String tmpID = MessageUtils.strip(name.toString().toUpperCase().trim());
            PowerUp powerUp = powerUpCache.getIfPresent(tmpID);
            if (!powerUpCache.exists(powerUp.getID())) {
                placeholderList.add(new Placeholder("$powerup_name", name.toString()));
                Messages.NO_EXISTS.sendMessage(sender, placeholderList);
                return;
            }

            placeholderList.add(new Placeholder("$powerup_name", powerUp.getName()));
            placeholderList.add(new Placeholder("$powerup_id", powerUp.getID()));

            Location lookingLocation = player.getTargetBlock(null, 20).getLocation();
            powerUpManager.spawnPowerUp(powerUp, lookingLocation);
        }
    }

    @Override
    public List<String> getTabCompletion(int index, String[] args) {
        if (powerUpCache.get().isEmpty()) return null;
        if (args.length == 2) {
            List<String> available = new ArrayList<>();
            powerUpCache.get().values().forEach(powerUp -> available.add(powerUp.getID()));

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
