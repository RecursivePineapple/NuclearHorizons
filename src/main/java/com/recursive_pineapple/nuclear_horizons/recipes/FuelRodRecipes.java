package com.recursive_pineapple.nuclear_horizons.recipes;

import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.*;
import static gregtech.api.recipe.RecipeMaps.cannerRecipes;
import static gregtech.api.recipe.RecipeMaps.thermalCentrifugeRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;

import net.minecraft.item.ItemStack;

import com.recursive_pineapple.nuclear_horizons.reactors.items.ItemList;

import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear;
import goodgenerator.loader.Loaders;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;

public class FuelRodRecipes {

    public static void registerRecipes() {
        registerCanning();
        registerRecyclingRecipes();
    }

    public static void registerCanning() {
        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 1),
                ENRICHED_URANIUM_FUEL.getDust(4))
            .itemOutputs(new ItemStack(ItemList.URANIUM_1X_ROD, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 2),
                ENRICHED_URANIUM_FUEL.getDust(8))
            .itemOutputs(new ItemStack(ItemList.URANIUM_2X_ROD, 1))
            .duration(40 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 4),
                ENRICHED_URANIUM_FUEL.getDust(16))
            .itemOutputs(new ItemStack(ItemList.URANIUM_4X_ROD, 1))
            .duration(80 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 1),
                ENRICHED_THORIUM_FUEL.getDust(4))
            .itemOutputs(new ItemStack(ItemList.THORIUM_1X_ROD, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 2),
                ENRICHED_THORIUM_FUEL.getDust(8))
            .itemOutputs(new ItemStack(ItemList.THORIUM_2X_ROD, 1))
            .duration(40 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 4),
                ENRICHED_THORIUM_FUEL.getDust(16))
            .itemOutputs(new ItemStack(ItemList.THORIUM_4X_ROD, 1))
            .duration(80 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 1),
                ENRICHED_MOX_FUEL.getDust(4))
            .itemOutputs(new ItemStack(ItemList.MOX_1X_ROD, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 2),
                ENRICHED_MOX_FUEL.getDust(8))
            .itemOutputs(new ItemStack(ItemList.MOX_2X_ROD, 1))
            .duration(40 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 4),
                ENRICHED_MOX_FUEL.getDust(16))
            .itemOutputs(new ItemStack(ItemList.MOX_4X_ROD, 1))
            .duration(80 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);
    }

    public static void registerRecyclingRecipes() {
        GTValues.RA.stdBuilder()
            .itemInputs(new ItemStack(ItemList.DEPLETED_URANIUM_1X_ROD, 1))
            .itemOutputs(
                DEPLETED_URANIUM_FUEL.getDust(4),
                new ItemStack(Loaders.advancedFuelRod, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(new ItemStack(ItemList.DEPLETED_URANIUM_2X_ROD, 1))
            .itemOutputs(
                DEPLETED_URANIUM_FUEL.getDust(8),
                new ItemStack(Loaders.advancedFuelRod, 2))
            .duration(40 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(new ItemStack(ItemList.DEPLETED_URANIUM_4X_ROD, 1))
            .itemOutputs(
                DEPLETED_URANIUM_FUEL.getDust(16),
                new ItemStack(Loaders.advancedFuelRod, 4))
            .duration(80 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(new ItemStack(ItemList.DEPLETED_THORIUM_1X_ROD, 1))
            .itemOutputs(
                DEPLETED_THORIUM_FUEL.getDust(4),
                new ItemStack(Loaders.advancedFuelRod, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(new ItemStack(ItemList.DEPLETED_THORIUM_2X_ROD, 1))
            .itemOutputs(
                DEPLETED_THORIUM_FUEL.getDust(8),
                new ItemStack(Loaders.advancedFuelRod, 2))
            .duration(40 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(new ItemStack(ItemList.DEPLETED_THORIUM_2X_ROD, 1))
            .itemOutputs(
                DEPLETED_THORIUM_FUEL.getDust(16),
                new ItemStack(Loaders.advancedFuelRod, 4))
            .duration(80 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(new ItemStack(ItemList.DEPLETED_MOX_ROD, 1))
            .itemOutputs(
                DEPLETED_MOX_FUEL.getDust(4),
                new ItemStack(Loaders.advancedFuelRod, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(new ItemStack(ItemList.DEPLETED_MOX_2X_ROD, 1))
            .itemOutputs(
                DEPLETED_MOX_FUEL.getDust(8),
                new ItemStack(Loaders.advancedFuelRod, 2))
            .duration(40 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(new ItemStack(ItemList.DEPLETED_MOX_4X_ROD, 1))
            .itemOutputs(
                DEPLETED_MOX_FUEL.getDust(16),
                new ItemStack(Loaders.advancedFuelRod, 4))
            .duration(80 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);
    }
}
