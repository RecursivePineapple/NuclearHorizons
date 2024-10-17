package com.recursive_pineapple.nuclear_horizons.recipes;

import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear;
import gregtech.api.enums.*;
import gregtech.api.util.GTOreDictUnificator;

import gtPlusPlus.core.util.minecraft.ItemUtils;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.FluidRegistry;

import static gregtech.api.recipe.RecipeMaps.multiblockChemicalReactorRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;
import static gregtech.api.util.GTRecipeConstants.UniversalChemical;

public class ChemicalReactor {
    public void run() {
        //reduction of uranium dioxides to uranium metals
        GTValues.RA.stdBuilder()
            .itemInputs(
                MaterialsNuclear.URANIUM_235_DIOXIDE.get(OrePrefixes.dust, 3),
                Materials.Sodium.getDust(4))
            .fluidInputs(
                Materials.HydrofluoricAcid.getFluid(4000))
            .itemOutputs(
                Materials.Uranium235.getDust(1),
                ItemUtils.getItemStackOfAmountFromOreDict("dustSodiumFluoride", 4))
            .fluidOutputs(
                Materials.Water.getFluid(2000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(UniversalChemical);

        GTValues.RA.stdBuilder()
            .itemInputs(
                MaterialsNuclear.URANIUM_233_DIOXIDE.get(OrePrefixes.dust, 3),
                Materials.Sodium.getDust(4))
            .fluidInputs(
                Materials.HydrofluoricAcid.getFluid(4000))
            .itemOutputs(
                ItemUtils.getItemStackOfAmountFromOreDict("dustUranium233", 1),
                ItemUtils.getItemStackOfAmountFromOreDict("dustSodiumFluoride", 4))
            .fluidOutputs(
                Materials.Water.getFluid(2000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(UniversalChemical);

        GTValues.RA.stdBuilder()
            .itemInputs(
                MaterialsNuclear.URANIUM_238_DIOXIDE.get(OrePrefixes.dust, 3),
                Materials.Sodium.getDust(4))
            .fluidInputs(
                Materials.HydrofluoricAcid.getFluid(4000))
            .itemOutputs(
                Materials.Uranium.getDust(1),
                ItemUtils.getItemStackOfAmountFromOreDict("dustSodiumFluoride", 4))
            .fluidOutputs(
                Materials.Water.getFluid(2000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(UniversalChemical);

        //reduction of plutonium oxides to plutonium metal
        GTValues.RA.stdBuilder()
            .itemInputs(
                MaterialsNuclear.PLUTONIUM_241_DIOXIDE.get(OrePrefixes.dust, 3),
                Materials.Calcium.getDust(2))
            .fluidInputs(
                Materials.HydrofluoricAcid.getFluid(4000))
            .itemOutputs(
                Materials.Plutonium241.getDust(1),
                ItemUtils.getItemStackOfAmountFromOreDict("dustCalciumFluoride", 2))
            .fluidOutputs(
                Materials.Water.getFluid(2000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(UniversalChemical);

        GTValues.RA.stdBuilder()
            .itemInputs(
                MaterialsNuclear.PLUTONIUM_239_DIOXIDE.get(OrePrefixes.dust, 3),
                Materials.Calcium.getDust(2))
            .fluidInputs(
                Materials.HydrofluoricAcid.getFluid(4000))
            .itemOutputs(
                Materials.Plutonium.getDust(1),
                ItemUtils.getItemStackOfAmountFromOreDict("dustCalciumFluoride", 2))
            .fluidOutputs(
                Materials.Water.getFluid(2000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(UniversalChemical);

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

        //PUREX
        GTValues.RA.stdBuilder()
            .fluidInputs(Materials.NitricAcid.getFluid(9000))
            .itemInputs(MaterialsNuclear.DEPLETED_URANIUM_FUEL.get(OrePrefixes.dust, 10))
            .itemOutputs(MaterialsNuclear.TRANSPLUTONIC_WASTE_MIXTURE.get(OrePrefixes.dust, 1))
            .fluidOutputs(MaterialsNuclear.DEPLETED_URANIUM_FUEL_SOLUTION.getFluidOrGas(9000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(UniversalChemical);

        GTValues.RA.stdBuilder()
            .fluidInputs(Materials.NitricAcid.getFluid(10000))
            .itemInputs(
                MaterialsNuclear.DEPLETED_THORIUM_FUEL.get(OrePrefixes.dust, 10),
                Materials.HydrofluoricAcid.getCells(1))
            .itemOutputs(ItemList.Cell_Empty.get(1))
            .fluidOutputs(MaterialsNuclear.DEPLETED_THORIUM_FUEL_SOLUTION.getFluidOrGas(11000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(UniversalChemical);

        //treating with TBP and kerosene
        GTValues.RA.stdBuilder()
            .fluidInputs(
                MaterialsNuclear.DEPLETED_URANIUM_FUEL_SOLUTION.getFluidOrGas(9000),
                MaterialsNuclear.TRIBUTYL_PHOSPHATE.getFluidOrGas(8000),
                FluidRegistry.getFluidStack("fluid.kerosene", 8000))
            .itemOutputs(MaterialsNuclear.URANIUM_FISSION_PRODUCT_MIXTURE.get(OrePrefixes.dust, 1))
            .fluidOutputs(MaterialsNuclear.TREATED_DEPLETED_URANIUM_FUEL_SOLUTION.getFluidOrGas(24000))
            .duration(60 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(
                MaterialsNuclear.DEPLETED_THORIUM_FUEL_SOLUTION.getFluidOrGas(11000),
                MaterialsNuclear.TRIBUTYL_PHOSPHATE.getFluidOrGas(9000),
                FluidRegistry.getFluidStack("fluid.kerosene", 9000))
            .itemOutputs(MaterialsNuclear.URANIUM_FISSION_PRODUCT_MIXTURE.get(OrePrefixes.dust, 1))
            .fluidOutputs(MaterialsNuclear.TREATED_DEPLETED_THORIUM_FUEL_SOLUTION.getFluidOrGas(28000))
            .duration(60 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);

        //reducing separated solutions back to fuel materials
        GTValues.RA.stdBuilder()
            .fluidInputs(MaterialsNuclear.AQUEOUS_PLUTONIUM_SOLUTION.getFluidOrGas(6000))
            .itemOutputs(
                MaterialsNuclear.PLUTONIUM_239_DIOXIDE.get(OrePrefixes.dust, 1),
                MaterialsNuclear.PLUTONIUM_241_DIOXIDE.get(OrePrefixes.dust, 1))
            .fluidOutputs(
                MaterialsNuclear.TRIBUTYL_PHOSPHATE.getFluidOrGas(2000),
                Materials.NitrogenDioxide.getGas(2000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(MaterialsNuclear.AQUEOUS_THORIUM_SOLUTION.getFluidOrGas(22000))
            .itemOutputs(ItemUtils.getItemStackOfAmountFromOreDict("dustThorianite", 7))
            .fluidOutputs(
                MaterialsNuclear.TRIBUTYL_PHOSPHATE.getFluidOrGas(9000),
                Materials.Fluorine.getGas(1000),
                Materials.NitrogenDioxide.getGas(7000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(MaterialsNuclear.KEROSENE_URANIUM233_SOLUTION.getFluidOrGas(9000))
            .itemOutputs(MaterialsNuclear.URANIUM_233_DIOXIDE.get(OrePrefixes.dust, 3))
            .fluidOutputs(
                FluidRegistry.getFluidStack("fluid.kerosene", 9000),
                Materials.NitrogenDioxide.getGas(3000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(MaterialsNuclear.KEROSENE_URANIUM_SOLUTION.getFluidOrGas(21000))
            .itemOutputs(
                MaterialsNuclear.URANIUM_235_DIOXIDE.get(OrePrefixes.dust, 1),
                MaterialsNuclear.URANIUM_238_DIOXIDE.get(OrePrefixes.dust, 6))
            .fluidOutputs(
                FluidRegistry.getFluidStack("fluid.kerosene", 8000),
                MaterialsNuclear.TRIBUTYL_PHOSPHATE.getFluidOrGas(6000),
                Materials.NitrogenDioxide.getGas(7000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);
    }
}
