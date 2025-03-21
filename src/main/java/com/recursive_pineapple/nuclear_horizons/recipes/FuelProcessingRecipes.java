package com.recursive_pineapple.nuclear_horizons.recipes;

import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.*;
import static gregtech.api.recipe.RecipeMaps.centrifugeRecipes;
import static gregtech.api.recipe.RecipeMaps.mixerRecipes;
import static gregtech.api.recipe.RecipeMaps.multiblockChemicalReactorRecipes;
import static gregtech.api.util.GTRecipeBuilder.MINUTES;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;
import static gregtech.api.util.GTRecipeConstants.UniversalChemical;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.TierEU;
import gtPlusPlus.core.util.minecraft.ItemUtils;

public class FuelProcessingRecipes {

    public static void registerRecipes() {
        registerEnrichmentRecipes();
        registerFuelRecipes();
    }

    private static void registerEnrichmentRecipes() {
        // natural uranium tetrafluoride
        GTValues.RA.stdBuilder()
            .itemInputs(URANINITE.getDust(3))
            .fluidInputs(HYDROFLUORICACID.getFluid(16000))
            .itemOutputs(
                NATURAL_URANIUM_TETRAFLUORIDE.getDust(4),
                ItemUtils.getItemStackOfAmountFromOreDict("dustRadium226", 1))
            .outputChances(10000, 750)
            .fluidOutputs(WATER.getFluid(4000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(UniversalChemical);

        // natural uranium hexafluoride
        GTValues.RA.stdBuilder()
            .itemInputs(NATURAL_URANIUM_TETRAFLUORIDE.getDust(1), FLUORINE.getCells(2))
            .itemOutputs(NATURAL_URANIUM_HEXAFLUORIDE.getCells(1), ItemList.Cell_Empty.get(1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(UniversalChemical);

        // uranium enrichment
        GTValues.RA.stdBuilder()
            .itemInputs(NATURAL_URANIUM_HEXAFLUORIDE.getCells(10000))
            .itemOutputs(ENRICHED_URANIUM_HEXAFLUORIDE.getCells(1), DEPLETED_URANIUM_HEXAFLUORIDE.getCells(9))
            .duration(5 * MINUTES)
            .eut(TierEU.RECIPE_HV)
            .addTo(centrifugeRecipes);

        // depleted uranium hexafluoride
        GTValues.RA.stdBuilder()
            .fluidInputs(WATER.getFluid(36000), DEPLETED_URANIUM_HEXAFLUORIDE.getFluid(12000))
            .itemOutputs(URANINITE.getDust(6), URANIUM_238_DIOXIDE.getDust(6))
            .fluidOutputs(HYDROFLUORICACID.getFluid(72000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);

        // enriched uranium hexafluoride
        GTValues.RA.stdBuilder()
            .fluidInputs(WATER.getFluid(36000), ENRICHED_URANIUM_HEXAFLUORIDE.getFluid(12000))
            .itemOutputs(URANINITE.getDust(9), URANIUM_235_DIOXIDE.getDust(3))
            .fluidOutputs(HYDROFLUORICACID.getFluid(72000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);
    }

    private static void registerFuelRecipes() {
        GTValues.RA.stdBuilder()
            .itemInputs(URANIUM_233_DIOXIDE.getDust(1), URANIUM_238_DIOXIDE.getDust(4))
            .itemOutputs(ENRICHED_URANIUM_FUEL.getDust(5))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(URANIUM_235_DIOXIDE.getDust(1), URANIUM_238_DIOXIDE.getDust(4))
            .itemOutputs(ENRICHED_URANIUM_FUEL.getDust(5))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(URANIUM_233_DIOXIDE.getDust(1), THORIANIT.getDust(4))
            .itemOutputs(ENRICHED_THORIUM_FUEL.getDust(5))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(URANIUM_235_DIOXIDE.getDust(1), THORIANIT.getDust(4))
            .itemOutputs(ENRICHED_THORIUM_FUEL.getDust(5))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(mixerRecipes);
    }
}
