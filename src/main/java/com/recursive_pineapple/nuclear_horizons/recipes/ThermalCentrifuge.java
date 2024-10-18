package com.recursive_pineapple.nuclear_horizons.recipes;

import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.items.ItemList;
import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear;
import cpw.mods.fml.common.registry.GameRegistry;
import goodgenerator.loader.Loaders;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;
import gregtech.api.util.GTOreDictUnificator;

import gtPlusPlus.core.util.minecraft.ItemUtils;
import net.minecraft.item.ItemStack;

import static gregtech.api.recipe.RecipeMaps.thermalCentrifugeRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;


public class ThermalCentrifuge {

    public void run() {
        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(ItemList.DEPLETED_URANIUM_1X_ROD, 1))
            .itemOutputs(
                MaterialsNuclear.DEPLETED_URANIUM_FUEL.get(OrePrefixes.dust, 4),
                new ItemStack(Loaders.advancedFuelRod, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(ItemList.DEPLETED_THORIUM_1X_ROD, 1))
            .itemOutputs(
                MaterialsNuclear.DEPLETED_THORIUM_FUEL.get(OrePrefixes.dust, 4),
                new ItemStack(Loaders.advancedFuelRod, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);
    }
}
