package com.recursive_pineapple.nuclear_horizons.reactors.items;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;

public interface INeutronReflector {
    public int getRemainingHealth(@Nonnull ItemStack itemStack);
    public void applyDamage(@Nonnull ItemStack itemStack, int damage);

    public boolean canReflectNeutrons(@Nonnull ItemStack itemStack);
}
