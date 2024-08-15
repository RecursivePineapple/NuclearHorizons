package com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;

public interface IReactorPlating {

    public default double getExplosionRadiusMultiplier(@Nonnull ItemStack itemStack) {
        return 1.0;
    }

    public default int getReactorMaxHeatIncrease(@Nonnull ItemStack itemStack) {
        return 0;
    }
}
