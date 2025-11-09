package com.recursive_pineapple.nuclear_horizons.reactors.items.basic;

import net.minecraft.item.Item;

import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import cpw.mods.fml.common.registry.GameRegistry;

public class BasicFuelRodProductItem extends Item {

    private final String name;

    public BasicFuelRodProductItem(String name, String textureName) {
        setUnlocalizedName(name);
        setTextureName(NuclearHorizons.MODID + ":" + textureName);

        this.name = name;
    }

    public void register() {
        GameRegistry.registerItem(this, name);
    }
}
