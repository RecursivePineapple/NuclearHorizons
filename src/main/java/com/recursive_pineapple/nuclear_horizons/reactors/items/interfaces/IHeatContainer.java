package com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;

import com.recursive_pineapple.nuclear_horizons.reactors.items.HeatUtils;

public interface IHeatContainer {

    public default int getStoredHeat(@Nonnull ItemStack itemStack) {
        return itemStack.getItemDamage();
    }

    public default int getRemainingHealth(@Nonnull ItemStack itemStack) {
        if (!itemStack.isItemStackDamageable()) {
            return 1;
        }

        return itemStack.getMaxDamage() - itemStack.getItemDamage();
    }

    public default int addHeat(@Nonnull ItemStack itemStack, int heat) {
        if (!itemStack.isItemStackDamageable()) {
            return heat;
        }

        int stored = itemStack.getItemDamage();

        int consumed = HeatUtils.getConsumableHeat(itemStack.getMaxDamage(), stored, heat);

        itemStack.setItemDamage(stored + consumed);

        return heat - consumed;
    }

    public default int getMaxHeat(@Nonnull ItemStack itemStack) {
        return itemStack.getMaxDamage();
    }

    public boolean isConsumable(@Nonnull ItemStack itemStack);

    public @Nullable ItemStack getProduct(@Nonnull ItemStack itemStack);
}
