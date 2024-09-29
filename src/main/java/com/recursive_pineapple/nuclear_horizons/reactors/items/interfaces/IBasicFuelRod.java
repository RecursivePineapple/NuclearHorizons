package com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.recursive_pineapple.nuclear_horizons.Config;

import net.minecraft.item.ItemStack;

public interface IBasicFuelRod {

    public double getEnergyMult(@Nonnull ItemStack itemStack);

    public double getHeatMult(@Nonnull ItemStack itemStack);

    public int getRodCount(@Nonnull ItemStack itemStack);

    public boolean isMox(@Nonnull ItemStack itemStack);

    public default double getMoxHeatCoefficient(@Nonnull ItemStack itemStack) {
        return 2.0;
    }

    public default double getMoxEUCoefficient(@Nonnull ItemStack itemStack) {
        return Config.MOX_EU_COEFFICIENT;
    }

    public int getRemainingHealth(@Nonnull ItemStack itemStack);

    public void applyDamage(@Nonnull ItemStack itemStack, int damage);

    public @Nullable ItemStack getProduct(@Nonnull ItemStack itemStack);

}
