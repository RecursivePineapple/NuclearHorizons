package com.recursive_pineapple.nuclear_horizons.reactors.items.basic;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.recursive_pineapple.nuclear_horizons.Config;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.FuelRodAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces.IBasicFuelRod;

public class BasicFuelRodItem extends ReactorItem implements IBasicFuelRod {

    private final double energyMult;
    private final double heatMult;
    private final int rodCount;
    private final boolean isMox;
    private ItemStack product;
    private Fluid spargeGas;
    private int spargeMin, spargeMax;

    public BasicFuelRodItem(String name, String textureName, double energyMult, double heatMult, int rodCount,
        boolean isMox, int maxHealth) {
        super(name, textureName, "damage", maxHealth);
        this.energyMult = energyMult;
        this.heatMult = heatMult;
        this.rodCount = rodCount;
        this.isMox = isMox;
    }

    @Override
    public double getEnergyMult(@NotNull ItemStack itemStack) {
        return energyMult;
    }

    @Override
    public double getHeatMult(@NotNull ItemStack itemStack) {
        return heatMult;
    }

    @Override
    public int getRodCount(@NotNull ItemStack itemStack) {
        return rodCount;
    }

    @Override
    public boolean isMox(@NotNull ItemStack itemStack) {
        return isMox;
    }

    @Override
    public void applyDamage(@NotNull ItemStack itemStack, int damage) {
        itemStack.setItemDamage(itemStack.getItemDamage() + damage);
    }

    @Override
    public ItemStack getProduct(@NotNull ItemStack itemStack) {
        return product;
    }

    @Override
    public @Nullable FluidStack getSpargeGas(@NotNull ItemStack itemStack) {
        if (spargeGas == null) return null;

        Random rng = ThreadLocalRandom.current();

        return new FluidStack(spargeGas, (int) (spargeMin + (spargeMax - spargeMin) * rng.nextFloat()));
    }

    public BasicFuelRodItem setProduct(@Nullable ItemStack product) {
        this.product = product;
        return this;
    }

    public BasicFuelRodItem setSpargeGas(Fluid fluid, int min, int max) {
        this.spargeGas = fluid;
        this.spargeMin = min;
        this.spargeMax = max;
        return this;
    }

    @Override
    public @NotNull IComponentAdapter getAdapter(@NotNull ItemStack itemStack, @NotNull IReactorGrid reactor, int x,
        int y) {
        return new FuelRodAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public void addReactorItemInfo(ItemStack itemStack, EntityPlayer player, List<String> chunks) {
        chunks.add(
            I18n.format(
                "nh_tooltip.fuel_rod.gen_stats",
                (int) (this.heatMult * Config.ROD_HU_MULTIPLIER),
                (int) (this.energyMult * Config.ROD_EU_MULTIPLIER),
                1 + this.rodCount / 2));

        if (this.isMox) {
            chunks.add(I18n.format("nh_tooltip.fuel_rod.mox_stats", Config.MOX_EU_COEFFICIENT));
        }

        chunks.add(I18n.format("nh_tooltip.fuel_rod.heat_epilogue"));
    }

    @Override
    protected String getDurabilityTooltip(ItemStack itemStack) {
        return I18n.format("nh_tooltip.rod.lifetime", this.getRemainingHealth(itemStack), itemStack.getMaxDamage());
    }
}
