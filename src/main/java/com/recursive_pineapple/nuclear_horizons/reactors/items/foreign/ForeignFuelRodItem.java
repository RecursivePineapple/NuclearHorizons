package com.recursive_pineapple.nuclear_horizons.reactors.items.foreign;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;

import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapterFactory;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.FuelRodAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.items.basic.BasicFuelRodItem;
import com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces.IBasicFuelRod;

public class ForeignFuelRodItem implements IBasicFuelRod, IComponentAdapterFactory {

    @Nonnull
    protected final Item item;
    protected final double energyMult;
    protected final double heatMult;
    protected final int rodCount;
    protected final boolean isMox;
    protected final int maxHealth;

    @Nullable
    protected ItemStack product;
    private Fluid spargeGas;
    private int spargeMin, spargeMax;

    public ForeignFuelRodItem(@Nonnull Item item, double energyMult, double heatMult, int rodCount, boolean isMox,
        int maxHealth) {
        this.item = item;
        this.energyMult = energyMult;
        this.heatMult = heatMult;
        this.rodCount = rodCount;
        this.isMox = isMox;
        this.maxHealth = maxHealth;
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
        return new FuelRodAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public double getEnergyMult(@Nonnull ItemStack itemStack) {
        return energyMult;
    }

    @Override
    public double getHeatMult(@Nonnull ItemStack itemStack) {
        return heatMult;
    }

    @Override
    public int getRodCount(@Nonnull ItemStack itemStack) {
        return rodCount;
    }

    @Override
    public boolean isMox(@Nonnull ItemStack itemStack) {
        return isMox;
    }

    @Override
    public int getRemainingHealth(@Nonnull ItemStack itemStack) {
        return maxHealth - itemStack.getItemDamage();
    }

    @Override
    public void applyDamage(@Nonnull ItemStack itemStack, int damage) {
        itemStack.setItemDamage(itemStack.getItemDamage() + damage);
    }

    @Override
    public @Nullable ItemStack getProduct(@Nonnull ItemStack itemStack) {
        return product;
    }

    @Override
    public @org.jetbrains.annotations.Nullable FluidStack getSpargeGas(@NotNull ItemStack itemStack) {
        if (spargeGas == null) return null;

        Random rng = ThreadLocalRandom.current();

        return new FluidStack(spargeGas, (int) (spargeMin + (spargeMax - spargeMin) * rng.nextFloat()));
    }

    public ForeignFuelRodItem setSpargeGas(Fluid fluid, int min, int max) {
        this.spargeGas = fluid;
        this.spargeMin = min;
        this.spargeMax = max;
        return this;
    }
}
