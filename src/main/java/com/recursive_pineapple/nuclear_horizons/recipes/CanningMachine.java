package com.recursive_pineapple.nuclear_horizons.recipes;

import static gregtech.api.recipe.RecipeMaps.cannerRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;

import net.minecraft.item.ItemStack;

import com.recursive_pineapple.nuclear_horizons.reactors.items.ItemList;
import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear;

import goodgenerator.loader.Loaders;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;

public class CanningMachine {

    public void run() {
        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 1),
                MaterialsNuclear.ENRICHED_URANIUM_FUEL.get(OrePrefixes.dust, 4))
            .itemOutputs(new ItemStack(ItemList.URANIUM_1X_ROD, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 1),
                MaterialsNuclear.ENRICHED_THORIUM_FUEL.get(OrePrefixes.dust, 4))
            .itemOutputs(new ItemStack(ItemList.THORIUM_1X_ROD, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);
    }
}
