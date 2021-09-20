package com.strixmc.powerups;

import com.strixmc.powerups.services.PluginService;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class PowerUps extends JavaPlugin {

    @Getter
    private PluginService pluginService;

    @Override
    public void onEnable() {
        this.pluginService = new PluginService(this);
        if (!this.pluginService.isInitialized()) {
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        pluginService.finish();
    }
}
