package com.recursive_pineapple.nuclear_horizons.recipes;

import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.ALUMINIUMOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.DEPLETED_URANIUM_HEXAFLUORIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.EMPTY;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.ENRICHED_MOX_FUEL;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.ENRICHED_THORIUM_FUEL;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.ENRICHED_URANIUM_FUEL;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.ENRICHED_URANIUM_HEXAFLUORIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.FLUORINE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.HYDROFLUORICACID;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.MAGNETITE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.NATURAL_URANIUM_HEXAFLUORIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.NITRICACID;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.PLUTONIUM_239_DIOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.PLUTONIUM_241_DIOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.RAREEARTH;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.REFINED_THORIUM;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.SILICONDIOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.THORIANIT;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.THORIUM_NITRATE_SOLUTION;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.THORIUM_ORE_IMPURITIES;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.UNREFINED_THORIUM_SOLUTION;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.URANINITE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.URANIUM_233_DIOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.URANIUM_235_DIOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.URANIUM_238_DIOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.WATER;
import static gregtech.api.recipe.RecipeMaps.centrifugeRecipes;
import static gregtech.api.recipe.RecipeMaps.chemicalReactorRecipes;
import static gregtech.api.recipe.RecipeMaps.fluidExtractionRecipes;
import static gregtech.api.recipe.RecipeMaps.mixerRecipes;
import static gregtech.api.recipe.RecipeMaps.multiblockChemicalReactorRecipes;
import static gregtech.api.util.GTRecipeBuilder.MINUTES;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;
import gregtech.api.util.GTOreDictUnificator;
import gtPlusPlus.core.util.minecraft.ItemUtils;

public class FuelProcessingRecipes {

    public static void registerRecipes() {
        registerEnrichmentRecipes();
        registerFuelRecipes();
        registerThoriumLineRecipes();
    }

