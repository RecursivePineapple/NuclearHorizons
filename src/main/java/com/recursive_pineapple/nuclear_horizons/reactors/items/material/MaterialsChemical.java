package com.recursive_pineapple.nuclear_horizons.reactors.items.material;

import static com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear.DEFAULT_STATS;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear.DUST;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear.FLUID;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear.process;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear.rgb;

import bartworks.system.material.Werkstoff;
import gregtech.api.enums.TextureSet;

public class MaterialsChemical {

    public static final Werkstoff TRIBUTYL_PHOSPHATE = new Werkstoff(
        new short[] { 92, 175, 214 },
        "Tributyl Phosphate",
        process("PO(OC↓4H↓9)↓3"),
        DEFAULT_STATS,
        Werkstoff.Types.COMPOUND,
        FLUID,
        MaterialIDs.TRIBUTYL_PHOSPHATE.id,
        TextureSet.SET_FLUID);
    public static final Werkstoff PHOSPHORYL_CHLORIDE = new Werkstoff(
        new short[] { 255, 255, 175 },
        "Phosphoryl Chloride",
        process("POCl↓3"),
        DEFAULT_STATS,
        Werkstoff.Types.COMPOUND,
        FLUID,
        MaterialIDs.PHOSPHORYL_CHLORIDE.id,
        TextureSet.SET_FLUID);
    public static final Werkstoff DILUTED_NITRIC_ACID = new Werkstoff(
        new short[] { 180, 200, 171 },
        "Diluted Nitric Acid",
        process("(HNO↓3)(H↓2O)"),
        DEFAULT_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.DILUTED_NITRIC_ACID.id,
        TextureSet.SET_FLUID);
    public static final Werkstoff ZINC_NITRATE_SOLUTION = new Werkstoff(
        new short[] { 180, 200, 171 },
        "Zinc Nitrate Solution",
        process("(Zn(NO↓3)↓2)(H↓2O)"),
        DEFAULT_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.ZINC_NITRATE_SOLUTION.id,
        TextureSet.SET_FLUID);
    public static final Werkstoff ZINC_NITRATE = new Werkstoff(
        new short[] { 180, 200, 171 },
        "Zinc Nitrate",
        process("Zn(NO↓3)↓2"),
        new Werkstoff.Stats().setElektrolysis(true),
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.ZINC_NITRATE.id,
        TextureSet.SET_DULL);
    public static final Werkstoff METYHL_MAGNESIUM_BROMIDE = new Werkstoff(
        new short[] { 225, 225, 175 },
        "Methyl Magnesium Bromide",
        process("CH↓3MgBr"),
        DEFAULT_STATS,
        Werkstoff.Types.COMPOUND,
        FLUID,
        MaterialIDs.METYHL_MAGNESIUM_BROMIDE.id,
        TextureSet.SET_FLUID);
    public static final Werkstoff METHYL_ISOBUTYL_KETONE = new Werkstoff(
        new short[] { 225, 225, 175 },
        "Methyl Isobutyl Ketone",
        process("(CH↓3)↓2COC↓4H↓9"),
        DEFAULT_STATS,
        Werkstoff.Types.COMPOUND,
        FLUID,
        MaterialIDs.METHYL_ISOBUTYL_KETONE.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff HOT_LITHIUM_TETRAFLUOROBERYLLATE = new Werkstoff(
        new short[] { 225, 245, 225 },
        "Hot Lithium Tetrafluoroberyllate",
        process("Li↓2BeF↓4"),
        new Werkstoff.Stats().setElektrolysis(false).setBoilingPoint(1700),
        Werkstoff.Types.COMPOUND,
        FLUID,
        MaterialIDs.HOT_LITHIUM_TETRAFLUOROBERYLLATE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff AMERICIUM_III_OXIDE = new Werkstoff(
        new short[] { 133, 52, 37 },
        "Americium III Oxide",
        process("Am↓2O↓3"),
        DEFAULT_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.AMERICIUM_III_OXIDE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff AMERICIUM_IV_OXIDE = new Werkstoff(
        new short[] { 29, 13, 12 },
        "Americium IV Oxide",
        process("AmO↓2"),
        DEFAULT_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.AMERICIUM_IV_OXIDE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff CURIUM_III_OXIDE = new Werkstoff(
        new short[] { 205, 158, 147 },
        "Curium III Oxide",
        process("Cm↓2O↓3"),
        DEFAULT_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.CURIUM_III_OXIDE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff CURIUM_IV_OXIDE = new Werkstoff(
        new short[] { 146, 90, 72 },
        "Curium IV Oxide",
        process("CmO↓2"),
        DEFAULT_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.CURIUM_IV_OXIDE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff CALIFORNIUM_III_OXIDE = new Werkstoff(
        new short[] { 212, 255, 58 },
        "Californium III Oxide",
        process("Cf↓2O↓3"),
        DEFAULT_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.CALIFORNIUM_III_OXIDE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff CALIFORNIUM_IV_OXIDE = new Werkstoff(
        new short[] { 44, 26, 22 },
        "Californium IV Oxide",
        process("CfO↓2"),
        DEFAULT_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.CALIFORNIUM_IV_OXIDE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff URANIUM_FISSION_PRODUCT_MIXTURE = new Werkstoff(
        new short[] { 96, 115, 95 },
        "Uranium Fission Product Mixture",
        process("?CeZrGaKrLuBaMo?"),
        DEFAULT_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.URANIUM_FISSION_PRODUCT_MIXTURE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff PLUTONIUM_FISSION_PRODUCT_MIXTURE = new Werkstoff(
        new short[] { 115, 73, 76 },
        "Plutonium Fission Product Mixture",
        process("?SrYZrNbBaLaCe?"),
        DEFAULT_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.PLUTONIUM_FISSION_PRODUCT_MIXTURE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff THORIUM_FISSION_PRODUCT_MIXTURE = new Werkstoff(
        new short[] { 52, 51, 60 },
        "Thorium Fission Product Mixture",
        process("?BaCsSrZrNb?"),
        DEFAULT_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.THORIUM_FISSION_PRODUCT_MIXTURE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff TRANSURANIC_WASTE_MIXTURE = new Werkstoff(
        new short[] { 199, 199, 199 },
        "Transuranic Waste Mixture",
        process("?AmCm?"),
        DEFAULT_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.TRANSURANIC_WASTE_MIXTURE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff LANTHANUM_II_OXIDE = new Werkstoff(
        new short[] { 199, 199, 199 },
        "Lanthanum II Oxide",
        process("LaO"),
        DEFAULT_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.LANTHANUM_II_OXIDE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff LANTHANIDE_WASTE_MIXTURE = new Werkstoff(
        new short[] { 97, 96, 113 },
        "Lanthanide Waste Mixture",
        process("?LaCeNdSmEuHo?"),
        DEFAULT_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.LANTHANIDE_WASTE_MIXTURE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff BROMINE_SALT_WATER = new Werkstoff(
        rgb(0x4499BB),
        "Bromine-Rich Salt Water",
        process("?Br(NaCl)(H↓2O)?"),
        DEFAULT_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.BROMINE_SALT_WATER.id,
        TextureSet.SET_DULL);

    public static final Werkstoff THORIUM_ORE_IMPURITIES = new Werkstoff(
        rgb(0x212430),
        "Thorium Rare-Earth Impurities",
        process("??(SiO↓2)(Al↓2O↓3)(Fe↓2O↓3)??"),
        DEFAULT_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.THORIUM_ORE_IMPURITIES.id,
        TextureSet.SET_DULL);

    public static void init() {

    }

}
