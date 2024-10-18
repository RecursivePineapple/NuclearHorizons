package com.recursive_pineapple.nuclear_horizons.recipes;

import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.blocks.BlockList;
import com.recursive_pineapple.nuclear_horizons.reactors.items.ItemList;
import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;

import static gregtech.api.recipe.RecipeMaps.assemblerRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;

public class AssemblingMachine {
    public void run() {

        //basic components
        GTValues.RA.stdBuilder()
            .itemInputs()
            .fluidInputs()
            .itemOutputs(new ItemStack(ItemList.BASIC_HEAT_EXCHANGER, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs()
            .fluidInputs()
            .itemOutputs(new ItemStack(ItemList.ADVANCED_HEAT_EXCHANGER, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs()
            .fluidInputs()
            .itemOutputs(new ItemStack(ItemList.REACTOR_HEAT_EXCHANGER, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs()
            .fluidInputs()
            .itemOutputs(new ItemStack(ItemList.COMPONENT_HEAT_EXCHANGER, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs()
            .fluidInputs()
            .itemOutputs(new ItemStack(ItemList.BASIC_HEAT_VENT, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs()
            .fluidInputs()
            .itemOutputs(new ItemStack(ItemList.COMPONENT_HEAT_VENT, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs()
            .fluidInputs()
            .itemOutputs(new ItemStack(ItemList.ADVANCED_HEAT_VENT, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs()
            .fluidInputs()
            .itemOutputs(new ItemStack(ItemList.REACTOR_HEAT_VENT, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs()
            .fluidInputs()
            .itemOutputs(new ItemStack(ItemList.OVERCLOCKED_HEAT_VENT, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs()
            .fluidInputs()
            .itemOutputs(new ItemStack(BlockList.REACTOR_CORE, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs()
            .fluidInputs()
            .itemOutputs(new ItemStack(BlockList.REACTOR_CHAMBER, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs()
            .fluidInputs()
            .itemOutputs(new ItemStack(BlockList.PRESSURE_VESSEL, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs()
            .fluidInputs()
            .itemOutputs(new ItemStack(BlockList.REACTOR_FLUID_PORT, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs()
            .fluidInputs()
            .itemOutputs(new ItemStack(BlockList.REACTOR_ACCESS_HATCH, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs()
            .fluidInputs()
            .itemOutputs(new ItemStack(BlockList.REACTOR_REDSTONE_PORT, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs()
            .fluidInputs()
            .itemOutputs(new ItemStack(BlockList.REACTOR_THERMAL_SENSOR, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs()
            .fluidInputs()
            .itemOutputs(new ItemStack(BlockList.REACTOR_SIMULATOR, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(assemblerRecipes);
    }
}
