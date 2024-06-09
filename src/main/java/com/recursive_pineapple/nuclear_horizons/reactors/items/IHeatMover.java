package com.recursive_pineapple.nuclear_horizons.reactors.items;

import javax.annotation.Nonnull;

import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;

import net.minecraft.item.ItemStack;

public interface IHeatMover extends IHeatContainer {
    public int getTransferFromReactor(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor);
    public int getTransferFromNeighbour(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, @Nonnull IComponentAdapter neighbour);
    public int getTransferNeighbourToAir(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, @Nonnull IComponentAdapter neighbour);
    public int getTransferToAir(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor);
}
