package com.recursive_pineapple.nuclear_horizons.reactors.items.basic;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.HeatMoverAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.items.HeatUtils;
import com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces.IHeatMover;

public class BasicHeatExchangerItem extends ReactorItem implements IHeatMover {

    private final int maxHeatFromReactor;
    private final int maxHeatFromNeighbour;

    public BasicHeatExchangerItem(String name, String textureName, int maxHeatFromReactor, int maxHeatFromNeighbour,
        int maxHeat) {
        super(name, textureName, "heat", maxHeat);

        this.maxHeatFromReactor = maxHeatFromReactor;
        this.maxHeatFromNeighbour = maxHeatFromNeighbour;
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
    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
        int y) {
        return new HeatMoverAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public void addReactorItemInfo(ItemStack itemStack, EntityPlayer player, List<String> chunks) {
        chunks.add(I18n.format("nh_tooltip.prelude"));

        if (this.maxHeatFromReactor > 0) {
            chunks.add(I18n.format("nh_tooltip.mover.reactor_xfer", this.maxHeatFromReactor));
        }

        if (this.maxHeatFromNeighbour > 0) {
            chunks.add(I18n.format("nh_tooltip.exchanger.comp_xfer", this.maxHeatFromNeighbour));
        }
    }

    @Override
    protected String getDurabilityTooltip(ItemStack itemStack) {
        return I18n.format("nh_tooltip.stored_heat", itemStack.getItemDamage(), itemStack.getMaxDamage());
    }
}
