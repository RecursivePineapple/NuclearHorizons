package com.recursive_pineapple.nuclear_horizons.recipes;

import static gregtech.api.recipe.RecipeMaps.centrifugeRecipes;
import static gregtech.api.util.GTRecipeBuilder.MINUTES;

import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear;

import bartworks.system.material.WerkstoffLoader;
import gregtech.api.enums.*;
import gtnhlanth.common.register.WerkstoffMaterialPool;

public class Centrifuge {

    public void run() {

        // uranium enrichment
        GTValues.RA.stdBuilder()
            .itemInputs(ItemList.Cell_Empty.get(1))
            .fluidInputs(MaterialsNuclear.NATURAL_URANIUM_HEXAFLUORIDE.getFluidOrGas(10000))
            .itemOutputs(MaterialsNuclear.ENRICHED_URANIUM_HEXAFLUORIDE.get(OrePrefixes.cell, 1))
            .fluidOutputs(MaterialsNuclear.DEPLETED_URANIUM_HEXAFLUORIDE.getFluidOrGas(9000))
            .duration(5 * MINUTES)
            .eut(TierEU.RECIPE_HV)
            .addTo(centrifugeRecipes);

        // PUREX
        GTValues.RA.stdBuilder()
            .itemInputs(ItemList.Cell_Empty.get(3))
            .fluidInputs(MaterialsNuclear.TREATED_DEPLETED_URANIUM_FUEL_SOLUTION.getFluidOrGas(24000))
            .itemOutputs(MaterialsNuclear.AQUEOUS_PLUTONIUM_SOLUTION.get(OrePrefixes.cell, 3))
            .fluidOutputs(MaterialsNuclear.KEROSENE_URANIUM_SOLUTION.getFluidOrGas(21000))
            .duration(2 * MINUTES)
            .eut(TierEU.RECIPE_HV)
            .addTo(centrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(ItemList.Cell_Empty.get(9))
            .fluidInputs(MaterialsNuclear.TREATED_DEPLETED_THORIUM_FUEL_SOLUTION.getFluidOrGas(28000))
            .itemOutputs(MaterialsNuclear.KEROSENE_URANIUM233_SOLUTION.get(OrePrefixes.cell, 9))
            .fluidOutputs(MaterialsNuclear.AQUEOUS_THORIUM_SOLUTION.getFluidOrGas(22000))
            .duration(2 * MINUTES)
            .eut(TierEU.RECIPE_HV)
            .addTo(centrifugeRecipes);

        // fission product waste
        GTValues.RA.stdBuilder()
            .itemInputs(MaterialsNuclear.URANIUM_FISSION_PRODUCT_MIXTURE.get(OrePrefixes.dust, 7))
            .itemOutputs(
                WerkstoffMaterialPool.CeriumDioxide.get(OrePrefixes.dust, 1),
                WerkstoffLoader.Zirconium.get(OrePrefixes.dust, 1),
                Materials.Gallium.getDust(1),
                Materials.Lutetium.getDust(1),
                Materials.Barium.getDust(1),
                Materials.Molybdenum.getDust(1))
            .fluidOutputs(WerkstoffLoader.Krypton.getFluidOrGas(1000))
            .duration(2 * MINUTES)
            .eut(TierEU.RECIPE_HV)
            .addTo(centrifugeRecipes);
    }
}
