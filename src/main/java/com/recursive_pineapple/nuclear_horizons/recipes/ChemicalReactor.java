package com.recursive_pineapple.nuclear_horizons.recipes;

import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;
import gregtech.api.util.GTOreDictUnificator;

import gtPlusPlus.core.util.minecraft.ItemUtils;

import static gregtech.api.recipe.RecipeMaps.multiblockChemicalReactorRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;
import static gregtech.api.util.GTRecipeConstants.UniversalChemical;

public class ChemicalReactor {
    public void run() {

        //natural uranium tetrafluoride
        GTValues.RA.stdBuilder()
            .itemInputs(
                GTOreDictUnificator.get(OrePrefixes.dust, Materials.Uraninite, 3))
            .fluidInputs(Materials.HydrofluoricAcid.getFluid(8000))
            .itemOutputs(
                MaterialsNuclear.NATURAL_URANIUM_TETRAFLUORIDE.get(OrePrefixes.dust, 4),
                ItemUtils.getItemStackOfAmountFromOreDict("dustRadium226", 1))
            .outputChances(10000, 100)
            .fluidOutputs(Materials.Water.getFluid(4000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(UniversalChemical);

        //natural uranium hexafluoride
        GTValues.RA.stdBuilder()
            .itemInputs(MaterialsNuclear.NATURAL_URANIUM_TETRAFLUORIDE.get(OrePrefixes.dust, 1))
            .fluidInputs(Materials.Fluorine.getGas(2000))
            .fluidOutputs(MaterialsNuclear.NATURAL_URANIUM_HEXAFLUORIDE.getFluidOrGas(1000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(UniversalChemical);

        //depleted uranium hexafluoride
        GTValues.RA.stdBuilder()
            .fluidInputs(
                Materials.Water.getFluid(30000),
                MaterialsNuclear.DEPLETED_URANIUM_HEXAFLUORIDE.getFluidOrGas(10000))
            .itemOutputs(
                GTOreDictUnificator.get(OrePrefixes.dust, Materials.Uraninite, 6),
                MaterialsNuclear.URANIUM_238_DIOXIDE.get(OrePrefixes.dust, 8))
            .fluidOutputs(Materials.HydrofluoricAcid.getFluid(60000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);

        //enriched uranium hexafluoride
        GTValues.RA.stdBuilder()
            .fluidInputs(
                Materials.Water.getFluid(30000),
                MaterialsNuclear.ENRICHED_URANIUM_HEXAFLUORIDE.getFluidOrGas(10000))
            .itemOutputs(
                GTOreDictUnificator.get(OrePrefixes.dust, Materials.Uraninite, 21),
                MaterialsNuclear.URANIUM_235_DIOXIDE.get(OrePrefixes.dust, 3))
            .fluidOutputs(Materials.HydrofluoricAcid.getFluid(60000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);
    }
}
