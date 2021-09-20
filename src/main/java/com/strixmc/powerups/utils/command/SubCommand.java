package com.strixmc.powerups.utils.command;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public interface SubCommand {
    /**
     * Returns name of this subCommand. (used in /mainCommand subCommandName)
     *
     * @return The name.
     */
    String getName();

    /**
     * @return Alternative command names.
     */
    default List<String> getAliases() {
        return new ArrayList<>();
    }

    /**
     * Returns description of this subCommand if exist.
     *
     * @return The description.
     */
    default String getDescription() {
        return "";
    }

    /**
     * Returns the syntax of this subCommand. (ex. /troll swap &lt;player1&gt; &lt;player2&gt;)
     *
     * @return The syntax.
     */
    default String getSyntax(){
        return getName();
    }

    /**
     * @return Minimum arguments required by command.
     */
    default int getArgsCount() {
        return 0;
    }

    /**
     * Returns permission required to run this subCommand.
     *
     * @return The permission.
     */
    default String getPermission() {
        return null;
    }

    /**
     * Returns the boolean if command can be used only by players.
     *
     * @return The boolean if command is player only.
     */

    default boolean requirePlayer() {
        return true;
    }

    /**
     * Returns the boolean if command sender needs to be an admin.
     *
     * @return The boolean if command is admin only.
     */

    default boolean requireAdmin() {
        return false;
    }

    /**
     * Returns the list of words to tab-complete on an index starting after the subCommand. (ex. /troll swap 0 1 | numbers representing the index of tabComplete)
     *
     * @param index The index to get tab-complete for.
     * @param args  All the args in the command, including the currently tab-completed one.
     * @return The list of words to tab-complete.
     */
    default List<String> getTabCompletion(int index, String[] args) {
        return Collections.emptyList();
    }

    /**
     * Performs the command.
     *
     * @param sender Sender, who has invoked the command. (either Player or Console)
     * @param args   The arguments after this subCommand.
     */
    void execute(CommandSender sender, String commandLabel, String[] args);
}