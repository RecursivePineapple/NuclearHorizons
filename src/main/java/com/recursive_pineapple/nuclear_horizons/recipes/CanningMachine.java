package com.recursive_pineapple.nuclear_horizons.recipes;

import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;

import static gregtech.api.recipe.RecipeMaps.cannerRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;

public class CanningMachine {
    public void run() {
        GTValues.RA.stdBuilder()
            .itemInputs(
                GameRegistry.findItemStack("GoodGenerator", "advancedFuelRod", 1),
                MaterialsNuclear.ENRICHED_URANIUM_FUEL.get(OrePrefixes.dust, 4))
            .itemOutputs(
                GameRegistry.findItemStack(NuclearHorizons.MODID, "fuelRodUranium", 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                GameRegistry.findItemStack("GoodGenerator", "advancedFuelRod", 1),
                MaterialsNuclear.ENRICHED_THORIUM_FUEL.get(OrePrefixes.dust, 4))
            .itemOutputs(
                GameRegistry.findItemStack(NuclearHorizons.MODID, "fuelRodThorium", 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);
    }
}
