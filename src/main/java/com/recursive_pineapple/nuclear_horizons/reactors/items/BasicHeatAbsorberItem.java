package com.recursive_pineapple.nuclear_horizons.reactors.items;

import java.util.List;

import javax.annotation.Nonnull;

import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapterFactory;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.HeatAbsorberAdapter;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BasicHeatAbsorberItem extends Item implements IHeatContainer, IComponentAdapterFactory {
    
    private final int maxHeat;
    private final boolean consumable;

    public BasicHeatAbsorberItem(String name, String textureName, int maxHeat, boolean consumable) {
        setUnlocalizedName(name);
        setTextureName(NuclearHorizons.MODID + ":" + textureName);
        setMaxDamage(maxHeat);

        this.maxHeat = maxHeat;
        this.consumable = consumable;
    }

    @Override
    public boolean canAdaptItem(@Nonnull ItemStack itemStack) {
        return itemStack.getItem() == this;
    }

    @Override
    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x, int y) {
        return new HeatAbsorberAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public int getStoredHeat(@Nonnull ItemStack itemStack) {
        return 0;
    }

    @Override
    public int getRemainingHealth(@Nonnull ItemStack itemStack) {
        return this.maxHeat - itemStack.getItemDamage();
    }

    @Override
    public int addHeat(@Nonnull ItemStack itemStack, int heat) {
        int consumed = HeatUtils.getConsumableHeat(this.maxHeat, itemStack.getItemDamage(), heat);

        itemStack.setItemDamage(itemStack.getItemDamage() + consumed);

        return heat - consumed;
    }

    @Override
    public int getMaxHeat(@Nonnull ItemStack itemStack) {
        return this.maxHeat;
    }

    @Override
    public boolean isConsumable(@Nonnull ItemStack itemStack) {
        return consumable;
    }
    
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> desc, boolean advancedItemTooltips) {
        super.addInformation(itemStack, player, desc, advancedItemTooltips);

        desc.add(I18n.format("nh_tooltip.durability", itemStack.getItemDamage(), this.maxHeat));
    }
}
