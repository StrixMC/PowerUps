package com.strixmc.powerups.utils;

import com.strixmc.acid.files.FileCreator;
import com.strixmc.acid.messages.MessageUtils;
import com.strixmc.acid.messages.Placeholder;
import com.strixmc.acid.messages.StringUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public enum Messages {

    // HELP MESSAGES
    MAIN_HELP("HELP.MAIN", "Main help message has been deleted from the lang file."),
    CREATE_HELP("HELP.USAGES.CREATE", "&cCorrect usage: /$command create <NAME>"),
    DELETE_HELP("HELP.USAGES.DELETE", "&cCorrect usage: /$command delete <ID>"),
    ENABLE_HELP("HELP.USAGES.ENABLE", "&cCorrect usage: /$command enable <ID>"),
    DISABLE_HELP("HELP.USAGES.DISABLE", "&cCorrect usage: /$command disable <ID>"),
    // LIST MESSAGES
    NO_AVAILABLE("NO_AVAILABLE", "&cThere are no available powerups."),
    AVAILABLE_LIST("AVAILABLE_LIST", "&aAvailable powerup list. &7Hover to view more info."),
    // POWERUPS MESSAGES
    CREATED("CREATED", "&aSuccessfully created powerup $powerup_name &7($powerup_id)&a."),
    DELETED("DELETED", "&aSuccessfully deleted powerup $powerup_id$."),
    ENABLED("ENABLED", "&eThe powerup $powerup_id has been &aEnabled&e."),
    DISABLED("DISABLED", "&eThe powerup $powerup_id has been &cDisabled&e."),
    // IDK
    NO_EXISTS("NO_EXISTS", "&cThe powerup $powerup_id does not exist."),
    ALREADY_EXISTS("ALREADY_EXISTS", "&cThe powerup $powerup_id already exist."),
    ALREADY_ENABLED("ALREADY_ENABLED", "&eThe powerup $powerup_id is already &aEnabled&e."),
    ALREADY_DISABLED("ALREADY_DISABLED", "&eThe powerup $powerup_id is already &cDisabled&e."),
    // GENERAL MESSAGES,
    NO_PERMISSION("NO_PERMISSION", "&cYou have no permission to perform that!");

    @Getter
    private String path;
    @Getter
    private String defaultMessage;
    @Getter
    private List<String> defaultListMessage;
    @Setter
    public static FileCreator lang;

    Messages(String path, String defaultMessage) {
        this.path = path;
        this.defaultMessage = defaultMessage;
    }

    Messages(String path, List<String> defaultListMessage) {
        this.path = path;
        this.defaultListMessage = defaultListMessage;
    }

    @SneakyThrows
    public static void addMissingMessages() {
        FileCreator messages = lang;
        boolean saveFile = false;
        for (Messages message : values()) {
            if (!messages.contains(message.getPath())) {
                saveFile = true;
                if (message.getDefaultMessage() != null) {
                    messages.set(message.getPath(), message.getDefaultMessage());
                } else {
                    messages.set(message.getPath(), message.getDefaultListMessage());
                }
            }
        }
        if (saveFile) {
            messages.save();
            messages.reload();
        }
    }

    public static String convertList(List<String> list) {
        StringJoiner message = new StringJoiner("\n&r", "", "&f &r");
        list.forEach(line -> message.add(MessageUtils.translate(line)));
        return message.toString();
    }


    public String getMessage() {
        String message;
        boolean isList = isList();
        boolean exists = exists();

        if (isList) {
            if (exists) {
                message = convertList(lang.getStringList(path));
            } else {
                message = convertList(defaultListMessage);
            }
        } else {
            if (exists) {
                message = lang.getString(path);
            } else {
                message = defaultMessage;
            }
        }

        return MessageUtils.translate(message);
    }

    public String getMessage(List<Placeholder> placeholders) {
        String message = getMessage();

        // Replace placeholders
        for (Placeholder placeholder : placeholders) {
            message = StringUtils.replace(message, placeholder);
        }

        return MessageUtils.translate(message);
    }

    public void sendMessage(CommandSender sender) {
        sendMessage(sender, new ArrayList<>());
    }

    public void sendMessage(CommandSender sender, List<Placeholder> placeholders) {
        if (isSilent()) return;
        if (sender instanceof Player) {
            sendMessage((Player) sender, placeholders);
            return;
        }
        if (placeholders.isEmpty()) {
            sender.sendMessage(MessageUtils.strip(getMessage()));
            return;
        }
        sender.sendMessage(MessageUtils.strip(getMessage(placeholders)));
    }

    public void sendMessage(Player player) {
        sendMessage(player, new ArrayList<>());
    }

    public void sendMessage(Player player, List<Placeholder> placeholders) {
        if (isSilent()) return;
        if (placeholders == null) {
            player.sendMessage(getMessage());
            return;
        }

        player.sendMessage(getMessage(placeholders));
    }

    public void broadcastMessage() {
        broadcastMessage(null);
    }

    public void broadcastMessage(List<Placeholder> placeholders) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (placeholders == null) {
                sendMessage(onlinePlayer);
            } else {
                sendMessage(onlinePlayer, placeholders);
            }
        }
    }

    public boolean isSilent() {
        return exists() && isEmpty();
    }

    private boolean isEmpty() {
        return lang.getString(path).isEmpty();
    }

    private boolean exists() {
        return lang.contains(path);
    }

    private boolean isList() {
        if (lang.contains(path)) return !lang.getStringList(path).isEmpty();
        return defaultMessage == null;
    }
}