    private static void registerEnrichmentRecipes() {
        // natural uranium hexafluoride
        GTValues.RA.stdBuilder()
            .itemInputs(URANINITE.getDust(3))
            .fluidInputs(HYDROFLUORICACID.getFluid(4000), FLUORINE.getGas(2000))
            .itemOutputs(
                NATURAL_URANIUM_HEXAFLUORIDE.getDust(7),
                ItemUtils.getItemStackOfAmountFromOreDict("dustRadium226", 1))
            .outputChances(10000, 750)
            .fluidOutputs(WATER.getFluid(2000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);

        // sublimation
        GTValues.RA.stdBuilder()
            .itemInputs(NATURAL_URANIUM_HEXAFLUORIDE.getDust(1))
            .fluidOutputs(NATURAL_URANIUM_HEXAFLUORIDE.getFluid(1000))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(fluidExtractionRecipes);

        // uranium enrichment
        GTValues.RA.stdBuilder()
            .itemInputs(NATURAL_URANIUM_HEXAFLUORIDE.getCells(7))
            .itemOutputs(DEPLETED_URANIUM_HEXAFLUORIDE.getCells(6), ENRICHED_URANIUM_HEXAFLUORIDE.getCells(1))
            .duration(1 * MINUTES)
            .eut(TierEU.RECIPE_HV)
            .addTo(centrifugeRecipes);

        // U235 Dioxide
        GTValues.RA.stdBuilder()
            .fluidInputs(ENRICHED_URANIUM_HEXAFLUORIDE.getFluid(4000), WATER.getFluid(12000))
            .itemOutputs(URANINITE.getDust(9), URANIUM_235_DIOXIDE.getDust(3))
            .fluidOutputs(HYDROFLUORICACID.getFluid(24000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);

        // U238 Dioxide
        GTValues.RA.stdBuilder()
            .fluidInputs(DEPLETED_URANIUM_HEXAFLUORIDE.getFluid(2000), WATER.getFluid(6000))
            .itemOutputs(URANINITE.getDust(3), URANIUM_238_DIOXIDE.getDust(3))
            .fluidOutputs(HYDROFLUORICACID.getFluid(12000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);
    }

    private static void registerThoriumLineRecipes() {
        // Dissolve ore
        GTValues.RA.stdBuilder()
            .itemInputs(THORIANIT.getDust(3))
            .fluidInputs(NITRICACID.getFluid(1000))
            .fluidOutputs(UNREFINED_THORIUM_SOLUTION.getFluid(1000))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTOreDictUnificator.get(OrePrefixes.crushed, Materials.Thorium, 1))
            .fluidInputs(NITRICACID.getFluid(1000))
            .fluidOutputs(UNREFINED_THORIUM_SOLUTION.getFluid(1000))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTOreDictUnificator.get(OrePrefixes.crushedPurified, Materials.Thorium, 1))
            .fluidInputs(NITRICACID.getFluid(1000))
            .fluidOutputs(UNREFINED_THORIUM_SOLUTION.getFluid(1000))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(mixerRecipes);

        // Centrifuge out the impurities
        GTValues.RA.stdBuilder()
            .itemInputs(UNREFINED_THORIUM_SOLUTION.getCells(1))
            .itemOutputs(THORIUM_NITRATE_SOLUTION.getCells(1), THORIUM_ORE_IMPURITIES.getDust(1))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(centrifugeRecipes);

        // Impurity centrifuging
        GTValues.RA.stdBuilder()
            .itemInputs(THORIUM_ORE_IMPURITIES.getDust(1))
            .itemOutputs(
                SILICONDIOXIDE.getDust(1),
                ALUMINIUMOXIDE.getDust(1),
                MAGNETITE.getDust(1),
                RAREEARTH.getDust(1))
            .outputChances(7500, 4500, 6000, 2500)
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(centrifugeRecipes);

        // Reclaim the nitric acid and convert to dust
        GTValues.RA.stdBuilder()
            .itemInputs(WATER.getCells(1), THORIUM_NITRATE_SOLUTION.getCells(1))
            .itemOutputs(REFINED_THORIUM.getDust(1), EMPTY.getCells(2))
            .fluidOutputs(NITRICACID.getFluid(1000))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(chemicalReactorRecipes);
        GTValues.RA.stdBuilder()
            .fluidInputs(WATER.getFluid(1000), THORIUM_NITRATE_SOLUTION.getFluid(1000))
            .itemOutputs(REFINED_THORIUM.getDust(1))
            .fluidOutputs(NITRICACID.getFluid(1000))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);
    }

    private static void registerFuelRecipes() {
        GTValues.RA.stdBuilder()
            .itemInputs(URANIUM_233_DIOXIDE.getDust(1), REFINED_THORIUM.getDust(15))
            .itemOutputs(ENRICHED_THORIUM_FUEL.getDust(16))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(URANIUM_235_DIOXIDE.getDust(1), REFINED_THORIUM.getDust(11))
            .itemOutputs(ENRICHED_THORIUM_FUEL.getDust(12))
            .duration(15 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(URANIUM_238_DIOXIDE.getDust(1), REFINED_THORIUM.getDust(7))
            .itemOutputs(ENRICHED_THORIUM_FUEL.getDust(8))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(URANINITE.getDust(1), REFINED_THORIUM.getDust(3))
            .itemOutputs(ENRICHED_THORIUM_FUEL.getDust(4))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(URANIUM_233_DIOXIDE.getDust(1), URANIUM_238_DIOXIDE.getDust(4))
            .itemOutputs(ENRICHED_URANIUM_FUEL.getDust(5))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(URANIUM_235_DIOXIDE.getDust(1), URANIUM_238_DIOXIDE.getDust(4))
            .itemOutputs(ENRICHED_URANIUM_FUEL.getDust(5))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(PLUTONIUM_239_DIOXIDE.getDust(1), URANIUM_238_DIOXIDE.getDust(3))
            .itemOutputs(ENRICHED_MOX_FUEL.getDust(4))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_EV)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(PLUTONIUM_241_DIOXIDE.getDust(1), URANIUM_238_DIOXIDE.getDust(7))
            .itemOutputs(ENRICHED_MOX_FUEL.getDust(8))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_EV)
            .addTo(mixerRecipes);
    }
}
