package com.strixmc.powerups.utils;

import lombok.SneakyThrows;
import org.bukkit.plugin.Plugin;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.File;

public class YamlCreator extends YamlFile {

    private final Plugin plugin;
    private final String fileName;
    private final File file;

    public YamlCreator(Plugin plugin, String fileName) {
        this(plugin, fileName, plugin.getDataFolder());
    }

    public YamlCreator(Plugin plugin, String fileName, String folderName) {
        this(plugin, fileName, new File(plugin.getDataFolder().getAbsolutePath() + File.separator + folderName));
    }

    public YamlCreator(Plugin plugin, String fileName, File folder) {
        this.plugin = plugin;
        this.fileName = fileName + (fileName.endsWith(".yml") ? "" : ".yml");
        if (folder.exists()) folder.mkdir();
        this.file = new File(folder, this.fileName);
        setConfigurationFile(file);
    }

    public void createFile() {
        createFile(false);
    }

    @SneakyThrows
    public void createFile(boolean withComments) {
        if (!file.exists()) {
            if (this.plugin.getResource(this.fileName) != null) {
                this.plugin.saveResource(this.fileName, false);
            } else {
                this.createNewFile(true);
            }

            loadFile(true);
            return;
        }

        loadFile(true);
        save(file);
    }

    @SneakyThrows
    private void loadFile(boolean withComments) {
        if (withComments) {
            this.loadWithComments();
            return;
        }

        this.load();
    }

    public void clearFile() {
        this.getKeys(false).forEach(s -> set(s, null));
    }

    public void reload() {
        this.reload(false);
    }

    public void reload(boolean withComments) {
        loadFile(withComments);
    }

}
