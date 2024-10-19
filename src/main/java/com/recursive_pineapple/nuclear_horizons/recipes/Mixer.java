package com.recursive_pineapple.nuclear_horizons.recipes;

import bartworks.system.material.Werkstoff;
import bartworks.system.material.WerkstoffLoader;
import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.fluids.FluidList;
import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;
import gtPlusPlus.core.util.minecraft.ItemUtils;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import static gregtech.api.recipe.RecipeMaps.mixerRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;

public class Mixer {
    public void run() {
        GTValues.RA.stdBuilder()
            .fluidInputs(
                new FluidStack(FluidList.DISTILLED_WATER, 1000))
            .itemInputs(
                Materials.Lapis.getDust(1))
            .fluidOutputs(
                new FluidStack(FluidList.COOLANT, 1000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_LV)
            .addTo(mixerRecipes);

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
                WerkstoffLoader.Thorianit.get(OrePrefixes.dust, 4))
            .itemOutputs(
                MaterialsNuclear.ENRICHED_THORIUM_FUEL.get(OrePrefixes.dust, 5))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                MaterialsNuclear.URANIUM_233_DIOXIDE.get(OrePrefixes.dust, 1),
                WerkstoffLoader.Thorianit.get(OrePrefixes.dust, 4))
            .itemOutputs(
                MaterialsNuclear.ENRICHED_THORIUM_FUEL.get(OrePrefixes.dust, 5))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(mixerRecipes);
    }
}
