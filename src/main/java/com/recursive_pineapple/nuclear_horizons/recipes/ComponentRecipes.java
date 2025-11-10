package com.recursive_pineapple.nuclear_horizons.recipes;

import static gregtech.api.recipe.RecipeMaps.assemblerRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;

import net.minecraft.item.ItemStack;

import com.recursive_pineapple.nuclear_horizons.reactors.blocks.BlockList;
import com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTUtility;
import ic2.core.Ic2Items;

public class ComponentRecipes {

    public static void registerRecipes() {
        registerComponentRecipes();
        registerReactorRecipes();
    }

    private static void registerComponentRecipes() {
        GTValues.RA.stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(21),
                Materials.Silver.getPlates(2),
                Materials.Copper.getPlates(1),
                Materials.Aluminium.getPlates(2),
                GTOreDictUnificator.get(OrePrefixes.circuit, Materials.HV, 1L))
            .itemOutputs(NHItemList.BASIC_HEAT_EXCHANGER.get(1))
            .duration(3 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(21),
                Materials.Silver.getPlates(2),
                GTOreDictUnificator.get(OrePrefixes.plateDouble, Materials.Copper, 2),
                NHItemList.BASIC_HEAT_EXCHANGER.get(1))
            .fluidInputs()
            .itemOutputs(NHItemList.REACTOR_HEAT_EXCHANGER.get(1))
            .duration(3 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(21),
                Materials.Lapis.getPlates(2),
                Materials.Diamond.getPlates(1),
                GTOreDictUnificator.get(OrePrefixes.circuit, Materials.EV, 2L),
                NHItemList.BASIC_HEAT_EXCHANGER.get(1),
                NHItemList.BASIC_HEAT_EXCHANGER.get(1))
            .fluidInputs()
            .itemOutputs(NHItemList.ADVANCED_HEAT_EXCHANGER.get(1))
            .duration(3 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(21),
                Materials.Gold.getPlates(2),
                NHItemList.BASIC_HEAT_EXCHANGER.get(1))
            .fluidInputs(Materials.StainlessSteel.getMolten(72))
            .itemOutputs(NHItemList.COMPONENT_HEAT_EXCHANGER.get(1))
            .duration(3 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(21),
                Materials.Aluminium.getPlates(2),
                gregtech.api.enums.ItemList.Electric_Motor_LV.get(1L),
                GTModHandler.getModItem("dreamcraft", "item.SteelBars", 2))
            .fluidInputs()
            .itemOutputs(NHItemList.BASIC_HEAT_VENT.get(1))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(22),
                Materials.Silver.getPlates(2),
                GTOreDictUnificator.get(OrePrefixes.plateDouble, Materials.Copper, 2),
                NHItemList.BASIC_HEAT_VENT.get(1))
            .fluidInputs()
            .itemOutputs(NHItemList.REACTOR_HEAT_VENT.get(1))
            .duration(15 * SECONDS)
            .eut(256)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(21),
                Ic2Items.industrialDiamond,
                NHItemList.BASIC_HEAT_VENT.get(1),
                GTModHandler.getModItem("dreamcraft", "item.StainlessSteelBars", 4))
            .fluidInputs()
            .itemOutputs(NHItemList.ADVANCED_HEAT_VENT.get(1))
            .duration(15 * SECONDS)
            .eut(256)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(21),
                GTOreDictUnificator.get(OrePrefixes.plateDense, Materials.Tin, 2),
                GTModHandler.getModItem("dreamcraft", "item.StainlessSteelBars", 4),
                NHItemList.BASIC_HEAT_VENT.get(1))
            .fluidInputs()
            .itemOutputs(NHItemList.COMPONENT_HEAT_VENT.get(1))
            .duration(15 * SECONDS)
            .eut(256)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(22),
                Materials.Gold.getPlates(2),
                NHItemList.ADVANCED_HEAT_VENT.get(1))
            .fluidInputs(Materials.StainlessSteel.getMolten(72))
            .itemOutputs(NHItemList.OVERCLOCKED_HEAT_VENT.get(1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(22),
                GTOreDictUnificator.get(OrePrefixes.plateDense, Materials.Lead, 2),
                new ItemStack(Ic2Items.advancedAlloy.getItem(), 2))
            .fluidInputs()
            .itemOutputs(NHItemList.REACTOR_PLATING.get(1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(21),
                GTOreDictUnificator.get(OrePrefixes.plateDense, Materials.Copper, 2),
                Materials.Copper.getPlates(1),
                Materials.Silver.getPlates(1),
                NHItemList.REACTOR_PLATING.get(1))
            .fluidInputs()
            .itemOutputs(NHItemList.REACTOR_PLATING_HEAT.get(1))
            .duration(30 * SECONDS)
            .eut(256)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(23),
                GTOreDictUnificator.get(OrePrefixes.plateDense, Materials.Lead, 2),
                new ItemStack(Ic2Items.advancedAlloy.getItem(), 2),
                NHItemList.REACTOR_PLATING.get(1))
            .fluidInputs()
            .itemOutputs(NHItemList.REACTOR_PLATING_EXPLOSIVE.get(1))
            .duration(30 * SECONDS)
            .eut(256)
            .addTo(assemblerRecipes);
    }

    private static void registerReactorRecipes() {
        GTValues.RA.stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(22),
                GTOreDictUnificator.get(OrePrefixes.plateDense, Materials.Lead, 2),
                GTOreDictUnificator.get(OrePrefixes.plateDense, Materials.Titanium, 2),
                new ItemStack(BlockList.REACTOR_CHAMBER, 3),
                GTOreDictUnificator.get(OrePrefixes.cableGt08, Materials.Platinum, 1),
                GTOreDictUnificator.get(OrePrefixes.circuit, Materials.EV, 1L))
            .fluidInputs()
            .itemOutputs(new ItemStack(BlockList.REACTOR_CORE, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(21),
                GTOreDictUnificator.get(OrePrefixes.plateDense, Materials.Lead, 2),
                GTOreDictUnificator.get(OrePrefixes.plateDense, Materials.Titanium, 2),
                new ItemStack(Ic2Items.advancedAlloy.getItem(), 2),
                Ic2Items.advancedMachine)
            .fluidInputs()
            .itemOutputs(new ItemStack(BlockList.REACTOR_CHAMBER, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(new ItemStack(Ic2Items.reinforcedStone.getItem(), 1), Materials.Lead.getPlates(2))
            .fluidInputs()
            .itemOutputs(new ItemStack(BlockList.PRESSURE_VESSEL, 1))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(BlockList.PRESSURE_VESSEL, 1),
                gregtech.api.enums.ItemList.Electric_Pump_HV.get(1L))
            .fluidInputs()
            .itemOutputs(new ItemStack(BlockList.REACTOR_FLUID_PORT, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(BlockList.PRESSURE_VESSEL, 1),
                gregtech.api.enums.ItemList.Conveyor_Module_HV.get(1L))
            .fluidInputs()
            .itemOutputs(new ItemStack(BlockList.REACTOR_ACCESS_HATCH, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(BlockList.PRESSURE_VESSEL, 1),
                gregtech.api.enums.ItemList.Cover_ActivityDetector.get(1L))
            .fluidInputs()
            .itemOutputs(new ItemStack(BlockList.REACTOR_REDSTONE_PORT, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(assemblerRecipes);

        // based on nuclear control thermal monitor recipe (the non-wireless one)
        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Ic2Items.reinforcedGlass.getItem(), 3),
                GTOreDictUnificator.get(OrePrefixes.plateDense, Materials.Lead, 2),
                gregtech.api.enums.ItemList.Cover_ActivityDetector.get(1L),
                GTOreDictUnificator.get(OrePrefixes.circuit, Materials.HV, 2L),
                gregtech.api.enums.ItemList.Cover_Screen.get(1L))
            .fluidInputs()
            .itemOutputs(new ItemStack(BlockList.REACTOR_THERMAL_SENSOR, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(assemblerRecipes);

        // idk lol
        GTValues.RA.stdBuilder()
            .itemInputs()
            .fluidInputs()
            .itemOutputs(new ItemStack(BlockList.REACTOR_SIMULATOR, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);
    }
}
