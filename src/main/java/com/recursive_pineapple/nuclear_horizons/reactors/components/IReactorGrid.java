package com.recursive_pineapple.nuclear_horizons.reactors.components;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;

public interface IReactorGrid {

    public int getWidth();

    public int getHeight();

    public @Nullable IComponentAdapter getComponent(int x, int y);

    public @Nullable ItemStack getItem(int x, int y);

    public void setItem(int x, int y, @Nullable ItemStack item);

    public boolean isActive();

    public int getHullHeat();

    public int getMaxHullHeat();

    public void setHullHeat(int newHeat);

    public void addHullHeat(int delta);

    public int addAirHeat(int delta);

    public void addEU(double eu);

    public boolean isFluid();
}
