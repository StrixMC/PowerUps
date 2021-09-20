package com.strixmc.powerups.powerup;

import com.strixmc.powerups.utils.ItemStackBuilder;
import com.strixmc.powerups.utils.MessageUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public class PowerUpsImpl implements PowerUp {

    private String ID;
    @Setter
    private String material;
    @Setter
    private short data;
    @Setter
    private String name;
    @Setter
    private boolean enabled;
    @Setter
    private int chance;
    private List<String> hologram;
    private List<String> actions;

    public PowerUpsImpl(String name) {
        this(name, 50);
    }

    public PowerUpsImpl(String name, int chance) {
        this(MessageUtils.strip(name.toUpperCase().trim()), name, chance);
    }

    public PowerUpsImpl(String ID, String name, int chance) {
        this(ID, "STONE", (short) 0, name, false, chance, Arrays.asList("&eDefault PowerUp", "&eHologram text."), Arrays.asList("[MESSAGE] Hey, this action works!", "[SOUND] ARROW_HIT;1.0;1.0"));
    }

    @Override
    public ItemStack getItem() {
        try {
            return new ItemStackBuilder(Material.matchMaterial(getMaterial())).data(getData());
        } catch (NullPointerException e) {
            for (int i = 0; i < 3; i++) {
                Bukkit.getLogger().warning(ID + " powerup have a wrong named material (\"" + ID + "\")");
            }
            return new ItemStack(Material.COAL_BLOCK);
        }
    }
}
