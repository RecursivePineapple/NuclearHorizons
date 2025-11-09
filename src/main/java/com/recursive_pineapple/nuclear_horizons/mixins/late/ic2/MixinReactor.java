package com.recursive_pineapple.nuclear_horizons.mixins.late.ic2;

import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import ic2.core.block.invslot.InvSlotReactor;
import ic2.core.block.reactor.tileentity.TileEntityNuclearReactorElectric;

@Mixin(value = TileEntityNuclearReactorElectric.class, remap = false)
public abstract class MixinReactor implements IReactorGrid {

    @Shadow
    @Final
    public InvSlotReactor reactorSlot;

    @Shadow
    private boolean fluidcoolreactor;

    @Shadow
    public abstract short getReactorSize();

    @Shadow
    public abstract boolean produceEnergy();

    @Shadow
    public abstract int getHeat();
    @Shadow
    public abstract void setHeat(int heat);
    @Shadow
    public abstract int addHeat(int delta);

    @Shadow
    public abstract void addEmitHeat(int heat);

    @Shadow
    public abstract int getMaxHeat();

    @Shadow
    public abstract float addOutput(float energy);

    @Override
    public int getWidth() {
        return getReactorSize();
    }

    @Override
    public int getHeight() {
        return 6;
    }

    @Override
    public @Nullable IComponentAdapter getComponent(int x, int y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public @Nullable ItemStack getItem(int x, int y) {
        return reactorSlot.get(x, y);
    }

    @Override
    public void setItem(int x, int y, @Nullable ItemStack item) {
        reactorSlot.put(x, y, item);
    }

    @Override
    public boolean isActive() {
        return produceEnergy();
    }

    @Override
    public int getHullHeat() {
        return getHeat();
    }

    @Override
    public int getMaxHullHeat() {
        return getMaxHeat();
    }

    @Override
    public void setHullHeat(int newHeat) {
        setHeat(newHeat);
    }

    @Override
    public void addHullHeat(int delta) {
        addHeat(delta);
    }

    @Override
    public int addAirHeat(int delta) {
        addEmitHeat(delta);
        return 0;
    }

    @Override
    public void addEU(double eu) {
        addOutput((float) eu);
    }

    @Override
    public boolean isFluid() {
        return fluidcoolreactor;
    }
}
