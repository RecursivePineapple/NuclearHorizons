package com.recursive_pineapple.nuclear_horizons.reactors.items.basic;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> desc,
        boolean advancedItemTooltips) {
        super.addInformation(itemStack, player, desc, advancedItemTooltips);
        desc.add(I18n.format("nh_tooltip.more_info"));
    }
}
