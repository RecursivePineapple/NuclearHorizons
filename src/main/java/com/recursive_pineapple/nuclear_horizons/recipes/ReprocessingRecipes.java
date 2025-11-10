package com.recursive_pineapple.nuclear_horizons.recipes;

import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.DEPLETED_MOX_FUEL;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.DEPLETED_THORIUM_FUEL;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.DEPLETED_THORIUM_FUEL_SOLUTION;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.DEPLETED_URANIUM_FUEL;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.HYDROFLUORICACID;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.METHYL_ISOBUTYL_KETONE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.MIXED_PU_SOLIDS;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.NITRICACID;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.NITROGENDIOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.PLUTONIUM;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.PLUTONIUM241;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.PLUTONIUM_FISSION_PRODUCT_MIXTURE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.PU239_MIBK_SOLUTION;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.PU239_WATER_SOLUTION;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.PU241_SOLUTION;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.PU_SOLUTION_AQ_PHASE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.REFINED_THORIUM;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.SODIUM;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.SODIUM_FLUORIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.THORIUM_FISSION_PRODUCT_MIXTURE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.TH_SOLUTION_AQ_PHASE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.TRANSURANIC_WASTE_MIXTURE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.TRIBUTYL_PHOSPHATE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.U235_SOLUTION;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.U238_MIBK_SOLUTION;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.U238_WATER_SOLUTION;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.URANIUM233;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.URANIUM233_SOLUTION_ORG_PHASE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.URANIUM_235_DIOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.URANIUM_238_DIOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.URANIUM_FISSION_PRODUCT_MIXTURE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.URANYL_233_NITRATE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.URANYL_235_NITRATE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.U_SOLUTION_ORG_PHASE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.WATER;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.ZINC;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.ZINC_NITRATE_SOLUTION;
import static gregtech.api.recipe.RecipeMaps.blastFurnaceRecipes;
import static gregtech.api.recipe.RecipeMaps.distillationTowerRecipes;
import static gregtech.api.recipe.RecipeMaps.multiblockChemicalReactorRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;
import static gregtech.api.util.GTRecipeConstants.COIL_HEAT;
import static gregtech.api.util.GTRecipeConstants.UniversalChemical;
import static gregtech.api.util.GTUtility.getIntegratedCircuit;
import static gtPlusPlus.api.recipe.GTPPRecipeMaps.mixerNonCellRecipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.TierEU;
import gregtech.api.interfaces.ISubTagContainer;
import gtPlusPlus.core.item.ModItems;

public class ReprocessingRecipes {

    private static final MaterialWrapper KEROSENE = new MaterialWrapper() {

        @Override
        public ISubTagContainer getMaterial() {
            return null;
        }

        @Override
        public ItemStack getCells(int amount) {
            return null;
        }

        @Override
        public ItemStack getDust(int amount) {
            return null;
        }

        @Override
        public ItemStack getDustTiny(int amount) {
            return null;
        }

        @Override
        public FluidStack getFluid(int amount) {
            return FluidRegistry.getFluidStack("fluid.kerosene", amount);
        }

        @Override
        public FluidStack getGas(int amount) {
            return null;
        }
    };

    public static void registerRecipes() {
        registerUraniumRepro();
        registerThoriumRepro();
        registerPlutoniumRepro();
    }

