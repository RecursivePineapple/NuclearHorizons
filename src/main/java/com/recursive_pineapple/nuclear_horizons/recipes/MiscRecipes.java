package com.recursive_pineapple.nuclear_horizons.recipes;

import static gregtech.api.util.GTUtility.getIntegratedCircuit;

import com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTOreDictUnificator;

public class MiscRecipes {

    public static void registerRecipes() {
        registerCraftingIngredientRecipes();
    }

    private static void registerCraftingIngredientRecipes() {
         GTValues.RA.stdBuilder()
             .itemInputs(GTOreDictUnificator.get(OrePrefixes.itemCasing, Materials.Iron, 2), getIntegratedCircuit(2))
             .itemOutputs(NHItemList.EMPTY_FUEL_ROD_BASIC.get(1))
             .duration(16)
             .eut(TierEU.RECIPE_MV)
             .addTo(RecipeMaps.benderRecipes);

    }
}
