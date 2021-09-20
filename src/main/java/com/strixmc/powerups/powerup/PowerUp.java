package com.strixmc.powerups.powerup;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface PowerUp {

    String getID();

    String getMaterial();

    void setMaterial(String material);

    short getData();

    void setData(short data);

    String getName();

    void setName(String name);

    boolean isEnabled();

    void setEnabled(boolean bool);

    int getChance();

    void setChance(int chance);

    List<String> getHologram();

    List<String> getActions();

    ItemStack getItem();

}
