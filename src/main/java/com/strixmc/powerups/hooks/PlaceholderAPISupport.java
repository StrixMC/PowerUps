package com.strixmc.powerups.hooks;

import com.strixmc.powerups.PowerUps;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPISupport extends PlaceholderExpansion {

    private final PowerUps main;

    public PlaceholderAPISupport(PowerUps main) {
        this.main = main;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        String lower = params.toLowerCase();

        switch (lower) {
            default:
                return "";
        }
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "pup";
    }

    @Override
    public @NotNull String getAuthor() {
        return "StrixMC";
    }

    @Override
    public @NotNull String getVersion() {
        return main.getDescription().getVersion();
    }
}
