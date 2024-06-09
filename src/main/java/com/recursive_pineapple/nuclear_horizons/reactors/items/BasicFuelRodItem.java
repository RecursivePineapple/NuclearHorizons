package com.recursive_pineapple.nuclear_horizons.reactors.items;

import java.util.List;

import javax.annotation.Nonnull;

import org.lwjgl.input.Keyboard;

import com.recursive_pineapple.nuclear_horizons.Config;
import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapterFactory;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.FuelRodAdapter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BasicFuelRodItem extends Item implements IBasicFuelRod, IComponentAdapterFactory {

    private final double energyMult;
    private final double heatMult;
    private final int rodCount;
    private final boolean isMox;
    private final int maxHealth;

    public BasicFuelRodItem(String name, String textureName, double energyMult, double heatMult, int rodCount, boolean isMox, int maxHealth) {
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
    public boolean canAdaptItem(@Nonnull ItemStack itemStack) {
        return itemStack.getItem() == this;
    }

    @Override
    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x, int y) {
        return new FuelRodAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> desc, boolean advancedItemTooltips) {
        super.addInformation(itemStack, player, desc, advancedItemTooltips);

        if(!advancedItemTooltips) {
            desc.add(String.format("Durability: %,d / %,d", this.getRemainingHealth(itemStack), this.maxHealth));
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            desc.add("Every two seconds, this component will:");
            
            desc.add(String.format("Generate %,d * n * (n + 1) HU", (int)this.heatMult));
            desc.add(String.format("Generate %,d * n * (n + 1) EU", (int)(this.energyMult * Config.ROD_EU_MULTIPLIER)));

            desc.add(String.format("Where n = %d + Number of adjacent rods and reflectors", 1 + this.rodCount / 2));

            if(this.isMox) {
                desc.add("");
                desc.add("If the reactor's hull heat is above 50%, the HU output will be multiplied by 2.");
                desc.add(String.format("The EU output will always be multiplied by 1 + %f * (Current Heat / Max Heat).", Config.MOX_EU_COEFFICIENT));
            }

            desc.add("");
            desc.add("If there are adjacent components which can contain heat, heat will be spread among them evenly.");
            desc.add("Otherwise, heat will be added to the reactor hull.");
        } else {
            desc.add("Hold Shift for more info.");
        }
    }
}
