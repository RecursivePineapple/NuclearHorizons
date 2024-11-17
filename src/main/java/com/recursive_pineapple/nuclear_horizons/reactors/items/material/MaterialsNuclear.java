package com.recursive_pineapple.nuclear_horizons.reactors.items.material;

import static bartworks.util.BWUtil.subscriptNumbers;

import bartworks.system.material.Werkstoff;
import gregtech.api.enums.TextureSet;

public class MaterialsNuclear implements Runnable {

    private static final int offsetID = 14_000;

    public static final Werkstoff NATURAL_URANIUM = new Werkstoff(
        new short[] { 49, 138, 34 },
        "Natural Uranium",
        subscriptNumbers("?U"),
        new Werkstoff.Stats(),
        Werkstoff.Types.ELEMENT,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID,
        TextureSet.SET_METALLIC);

    public static final Werkstoff NATURAL_URANIUM_TETRAFLUORIDE = new Werkstoff(
        new short[] { 34, 138, 103 },
        "Natural Uranium Tetrafluoride",
        subscriptNumbers("?UF4"),
        new Werkstoff.Stats().setElektrolysis(false),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 1,
        TextureSet.SET_DULL);

    public static final Werkstoff NATURAL_URANIUM_HEXAFLUORIDE = new Werkstoff(
        new short[] { 49, 138, 34 },
        "Natural Uranium Hexafluoride",
        subscriptNumbers("?UF6"),
        new Werkstoff.Stats().setElektrolysis(false),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 2,
        TextureSet.SET_FLUID);

    public static final Werkstoff DEPLETED_URANIUM_HEXAFLUORIDE = new Werkstoff(
        new short[] { 57, 190, 33 },
        "Depleted Uranium Hexafluoride",
        subscriptNumbers("238UF6"),
        new Werkstoff.Stats().setElektrolysis(false),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 3,
        TextureSet.SET_FLUID);

    public static final Werkstoff ENRICHED_URANIUM_HEXAFLUORIDE = new Werkstoff(
        new short[] { 84, 242, 55 },
        "Enriched Uranium Hexafluoride",
        subscriptNumbers("*235UF6"),
        new Werkstoff.Stats().setElektrolysis(false),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 4,
        TextureSet.SET_FLUID);

