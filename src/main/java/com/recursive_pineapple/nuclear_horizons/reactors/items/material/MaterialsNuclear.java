package com.recursive_pineapple.nuclear_horizons.reactors.items.material;

import bartworks.system.material.Werkstoff;
import gregtech.api.enums.TextureSet;

public class MaterialsNuclear {

    public static final Werkstoff.GenerationFeatures DUST = new Werkstoff.GenerationFeatures().disable().onlyDust();
    public static final Werkstoff.GenerationFeatures FLUID = new Werkstoff.GenerationFeatures().disable().addCells();
    public static final Werkstoff.Stats DEFAULT_STATS = new Werkstoff.Stats().setElektrolysis(false);
    public static final Werkstoff.Stats RADIO_STATS = new Werkstoff.Stats().setElektrolysis(false).setRadioactive(true);

    public static final Werkstoff NATURAL_URANIUM = new Werkstoff(
        new short[] { 49, 138, 34 },
        "Natural Uranium",
        process("??U??"),
        DEFAULT_STATS,
        Werkstoff.Types.ELEMENT,
        DUST,
        MaterialIDs.NATURAL_URANIUM.id,
        TextureSet.SET_DULL);

    public static final Werkstoff NATURAL_URANIUM_TETRAFLUORIDE = new Werkstoff(
        new short[] { 34, 138, 103 },
        "Natural Uranium Tetrafluoride",
        process("??UF↓4??"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.NATURAL_URANIUM_TETRAFLUORIDE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff NATURAL_URANIUM_HEXAFLUORIDE = new Werkstoff(
        new short[] { 49, 138, 34 },
        "Natural Uranium Hexafluoride",
        process("??UF↓6??"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        FLUID,
        MaterialIDs.NATURAL_URANIUM_HEXAFLUORIDE.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff DEPLETED_URANIUM_HEXAFLUORIDE = new Werkstoff(
        new short[] { 57, 190, 33 },
        "Depleted Uranium Hexafluoride",
        process("?U↑238F↓6?"),
        DEFAULT_STATS,
        Werkstoff.Types.COMPOUND,
        FLUID,
        MaterialIDs.DEPLETED_URANIUM_HEXAFLUORIDE.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff ENRICHED_URANIUM_HEXAFLUORIDE = new Werkstoff(
        new short[] { 84, 242, 55 },
        "Enriched Uranium Hexafluoride",
        process("?U↑235F↓6?"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        FLUID,
        MaterialIDs.ENRICHED_URANIUM_HEXAFLUORIDE.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff ENRICHED_URANIUM_FUEL = new Werkstoff(
        new short[] { 92, 214, 92 },
        "Enriched Uranium Fuel",
        process("UO↓2(U↑238O↓2)↓4"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.ENRICHED_URANIUM_FUEL.id,
        TextureSet.SET_METALLIC);

    public static final Werkstoff ENRICHED_THORIUM_FUEL = new Werkstoff(
        new short[] { 0, 77, 0 },
        "Enriched Thorium Fuel",
        process("UO↓2(ThO↓2)↓4"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.ENRICHED_THORIUM_FUEL.id,
        TextureSet.SET_METALLIC);

    public static final Werkstoff ENRICHED_MOX_FUEL = new Werkstoff(
        new short[] { 150, 138, 0 },
        "Enriched Mixed Oxide Fuel",
        process("PuO↓2(U↑238O↓2)↓4"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.ENRICHED_MOX_FUEL.id,
        TextureSet.SET_METALLIC);

    public static final Werkstoff DEPLETED_URANIUM_FUEL = new Werkstoff(
        new short[] { 41, 163, 41 },
        "Depleted Uranium Fuel",
        process("??U↑238O↓2??"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.DEPLETED_URANIUM_FUEL.id,
        TextureSet.SET_METALLIC);

    public static final Werkstoff DEPLETED_THORIUM_FUEL = new Werkstoff(
        new short[] { 0, 122, 0 },
        "Depleted Thorium Fuel",
        process("??ThO↓2??"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.DEPLETED_THORIUM_FUEL.id,
        TextureSet.SET_METALLIC);

    public static final Werkstoff DEPLETED_MOX_FUEL = new Werkstoff(
        new short[] { 206, 138, 0 },
        "Depleted Mixed Oxide Fuel",
        process("??U↑238O↓2??"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.DEPLETED_MOX_FUEL.id,
        TextureSet.SET_METALLIC);

    public static final Werkstoff DEPLETED_URANIUM_FUEL_SOLUTION = new Werkstoff(
        new short[] { 41, 163, 41 },
        "Depleted Uranium Fuel Solution",
        process("??(U↑238O↓2)(HNO↓3)??"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.DEPLETED_URANIUM_FUEL_SOLUTION.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff DEPLETED_THORIUM_FUEL_SOLUTION = new Werkstoff(
        new short[] { 0, 122, 0 },
        "Depleted Thorium Fuel Solution",
        process("??(ThO2)(HNO3)(HF)??"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.DEPLETED_THORIUM_FUEL_SOLUTION.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff DEPLETED_MOX_FUEL_SOLUTION = new Werkstoff(
        new short[] { 206, 138, 0 },
        "Depleted Mixed Oxide Fuel Solution",
        process("??(U↑238O↓2)(HNO↓3)??"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.DEPLETED_MOX_FUEL_SOLUTION.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff PU_SOLUTION_AQ_PHASE = new Werkstoff(
        new short[] { 196, 52, 83 },
        "Mixed Plutonium Solution (Aqueous Phase)",
        process("?(PuO↓2)(NO↓3)↓2?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.PU_SOLUTION_AQ_PHASE.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff U_SOLUTION_ORG_PHASE = new Werkstoff(
        new short[] { 143, 196, 53 },
        "Mixed Uranium Solution (Organic Phase)",
        process("?(UO↓2)(PO(OC↓4H↓9)↓3)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.U_SOLUTION_ORG_PHASE.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff U238_MIBK_SOLUTION = new Werkstoff(
        new short[] { 143, 196, 53 },
        "Uranium-238 Enriched Methyl Isobutyl Ketone",
        process("?(U↑238O↓2(NO↓3))((CH↓3)↓2COC↓4H↓9)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.U238_MIBK_SOLUTION.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff U238_WATER_SOLUTION = new Werkstoff(
        new short[] { 143, 196, 53 },
        "Uranium-238 Enriched Solution",
        process("?(U↑238O↓2(NO↓3))(H↓2O)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.U238_WATER_SOLUTION.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff U235_SOLUTION = new Werkstoff(
        new short[] { 143, 196, 53 },
        "Uranium-235 Enriched Solution",
        process("?(U↑238O↓2(NO↓3))(PO(OC↓4H↓9)↓3)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.U235_SOLUTION.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff MIXED_PU_SOLIDS = new Werkstoff(
        new short[] { 196, 52, 83 },
        "Mixed Plutonyl Nitrate",
        process("?PuO↓2(NO↓3)↓2?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.MIXED_PU_SOLIDS.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff PU239_MIBK_SOLUTION = new Werkstoff(
        new short[] { 143, 196, 53 },
        "Plutonium-239 Enriched Methyl Isobutyl Ketone",
        process("?(Pu↑239O↓2(NO↓3))((CH↓3)↓2COC↓4H↓9)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.PU239_MIBK_SOLUTION.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff PU239_WATER_SOLUTION = new Werkstoff(
        new short[] { 143, 196, 53 },
        "Plutonium-239 Enriched Solution",
        process("?(Pu↑239O↓2(NO↓3))(H↓2O)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.PU239_WATER_SOLUTION.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff PU241_SOLUTION = new Werkstoff(
        new short[] { 143, 196, 53 },
        "Plutonium-241 Enriched Solution",
        process("?(Pu↑241O↓2(NO↓3))(H↓2O)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.PU241_SOLUTION.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff TH_SOLUTION_AQ_PHASE = new Werkstoff(
        new short[] { 45, 64, 85 },
        "Thorium Solution (Aqueous Phase)",
        process("?Th(NO↓3)↓2(HF)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.TH_SOLUTION_AQ_PHASE.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff URANIUM233_SOLUTION_ORG_PHASE = new Werkstoff(
        new short[] { 143, 224, 53 },
        "Uranium-233 Solution (Organic Phase)",
        process("?U↑233(PO(OC↓4H↓9)↓3)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.URANIUM233_SOLUTION_ORG_PHASE.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff PLUTONYL_239_NITRATE = new Werkstoff(
        new short[] { 255, 38, 38 },
        "Plutonyl-239 Nitrate",
        process("Pu↑239O↓2(NO↓3)2"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.PLUTONYL_239_NITRATE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff PLUTONYL_241_NITRATE = new Werkstoff(
        new short[] { 227, 1, 1 },
        "Plutonyl-241 Nitrate",
        process("Pu↑241O↓2(NO↓3)2"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.PLUTONYL_241_NITRATE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff URANYL_233_NITRATE = new Werkstoff(
        new short[] { 84, 242, 127 },
        "Uranyl-233 Nitrate",
        process("U↑233O↓2(NO↓3)2"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.URANYL_233_NITRATE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff URANYL_235_NITRATE = new Werkstoff(
        new short[] { 84, 242, 55 },
        "Uranyl-235 Nitrate",
        process("U↑235O↓2(NO↓3)2"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.URANYL_235_NITRATE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff URANYL_238_NITRATE = new Werkstoff(
        rgb(0x39BE21),
        "Uranyl-238 Nitrate",
        process("U↑238O↓2(NO↓3)2"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.URANYL_238_NITRATE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff URANIUM_233_DIOXIDE = new Werkstoff(
        rgb(0xA9DD4C),
        "Uranium-233 Dioxide",
        process("U↑233O↓2"),
        DEFAULT_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.URANIUM_233_DIOXIDE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff URANIUM_235_DIOXIDE = new Werkstoff(
        rgb(0x75DD16),
        "Uranium-235 Dioxide",
        process("U↑235O↓2"),
        DEFAULT_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.URANIUM_235_DIOXIDE.id,
        TextureSet.SET_METALLIC);

    public static final Werkstoff URANIUM_238_DIOXIDE = new Werkstoff(
        rgb(0x39BE21),
        "Uranium-238 Dioxide",
        process("U↑238O↓2"),
        DEFAULT_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.URANIUM_238_DIOXIDE.id,
        TextureSet.SET_DULL);

    public static void init() {

    }

    public static String process(String b) {
        char[] chars = b.toCharArray();
        char[] nu = new char[chars.length];

        boolean sub = false, sup = false;

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (c == '↑') {
                sup = true;
                sub = false;
            } else if (c == '↓') {
                sub = true;
                sup = false;
            } else if (c == '↕') {
                sub = false;
                sup = false;
            } else if (sub) {
                nu[i] = switch (chars[i]) {
                    case '0' -> '\u2080';
                    case '1' -> '\u2081';
                    case '2' -> '\u2082';
                    case '3' -> '\u2083';
                    case '4' -> '\u2084';
                    case '5' -> '\u2085';
                    case '6' -> '\u2086';
                    case '7' -> '\u2087';
                    case '8' -> '\u2088';
                    case '9' -> '\u2089';
                    default -> {
                        sub = false;
                        yield chars[i];
                    }
                };
            } else if (sup) {
                nu[i] = switch (chars[i]) {
                    case '0' -> '\u2070';
                    case '1' -> '\u2071';
                    case '2' -> '\u00B2';
                    case '3' -> '\u00B3';
                    case '4' -> '\u2074';
                    case '5' -> '\u2075';
                    case '6' -> '\u2076';
                    case '7' -> '\u2077';
                    case '8' -> '\u2078';
                    case '9' -> '\u2079';
                    default -> {
                        sup = false;
                        yield chars[i];
                    }
                };
            } else {
                nu[i] = chars[i];
            }
        }
        return new String(nu);
    }

    public static short[] rgb(int color) {
        return new short[] { (short) ((color >> 16) & 0xFF), (short) ((color >> 8) & 0xFF), (short) (color & 0xFF) };
    }
}
