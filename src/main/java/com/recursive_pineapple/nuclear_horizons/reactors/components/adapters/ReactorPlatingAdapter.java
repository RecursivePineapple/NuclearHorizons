package com.recursive_pineapple.nuclear_horizons.reactors.components.adapters;

import net.minecraft.item.ItemStack;

import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.items.IReactorPlating;

public class ReactorPlatingAdapter implements IComponentAdapter {

    @SuppressWarnings("unused")
    private final IReactorGrid reactor;
    private final int x, y;
    private final ItemStack itemStack;
    private final IReactorPlating plating;

    public ReactorPlatingAdapter(IReactorGrid reactor, int x, int y, ItemStack itemStack, IReactorPlating plating) {
        this.reactor = reactor;
        this.x = x;
        this.y = y;
        this.itemStack = itemStack;
        this.plating = plating;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public double getExplosionRadiusMultiplier() {
        return plating.getExplosionRadiusMultiplier(itemStack);
    }

    @Override
    public int getReactorMaxHeatIncrease() {
        return plating.getReactorMaxHeatIncrease(itemStack);
    }
}
