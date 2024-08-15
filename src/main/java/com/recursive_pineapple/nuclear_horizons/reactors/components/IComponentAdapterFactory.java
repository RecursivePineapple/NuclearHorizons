package com.recursive_pineapple.nuclear_horizons.reactors.components;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;

public interface IComponentAdapterFactory {

    public boolean canAdaptItem(@Nonnull ItemStack itemStack);

    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
        int y);
}
