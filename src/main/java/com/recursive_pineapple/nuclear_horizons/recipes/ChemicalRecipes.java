package com.recursive_pineapple.nuclear_horizons.recipes;

import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.ACETONE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.AMERICIUM;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.AMERICIUM_III_OXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.AMERICIUM_IV_OXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.BARIUM;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.BROMINE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.BROMINE_SALT_WATER;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.CAESIUM;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.CALIFORNIUM;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.CALIFORNIUM_III_OXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.CALIFORNIUM_IV_OXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.CARBONDIOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.CARBONMONOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.CERIUMDIOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.CHLORINE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.COBALT;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.CURIUM;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.CURIUM_III_OXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.CURIUM_IV_OXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.DILUTED_NITRIC_ACID;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.EUROPIUMIIIOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.FLUORINE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.GALLIUM;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.HOLMIUM;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.LANTHANIDE_WASTE_MIXTURE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.LANTHANUM;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.LANTHANUMOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.LANTHANUM_II_OXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.LUTETIUM;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.MAGNESIUM;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.METHANE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.METHYL_ISOBUTYL_KETONE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.METYHL_MAGNESIUM_BROMIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.NEODYMIUMOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.NIOBIUM;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.NITRICACID;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.NITROGENDIOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.OXYGEN;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.PHOSPHORUSTRICHLORIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.PHOSPHORYL_CHLORIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.PLUTONIUM;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.PLUTONIUM241;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.PLUTONIUM_239_DIOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.PLUTONIUM_241_DIOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.PROPENE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.SAMARIUMOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.SODIUM;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.SODIUM_FLUORIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.STRONTIUM;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.THORIUM_FISSION_PRODUCT_MIXTURE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.TRANSURANIC_WASTE_MIXTURE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.TRIBUTYL_PHOSPHATE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.URANIUM233;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.URANIUM235;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.URANIUM238;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.URANIUM_233_DIOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.URANIUM_235_DIOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.URANIUM_238_DIOXIDE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.URANIUM_FISSION_PRODUCT_MIXTURE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.WATER;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.ZINC;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.ZINC_NITRATE;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.ZINC_NITRATE_SOLUTION;
import static com.recursive_pineapple.nuclear_horizons.recipes.GTMats.ZIRCONIUM;
import static gregtech.api.recipe.RecipeMaps.blastFurnaceRecipes;
import static gregtech.api.recipe.RecipeMaps.centrifugeRecipes;
import static gregtech.api.recipe.RecipeMaps.distillationTowerRecipes;
import static gregtech.api.recipe.RecipeMaps.electrolyzerRecipes;
import static gregtech.api.recipe.RecipeMaps.mixerRecipes;
import static gregtech.api.recipe.RecipeMaps.multiblockChemicalReactorRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;
import static gregtech.api.util.GTRecipeConstants.UniversalChemical;
import static gregtech.api.util.GTUtility.getIntegratedCircuit;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;
import gregtech.api.util.GTOreDictUnificator;

public class ChemicalRecipes {

    public static void registerRecipes() {
        registerSynthesisRecipes();
        registerRecyclingRecipes();
        registerDioxideRecipes();
    }

    private static void registerSynthesisRecipes() {
        // Bromine Rich Salt Water
        GTValues.RA.stdBuilder()
            .itemInputs(GTOreDictUnificator.get(OrePrefixes.crushed, Materials.Salt, 1))
            .fluidInputs(WATER.getFluid(1000))
            .fluidOutputs(BROMINE_SALT_WATER.getFluid(1000))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTOreDictUnificator.get(OrePrefixes.crushed, Materials.RockSalt, 1))
            .fluidInputs(WATER.getFluid(1000))
            .fluidOutputs(BROMINE_SALT_WATER.getFluid(1000))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(mixerRecipes);

