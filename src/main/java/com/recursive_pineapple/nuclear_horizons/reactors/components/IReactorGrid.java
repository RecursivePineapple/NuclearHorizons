package com.recursive_pineapple.nuclear_horizons.reactors.components;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;

public interface IReactorGrid {

    int getWidth();

    int getHeight();

    @Nullable IComponentAdapter getComponent(int x, int y);

    @Nullable ItemStack getItem(int x, int y);

    void setItem(int x, int y, @Nullable ItemStack item);

    boolean isActive();

    int getHullHeat();

    int getMaxHullHeat();

    default float getHeatRatio() {
        return getHullHeat() / (float) getMaxHullHeat();
    }

    void setHullHeat(int newHeat);

    void addHullHeat(int delta);

    int addAirHeat(int delta);

    void addEU(double eu);

    boolean isFluid();

    int getTickRate();
}
