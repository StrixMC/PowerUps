package com.strixmc.powerups.powerup;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PowerUp implements ConfigurationSerializable {

    final String ID;
    String name;
    String material;

    short data;
    int chance;
    boolean enabled;

    List<String> hologram;
    List<String> actions;

    public PowerUp(String ID) {
        this(ID, "Default Name", "COAL_BLOCK", (short) 0, 50, false, new ArrayList<>(), new ArrayList<>());
    }

    public PowerUp(Map<String, Object> map) {
        this.ID = (String) map.get("UNIQUE");
        this.enabled = (boolean) map.get("ENABLED");
        this.name = (String) map.get("NAME");
        this.material = (String) map.get("MATERIAL");
        this.data = map.get("DATA") == null ? 0 : ((Integer) map.get("DATA")).shortValue();
        this.chance = map.get("CHANCE") == null ? 50 : (int) map.get("CHANCE");
        this.hologram = map.get("HOLOGRAM") == null ? new ArrayList<>() : (List<String>) map.get("HOLOGRAM");
        this.actions = map.get("ACTIONS") == null ? new ArrayList<>() : (List<String>) map.get("ACTIONS");
    }

    public PowerUp(String ID, String name, String material, short data, int chance, boolean enabled, List<String> hologram, List<String> actions) {
        this.ID = ID.toUpperCase();
        this.name = name;
        this.material = material;
        this.data = data;
        this.chance = chance;
        this.enabled = enabled;
        this.hologram = hologram;
        this.actions = actions;
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("UNIQUE", this.ID);
        map.put("ENABLED", this.enabled);
        map.put("NAME", this.name);
        map.put("MATERIAL", this.material);
        map.put("DATA", this.data);
        map.put("CHANCE", this.chance);
        map.put("HOLOGRAM", this.hologram);
        map.put("ACTIONS", this.actions);

        return map;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public short getData() {
        return data;
    }

    public void setData(short data) {
        this.data = data;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getHologram() {
        return hologram;
    }

    public List<String> getActions() {
        return actions;
    }
}
