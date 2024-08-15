package com.recursive_pineapple.nuclear_horizons.reactors.components;

import net.minecraft.item.ItemStack;

import com.recursive_pineapple.nuclear_horizons.reactors.tile.simulator.SimulationResult;

public interface IComponentAdapter {

    public int getX();

    public int getY();

    public ItemStack getItemStack();

    public default boolean containsHeat() {
        return false;
    }

    public default int getStoredHeat() {
        return 0;
    }

    /**
     * @return The amount of heat rejected
     */
    public default int addHeat(int delta) {
        return delta;
    }

    public default void onHeatTick() {

    }

    public default void onEnergyTick() {

    }

    public default boolean reflectsNeutrons() {
        return false;
    }

    public default int getFuelRodCount() {
        return 0;
    }

    public default int getReactorMaxHeatIncrease() {
        return 0;
    }

    public default double getExplosionRadiusMultiplier() {
        return 1.0;
    }

    public default void modifySimulationResults(SimulationResult result, int componentIndex) {

    }
}
