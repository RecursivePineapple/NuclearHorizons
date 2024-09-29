package com.recursive_pineapple.nuclear_horizons.reactors.items.basic;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import org.lwjgl.input.Keyboard;

import com.recursive_pineapple.nuclear_horizons.Config;
import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.components.ComponentRegistry;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapterFactory;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.HeatMoverAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.items.HeatUtils;
import com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces.IHeatMover;

import cpw.mods.fml.common.registry.GameRegistry;

public class BasicHeatVentItem extends Item implements IHeatMover, IComponentAdapterFactory {

    private final String name;
    private final int maxHeatFromReactor;
    private final int maxNeighbourToAir;
    private final int maxHeatToAir;
    private final int maxHeat;

    public BasicHeatVentItem(String name, String textureName, int maxHeatFromReactor, int maxNeighbourToAir,
        int maxHeatToAir, int maxHeat) {
        setUnlocalizedName(name);
        setTextureName(NuclearHorizons.MODID + ":" + textureName);
        setMaxDamage(maxHeat);

        this.name = name;
        this.maxHeatFromReactor = maxHeatFromReactor;
        this.maxNeighbourToAir = maxNeighbourToAir;
        this.maxHeatToAir = maxHeatToAir;
        this.maxHeat = maxHeat;
    }

    public void register() {
        GameRegistry.registerItem(this, name);
        ComponentRegistry.registerAdapter(this, this);
    }

    @Override
    public int getDamage(ItemStack stack) {
        return HeatUtils.getNBTInt(stack, "neutrons", 0);
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        HeatUtils.setNBTInt(stack, "neutrons", damage);
    }

    @Override
    public int getStoredHeat(@Nonnull ItemStack itemStack) {
        return itemStack.getItemDamage();
    }

    @Override
    public int getRemainingHealth(@Nonnull ItemStack itemStack) {
        if (this.getMaxHeat(itemStack) == 0) {
            return 1;
        }
        
        return HeatUtils.getNBTInt(itemStack, "head", 0);
    }

    @Override
    public int addHeat(@Nonnull ItemStack itemStack, int heat) {
        NBTTagCompound tag = HeatUtils.getOrCreateTag(itemStack);

        int stored = tag.getInteger("heat");

        int consumed = HeatUtils.getConsumableHeat(this.maxHeat, stored, heat);

        tag.setInteger("heat", stored + consumed);
        
        return heat - consumed;
    }

    @Override
    public int getMaxHeat(@Nonnull ItemStack itemStack) {
        return this.maxHeat;
    }

    @Override
    public int getTransferFromReactor(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor) {
        return Math.min(this.maxHeatFromReactor, reactor.getHullHeat());
    }

    @Override
    public int getTransferFromNeighbour(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor,
        @Nonnull IComponentAdapter neighbour) {
        return 0;
    }

    @Override
    public int getTransferNeighbourToAir(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor,
        @Nonnull IComponentAdapter neighbour) {
        return Math.min(this.maxNeighbourToAir, neighbour.getStoredHeat());
    }

    @Override
    public int getTransferToAir(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor) {
        return Math.min(this.maxHeatToAir, this.getStoredHeat(itemStack));
    }

    @Override
    public boolean canAdaptItem(@Nonnull ItemStack itemStack) {
        return itemStack.getItem() == this;
    }

    @Override
    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
        int y) {
        return new HeatMoverAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> desc,
        boolean advancedItemTooltips) {
        super.addInformation(itemStack, player, desc, advancedItemTooltips);

        if (this.maxHeat > 0) {
            desc.add(I18n.format("nh_tooltip.stored_heat", this.getStoredHeat(itemStack), this.maxHeat));
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            desc.add(I18n.format("nh_tooltip.prelude"));

            if (this.maxHeatFromReactor > 0) {
                desc.add(I18n.format("nh_tooltip.mover.reactor_xfer", this.maxHeatFromReactor));
            }

            if (this.maxHeatToAir > 0) {
                desc.add(I18n.format("nh_tooltip.vent.void_self", this.maxHeatToAir));
            }

            if (this.maxNeighbourToAir > 0) {
                desc.add(I18n.format("nh_tooltip.vent.void_adj", this.maxNeighbourToAir));
            }

            if (this.maxHeatToAir > 0) {
                desc.add(I18n.format("nh_tooltip.vent.fluid_disclaimer", Config.FLUID_NUKE_HU_MULTIPLIER));
            }
        } else {
            desc.add(I18n.format("nh_tooltip.more_info"));
        }
    }
}
