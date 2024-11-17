package com.recursive_pineapple.nuclear_horizons.recipes;

import static gregtech.api.recipe.RecipeMaps.electrolyzerRecipes;
import static gregtech.api.util.GTRecipeBuilder.MINUTES;

import gregtech.api.enums.*;
import gtPlusPlus.core.material.nuclear.MaterialsFluorides;

public class Electrolyzer {

    public void run() {
        GTValues.RA.stdBuilder()
            .itemInputs(MaterialsFluorides.SODIUM_FLUORIDE.getDust(2))
            .itemOutputs(Materials.Sodium.getDust(1))
            .fluidOutputs(Materials.Fluorine.getGas(1000))
            .duration(1 * MINUTES)
            .eut(TierEU.RECIPE_MV)
            .addTo(electrolyzerRecipes);

    }
}
