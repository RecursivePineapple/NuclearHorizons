package com.recursive_pineapple.nuclear_horizons.reactors.tile;

import javax.annotation.Nullable;

import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;

public interface IReactorBlock {

    public @Nullable TileReactorCore getReactor();

    public void setReactor(TileReactorCore reactor);

    public default void onHeatTick(IReactorGrid reactor) {

    }

    public default void onEnergyTick(IReactorGrid reactor) {

    }

    public default ReactorEnableState getEnableState() {
        return ReactorEnableState.Idle;
    }

    public static enum ReactorEnableState {
        Active,
        Idle,
        Inhibiting,
    }
}
