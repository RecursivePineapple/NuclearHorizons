package com.recursive_pineapple.nuclear_horizons.recipes;

import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.DEPLETED_MOX_FUEL;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.DEPLETED_THORIUM_FUEL;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.DEPLETED_URANIUM_FUEL;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.ENRICHED_MOX_FUEL;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.ENRICHED_THORIUM_FUEL;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.ENRICHED_URANIUM_FUEL;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.REFINED_THORIUM;
import static gregtech.api.enums.ItemList.DepletedRodMOX;
import static gregtech.api.enums.ItemList.DepletedRodMOX2;
import static gregtech.api.enums.ItemList.DepletedRodMOX4;
import static gregtech.api.enums.ItemList.DepletedRodThorium;
import static gregtech.api.enums.ItemList.DepletedRodThorium2;
import static gregtech.api.enums.ItemList.DepletedRodThorium4;
import static gregtech.api.enums.ItemList.DepletedRodUranium;
import static gregtech.api.enums.ItemList.DepletedRodUranium2;
import static gregtech.api.enums.ItemList.DepletedRodUranium4;
import static gregtech.api.enums.ItemList.RodMOX;
import static gregtech.api.enums.ItemList.RodMOX2;
import static gregtech.api.enums.ItemList.RodMOX4;
import static gregtech.api.enums.ItemList.RodThorium;
import static gregtech.api.enums.ItemList.RodThorium2;
import static gregtech.api.enums.ItemList.RodThorium4;
import static gregtech.api.enums.ItemList.RodUranium;
import static gregtech.api.enums.ItemList.RodUranium2;
import static gregtech.api.enums.ItemList.RodUranium4;
import static gregtech.api.recipe.RecipeMaps.cannerRecipes;
import static gregtech.api.recipe.RecipeMaps.thermalCentrifugeRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;

import net.minecraft.item.ItemStack;

import com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList;
import goodgenerator.loader.Loaders;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.TierEU;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTUtility;
import gtPlusPlus.core.item.ModItems;

public class FuelRodRecipes {

    public static void registerRecipes() {
        registerCanning();
        registerRecyclingRecipes();
        registerFakeNukeRecipes();
    }

    public static void registerCanning() {
        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 1),
                ENRICHED_URANIUM_FUEL.getDust(4))
            .itemOutputs(RodUranium.get(1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 2),
                ENRICHED_URANIUM_FUEL.getDust(8))
            .itemOutputs(RodUranium2.get(1))
            .duration(40 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 4),
                ENRICHED_URANIUM_FUEL.getDust(16))
            .itemOutputs(RodUranium4.get(1))
            .duration(80 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 1),
                ENRICHED_THORIUM_FUEL.getDust(2))
            .itemOutputs(RodThorium.get(1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 2),
                ENRICHED_THORIUM_FUEL.getDust(4))
            .itemOutputs(RodThorium2.get(1))
            .duration(40 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 4),
                ENRICHED_THORIUM_FUEL.getDust(8))
            .itemOutputs(RodThorium4.get(1))
            .duration(80 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 1),
                ENRICHED_MOX_FUEL.getDust(4))
            .itemOutputs(RodMOX.get(1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 2),
                ENRICHED_MOX_FUEL.getDust(8))
            .itemOutputs(RodMOX2.get(1))
            .duration(40 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 4),
                ENRICHED_MOX_FUEL.getDust(16))
            .itemOutputs(RodMOX4.get(1))
            .duration(80 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                new ItemStack(Loaders.advancedFuelRod, 1),
                REFINED_THORIUM.getDust(1))
            .itemOutputs(new ItemStack(NHItemList.THORIUM_BREEDER_ROD, 1))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(cannerRecipes);
    }

    public static void registerRecyclingRecipes() {
        GTValues.RA.stdBuilder()
            .itemInputs(DepletedRodUranium.get(1))
            .itemOutputs(
                DEPLETED_URANIUM_FUEL.getDust(4),
                new ItemStack(Loaders.advancedFuelRod, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(DepletedRodUranium2.get(1))
            .itemOutputs(
                DEPLETED_URANIUM_FUEL.getDust(8),
                new ItemStack(Loaders.advancedFuelRod, 2))
            .duration(40 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(DepletedRodUranium4.get(1))
            .itemOutputs(
                DEPLETED_URANIUM_FUEL.getDust(16),
                new ItemStack(Loaders.advancedFuelRod, 4))
            .duration(80 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(DepletedRodThorium.get(1))
            .itemOutputs(
                DEPLETED_THORIUM_FUEL.getDust(4),
                new ItemStack(Loaders.advancedFuelRod, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(DepletedRodThorium2.get(1))
            .itemOutputs(
                DEPLETED_THORIUM_FUEL.getDust(8),
                new ItemStack(Loaders.advancedFuelRod, 2))
            .duration(40 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(DepletedRodThorium4.get(1))
            .itemOutputs(
                DEPLETED_THORIUM_FUEL.getDust(16),
                new ItemStack(Loaders.advancedFuelRod, 4))
            .duration(80 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(DepletedRodMOX.get(1))
            .itemOutputs(
                DEPLETED_MOX_FUEL.getDust(4),
                new ItemStack(Loaders.advancedFuelRod, 1))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(DepletedRodMOX2.get(1))
            .itemOutputs(
                DEPLETED_MOX_FUEL.getDust(8),
                new ItemStack(Loaders.advancedFuelRod, 2))
            .duration(40 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(DepletedRodMOX4.get(1))
            .itemOutputs(
                DEPLETED_MOX_FUEL.getDust(16),
                new ItemStack(Loaders.advancedFuelRod, 4))
            .duration(80 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(new ItemStack(NHItemList.THORIUM_BREEDER_ROD_FINISHED, 1))
            .itemOutputs(
                new ItemStack(Loaders.advancedFuelRod, 1),
                new ItemStack(ModItems.dustProtactinium233, 1))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(thermalCentrifugeRecipes);
    }

    private static void registerFakeNukeRecipes() {
        GTValues.RA.stdBuilder()
            .itemInputs(new ItemStack(NHItemList.THORIUM_BREEDER_ROD))
            .itemOutputs(new ItemStack(NHItemList.THORIUM_BREEDER_ROD_FINISHED))
            .setNEIDesc(
                GTUtility.breakLines(
                    GTUtility.translate("GT5U.nei.nuclear.breeder.heat_neutral"),
                    GTUtility.translate(
                        "GT5U.nei.nuclear.breeder.reactor_hull_heat",
                        NHItemList.THORIUM_BREEDER_ROD.heatDivisor,
                        NHItemList.THORIUM_BREEDER_ROD.heatMultiplier),
                    GTUtility.translate("GT5U.nei.nuclear.breeder.required_pulse", NHItemList.THORIUM_BREEDER_ROD.maxNeutrons)))
            .duration(0)
            .eut(0)
            .addTo(RecipeMaps.ic2NuclearFakeRecipes);
    }
}
