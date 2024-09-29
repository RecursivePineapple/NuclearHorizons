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

    protected double getEUMultiplier() {
        double mult = Config.ROD_EU_MULTIPLIER;

        if (fuelRod.isMox(itemStack)) {
            mult *= 1 + fuelRod.getMoxEUCoefficient(itemStack) * reactor.getHeatRatio();
        }

        return mult;
    }

    protected double getHeatMultiplier() {
        double mult = Config.ROD_HU_MULTIPLIER;

        if (fuelRod.isMox(itemStack) && reactor.isFluid() && reactor.getHeatRatio() >= 0.5) {
            mult *= fuelRod.getMoxHeatCoefficient(itemStack);
        }

        return mult;
    }

    @Override
    public void onHeatTick() {
        if (!reactor.isActive()) {
            return;
        }

        if (fuelRod.getRemainingHealth(itemStack) <= 0) {
            reactor.setItem(x, y, ItemStack.copyItemStack(fuelRod.getProduct(itemStack)));
            return;
        }

        int pulses = this.getPulseCount();
        int heat = (int) (fuelRod.getHeatMult(itemStack) * fuelRod.getRodCount(itemStack)
            * getHeatMultiplier()
            * pulses
            * (pulses + 1)
            / 2);

        var heatableNeighbours = this.getHeatableNeighbours();

        if (heatableNeighbours.isEmpty()) {
            reactor.addHullHeat(heat);
        } else {
            for (int i = 0; i < heatableNeighbours.size(); i++) {
                int remainingNeighbours = heatableNeighbours.size() - i;

                int heatToTransfer = heat / remainingNeighbours;
                heat -= heatToTransfer;

                int rejected = heatableNeighbours.get(i)
                    .addHeat(heatToTransfer);

                heat += rejected;
            }

            if (heat > 0) {
                reactor.addHullHeat(heat);
            }
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
        double energy = fuelRod.getEnergyMult(itemStack) * fuelRod.getRodCount(itemStack) * getEUMultiplier() * pulses;

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
