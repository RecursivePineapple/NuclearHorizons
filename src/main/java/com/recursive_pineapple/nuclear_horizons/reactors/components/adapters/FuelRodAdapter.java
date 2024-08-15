package com.recursive_pineapple.nuclear_horizons.reactors.components.adapters;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

import com.recursive_pineapple.nuclear_horizons.Config;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.InventoryDirection;
import com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces.IBasicFuelRod;

public class FuelRodAdapter implements IComponentAdapter {

    private final IReactorGrid reactor;
    private final int x, y;
    private final ItemStack itemStack;
    private final IBasicFuelRod fuelRod;

    public FuelRodAdapter(IReactorGrid reactor, int x, int y, ItemStack itemStack, IBasicFuelRod fuelRod) {
        this.reactor = reactor;
        this.x = x;
        this.y = y;
        this.itemStack = itemStack;
        this.fuelRod = fuelRod;
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
    public void onHeatTick() {
        if (!reactor.isActive()) {
            return;
        }

        if (fuelRod.getRemainingHealth(itemStack) <= 0) {
            var product = fuelRod.getProduct(itemStack);
            reactor.setItem(x, y, product != null ? product.copy() : null);
            return;
        }

        int pulses = this.getPulseCount();
        int heat = (int) (fuelRod.getHeatMult(itemStack) * pulses * (pulses + 1) * Config.ROD_HU_MULTIPLIER);

        if (fuelRod.isMox(itemStack) && reactor.isFluid()
            && (reactor.getHullHeat() / reactor.getMaxHullHeat()) >= 0.5) {
            heat *= 2;
        }

        var heatableNeighbours = this.getHeatableNeighbours();

        if (heatableNeighbours.isEmpty()) {
            reactor.addHullHeat(heat);
        } else {
            int rodCount = fuelRod.getRodCount(itemStack);

            int heatPerRod = heat / rodCount;

            for (int i = 0; i < heatableNeighbours.size(); i++) {
                int remainingNeighbours = heatableNeighbours.size() - i;
                int heatToTransfer = heatPerRod / remainingNeighbours;
                heatPerRod -= heatToTransfer;
                heatPerRod += heatableNeighbours.get(i)
                    .addHeat(heatToTransfer * rodCount) / rodCount;
            }

            reactor.addHullHeat(heatPerRod * rodCount);
        }
    }

    @Override
    public void onEnergyTick() {
        if (!reactor.isActive()) {
            return;
        }

        if (fuelRod.getRemainingHealth(itemStack) <= 0) {
            return;
        }

        int pulses = this.getPulseCount();
        double energy = fuelRod.getEnergyMult(itemStack) * pulses * Config.ROD_EU_MULTIPLIER;

        if (fuelRod.isMox(itemStack)) {
            energy *= 1 + Config.MOX_EU_COEFFICIENT * reactor.getHullHeat() / reactor.getMaxHullHeat();
        }

        reactor.addEU(energy);
        fuelRod.applyDamage(itemStack, 1);
    }

    @Override
    public boolean reflectsNeutrons() {
        return fuelRod.getRemainingHealth(itemStack) > 0;
    }

    @Override
    public int getFuelRodCount() {
        return this.fuelRod.getRodCount(itemStack);
    }

    private int getPulseCount() {
        int pulses = 1 + this.getFuelRodCount() / 2;

        for (var dir : InventoryDirection.values()) {
            int x2 = dir.offsetX(x);
            int y2 = dir.offsetY(y);

            if (x2 < 0 || y2 < 0 || x2 >= reactor.getWidth() || y2 >= reactor.getHeight()) {
                continue;
            }

            var neighbour = reactor.getComponent(x2, y2);

            if (neighbour != null && neighbour.reflectsNeutrons()) {
                pulses++;
            }
        }

        return pulses;
    }

    private ArrayList<IComponentAdapter> getHeatableNeighbours() {
        ArrayList<IComponentAdapter> neighbours = new ArrayList<>();

        for (var dir : InventoryDirection.values()) {
            int x2 = dir.offsetX(x);
            int y2 = dir.offsetY(y);

            if (x2 < 0 || y2 < 0 || x2 >= reactor.getWidth() || y2 >= reactor.getHeight()) {
                continue;
            }

            var neighbour = reactor.getComponent(x2, y2);

            if (neighbour != null && neighbour.containsHeat()) {
                neighbours.add(neighbour);
            }
        }

        return neighbours;
    }
}