        // Swap the bromine ions with chlorine ions
        GTValues.RA.stdBuilder()
            .itemInputs(BROMINE_SALT_WATER.getCells(1))
            .fluidInputs(CHLORINE.getGas(100))
            // Use Materials because SALTWATER is an unused gt++ material
            .itemOutputs(Materials.SaltWater.getCells(1))
            .fluidOutputs(BROMINE.getFluid(100))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(mixerRecipes);

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
            .itemInputs(COBALT.getDustTiny(1), getIntegratedCircuit(1))
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
            .itemInputs(COBALT.getDust(1), getIntegratedCircuit(9))
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
            .itemOutputs(ZINC_NITRATE.getDust(3))
            .fluidOutputs(WATER.getFluid(1000))
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_HV)
            .addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(ZINC_NITRATE.getDust(3))
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
            .itemOutputs(AMERICIUM_IV_OXIDE.getDust(1), CURIUM_IV_OXIDE.getDust(1), CALIFORNIUM_IV_OXIDE.getDust(1))
            .outputChances(1500, 2750, 1000)
            .duration(30 * SECONDS)
            .eut(TierEU.RECIPE_LuV)
            .addTo(centrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(URANIUM_FISSION_PRODUCT_MIXTURE.getDust(1))
            .itemOutputs(
                LANTHANIDE_WASTE_MIXTURE.getDust(1),
                GALLIUM.getDust(1),
                ZIRCONIUM.getDust(1),
                BARIUM.getDust(1),
                CERIUMDIOXIDE.getDust(1),
                LUTETIUM.getDust(1))
            .outputChances(2500, 2500, 750, 750, 500, 250)
            .duration(10 * SECONDS)
            .eut(TierEU.RECIPE_IV)
            .addTo(centrifugeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(THORIUM_FISSION_PRODUCT_MIXTURE.getDust(1))
            .itemOutputs(
                LUTETIUM.getDust(1),
                ZIRCONIUM.getDust(1),
                CAESIUM.getDust(1),
                NIOBIUM.getDust(1),
                BARIUM.getDust(1),
                STRONTIUM.getDust(1))
            .outputChances(9000, 7500, 4500, 1750, 1500, 1250)
            .duration(20 * SECONDS)
            .eut(TierEU.RECIPE_EV)
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
            .duration(30 * SECONDS)
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

    private static void registerDioxideRecipes() {
        // Dioxide synthesis
        GTValues.RA.stdBuilder()
            .itemInputs(URANIUM233.getDust(1), OXYGEN.getCells(2))
            .itemOutputs(URANIUM_233_DIOXIDE.getDust(3))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(UniversalChemical);

        GTValues.RA.stdBuilder()
            .itemInputs(URANIUM235.getDust(1), OXYGEN.getCells(2))
            .itemOutputs(URANIUM_235_DIOXIDE.getDust(3))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(UniversalChemical);

        GTValues.RA.stdBuilder()
            .itemInputs(URANIUM238.getDust(1), OXYGEN.getCells(2))
            .itemOutputs(URANIUM_238_DIOXIDE.getDust(3))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(UniversalChemical);

        GTValues.RA.stdBuilder()
            .itemInputs(PLUTONIUM.getDust(1), OXYGEN.getCells(2))
            .itemOutputs(PLUTONIUM_239_DIOXIDE.getDust(3))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(UniversalChemical);

        GTValues.RA.stdBuilder()
            .itemInputs(PLUTONIUM241.getDust(1), OXYGEN.getCells(2))
            .itemOutputs(PLUTONIUM_241_DIOXIDE.getDust(3))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(UniversalChemical);

        // Dioxide electrolysis
        GTValues.RA.stdBuilder()
            .itemInputs(URANIUM_233_DIOXIDE.getDust(3))
            .itemOutputs(URANIUM233.getDust(1))
            .fluidOutputs(OXYGEN.getGas(2000))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(electrolyzerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(URANIUM_235_DIOXIDE.getDust(3))
            .itemOutputs(URANIUM235.getDust(1))
            .fluidOutputs(OXYGEN.getGas(2000))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(electrolyzerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(PLUTONIUM_239_DIOXIDE.getDust(3))
            .itemOutputs(PLUTONIUM.getDust(1))
            .fluidOutputs(OXYGEN.getGas(2000))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(electrolyzerRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(PLUTONIUM_241_DIOXIDE.getDust(3))
            .itemOutputs(PLUTONIUM241.getDust(1))
            .fluidOutputs(OXYGEN.getGas(2000))
            .duration(5 * SECONDS)
            .eut(TierEU.RECIPE_MV)
            .addTo(electrolyzerRecipes);
    }
}
