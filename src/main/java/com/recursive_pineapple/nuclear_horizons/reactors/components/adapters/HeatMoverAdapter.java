package com.recursive_pineapple.nuclear_horizons.reactors.components.adapters;

import net.minecraft.item.ItemStack;

import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.InventoryDirection;
import com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces.IHeatMover;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.simulator.SimulationComponentResult;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.simulator.SimulationResult;

public class HeatMoverAdapter implements IComponentAdapter {

    private final IReactorGrid reactor;
    private final int x, y;
    private final ItemStack itemStack;
    private final IHeatMover heatMover;

    private SimulationComponentResult simResult;

    public HeatMoverAdapter(IReactorGrid reactor, int x, int y, ItemStack itemStack, IHeatMover heatMover) {
        this.reactor = reactor;
        this.x = x;
        this.y = y;
        this.itemStack = itemStack;
        this.heatMover = heatMover;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public boolean containsHeat() {
        return this.heatMover.getMaxHeat(itemStack) > 0;
    }

    @Override
    public int getStoredHeat() {
        return this.heatMover.getStoredHeat(itemStack);
    }

    @Override
    public int addHeat(int delta) {
        int rejected = this.heatMover.addHeat(itemStack, delta);

        if (this.heatMover.getRemainingHealth(itemStack) <= 0) {
            this.reactor.setItem(this.x, this.y, null);
        }

        return rejected;
    }

    @Override
    public void onSimulationFinished(SimulationResult result, int componentIndex) {

    }

    @Override
    public void onHeatTick() {
        int fromReactor = this.heatMover.getTransferFromReactor(itemStack, reactor);

        if (fromReactor != 0) {
            this.reactor.addHullHeat(-fromReactor);
            this.addHeat(fromReactor);
        }

        int toAir = this.heatMover.getTransferToAir(itemStack, reactor);

        if (toAir != 0) {
            this.addHeat(-toAir);
            int rejected = this.reactor.addAirHeat(toAir);

            if (rejected != 0) {
                this.addHeat(rejected);
            }
        }

        for (var dir : InventoryDirection.values()) {
            int x2 = dir.offsetX(x);
            int y2 = dir.offsetY(y);

            if (x2 < 0 || y2 < 0 || x2 >= reactor.getWidth() || y2 >= reactor.getHeight()) {
                continue;
            }

            var neighbour = reactor.getComponent(x2, y2);

            if (neighbour != null && neighbour.containsHeat()) {
                int fromNeighbourToAir = this.heatMover.getTransferNeighbourToAir(itemStack, reactor, neighbour);

                if (fromNeighbourToAir != 0) {
                    neighbour.addHeat(-fromNeighbourToAir);
                    int rejected = this.reactor.addAirHeat(fromNeighbourToAir);

                    if (rejected != 0) {
                        neighbour.addHeat(rejected);
                    }
                }

                int fromNeighbour = this.heatMover.getTransferFromNeighbour(itemStack, reactor, neighbour);

                if (fromNeighbour != 0) {
                    neighbour.addHeat(-fromNeighbour);
                    this.addHeat(fromNeighbour);
                }
            }
        }
    }
}
