package com.recursive_pineapple.nuclear_horizons.reactors.items.basic;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.components.ComponentRegistry;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapterFactory;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.HeatAbsorberAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.items.NHItem;
import com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces.IHeatContainer;

public class DebugHeatAbsorber extends NHItem implements IHeatContainer, IComponentAdapterFactory {

    public DebugHeatAbsorber(String name, String textureName) {
        super(name);
        setTextureName(NuclearHorizons.MODID + ":" + textureName);
        setMaxDamage(1);
    }

    @Override
    public void registerPreInit() {
        super.registerPreInit();
        ComponentRegistry.registerAdapter(this, this);
    }

    @Override
    public boolean canAdaptItem(@Nonnull ItemStack itemStack) {
        return itemStack.getItem() == this;
    }

    @Override
    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
        int y) {
        return new HeatAbsorberAdapter(reactor, x, y, itemStack, this) {

            @Override
            public void onEnergyTick() {
                super.onEnergyTick();

                NBTTagCompound tag = itemStack.getTagCompound();

                if (tag != null) {
                    tag.removeTag("heat");
                }
            }
        };
    }

    @Override
    public int getStoredHeat(@Nonnull ItemStack itemStack) {
        return 0;
    }

    @Override
    public int getRemainingHealth(@Nonnull ItemStack itemStack) {
        return 1;
    }

    @Override
    public int addHeat(@Nonnull ItemStack itemStack, int heat) {
        if (itemStack.getTagCompound() == null) itemStack.setTagCompound(new NBTTagCompound());

        NBTTagCompound tag = itemStack.getTagCompound();

        tag.setInteger("heat", tag.getInteger("heat") + heat);

        return 0;
    }

    @Override
    public int getMaxHeat(@Nonnull ItemStack itemStack) {
        return 1;
    }

    @Override
    public boolean isConsumable(@Nonnull ItemStack itemStack) {
        return false;
    }

    @Override
    public @Nullable ItemStack getProduct(@Nonnull ItemStack itemStack) {
        return null;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> desc,
        boolean advancedItemTooltips) {
        super.addInformation(itemStack, player, desc, advancedItemTooltips);

        NBTTagCompound tag = itemStack.getTagCompound();

        desc.add(String.format("HU/s: ") + (tag == null ? 0 : tag.getInteger("heat")));
    }
}
