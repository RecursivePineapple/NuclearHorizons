package com.recursive_pineapple.nuclear_horizons.reactors.items;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

import com.recursive_pineapple.nuclear_horizons.Config;
import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapterFactory;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.FuelRodAdapter;

public class BasicFuelRodItem extends Item implements IBasicFuelRod, IComponentAdapterFactory {

    private final double energyMult;
    private final double heatMult;
    private final int rodCount;
    private final boolean isMox;
    private final int maxHealth;
    private ItemStack product;

    public BasicFuelRodItem(String name, String textureName, double energyMult, double heatMult, int rodCount,
        boolean isMox, int maxHealth) {
        setUnlocalizedName(name);
        setTextureName(NuclearHorizons.MODID + ":" + textureName);
        setMaxDamage(maxHealth);

        this.energyMult = energyMult;
        this.heatMult = heatMult;
        this.rodCount = rodCount;
        this.isMox = isMox;
        this.maxHealth = maxHealth;
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
        return this.maxHealth - itemStack.getItemDamage();
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
    public boolean canAdaptItem(@Nonnull ItemStack itemStack) {
        return itemStack.getItem() == this;
    }

    @Override
    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
        int y) {
        return new FuelRodAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> desc,
        boolean advancedItemTooltips) {
        super.addInformation(itemStack, player, desc, advancedItemTooltips);

        if (!advancedItemTooltips) {
            desc.addAll(
                Arrays.asList(
                    I18n.format("nh_tooltip.durability", this.getRemainingHealth(itemStack), this.maxHealth)
                        .split("\\n")));
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            desc.add(I18n.format("nh_tooltip.prelude"));

            desc.addAll(
                Arrays.asList(
                    I18n.format(
                        "nh_tooltip.fuel_rod.gen_stats",
                        (int) (this.heatMult * Config.ROD_HU_MULTIPLIER),
                        (int) (this.energyMult * Config.ROD_EU_MULTIPLIER),
                        1 + this.rodCount / 2)
                        .split("\\n")));

            if (this.isMox) {
                desc.addAll(
                    Arrays.asList(
                        I18n.format("nh_tooltip.fuel_rod.mox_stats", Config.MOX_EU_COEFFICIENT)
                            .split("\\n")));
            }

            desc.addAll(
                Arrays.asList(
                    I18n.format("nh_tooltip.fuel_rod.heat_epilogue")
                        .split("\\n")));
        } else {
            desc.add(I18n.format("nh_tooltip.more_info"));
        }
    }
}
