package com.recursive_pineapple.nuclear_horizons.reactors.items.basic;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapterFactory;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.HeatMoverAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.items.HeatUtils;
import com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces.IHeatMover;

public class BasicHeatExchangerItem extends Item implements IHeatMover, IComponentAdapterFactory {

    private final int maxHeatFromReactor;
    private final int maxHeatFromNeighbour;
    private final int maxHeat;

    public BasicHeatExchangerItem(String name, String textureName, int maxHeatFromReactor, int maxHeatFromNeighbour,
        int maxHeat) {
        setUnlocalizedName(name);
        setTextureName(NuclearHorizons.MODID + ":" + textureName);
        setMaxDamage(maxHeat);

        this.maxHeatFromReactor = maxHeatFromReactor;
        this.maxHeatFromNeighbour = maxHeatFromNeighbour;
        this.maxHeat = maxHeat;
    }

    @Override
    public int getStoredHeat(@Nonnull ItemStack itemStack) {
        return itemStack.getItemDamage();
    }

    @Override
    public int addHeat(@Nonnull ItemStack itemStack, int heat) {
        int consumed = HeatUtils.getConsumableHeat(this.maxHeat, this.getStoredHeat(itemStack), heat);

        itemStack.setItemDamage(itemStack.getItemDamage() + consumed);

        return heat - consumed;
    }

    @Override
    public int getMaxHeat(@Nonnull ItemStack itemStack) {
        return this.maxHeat;
    }

    @Override
    public int getTransferFromReactor(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor) {
        return HeatUtils
            .getTransferAmount(reactor.getHullHeat(), this.getStoredHeat(itemStack), this.maxHeatFromReactor);
    }

    @Override
    public int getTransferFromNeighbour(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor,
        @Nonnull IComponentAdapter neighbour) {
        return HeatUtils
            .getTransferAmount(neighbour.getStoredHeat(), this.getStoredHeat(itemStack), this.maxHeatFromNeighbour);
    }

    @Override
    public int getTransferNeighbourToAir(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor,
        @Nonnull IComponentAdapter neighbour) {
        return 0;
    }

    @Override
    public int getTransferToAir(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor) {
        return 0;
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
            desc.add(I18n.format("nh_tooltip.durability", this.getStoredHeat(itemStack), this.maxHeat));
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            desc.add(I18n.format("nh_tooltip.prelude"));

            if (this.maxHeatFromReactor > 0) {
                desc.add(I18n.format("nh_tooltip.mover.reactor_xfer", this.maxHeatFromReactor));
            }

            if (this.maxHeatFromNeighbour > 0) {
                desc.add(I18n.format("nh_tooltip.exchanger.comp_xfer", this.maxHeatFromNeighbour));
            }
        } else {
            desc.add(I18n.format("nh_tooltip.more_info"));
        }
    }
}
