package com.strixmc.powerups.utils;

import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class FileCreator extends YamlConfiguration {

    private final String fileName;
    private final Plugin plugin;
    private final File file;

    public FileCreator(Plugin plugin, String filename, File folder) {
        this.plugin = plugin;
        this.fileName = filename + (filename.endsWith(".yml") ? "" : ".yml");
        if (!folder.exists()) {
            folder.mkdir();
        }
        this.file = new File(folder, this.fileName);
        this.createFile();
    }

    public FileCreator(Plugin plugin, String fileName) {
        this(plugin, fileName, plugin.getDataFolder());
    }

    public FileCreator(Plugin plugin, String fileName, String filePath) {
        this(plugin, fileName, new File(plugin.getDataFolder().getAbsolutePath() + File.separator + filePath));
    }

    @SneakyThrows
    private void createFile() {
        if (!file.exists()) {
            if (this.plugin.getResource(this.fileName) != null) {
                this.plugin.saveResource(this.fileName, false);
            } else {
                this.save(file);
            }
            this.load(file);
            return;
        }
        this.load(file);

        this.save(file);
    }

    @SneakyThrows
    public void reload() {
        load(file);
    }

    public void save() throws IOException {
        this.save(file);
    }

    public void clearFile() {
        this.getKeys(false).forEach(s -> set(s, null));
    }

    public void destroy() {
        file.delete();
    }

}