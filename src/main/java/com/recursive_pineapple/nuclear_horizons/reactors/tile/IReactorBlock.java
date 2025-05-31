package com.recursive_pineapple.nuclear_horizons.reactors.tile;

import javax.annotation.Nullable;

import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;

public interface IReactorBlock {

    @Nullable IReactorGrid getReactor();

    void setReactor(@Nullable TileReactorCore reactor);

    default void onHeatTick(IReactorGrid reactor) {

    }

    default void onEnergyTick(IReactorGrid reactor) {

    }

    default ReactorEnableState getEnableState() {
        return ReactorEnableState.Idle;
    }

    enum ReactorEnableState {
        Active,
        Idle,
        Inhibiting,
    }
}
