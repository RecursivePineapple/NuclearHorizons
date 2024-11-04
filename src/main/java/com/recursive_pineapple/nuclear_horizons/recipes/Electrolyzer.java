package com.recursive_pineapple.nuclear_horizons.recipes;

import bartworks.system.material.WerkstoffLoader;
import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear;
import gregtech.api.enums.*;
import gtPlusPlus.core.material.nuclear.MaterialsFluorides;

import static gregtech.api.recipe.RecipeMaps.electrolyzerRecipes;
import static gregtech.api.util.GTRecipeBuilder.MINUTES;

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
