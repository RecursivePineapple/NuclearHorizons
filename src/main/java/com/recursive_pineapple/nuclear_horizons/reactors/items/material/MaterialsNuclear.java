package com.recursive_pineapple.nuclear_horizons.reactors.items.material;

import bartworks.system.material.Werkstoff;
import bartworks.util.Pair;
import gregtech.api.enums.Materials;
import gregtech.api.enums.TextureSet;
import gtPlusPlus.core.material.MaterialsElements;

import static bartworks.util.BWUtil.subscriptNumbers;

public class MaterialsNuclear implements Runnable {

    private static final int offsetID = 14_000;

    public static final Werkstoff NATURAL_URANIUM = new Werkstoff(
        new short[] { 232, 224, 219 },
        "Natural Uranium",
        subscriptNumbers("?U"),
        new Werkstoff.Stats(),
        Werkstoff.Types.ELEMENT,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID,
        TextureSet.SET_METALLIC);

    public static final Werkstoff NATURAL_URANIUM_TETRAFLUORIDE = new Werkstoff(
        new short[] { 232, 244, 219 },
        "Natural Uranium Tetrafluoride",
        subscriptNumbers("?UF4"),
        new Werkstoff.Stats().setElektrolysis(false),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 1,
        TextureSet.SET_DULL,

        new Pair<>(NATURAL_URANIUM, 1),
        new Pair<>(Materials.Fluorine, 4));

    public static final Werkstoff NATURAL_URANIUM_HEXAFLUORIDE = new Werkstoff(
        new short[] { 232, 124, 219 },
        "Natural Uranium Hexafluoride",
        subscriptNumbers("?UF6"),
        new Werkstoff.Stats().setElektrolysis(false),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 2,
        TextureSet.SET_FLUID,
        new Pair<>(NATURAL_URANIUM_TETRAFLUORIDE, 1),
        new Pair<>(Materials.Fluorine, 2));

    public static final Werkstoff DEPLETED_URANIUM_HEXAFLUORIDE = new Werkstoff(
        new short[] { 232, 124, 219 },
        "Depleted Uranium Hexafluoride",
        subscriptNumbers("238UF6"),
        new Werkstoff.Stats().setElektrolysis(false),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 3,
        TextureSet.SET_FLUID,
        new Pair<>(NATURAL_URANIUM_TETRAFLUORIDE, 1),
        new Pair<>(Materials.Fluorine, 2));

    public static final Werkstoff ENRICHED_URANIUM_HEXAFLUORIDE = new Werkstoff(
        new short[] { 232, 124, 219 },
        "Enriched Uranium Hexafluoride",
        subscriptNumbers("235UF6"),
        new Werkstoff.Stats().setElektrolysis(false),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 4,
        TextureSet.SET_FLUID,
        new Pair<>(NATURAL_URANIUM_TETRAFLUORIDE, 1),
        new Pair<>(Materials.Fluorine, 2));

    public static final Werkstoff URANIUM_238_DIOXIDE = new Werkstoff(
        new short[] { 132, 224, 219 },
        "Uranium-238 Dioxide",
        subscriptNumbers("238UO2"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 5,
        TextureSet.SET_DULL,
        new Pair<>(Materials.Uranium, 1),
        new Pair<>(Materials.Oxygen, 2));

    public static final Werkstoff URANIUM_235_DIOXIDE = new Werkstoff(
        new short[] { 132, 224, 219 },
        "Uranium-235 Dioxide",
        subscriptNumbers("235UO2"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 6,
        TextureSet.SET_DULL,
        new Pair<>(Materials.Uranium235, 1),
        new Pair<>(Materials.Oxygen, 2));

    public static final Werkstoff TRIBUTYL_PHOSPHATE = new Werkstoff(
        new short[] { 132, 224, 219 },
        "Tributyl Phosphate",
        subscriptNumbers("PO(OC4H9)3"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 7,
        TextureSet.SET_FLUID);

    public static final Werkstoff PHOSPHORYL_CHLORIDE = new Werkstoff(
        new short[] { 132, 224, 219 },
        "Phosphoryl Chloride",
        subscriptNumbers("POCl3"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        offsetID + 8,
        TextureSet.SET_FLUID);

    public static final Werkstoff URANIUM_FISSION_PRODUCT_MIXTURE = new Werkstoff(
        new short[] { 132, 224, 219 },
        "Uranium Fission Product Mixture",
        subscriptNumbers("CsCeZrGaKrLuBaMoSr"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust(),
        offsetID + 9,
        TextureSet.SET_DULL);

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
