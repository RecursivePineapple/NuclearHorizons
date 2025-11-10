package com.recursive_pineapple.nuclear_horizons.reactors.items;

import net.minecraft.item.Item;

import cpw.mods.fml.common.registry.GameRegistry;

public class NHItem extends Item {

    public final String name;

    public NHItem(String name) {
        this.name = name;
        setUnlocalizedName(name);
    }

    public void registerPreInit() {
        GameRegistry.registerItem(this, name);
    }

    public void registerInit() {

    }

    public void registerPostInit() {

    }
}
