package com.recursive_pineapple.nuclear_horizons.recipes;

import com.recursive_pineapple.nuclear_horizons.reactors.fluids.FluidList;
import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear;
import gregtech.api.enums.*;
import gregtech.api.util.GTUtility;
import net.minecraftforge.fluids.FluidStack;

import static gregtech.api.recipe.RecipeMaps.*;
import static gregtech.api.util.GTRecipeBuilder.MINUTES;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;

public class Distillery {
    public void run() {
        //distilled water
        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(5)) //programmed circuit
            .fluidInputs(Materials.Water.getFluid(500))
            .fluidOutputs(
                MaterialsNuclear.DEPLETED_URANIUM_HEXAFLUORIDE.getFluidOrGas(9000))
            .duration(80 * SECONDS)
            .eut(10)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(Materials.Water.getFluid(1000))
            .fluidOutputs(new FluidStack(FluidList.DISTILLED_WATER, 1000))
            .duration(32)
            .eut(120)
            .addTo(distillationTowerRecipes);
    }
}
