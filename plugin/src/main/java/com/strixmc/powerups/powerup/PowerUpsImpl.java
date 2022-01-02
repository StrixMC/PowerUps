package com.strixmc.powerups.powerup;

import com.strixmc.acid.messages.MessageUtils;
import com.strixmc.powerups.utils.ItemStackBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PowerUpsImpl {

    private String ID;
    private String material;
    private short data;
    private String name;
    private boolean enabled;
    private int chance;
    private List<String> hologram;
    private List<String> actions;

    public PowerUpsImpl(Map<String, Object> map) {
        this.ID = (String) map.get("UNIQUE");
        this.name = (String) map.get("NAME");
        this.material = (String) map.get("MATERIAL");
        this.data = Short.parseShort(map.get("DATA").toString());
        this.chance = (int) map.get("CHANCE");
        this.enabled = (boolean) map.get("ENABLED");
        this.hologram = (List<String>) map.get("HOLOGRAMS");
        this.actions = (List<String>) map.get("ACTIONS");
    }

    public PowerUpsImpl(String name) {
        this(name, 50);
    }

    public PowerUpsImpl(String name, int chance) {
        this(MessageUtils.strip(name.toUpperCase().trim()), name, chance);
    }

    public PowerUpsImpl(String ID, String name, int chance) {
        this(ID, "STONE", (short) 0, name, false, chance, Arrays.asList("&eDefault PowerUp", "&eHologram text."), Arrays.asList("[MESSAGE] Hey, this action works!", "[SOUND] ARROW_HIT;1.0;1.0"));
    }

    public PowerUpsImpl(String ID, String material, short data, String name, boolean enabled, int chance, List<String> hologram, List<String> actions) {
        this.ID = ID;
        this.material = material;
        this.data = data;
        this.name = name;
        this.enabled = enabled;
        this.chance = chance;
        this.hologram = hologram;
        this.actions = actions;
    }

    /*public ItemStack getItem() {
        try {
            return new ItemStackBuilder(Material.matchMaterial(getMaterial())).data(getData());
        } catch (NullPointerException e) {
            for (int i = 0; i < 3; i++) {
                Bukkit.getLogger().warning(ID + " powerup have a wrong named material (\"" + ID + "\")");
            }
            return new ItemStack(Material.COAL_BLOCK);
        }
    }*/

    @NotNull
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();

        map.put("ENABLED", enabled);
        map.put("UNIQUE", ID);
        map.put("NAME", name);
        map.put("MATERIAL", material);
        map.put("DATA", data);
        map.put("CHANCE", chance);
        map.put("HOLOGRAMS", hologram);
        map.put("ACTIONS", actions);

        return map;
    }

    public static PowerUpsImpl deserialize(Map<String, Object> map) {
        return new PowerUpsImpl(map);
    }

}
