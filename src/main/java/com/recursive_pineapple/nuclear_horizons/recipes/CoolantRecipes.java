package com.recursive_pineapple.nuclear_horizons.recipes;

import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.*;
import static gregtech.api.recipe.RecipeMaps.autoclaveRecipes;
import static gregtech.api.recipe.RecipeMaps.blastFurnaceRecipes;
import static gregtech.api.recipe.RecipeMaps.distillationTowerRecipes;
import static gregtech.api.recipe.RecipeMaps.distilleryRecipes;
import static gregtech.api.recipe.RecipeMaps.fluidHeaterRecipes;
import static gregtech.api.recipe.RecipeMaps.mixerRecipes;
import static gregtech.api.recipe.RecipeMaps.vacuumFreezerRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;
import static gregtech.api.util.GTRecipeConstants.UniversalChemical;

import net.minecraftforge.fluids.FluidStack;

import com.recursive_pineapple.nuclear_horizons.reactors.fluids.CoolantRegistry;
import com.recursive_pineapple.nuclear_horizons.reactors.fluids.FluidList;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.TierEU;
import gregtech.api.registries.LHECoolantRegistry;
import gregtech.api.util.GTUtility;

public class CoolantRecipes {

    public static void registerRecipes() {
        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(5))
            .fluidInputs(WATER.getFluid(5))
            .fluidOutputs(new FluidStack(FluidList.DISTILLED_WATER, 5))
            .duration(16)
            .eut(10)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(WATER.getFluid(1000))
            .fluidOutputs(new FluidStack(FluidList.DISTILLED_WATER, 1000))
            .duration(32)
            .eut(120)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(LAPIS.getDust(1), GTUtility.getIntegratedCircuit(4))
            .fluidInputs(new FluidStack(FluidList.DISTILLED_WATER, 1000))
            .fluidOutputs(new FluidStack(FluidList.COOLANT, 1000))
            .duration(256)
            .eut(48)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(HOT_LITHIUM_TETRAFLUOROBERYLLATE.getFluid(1000))
            .fluidOutputs(LI2BEF4.getFluid(1000))
            .duration(60 * SECONDS)
            .eut(TierEU.RECIPE_IV)
            .addTo(vacuumFreezerRecipes);

        CoolantRegistry.registerCoolant(LI2BEF4.getFluid(1).getFluid(), HOT_LITHIUM_TETRAFLUOROBERYLLATE.getFluid(1).getFluid(), 16);
        LHECoolantRegistry.registerCoolant(
            LI2BEF4.getFluid(1).getFluid().getName(),
            HOT_LITHIUM_TETRAFLUOROBERYLLATE.getFluid(1).getFluid().getName(),
            1.0 / 2.0 * 16.0,
            1.0 / 5.0 / 16.0);

    }
}
