package com.recursive_pineapple.nuclear_horizons.reactors.components.adapters;

import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.items.IHeatContainer;

import net.minecraft.item.ItemStack;

public class HeatAbsorberAdapter implements IComponentAdapter {
    private final IReactorGrid reactor;
    private final int x, y;
    private final ItemStack itemStack;
    private final IHeatContainer heatContainer;

    public HeatAbsorberAdapter(IReactorGrid reactor, int x, int y, ItemStack itemStack, IHeatContainer heatContainer) {
        this.reactor = reactor;
        this.x = x;
        this.y = y;
        this.itemStack = itemStack;
        this.heatContainer = heatContainer;
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
    public boolean containsHeat() {
        return this.heatContainer.getMaxHeat(itemStack) > 0;
    }

    @Override
    public int getStoredHeat() {
        return this.heatContainer.getStoredHeat(itemStack);
    }

    @Override
    public int addHeat(int delta) {
        int rejected = this.heatContainer.addHeat(itemStack, delta);

        if(this.heatContainer.getRemainingHealth(itemStack) <= 0 && this.heatContainer.isConsumable(itemStack)) {
            this.reactor.setItem(x, y, null);
        }

        return rejected;
    }
}
