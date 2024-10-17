package com.recursive_pineapple.nuclear_horizons.recipes;

import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;
import gtPlusPlus.core.util.minecraft.ItemUtils;

import static gregtech.api.recipe.RecipeMaps.mixerRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;

public class Mixer {
    public void run() {
        GTValues.RA.stdBuilder()
            .itemInputs(
                MaterialsNuclear.URANIUM_235_DIOXIDE.get(OrePrefixes.dust, 1),
                MaterialsNuclear.URANIUM_238_DIOXIDE.get(OrePrefixes.dust, 4))
            .itemOutputs(
                MaterialsNuclear.ENRICHED_URANIUM_FUEL.get(OrePrefixes.dust, 5))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                MaterialsNuclear.URANIUM_233_DIOXIDE.get(OrePrefixes.dust, 1),
                MaterialsNuclear.URANIUM_238_DIOXIDE.get(OrePrefixes.dust, 4))
            .itemOutputs(
                MaterialsNuclear.ENRICHED_URANIUM_FUEL.get(OrePrefixes.dust, 5))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                MaterialsNuclear.URANIUM_235_DIOXIDE.get(OrePrefixes.dust, 1),
                ItemUtils.getItemStackOfAmountFromOreDict("dustThorianite", 4))
            .itemOutputs(
                MaterialsNuclear.ENRICHED_THORIUM_FUEL.get(OrePrefixes.dust, 5))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                MaterialsNuclear.URANIUM_233_DIOXIDE.get(OrePrefixes.dust, 1),
                ItemUtils.getItemStackOfAmountFromOreDict("dustThorianite", 4))
            .itemOutputs(
                MaterialsNuclear.ENRICHED_THORIUM_FUEL.get(OrePrefixes.dust, 5))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(mixerRecipes);
    }
}
