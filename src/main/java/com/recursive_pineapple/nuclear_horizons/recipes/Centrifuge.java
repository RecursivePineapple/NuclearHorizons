package com.recursive_pineapple.nuclear_horizons.recipes;

import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear;
import gregtech.api.enums.*;

import static gregtech.api.recipe.RecipeMaps.centrifugeRecipes;
import static gregtech.api.util.GTRecipeBuilder.MINUTES;

public class Centrifuge {
    public void run() {

        //uranium enrichment
        GTValues.RA.stdBuilder()
            .itemInputs(ItemList.Cell_Empty.get(1))
            .fluidInputs(
                MaterialsNuclear.NATURAL_URANIUM_HEXAFLUORIDE.getFluidOrGas(10000))
            .itemOutputs(
                MaterialsNuclear.ENRICHED_URANIUM_HEXAFLUORIDE.get(OrePrefixes.cell, 1))
            .fluidOutputs(
                MaterialsNuclear.DEPLETED_URANIUM_HEXAFLUORIDE.getFluidOrGas(9000))
            .duration(5 * MINUTES)
            .eut(TierEU.RECIPE_HV)
            .addTo(centrifugeRecipes);
    }
}