    private static void registerPlutoniumRepro() {
        // Treat dissolved fuel waste with TBP and kerosene
        GTValues.RA.stdBuilder()
            .itemInputs(DEPLETED_MOX_FUEL.getDust(9))
            .fluidInputs(
                NITRICACID.getFluid(2000),
                TRIBUTYL_PHOSPHATE.getFluid(500),
                KEROSENE.getFluid(500))
            .itemOutputs(TRANSURANIC_WASTE_MIXTURE.getDust(1), PLUTONIUM_FISSION_PRODUCT_MIXTURE.getDust(1))
            .fluidOutputs(
                U_SOLUTION_ORG_PHASE.getFluid(1000),
                PU_SOLUTION_AQ_PHASE.getFluid(2000))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_IV)
            .addTo(mixerNonCellRecipes);
    }

    public static void registerThoriumRepro() {
        // Dissolve spent fuel in nitric acid and water.
        GTValues.RA.stdBuilder()
            .fluidInputs(NITRICACID.getFluid(1000))
            .itemInputs(DEPLETED_THORIUM_FUEL.getDust(1), getIntegratedCircuit(1))
            .fluidOutputs(DEPLETED_THORIUM_FUEL_SOLUTION.getFluid(1000))
            .itemOutputs(TRANSURANIC_WASTE_MIXTURE.getDust(1))
            .duration(30 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(UniversalChemical);

        // Alternate faster path that is fluorine negative
        GTValues.RA.stdBuilder()
            .fluidInputs(NITRICACID.getFluid(1000), HYDROFLUORICACID.getFluid(10))
            .itemInputs(DEPLETED_THORIUM_FUEL.getDust(1), getIntegratedCircuit(2))
            .fluidOutputs(DEPLETED_THORIUM_FUEL_SOLUTION.getFluid(1000))
            .itemOutputs(TRANSURANIC_WASTE_MIXTURE.getDust(1))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(UniversalChemical);

        // Treat dissolved fuel waste with TBP and kerosene
        GTValues.RA.stdBuilder()
            .fluidInputs(
                DEPLETED_THORIUM_FUEL_SOLUTION.getFluid(8000),
                TRIBUTYL_PHOSPHATE.getFluid(8000),
                KEROSENE.getFluid(8000))
            .itemOutputs(
                THORIUM_FISSION_PRODUCT_MIXTURE.getDust(1),
                new ItemStack(ModItems.dustProtactinium233, 1))
            .fluidOutputs(URANIUM233_SOLUTION_ORG_PHASE.getFluid(11000), TH_SOLUTION_AQ_PHASE.getFluid(13000))
            .duration(60 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(mixerNonCellRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(URANIUM233_SOLUTION_ORG_PHASE.getFluid(11000))
            .itemOutputs(URANYL_233_NITRATE.getDust(3))
            .fluidOutputs(KEROSENE.getFluid(8000), NITROGENDIOXIDE.getGas(3000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(TH_SOLUTION_AQ_PHASE.getFluid(13000))
            .itemOutputs(REFINED_THORIUM.getDust(7))
            .fluidOutputs(TRIBUTYL_PHOSPHATE.getFluid(8000), NITROGENDIOXIDE.getGas(5000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);

        // Reduction to metals via zinc
        GTValues.RA.stdBuilder()
            .itemInputs(URANYL_233_NITRATE.getDust(3), SODIUM.getDust(4))
            .fluidInputs(HYDROFLUORICACID.getFluid(4000))
            .itemOutputs(URANIUM233.getDust(1), SODIUM_FLUORIDE.getDust(8))
            .fluidOutputs(WATER.getFluid(2000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(UniversalChemical);
    }

    private static void registerUraniumRepro() {
        // Treat dissolved fuel waste with TBP and kerosene
        GTValues.RA.stdBuilder()
            .itemInputs(DEPLETED_URANIUM_FUEL.getDust(9))
            .fluidInputs(
                NITRICACID.getFluid(1000),
                TRIBUTYL_PHOSPHATE.getFluid(1000),
                KEROSENE.getFluid(1000))
            .itemOutputs(TRANSURANIC_WASTE_MIXTURE.getDust(1), URANIUM_FISSION_PRODUCT_MIXTURE.getDust(1))
            .fluidOutputs(
                U_SOLUTION_ORG_PHASE.getFluid(2000),
                PU_SOLUTION_AQ_PHASE.getFluid(1000))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_IV)
            .addTo(mixerNonCellRecipes);

        // Use MIBK to separate U235 and U238
        GTValues.RA.stdBuilder()
            .fluidInputs(U_SOLUTION_ORG_PHASE.getFluid(1000), METHYL_ISOBUTYL_KETONE.getFluid(1000))
            .fluidOutputs(U235_SOLUTION.getFluid(1000), U238_MIBK_SOLUTION.getFluid(1000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_EV)
            .addTo(mixerNonCellRecipes);

        // Knock the uranyl 235 complex off of the TBP with nitric acid, precipitating it
        GTValues.RA.stdBuilder()
            .fluidInputs(U235_SOLUTION.getFluid(2000), NITRICACID.getFluid(1000))
            .itemOutputs(URANYL_235_NITRATE.getDust(3))
            .fluidOutputs(KEROSENE.getFluid(1000), TRIBUTYL_PHOSPHATE.getFluid(1000))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_EV)
            .addTo(multiblockChemicalReactorRecipes);

        // Roast the nitrate into dioxide and capture the no2
        GTValues.RA.stdBuilder()
            .itemInputs(URANYL_235_NITRATE.getDust(3))
            .itemOutputs(URANIUM_235_DIOXIDE.getDust(1))
            .fluidOutputs(NITROGENDIOXIDE.getGas(2000))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_IV)
            .metadata(COIL_HEAT, 600)
            .addTo(blastFurnaceRecipes);

        // Rinse the U238 MIBK with water
        GTValues.RA.stdBuilder()
            .fluidInputs(U238_MIBK_SOLUTION.getFluid(1000), WATER.getFluid(1000))
            .fluidOutputs(U238_WATER_SOLUTION.getFluid(1000), METHYL_ISOBUTYL_KETONE.getFluid(1000))
            .duration(30 * SECONDS)
            .eut(TierEU.RECIPE_EV)
            .addTo(mixerNonCellRecipes);

        // React the uranyl 238 nitrate with zinc, reducing it into elemental u 238
        GTValues.RA.stdBuilder()
            .itemInputs(ZINC.getDust(1))
            .fluidInputs(U238_WATER_SOLUTION.getFluid(4000))
            .itemOutputs(URANIUM_238_DIOXIDE.getDust(6))
            .fluidOutputs(ZINC_NITRATE_SOLUTION.getFluid(1000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_EV)
            .addTo(multiblockChemicalReactorRecipes);

        // Distill off the fluids so that the nitric acid doesn't destroy the mibk
        GTValues.RA.stdBuilder()
            .fluidInputs(PU_SOLUTION_AQ_PHASE.getFluid(2000))
            .itemOutputs(MIXED_PU_SOLIDS.getDust(4))
            // Half of the nitrate remains in the plutonium, 250/dust
            .fluidOutputs(NITRICACID.getFluid(1000), WATER.getFluid(1000))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_IV)
            .addTo(distillationTowerRecipes);

        // Dissolve solids and use MIBK to separate Pu239 and Pu241
        GTValues.RA.stdBuilder()
            // 500L nitrate
            .itemInputs(MIXED_PU_SOLIDS.getDust(2))
            .fluidInputs(METHYL_ISOBUTYL_KETONE.getFluid(800), WATER.getFluid(200))
            .fluidOutputs(PU239_MIBK_SOLUTION.getFluid(800), PU241_SOLUTION.getFluid(200))
            .duration(30 * SECONDS)
            .eut(TierEU.RECIPE_EV)
            .addTo(mixerNonCellRecipes);

        // Dissolve the plutonyl 239 nitrate into water from mibk
        GTValues.RA.stdBuilder()
            .fluidInputs(PU239_MIBK_SOLUTION.getFluid(1000), WATER.getFluid(1000))
            .fluidOutputs(PU239_WATER_SOLUTION.getFluid(1000), METHYL_ISOBUTYL_KETONE.getFluid(1000))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_EV)
            .addTo(mixerNonCellRecipes);

        // React the plutonyl 239 nitrate with zinc, reducing it into elemental pu 239
        GTValues.RA.stdBuilder()
            .itemInputs(ZINC.getDust(1))
            .fluidInputs(PU239_WATER_SOLUTION.getFluid(2000))
            .itemOutputs(PLUTONIUM.getDust(1))
            .fluidOutputs(ZINC_NITRATE_SOLUTION.getFluid(1000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_EV)
            .addTo(multiblockChemicalReactorRecipes);

        // React the plutonyl 241 nitrate solution with zinc, reducing it into elemental pu 241
        GTValues.RA.stdBuilder()
            .itemInputs(ZINC.getDust(1))
            .fluidInputs(PU241_SOLUTION.getFluid(2000)) // 1000L nitric per 1000L
            .itemOutputs(PLUTONIUM241.getDust(2))
            .fluidOutputs(ZINC_NITRATE_SOLUTION.getFluid(1000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_EV)
            .addTo(multiblockChemicalReactorRecipes);
    }
}
