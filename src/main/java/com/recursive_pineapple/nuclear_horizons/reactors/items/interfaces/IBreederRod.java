package com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;

public interface IBreederRod {
    public ItemStack getProduct(@Nonnull ItemStack itemStack);

    public int getMaxNeutrons(@Nonnull ItemStack itemStack);

    public int getStoredNeutrons(@Nonnull ItemStack itemStack);

    public void setNeutrons(@Nonnull ItemStack itemStack, int neutrons);

    public int getReactorHeatDivisor(@Nonnull ItemStack itemStack);

    public int getHeatMultiplier(@Nonnull ItemStack itemStack);
}