    public static final Werkstoff URANIUM_238_DIOXIDE = new Werkstoff(
        new short[] { 57, 190, 33 },
        "Uranium-238 Dioxide",
        subscriptNumbers("238UO2"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 5,
        TextureSet.SET_DULL);

    public static final Werkstoff URANIUM_235_DIOXIDE = new Werkstoff(
        new short[] { 84, 242, 55 },
        "Uranium-235 Dioxide",
        subscriptNumbers("*235UO2"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 6,
        TextureSet.SET_DULL);

    public static final Werkstoff TRIBUTYL_PHOSPHATE = new Werkstoff(
        new short[] { 92, 175, 214 },
        "Tributyl Phosphate",
        subscriptNumbers("PO(OC4H9)3"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 7,
        TextureSet.SET_FLUID);

    public static final Werkstoff PHOSPHORYL_CHLORIDE = new Werkstoff(
        new short[] { 255, 255, 175 },
        "Phosphoryl Chloride",
        subscriptNumbers("POCl3"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 8,
        TextureSet.SET_FLUID);

    public static final Werkstoff URANIUM_FISSION_PRODUCT_MIXTURE = new Werkstoff(
        new short[] { 96, 115, 95 },
        "Uranium Fission Product Mixture",
        subscriptNumbers("?CeZrGaKrLuBaMo?"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 9,
        TextureSet.SET_DULL);

    public static final Werkstoff ENRICHED_URANIUM_FUEL = new Werkstoff(
        new short[] { 92, 214, 92 },
        "Enriched Uranium Fuel",
        subscriptNumbers("*UO2(238UO2)4"),
        new Werkstoff.Stats(),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 10,
        TextureSet.SET_METALLIC);

    public static final Werkstoff ENRICHED_THORIUM_FUEL = new Werkstoff(
        new short[] { 0, 77, 0 },
        "Enriched Thorium Fuel",
        subscriptNumbers("*UO2(ThO2)4"),
        new Werkstoff.Stats(),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 11,
        TextureSet.SET_METALLIC);

    public static final Werkstoff DEPLETED_URANIUM_FUEL = new Werkstoff(
        new short[] { 41, 163, 41 },
        "Depleted Uranium Fuel",
        subscriptNumbers("??(238UO2)"),
        new Werkstoff.Stats(),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 12,
        TextureSet.SET_METALLIC);

    public static final Werkstoff DEPLETED_THORIUM_FUEL = new Werkstoff(
        new short[] { 0, 122, 0 },
        "Depleted Thorium Fuel",
        subscriptNumbers("??(ThO2)"),
        new Werkstoff.Stats(),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 13,
        TextureSet.SET_METALLIC);

    public static final Werkstoff URANIUM_233_DIOXIDE = new Werkstoff(
        new short[] { 84, 242, 127 },
        "Uranium-233 Dioxide",
        subscriptNumbers("*233UO2"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 14,
        TextureSet.SET_DULL);

    public static final Werkstoff DEPLETED_URANIUM_FUEL_SOLUTION = new Werkstoff(
        new short[] { 41, 163, 41 },
        "Depleted Uranium Fuel Solution",
        subscriptNumbers("??(238UO2)(HNO3)"),
        new Werkstoff.Stats(),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 15,
        TextureSet.SET_FLUID);

    public static final Werkstoff DEPLETED_THORIUM_FUEL_SOLUTION = new Werkstoff(
        new short[] { 0, 122, 0 },
        "Depleted Thorium Fuel Solution",
        subscriptNumbers("??(ThO2)(HNO3)(HF)"),
        new Werkstoff.Stats(),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 16,
        TextureSet.SET_FLUID);

    public static final Werkstoff TREATED_DEPLETED_URANIUM_FUEL_SOLUTION = new Werkstoff(
        new short[] { 41, 163, 102 },
        "Treated Depleted Uranium Fuel Solution",
        subscriptNumbers("??(238UO2)(HNO3)(PO(OC4H9)3)"),
        new Werkstoff.Stats(),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 17,
        TextureSet.SET_FLUID);

    public static final Werkstoff TREATED_DEPLETED_THORIUM_FUEL_SOLUTION = new Werkstoff(
        new short[] { 0, 122, 110 },
        "Treated Depleted Thorium Fuel Solution",
        subscriptNumbers("??(ThO2)(HNO3)(HF)(PO(OC4H9)3)"),
        new Werkstoff.Stats(),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 18,
        TextureSet.SET_FLUID);

    public static final Werkstoff AQUEOUS_PLUTONIUM_SOLUTION = new Werkstoff(
        new short[] { 196, 52, 83 },
        "Aqueous Plutonium Solution",
        subscriptNumbers("??Pu(NO3)2"),
        new Werkstoff.Stats(),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 19,
        TextureSet.SET_FLUID);

    public static final Werkstoff KEROSENE_URANIUM_SOLUTION = new Werkstoff(
        new short[] { 143, 196, 53 },
        "Kerosene Uranium Solution",
        subscriptNumbers("238U(NO3)2"),
        new Werkstoff.Stats(),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 20,
        TextureSet.SET_FLUID);

    public static final Werkstoff AQUEOUS_THORIUM_SOLUTION = new Werkstoff(
        new short[] { 45, 64, 85 },
        "Aqueous Thorium Solution",
        subscriptNumbers("??Th(NO3)2(HF)"),
        new Werkstoff.Stats(),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 21,
        TextureSet.SET_FLUID);

    public static final Werkstoff KEROSENE_URANIUM233_SOLUTION = new Werkstoff(
        new short[] { 143, 224, 53 },
        "Kerosene Uranium-233 Solution",
        subscriptNumbers("*233U(NO3)2"),
        new Werkstoff.Stats(),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 22,
        TextureSet.SET_FLUID);

    public static final Werkstoff TRANSPLUTONIC_WASTE_MIXTURE = new Werkstoff(
        new short[] { 199, 199, 199 },
        "Transplutonic Waste Mixture",
        subscriptNumbers("?Am?"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 23,
        TextureSet.SET_DULL);

    public static final Werkstoff PLUTONIUM_239_DIOXIDE = new Werkstoff(
        new short[] { 255, 38, 38 },
        "Plutonium-239 Dioxide",
        subscriptNumbers("*239PuO2"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 24,
        TextureSet.SET_DULL);

    public static final Werkstoff PLUTONIUM_241_DIOXIDE = new Werkstoff(
        new short[] { 227, 1, 1 },
        "Plutonium-241 Dioxide",
        subscriptNumbers("*241PuO2"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 25,
        TextureSet.SET_DULL);

    public static final Werkstoff PLUTONIUM_FISSION_PRODUCT_MIXTURE = new Werkstoff(
        new short[] { 96, 115, 95 },
        "Plutonium Fission Product Mixture",
        subscriptNumbers("?CeZrGaKrLuBaMo?"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 26,
        TextureSet.SET_DULL);

    public static final Werkstoff ENRICHED_MOX_FUEL = new Werkstoff(
        new short[] { 150, 138, 0 },
        "Enriched Mixed Oxide Fuel",
        subscriptNumbers("*PuO2(238UO2)4"),
        new Werkstoff.Stats(),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 27,
        TextureSet.SET_METALLIC);

    public static final Werkstoff DEPLETED_MOX_FUEL = new Werkstoff(
        new short[] { 206, 138, 0 },
        "Depleted Mixed Oxide Fuel",
        subscriptNumbers("??(238UO2)"),
        new Werkstoff.Stats(),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 28,
        TextureSet.SET_METALLIC);

    public static final Werkstoff DEPLETED_MOX_FUEL_SOLUTION = new Werkstoff(
        new short[] { 206, 138, 0 },
        "Depleted Mixed Oxide Fuel Solution",
        subscriptNumbers("??(238UO2)(HNO3)"),
        new Werkstoff.Stats(),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 29,
        TextureSet.SET_FLUID);

    private static final MaterialsNuclear INSTANCE = new MaterialsNuclear();

    public static MaterialsNuclear getInstance() {
        return INSTANCE;
    }

    public static void runInit() {

    }

    @Override
    public void run() {

    }
}
