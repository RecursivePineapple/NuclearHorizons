package com.recursive_pineapple.nuclear_horizons.recipes;

import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.COOLANT;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.DISTILLED_WATER;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.HOT_COOLANT;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.HOT_LEAD;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.HOT_LITHIUM_TETRAFLUOROBERYLLATE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.LAPIS;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.LEAD;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.LI2BEF4;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.WATER;
import static gregtech.api.recipe.RecipeMaps.distillationTowerRecipes;
import static gregtech.api.recipe.RecipeMaps.distilleryRecipes;
import static gregtech.api.recipe.RecipeMaps.mixerRecipes;
import static gregtech.api.recipe.RecipeMaps.vacuumFreezerRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;

import net.minecraftforge.fluids.FluidRegistry;

import com.recursive_pineapple.nuclear_horizons.Config;
import com.recursive_pineapple.nuclear_horizons.reactors.fluids.CoolantRegistry;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.TierEU;
import gregtech.api.util.GTUtility;

public class CoolantRecipes {

    public static void registerRecipes() {
        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(5))
            .fluidInputs(WATER.getFluid(5))
            .fluidOutputs(DISTILLED_WATER.getFluid(5))
            .duration(16)
            .eut(10)
            .addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(WATER.getFluid(1000))
            .fluidOutputs(DISTILLED_WATER.getFluid(1000))
            .duration(32)
            .eut(120)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(LAPIS.getDust(1), GTUtility.getIntegratedCircuit(4))
            .fluidInputs(DISTILLED_WATER.getFluid(1000))
            .fluidOutputs(COOLANT.getFluid(1000))
            .duration(256)
            .eut(48)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(HOT_LITHIUM_TETRAFLUOROBERYLLATE.getFluid(1000))
            .fluidOutputs(LI2BEF4.getFluid(1000))
            .duration(60 * SECONDS)
            .eut(TierEU.RECIPE_IV)
            .addTo(vacuumFreezerRecipes);


        CoolantRegistry.registerCoolant(COOLANT.getFluid(1), HOT_COOLANT.getFluid(1), Config.COOLANT_SPECIFIC_HEAT, 1, true);
        CoolantRegistry.registerCoolant(
            FluidRegistry.getFluidStack("ic2coolant", 1),
            FluidRegistry.getFluidStack("ic2hotcoolant", 1),
            Config.COOLANT_SPECIFIC_HEAT,
            1,
            false);

        CoolantRegistry.registerCoolant(DISTILLED_WATER.getFluid(1), FluidRegistry.getFluidStack("steam", 160), 1, 20, true);

        CoolantRegistry.registerCoolant(LEAD.getFluid(1), HOT_LEAD.getFluid(1), Config.LEAD_SPECIFIC_HEAT, 1, true);
    }
}
