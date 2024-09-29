package com.recursive_pineapple.nuclear_horizons.reactors.items.foreign;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapterFactory;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.BreederRodAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces.IBreederRod;

public class ForeignBreederRodItem implements IBreederRod, IComponentAdapterFactory {

    @Nonnull
    private final Item item;

    private final int heatDivisor, heatMultiplier, maxNeutrons;

    @Nullable
    private ItemStack product;

    public ForeignBreederRodItem(@Nonnull Item item, int heatDivisor, int heatMultiplier, int maxNeutrons) {
        this.item = item;
        this.heatDivisor = heatDivisor;
        this.heatMultiplier = heatMultiplier;
        this.maxNeutrons = maxNeutrons;
    }

    @Override
    public boolean canAdaptItem(@Nonnull ItemStack itemStack) {
        return itemStack.getItem() == item;
    }

    @Override
    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
        int y) {
        return new BreederRodAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public ItemStack getProduct(@Nonnull ItemStack itemStack) {
        return product;
    }

    @Override
    public int getMaxNeutrons(@Nonnull ItemStack itemStack) {
        return maxNeutrons;
    }

    @Override
    public int getStoredNeutrons(@Nonnull ItemStack itemStack) {
        return itemStack.getItemDamage();
    }

    @Override
    public void setNeutrons(@Nonnull ItemStack itemStack, int neutrons) {
        itemStack.setItemDamage(neutrons);
    }

    @Override
    public int getReactorHeatDivisor(@Nonnull ItemStack itemStack) {
        return heatDivisor;
    }

    @Override
    public int getHeatMultiplier(@Nonnull ItemStack itemStack) {
        return heatMultiplier;
    }

    public void setProduct(@Nullable ItemStack product) {
        this.product = product;
    }
}
