package com.recursive_pineapple.nuclear_horizons.reactors.items.basic;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.recursive_pineapple.nuclear_horizons.Config;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.HeatMoverAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces.IHeatMover;

public class BasicHeatVentCoolantItem extends ReactorItem implements IHeatMover {

    private final int maxHeatFromReactor;
    private final int maxNeighbourToAir;
    private final int maxHeatToAir;

    public BasicHeatVentCoolantItem(String name, String textureName, int maxHeatFromReactor, int maxNeighbourToAir,
        int maxHeatToAir, int maxHeat) {
        super(name, textureName, "heat", maxHeat);

        this.maxHeatFromReactor = maxHeatFromReactor;
        this.maxNeighbourToAir = maxNeighbourToAir;
        this.maxHeatToAir = maxHeatToAir;
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
    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
        int y) {
        return new CoolantHeatMoverAdapter(reactor, x, y, itemStack, this);
    }

    private static class CoolantHeatMoverAdapter extends HeatMoverAdapter {

        public CoolantHeatMoverAdapter(IReactorGrid reactor, int x, int y, ItemStack itemStack, IHeatMover heatMover) {
            super(reactor, x, y, itemStack, heatMover);
        }

        @Override
        protected int getTransferToAir() {
            return reactor.isFluid() ? super.getTransferToAir() : 0;
        }

        @Override
        protected int getTransferNeighbourToAir(IComponentAdapter neighbour) {
            return reactor.isFluid() ? super.getTransferNeighbourToAir(neighbour) : 0;
        }
    }

    @Override
    protected String getDurabilityTooltip(ItemStack itemStack) {
        return I18n.format("nh_tooltip.stored_heat", itemStack.getItemDamage(), itemStack.getMaxDamage());
    }

    @Override
    public void addReactorItemInfo(ItemStack itemStack, EntityPlayer player, List<String> chunks) {
        chunks.add(I18n.format("nh_tooltip.prelude"));

        if (this.maxHeatFromReactor > 0) {
            chunks.add(I18n.format("nh_tooltip.mover.reactor_xfer", this.maxHeatFromReactor));
        }

        if (this.maxHeatToAir > 0) {
            chunks.add(I18n.format("nh_tooltip.vent.void_self", this.maxHeatToAir));
        }

        if (this.maxNeighbourToAir > 0) {
            chunks.add(I18n.format("nh_tooltip.vent.void_adj", this.maxNeighbourToAir));
        }

        if (this.maxHeatToAir > 0) {
            chunks.add(I18n.format("nh_tooltip.vent.fluid_disclaimer", Config.FLUID_NUKE_HU_MULTIPLIER));
        }

        chunks.add(I18n.format("nh_tooltip.vent.fluid_only"));
    }
}
