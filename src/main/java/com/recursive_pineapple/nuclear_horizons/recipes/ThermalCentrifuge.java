package com.recursive_pineapple.nuclear_horizons.recipes;

import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;
import gregtech.api.util.GTOreDictUnificator;

import gtPlusPlus.core.util.minecraft.ItemUtils;

import static gregtech.api.recipe.RecipeMaps.thermalCentrifugeRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;


public class ThermalCentrifuge {

    public void run() {
        GTValues.RA.stdBuilder()
            .itemInputs(
                GameRegistry.findItemStack(NuclearHorizons.MODID, "depletedFuelRodUranium", 1))
            .itemOutputs(
                MaterialsNuclear.DEPLETED_URANIUM_FUEL.get(OrePrefixes.dust, 4),
                GameRegistry.findItemStack("GoodGenerator", "advancedFuelRod", 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                GameRegistry.findItemStack(NuclearHorizons.MODID, "depletedFuelRodThorium", 1))
            .itemOutputs(
                MaterialsNuclear.DEPLETED_THORIUM_FUEL.get(OrePrefixes.dust, 4),
                GameRegistry.findItemStack("GoodGenerator", "advancedFuelRod", 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);
    }
}
