package com.recursive_pineapple.nuclear_horizons.reactors.components.adapters;

import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.InventoryDirection;
import com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces.IBreederRod;

import net.minecraft.item.ItemStack;

public class BreederRodAdapter implements IComponentAdapter {

    private final IReactorGrid reactor;
    private final int x, y;
    private final ItemStack itemStack;
    private final IBreederRod breederRod;

    public BreederRodAdapter(IReactorGrid reactor, int x, int y, ItemStack itemStack, IBreederRod breederRod) {
        this.reactor = reactor;
        this.x = x;
        this.y = y;
        this.itemStack = itemStack;
        this.breederRod = breederRod;
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
    public void onHeatTick() {
        if(!reactor.isActive()) {
            return;
        }

        int neighbouringRods = 0;

        for (var dir : InventoryDirection.values()) {
            int x2 = dir.offsetX(x);
            int y2 = dir.offsetY(y);

            if (x2 < 0 || y2 < 0 || x2 >= reactor.getWidth() || y2 >= reactor.getHeight()) {
                continue;
            }

            var neighbour = reactor.getComponent(x2, y2);

            if (neighbour != null) {
                neighbouringRods += neighbour.getFuelRodCount();
            }
        }

        int heatMultiplier = 1 + reactor.getHullHeat() / breederRod.getReactorHeatDivisor(itemStack) * breederRod.getHeatMultiplier(itemStack);

        int storedNeutrons = breederRod.getStoredNeutrons(itemStack);

        storedNeutrons += neighbouringRods * heatMultiplier;

        int max = breederRod.getMaxNeutrons(itemStack);

        storedNeutrons = Math.min(storedNeutrons, max);

        breederRod.setNeutrons(itemStack, storedNeutrons);

        if (storedNeutrons >= max) {
            reactor.setItem(x, y, ItemStack.copyItemStack(breederRod.getProduct(itemStack)));
        }
    }
}
