package com.recursive_pineapple.nuclear_horizons.recipes;

import java.util.function.Supplier;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import com.gtnewhorizon.gtnhlib.util.data.Lazy;

import bartworks.system.material.Werkstoff;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.ISubTagContainer;
import gtPlusPlus.core.material.Material;

public interface MaterialWrapper {

    ISubTagContainer getMaterial();

    ItemStack getCells(int amount);

    ItemStack getDust(int amount);

    ItemStack getDustTiny(int amount);

    FluidStack getFluid(int amount);

    FluidStack getGas(int amount);

    interface GTMatSupplier extends Supplier<Materials> {
    }

    static MaterialWrapper of(GTMatSupplier gt) {
        Lazy<Materials> mat = new Lazy<>(gt);

        return new MaterialWrapper() {

            @Override
            public ISubTagContainer getMaterial() {
                return mat.get();
            }

            @Override
            public ItemStack getCells(int amount) {
                return mat.get()
                    .getCells(amount);
            }

            @Override
            public ItemStack getDust(int amount) {
                return mat.get()
                    .getDust(amount);
            }

            @Override
            public ItemStack getDustTiny(int amount) {
                return mat.get()
                    .getDustTiny(amount);
            }

            @Override
            public FluidStack getFluid(int amount) {
                FluidStack stack = mat.get()
                    .getFluid(amount);

                if (stack != null) return stack;

                return mat.get()
                    .getMolten(amount);
            }

            @Override
            public FluidStack getGas(int amount) {
                return mat.get()
                    .getGas(amount);
            }
        };
    }

    interface BWMatSupplier extends Supplier<Werkstoff> {
    }

    static MaterialWrapper of(BWMatSupplier bw) {
        Lazy<Werkstoff> mat = new Lazy<>(bw);

        return new MaterialWrapper() {

            @Override
            public ISubTagContainer getMaterial() {
                return mat.get();
            }

            @Override
            public ItemStack getCells(int amount) {
                return mat.get()
                    .get(OrePrefixes.cell, amount);
            }

            @Override
            public ItemStack getDust(int amount) {
                return mat.get()
                    .get(OrePrefixes.dust, amount);
            }

            @Override
            public ItemStack getDustTiny(int amount) {
                return mat.get()
                    .get(OrePrefixes.dustTiny, amount);
            }

            @Override
            public FluidStack getFluid(int amount) {
                return mat.get()
                    .getFluidOrGas(amount);
            }

            @Override
            public FluidStack getGas(int amount) {
                return mat.get()
                    .getFluidOrGas(amount);
            }
        };
    }

    interface GTPPMatSupplier extends Supplier<Material> {
    }

    static MaterialWrapper of(GTPPMatSupplier gtpp) {
        Lazy<Material> mat = new Lazy<>(gtpp);

        return new MaterialWrapper() {

            @Override
            public ISubTagContainer getMaterial() {
                return null;
            }

            @Override
            public ItemStack getCells(int amount) {
                return mat.get()
                    .getCell(amount);
            }

            @Override
            public ItemStack getDust(int amount) {
                return mat.get()
                    .getDust(amount);
            }

            @Override
            public ItemStack getDustTiny(int amount) {
                return mat.get()
                    .getTinyDust(amount);
            }

            @Override
            public FluidStack getFluid(int amount) {
                return mat.get()
                    .getFluidStack(amount);
            }

            @Override
            public FluidStack getGas(int amount) {
                return null;
            }
        };
    }

    class Mols {

        public int mols;

        public Mols(int mols) {
            this.mols = mols;
        }
    }

    static Mols mols(int mols) {
        return new Mols(mols);
    }
}
