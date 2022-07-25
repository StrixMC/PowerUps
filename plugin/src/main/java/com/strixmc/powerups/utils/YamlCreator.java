package com.strixmc.powerups.utils;

import org.bukkit.plugin.Plugin;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.File;
import java.io.IOException;

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
        if (!folder.exists()) folder.mkdir();
        this.file = new File(folder, this.fileName);
        setConfigurationFile(file);
    }

    public void createFile() {
        createFile(false);
    }

    public void createFile(boolean withComments) {
        if (!file.exists()) {
            if (this.plugin.getResource(this.fileName) != null) {
                this.plugin.saveResource(this.fileName, false);
            } else {
                try {
                    this.createNewFile(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            loadFile(true);
            return;
        }

        loadFile(true);
        try {
            save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFile(boolean withComments) {
        try {
            if (withComments) {
                this.loadWithComments();
            } else {
                this.load();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
