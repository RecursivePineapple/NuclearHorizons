package com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;

public interface IHeatContainer {

    public int getStoredHeat(@Nonnull ItemStack itemStack);

    public default int getRemainingHealth(@Nonnull ItemStack itemStack) {
        return this.getMaxHeat(itemStack) - this.getStoredHeat(itemStack);
    }

    public int addHeat(@Nonnull ItemStack itemStack, int heat);

    public int getMaxHeat(@Nonnull ItemStack itemStack);

    public boolean isConsumable(@Nonnull ItemStack itemStack);

    public @Nullable ItemStack getProduct(@Nonnull ItemStack itemStack);
}
