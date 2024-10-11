package com.recursive_pineapple.nuclear_horizons.reactors.items.basic;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

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

    public BasicFuelRodItem(String name, String textureName, double energyMult, double heatMult, int rodCount,
        boolean isMox, int maxHealth) {
        super(name, textureName, "damage", maxHealth);

        this.energyMult = energyMult;
        this.heatMult = heatMult;
        this.rodCount = rodCount;
        this.isMox = isMox;
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
    public void applyDamage(@Nonnull ItemStack itemStack, int damage) {
        itemStack.setItemDamage(itemStack.getItemDamage() + damage);
    }

    @Override
    public ItemStack getProduct(@Nonnull ItemStack itemStack) {
        return product;
    }

    public BasicFuelRodItem setProduct(@Nullable ItemStack product) {
        this.product = product;
        return this;
    }

    @Override
    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
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
}
