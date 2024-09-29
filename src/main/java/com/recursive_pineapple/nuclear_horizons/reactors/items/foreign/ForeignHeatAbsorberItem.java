package com.recursive_pineapple.nuclear_horizons.reactors.items.foreign;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapterFactory;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.HeatAbsorberAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.items.HeatUtils;
import com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces.IHeatContainer;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ForeignHeatAbsorberItem implements IHeatContainer, IComponentAdapterFactory {
    
    @Nonnull
    protected final Item item;
    protected final int maxHeat;
    protected final boolean consumable;

    @Nullable
    protected ItemStack product;
    
    public ForeignHeatAbsorberItem(@Nonnull Item item, int maxHeat, boolean consumable) {
        this.item = item;
        this.maxHeat = maxHeat;
        this.consumable = consumable;
    }

    public void setProduct(@Nullable ItemStack product) {
        this.product = product;
    }

    @Override
    public boolean canAdaptItem(@Nonnull ItemStack itemStack) {
        return itemStack.getItem() == item;
    }

    @Override
    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
        int y) {
        return new HeatAbsorberAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public int getStoredHeat(@Nonnull ItemStack itemStack) {
        return 0;
    }

    @Override
    public int getRemainingHealth(@Nonnull ItemStack itemStack) {
        return this.maxHeat - itemStack.getItemDamage();
    }

    @Override
    public int addHeat(@Nonnull ItemStack itemStack, int heat) {
        int consumed = HeatUtils.getConsumableHeat(this.maxHeat, itemStack.getItemDamage(), heat);

        itemStack.setItemDamage(itemStack.getItemDamage() + consumed);

        return heat - consumed;
    }

    @Override
    public int getMaxHeat(@Nonnull ItemStack itemStack) {
        return this.maxHeat;
    }

    @Override
    public boolean isConsumable(@Nonnull ItemStack itemStack) {
        return consumable;
    }

    @Override
    public @Nullable ItemStack getProduct(@Nonnull ItemStack itemStack) {
        return product;
    }
}
