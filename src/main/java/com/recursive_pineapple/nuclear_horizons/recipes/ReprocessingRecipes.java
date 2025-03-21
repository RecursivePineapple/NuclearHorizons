package com.recursive_pineapple.nuclear_horizons.recipes;

import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.*;
import static gregtech.api.recipe.RecipeMaps.blastFurnaceRecipes;
import static gregtech.api.recipe.RecipeMaps.centrifugeRecipes;
import static gregtech.api.recipe.RecipeMaps.distillationTowerRecipes;
import static gregtech.api.recipe.RecipeMaps.electrolyzerRecipes;
import static gregtech.api.recipe.RecipeMaps.multiblockChemicalReactorRecipes;
import static gregtech.api.util.GTRecipeBuilder.MINUTES;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;
import static gregtech.api.util.GTRecipeConstants.UniversalChemical;
import static gtPlusPlus.api.recipe.GTPPRecipeMaps.mixerNonCellRecipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.TierEU;
import gregtech.api.interfaces.ISubTagContainer;
import gregtech.api.util.GTUtility;

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
        registerSynthesisRecipes();
        registerRecyclingRecipes();
        registerUraniumRepro();
        registerThoriumRepro();
    }

    public static void registerThoriumRepro() {

        // Dissolve spent fuel in nitric acid and water.
        GTValues.RA.stdBuilder()
            .fluidInputs(NITRICACID.getFluid(1000))
            .itemInputs(DEPLETED_THORIUM_FUEL.getDust(1))
            .fluidOutputs(DEPLETED_THORIUM_FUEL_SOLUTION.getFluid(1000))
            .itemOutputs(TRANSURANIC_WASTE_MIXTURE.getDust(1))
            .outputChances(750)
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(UniversalChemical);

        // Treat dissolved fuel waste with TBP and kerosene
        GTValues.RA.stdBuilder()
            .fluidInputs(
                DEPLETED_THORIUM_FUEL_SOLUTION.getFluid(8000),
                TRIBUTYL_PHOSPHATE.getFluid(8000),
                KEROSENE.getFluid(8000))
            .itemOutputs(THORIUM_FISSION_PRODUCT_MIXTURE.getDust(1))
            .fluidOutputs(URANIUM233_SOLUTION_ORG_PHASE.getFluid(9000), TH_SOLUTION_AQ_PHASE.getFluid(22000))
            .duration(60 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(mixerNonCellRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(URANIUM233_SOLUTION_ORG_PHASE.getFluid(9000))
            .itemOutputs(URANYL_233_NITRATE.getDust(3))
            .fluidOutputs(KEROSENE.getFluid(8000), NITROGENDIOXIDE.getGas(3000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(TH_SOLUTION_AQ_PHASE.getFluid(22000))
            .itemOutputs(THORIANIT.getDust(7))
            .fluidOutputs(TRIBUTYL_PHOSPHATE.getFluid(9000), FLUORINE.getGas(1000), NITROGENDIOXIDE.getGas(7000))
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
        // Dissolve spent fuel in nitric acid and water.
        GTValues.RA.stdBuilder()
            .itemInputs(DEPLETED_URANIUM_FUEL.getDust(2))
            .fluidInputs(NITRICACID.getFluid(1000), WATER.getFluid(1000))
            .itemOutputs(TRANSURANIC_WASTE_MIXTURE.getDust(1))
            .outputChances(2500)
            .fluidOutputs(DEPLETED_URANIUM_FUEL_SOLUTION.getFluid(2000)) // 1000L nitric
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(UniversalChemical);

        // Treat dissolved fuel waste with TBP and kerosene
        GTValues.RA.stdBuilder()
            .fluidInputs(
                DEPLETED_URANIUM_FUEL_SOLUTION.getFluid(2000),
                TRIBUTYL_PHOSPHATE.getFluid(1000),
                KEROSENE.getFluid(1000))
            .itemOutputs(URANIUM_FISSION_PRODUCT_MIXTURE.getDust(1))
            .outputChances(1250)
            .fluidOutputs(
                PU_SOLUTION_AQ_PHASE.getFluid(2000),
                U_SOLUTION_ORG_PHASE.getFluid(2000))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(mixerNonCellRecipes);

        // Use MIBK to separate U235 and U238
        GTValues.RA.stdBuilder()
            .fluidInputs(U_SOLUTION_ORG_PHASE.getFluid(1000), METHYL_ISOBUTYL_KETONE.getFluid(1000))
            .fluidOutputs(U235_SOLUTION.getFluid(1000), U238_MIBK_SOLUTION.getFluid(1000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(mixerNonCellRecipes);

        // Knock the uranyl 235 complex off of the TBP with nitric acid, precipitating it
        GTValues.RA.stdBuilder()
            .fluidInputs(U235_SOLUTION.getFluid(2000), NITRICACID.getFluid(1000))
            .itemOutputs(URANYL_235_NITRATE.getDust(4))
            .fluidOutputs(KEROSENE.getFluid(1000), TRIBUTYL_PHOSPHATE.getFluid(1000), NITRICACID.getFluid(1000))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);

        // Roast the nitrate into dioxide and capture the no2
        GTValues.RA.stdBuilder()
            .itemInputs(URANYL_235_NITRATE.getDust(5))
            .itemOutputs(URANIUM_235_DIOXIDE.getDust(3))
            .fluidOutputs(NITROGENDIOXIDE.getGas(2000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(blastFurnaceRecipes);

        // Rinse the U238 MIBK with water
        GTValues.RA.stdBuilder()
            .fluidInputs(U238_MIBK_SOLUTION.getFluid(1000), WATER.getFluid(1000))
            .fluidOutputs(U238_WATER_SOLUTION.getFluid(1000), METHYL_ISOBUTYL_KETONE.getFluid(1000))
            .duration(60 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(mixerNonCellRecipes);

        // React the uranyl 238 nitrate with zinc, reducing it into elemental u 238
        GTValues.RA.stdBuilder()
            .itemInputs(ZINC.getDust(1))
            .fluidInputs(U238_WATER_SOLUTION.getFluid(4000))
            .itemOutputs(URANIUM238.getDust(4))
            .fluidOutputs(ZINC_NITRATE_SOLUTION.getFluid(1000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);

        // Distill off the fluids so that the nitric acid doesn't destroy the mibk
        GTValues.RA.stdBuilder()
            .fluidInputs(PU_SOLUTION_AQ_PHASE.getFluid(8000))
            .itemOutputs(MIXED_PU_SOLIDS.getDust(3)) // 2000L nitric
            .fluidOutputs(NITRICACID.getFluid(1000), WATER.getFluid(4000))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(distillationTowerRecipes);

        // Dissolve solids and use MIBK to separate Pu239 and Pu241
        GTValues.RA.stdBuilder()
            .itemInputs(MIXED_PU_SOLIDS.getDust(3))
            .fluidInputs(WATER.getFluid(1000), METHYL_ISOBUTYL_KETONE.getFluid(1000))
            .fluidOutputs(PU241_SOLUTION.getFluid(1000), PU239_MIBK_SOLUTION.getFluid(1000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(mixerNonCellRecipes);

        // Dissolve the plutonyl 239 nitrate into water from mibk
        GTValues.RA.stdBuilder()
            .fluidInputs(PU239_MIBK_SOLUTION.getFluid(1000), WATER.getFluid(1000))
            .fluidOutputs(PU239_WATER_SOLUTION.getFluid(1000), METHYL_ISOBUTYL_KETONE.getFluid(1000))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(mixerNonCellRecipes);

        // React the plutonyl 239 nitrate with zinc, reducing it into elemental p 239
        GTValues.RA.stdBuilder()
            .itemOutputs(ZINC.getDust(1))
            .fluidInputs(PU239_WATER_SOLUTION.getFluid(2000)) // 1000L nitric per 1000L
            .itemOutputs(PLUTONIUM.getDust(1))
            .fluidOutputs(ZINC_NITRATE_SOLUTION.getFluid(1000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);

        // React the plutonyl 241 nitrate solution with zinc, reducing it into elemental p 241
        GTValues.RA.stdBuilder()
            .itemInputs(ZINC.getDust(1))
            .fluidInputs(PU241_SOLUTION.getFluid(2000)) // 1000L nitric per 1000L
            .itemOutputs(PLUTONIUM241.getDust(2))
            .fluidOutputs(ZINC_NITRATE_SOLUTION.getFluid(1000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);
    }

    private static void registerSynthesisRecipes() {
        // phosphoryl chloride and tributyl phosphate (TBP) synthesis
        GTValues.RA.stdBuilder()
            .itemInputs(OXYGEN.getCells(1))
            .fluidInputs(PHOSPHORUSTRICHLORIDE.getFluid(2000))
            .fluidOutputs(PHOSPHORYL_CHLORIDE.getFluid(2000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(UniversalChemical);

        // TBP normally requires butanol, which can be synthesized using either a
        // metallic cobalt or organometallic rhodium catalyst
        // in this pack the kevlar line devs chose to use the more complicated synthesis
        // that uses rhodium and palladium organometallic catalysts
        // so this reaction is in a single step to avoid producing butanol which could bypass
        // their chemical gating
        GTValues.RA.stdBuilder()
            .itemInputs(COBALT.getDustTiny(1), GTUtility.getIntegratedCircuit(1))
            .fluidInputs(
                PHOSPHORYL_CHLORIDE.getFluid(1000),
                CARBONMONOXIDE.getGas(6000),
                WATER.getFluid(3000),
                PROPENE.getGas(3000))
            .fluidOutputs(TRIBUTYL_PHOSPHATE.getFluid(1000), CARBONDIOXIDE.getGas(3000))
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(COBALT.getDust(1), GTUtility.getIntegratedCircuit(9))
            .fluidInputs(
                PHOSPHORYL_CHLORIDE.getFluid(9000),
                CARBONMONOXIDE.getGas(54000),
                WATER.getFluid(27000),
                PROPENE.getGas(27000))
            .fluidOutputs(TRIBUTYL_PHOSPHATE.getFluid(9000), CARBONDIOXIDE.getGas(27000))
            .duration(180 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(MAGNESIUM.getDust(1))
            .fluidInputs(METHANE.getGas(1000), BROMINE.getFluid(1000))
            .fluidOutputs(METYHL_MAGNESIUM_BROMIDE.getFluid(2000))
            .duration(60 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(METYHL_MAGNESIUM_BROMIDE.getFluid(2000), ACETONE.getFluid(1000))
            .fluidOutputs(METHYL_ISOBUTYL_KETONE.getFluid(1000))
            .duration(60 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(multiblockChemicalReactorRecipes);
    }

    private static void registerRecyclingRecipes() {
        GTValues.RA.stdBuilder()
            .fluidInputs(ZINC_NITRATE_SOLUTION.getFluid(1000))
            .itemOutputs(ZINC_NITRATE.getDust(9))
            .fluidOutputs(WATER.getFluid(1000))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(ZINC_NITRATE.getDust(9))
            .itemOutputs(ZINC.getDust(1))
            .fluidOutputs(NITROGENDIOXIDE.getGas(2000))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(blastFurnaceRecipes);

        GTValues.RA.stdBuilder()
            .fluidInputs(DILUTED_NITRIC_ACID.getFluid(2000))
            .fluidOutputs(NITRICACID.getFluid(1000), WATER.getFluid(1000))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(SODIUM_FLUORIDE.getDust(2))
            .itemOutputs(SODIUM.getDust(1))
            .fluidOutputs(FLUORINE.getGas(1000))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(electrolyzerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(TRANSURANIC_WASTE_MIXTURE.getDust(1))
            .itemOutputs(
                AMERICIUM_IV_OXIDE.getDust(1),
                CURIUM_IV_OXIDE.getDust(1),
                CALIFORNIUM_IV_OXIDE.getDust(1))
            .outputChances(1500, 2750, 1000)
            .duration(2 * MINUTES)
            .eut(TierEU.RECIPE_LuV)
            .addTo(centrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(URANIUM_FISSION_PRODUCT_MIXTURE.getDust(1))
            .itemOutputs(
                CERIUMDIOXIDE.getDust(1),
                ZIRCONIUM.getDust(1),
                GALLIUM.getDust(1),
                LUTETIUM.getDust(1),
                BARIUM.getDust(1),
                LANTHANIDE_WASTE_MIXTURE.getDust(1))
            .outputChances(500, 750, 2500, 250, 750, 2500)
            .duration(2 * MINUTES)
            .eut(TierEU.RECIPE_HV)
            .addTo(centrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(THORIUM_FISSION_PRODUCT_MIXTURE.getDust(1))
            .itemOutputs(
                CAESIUM.getDust(1),
                ZIRCONIUM.getDust(1),
                LUTETIUM.getDust(1),
                BARIUM.getDust(1),
                NIOBIUM.getDust(1),
                STRONTIUM.getDust(1))
            .outputChances(1250, 1000, 3000, 1500, 1750, 1250)
            .duration(2 * MINUTES)
            .eut(TierEU.RECIPE_HV)
            .addTo(centrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(LANTHANIDE_WASTE_MIXTURE.getDust(1))
            .itemOutputs(
                LANTHANUMOXIDE.getDust(1),
                CERIUMDIOXIDE.getDust(1),
                NEODYMIUMOXIDE.getDust(1),
                SAMARIUMOXIDE.getDust(1),
                EUROPIUMIIIOXIDE.getDust(1),
                HOLMIUM.getDust(1))
            .outputChances(6500, 4500, 8000, 5000, 4000, 3000)
            .duration(2 * MINUTES)
            .eut(TierEU.RECIPE_ZPM)
            .addTo(centrifugeRecipes);

        // americium recycling
        GTValues.RA.stdBuilder()
            .itemInputs(AMERICIUM.getDust(1), AMERICIUM_IV_OXIDE.getDust(3))
            .fluidInputs(OXYGEN.getGas(1000))
            .itemOutputs(AMERICIUM_III_OXIDE.getDust(5))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_LuV)
            .addTo(UniversalChemical);

        GTValues.RA.stdBuilder()
            .itemInputs(AMERICIUM_III_OXIDE.getDust(5), ItemList.Cell_Empty.get(3))
            .itemOutputs(AMERICIUM.getDust(2), OXYGEN.getCells(3))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_LuV)
            .addTo(electrolyzerRecipes);

        // curium recycling
        GTValues.RA.stdBuilder()
            .itemInputs(CURIUM.getDust(1), CURIUM_IV_OXIDE.getDust(3))
            .fluidInputs(OXYGEN.getGas(1000))
            .itemOutputs(CURIUM_III_OXIDE.getDust(5))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_LuV)
            .addTo(UniversalChemical);

        GTValues.RA.stdBuilder()
            .itemInputs(CURIUM_III_OXIDE.getDust(5), ItemList.Cell_Empty.get(3))
            .itemOutputs(CURIUM.getDust(2), OXYGEN.getCells(3))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_LuV)
            .addTo(electrolyzerRecipes);

        // californium recycling
        GTValues.RA.stdBuilder()
            .itemInputs(CALIFORNIUM.getDust(1), CALIFORNIUM_IV_OXIDE.getDust(3))
            .fluidInputs(OXYGEN.getGas(1000))
            .itemOutputs(CALIFORNIUM_III_OXIDE.getDust(5))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_LuV)
            .addTo(UniversalChemical);

        GTValues.RA.stdBuilder()
            .itemInputs(CALIFORNIUM_III_OXIDE.getDust(5), ItemList.Cell_Empty.get(3))
            .itemOutputs(CALIFORNIUM.getDust(2), OXYGEN.getCells(3))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_LuV)
            .addTo(electrolyzerRecipes);

        // lanthanum recycling
        GTValues.RA.stdBuilder()
            .itemInputs(LANTHANUM.getDust(1), LANTHANUMOXIDE.getDust(5))
            .itemOutputs(LANTHANUM_II_OXIDE.getDust(6))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_LuV)
            .addTo(UniversalChemical);

        GTValues.RA.stdBuilder()
            .itemInputs(LANTHANUM_II_OXIDE.getDust(5), ItemList.Cell_Empty.get(3))
            .itemOutputs(LANTHANUM.getDust(2), OXYGEN.getCells(3))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_LuV)
            .addTo(electrolyzerRecipes);
    }
}
