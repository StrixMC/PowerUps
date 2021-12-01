package com.strixmc.powerups;

import com.strixmc.acid.files.FileCreator;
import com.strixmc.powerups.services.PluginService;
import com.strixmc.powerups.utils.Messages;
import com.strixmc.powerups.utils.YamlCreator;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class PowerUps extends JavaPlugin {

    @Getter
    private FileCreator lang;
    @Getter
    private YamlCreator configuration;
    @Getter
    private PluginService pluginService;

    @Override
    public void onLoad() {
        this.lang = new FileCreator(this, "lang");
        this.configuration = new YamlCreator(this, "config");
        configuration.createFile(true);

        Messages.setLang(this.lang);
    }

    @Override
    public void onEnable() {
        this.pluginService = new PluginService(this);
        if (!this.pluginService.isInitialized() || this.pluginService == null) {
            getLogger().log(Level.SEVERE, "Something went wrong while enabling the plugin.");
            getLogger().log(Level.SEVERE, "Please notify developer about it.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        if (pluginService != null) {
            pluginService.finish();
        }
    }
}
