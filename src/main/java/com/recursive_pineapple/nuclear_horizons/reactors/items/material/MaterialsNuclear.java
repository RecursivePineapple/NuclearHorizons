package com.recursive_pineapple.nuclear_horizons.reactors.items.material;

import bartworks.system.material.Werkstoff;
import gregtech.api.enums.TextureSet;
import gregtech.api.util.CustomGlyphs;

public class MaterialsNuclear {

    public static final Werkstoff.GenerationFeatures DUST = new Werkstoff.GenerationFeatures().disable().onlyDust();
    public static final Werkstoff.GenerationFeatures FLUID = new Werkstoff.GenerationFeatures().disable().addCells();
    public static final Werkstoff.Stats DEFAULT_STATS = new Werkstoff.Stats().setElektrolysis(false);
    public static final Werkstoff.Stats SYNTH = new Werkstoff.Stats().setElektrolysis(true);
    public static final Werkstoff.Stats RADIO_STATS = new Werkstoff.Stats().setElektrolysis(false).setRadioactive(true);

    public static final Werkstoff NATURAL_URANIUM = new Werkstoff(
        rgb(0x318A22),
        "Natural Uranium",
        process("??U??"),
        DEFAULT_STATS,
        Werkstoff.Types.ELEMENT,
        DUST,
        MaterialIDs.NATURAL_URANIUM.id,
        TextureSet.SET_DULL);

    public static final Werkstoff NATURAL_URANIUM_HEXAFLUORIDE = new Werkstoff(
        rgb(0xc7e6c3),
        "Natural Uranium Hexafluoride",
        process("??UF↓6??"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable().onlyDust().addCells(),
        MaterialIDs.NATURAL_URANIUM_HEXAFLUORIDE.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff DEPLETED_URANIUM_HEXAFLUORIDE = new Werkstoff(
        rgb(0x39BE21),
        "Depleted Uranium Hexafluoride",
        process("?↑238UF↓6?"),
        DEFAULT_STATS,
        Werkstoff.Types.COMPOUND,
        FLUID,
        MaterialIDs.DEPLETED_URANIUM_HEXAFLUORIDE.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff ENRICHED_URANIUM_HEXAFLUORIDE = new Werkstoff(
        rgb(0x54F237),
        "Enriched Uranium Hexafluoride",
        process("?↑235UF↓6?"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        FLUID,
        MaterialIDs.ENRICHED_URANIUM_HEXAFLUORIDE.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff ENRICHED_URANIUM_FUEL = new Werkstoff(
        rgb(0x5CD65C),
        "Enriched Uranium Fuel",
        process("?UO↓2?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.ENRICHED_URANIUM_FUEL.id,
        TextureSet.SET_METALLIC);

    public static final Werkstoff ENRICHED_THORIUM_FUEL = new Werkstoff(
        rgb(0x004D00),
        "Enriched Thorium Fuel",
        process("?(UO↓2)(ThO↓2)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.ENRICHED_THORIUM_FUEL.id,
        TextureSet.SET_METALLIC);

    public static final Werkstoff ENRICHED_MOX_FUEL = new Werkstoff(
        rgb(0x994433),
        "Enriched Mixed Metal Oxide Fuel",
        process("?(PuO↓2)(UO↓2)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.ENRICHED_MOX_FUEL.id,
        TextureSet.SET_METALLIC);

    public static final Werkstoff DEPLETED_URANIUM_FUEL = new Werkstoff(
        rgb(0x29A329),
        "Depleted Uranium Fuel",
        process("??↑238UO↓2??"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.DEPLETED_URANIUM_FUEL.id,
        TextureSet.SET_METALLIC);

    public static final Werkstoff DEPLETED_THORIUM_FUEL = new Werkstoff(
        rgb(0x007A00),
        "Depleted Thorium Fuel",
        process("??ThO↓2??"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.DEPLETED_THORIUM_FUEL.id,
        TextureSet.SET_METALLIC);

    public static final Werkstoff DEPLETED_MOX_FUEL = new Werkstoff(
        rgb(0x512626),
        "Depleted Mixed Metal Oxide Fuel",
        process("?(PuO↓2)(UO↓2)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.DEPLETED_MOX_FUEL.id,
        TextureSet.SET_METALLIC);

    public static final Werkstoff DEPLETED_URANIUM_FUEL_SOLUTION = new Werkstoff(
        rgb(0x29A329),
        "Depleted Uranium Fuel Solution",
        process("??(↑238UO↓2)(HNO↓3)??"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.DEPLETED_URANIUM_FUEL_SOLUTION.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff DEPLETED_THORIUM_FUEL_SOLUTION = new Werkstoff(
        rgb(0x007A09),
        "Depleted Thorium Fuel Solution",
        process("??(ThO↓2)(HNO↓3)??"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.DEPLETED_THORIUM_FUEL_SOLUTION.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff PU_SOLUTION_AQ_PHASE = new Werkstoff(
        rgb(0xC43453),
        "Mixed Plutonium Solution (Aqueous Phase)",
        process("?(PuO↓2)(NO↓3)↓2?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.PU_SOLUTION_AQ_PHASE.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff U_SOLUTION_ORG_PHASE = new Werkstoff(
        rgb(0x8FC435),
        "Mixed Uranium Solution (Organic Phase)",
        process("?(UO↓2)(PO(OC↓4H↓9)↓3)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.U_SOLUTION_ORG_PHASE.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff U238_MIBK_SOLUTION = new Werkstoff(
        rgb(0x679a28),
        "Uranium-238 Enriched Methyl Isobutyl Ketone",
        process("?(↑238UO↓2)((CH↓3)↓2COC↓4H↓9)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.U238_MIBK_SOLUTION.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff U238_WATER_SOLUTION = new Werkstoff(
        rgb(0x587b23),
        "Uranium-238 Enriched Solution",
        process("?(↑238UO↓2)(H↓2O)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.U238_WATER_SOLUTION.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff U235_SOLUTION = new Werkstoff(
        rgb(0x62c446),
        "Uranium-235 Enriched Solution",
        process("?(↑238UO↓2(NO↓3))(PO(OC↓4H↓9)↓3)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.U235_SOLUTION.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff MIXED_PU_SOLIDS = new Werkstoff(
        rgb(0xC43453),
        "Mixed Plutonyl Nitrate",
        process("?PuO↓2(NO↓3)↓2?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        DUST,
        MaterialIDs.MIXED_PU_SOLIDS.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff PU239_MIBK_SOLUTION = new Werkstoff(
        rgb(0x9e2615),
        "Plutonium-239 Enriched Methyl Isobutyl Ketone",
        process("?(↑239PuO↓2(NO↓3))((CH↓3)↓2COC↓4H↓9)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.PU239_MIBK_SOLUTION.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff PU239_WATER_SOLUTION = new Werkstoff(
        rgb(0x78141f),
        "Plutonium-239 Enriched Solution",
        process("?(↑239PuO↓2(NO↓3))(H↓2O)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.PU239_WATER_SOLUTION.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff PU241_SOLUTION = new Werkstoff(
        rgb(0xc44d56),
        "Plutonium-241 Enriched Solution",
        process("?(↑241PuO↓2(NO↓3))(H↓2O)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.PU241_SOLUTION.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff TH_SOLUTION_AQ_PHASE = new Werkstoff(
        rgb(0x2D4055),
        "Thorium Solution (Aqueous Phase)",
        process("?Th(NO↓3)↓2?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.TH_SOLUTION_AQ_PHASE.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff URANIUM233_SOLUTION_ORG_PHASE = new Werkstoff(
        rgb(0x19e01e),
        "Uranium-233 Solution (Organic Phase)",
        process("?↑233U(PO(OC↓4H↓9)↓3)?"),
        RADIO_STATS,
        Werkstoff.Types.MIXTURE,
        FLUID,
        MaterialIDs.URANIUM233_SOLUTION_ORG_PHASE.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff PLUTONYL_239_NITRATE = new Werkstoff(
        rgb(0xFF2626),
        "Plutonyl-239 Nitrate",
        process("↑239PuO↓2(NO↓3)↓2"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.PLUTONYL_239_NITRATE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff PLUTONYL_241_NITRATE = new Werkstoff(
        rgb(0xE30101),
        "Plutonyl-241 Nitrate",
        process("↑241PuO↓2(NO↓3)↓2"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.PLUTONYL_241_NITRATE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff URANYL_233_NITRATE = new Werkstoff(
        rgb(0x4AF27F),
        "Uranyl-233 Nitrate",
        process("↑233UO↓2(NO↓3)↓2"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.URANYL_233_NITRATE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff URANYL_235_NITRATE = new Werkstoff(
        rgb(0x54F237),
        "Uranyl-235 Nitrate",
        process("↑235UO↓2(NO↓3)↓2"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.URANYL_235_NITRATE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff URANYL_238_NITRATE = new Werkstoff(
        rgb(0x39BE21),
        "Uranyl-238 Nitrate",
        process("↑238UO↓2(NO↓3)↓2"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.URANYL_238_NITRATE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff URANIUM_233_DIOXIDE = new Werkstoff(
        rgb(0xA9DD4C),
        "Uranium-233 Dioxide",
        process("↑233UO↓2"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.URANIUM_233_DIOXIDE.id,
        TextureSet.SET_SHINY);

    public static final Werkstoff URANIUM_235_DIOXIDE = new Werkstoff(
        rgb(0x75DD16),
        "Uranium-235 Dioxide",
        process("↑235UO↓2"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.URANIUM_235_DIOXIDE.id,
        TextureSet.SET_SHINY);

    public static final Werkstoff URANIUM_238_DIOXIDE = new Werkstoff(
        rgb(0x39BE21),
        "Uranium-238 Dioxide",
        process("↑238UO↓2"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.URANIUM_238_DIOXIDE.id,
        TextureSet.SET_SHINY);


    public static final Werkstoff UNREFINED_THORIUM_SOLUTION = new Werkstoff(
        rgb(0x393b32),
        "Unrefined Thorium Solution",
        process("??Th(NO↓3)??"),
        DEFAULT_STATS,
        Werkstoff.Types.COMPOUND,
        FLUID,
        MaterialIDs.UNREFINED_THORIUM_SOLUTION.id,
        TextureSet.SET_FLUID);

    public static final Werkstoff THORIUM_NITRATE_SOLUTION = new Werkstoff(
        rgb(0x182a21),
        "Thorium Nitrate Solution",
        process("Th(NO↓3)"),
        DEFAULT_STATS,
        Werkstoff.Types.COMPOUND,
        FLUID,
        MaterialIDs.THORIUM_NITRATE.id,
        TextureSet.SET_SHINY);

    public static final Werkstoff REFINED_THORIUM = new Werkstoff(
        rgb(0x081a11),
        "Refined Thorium",
        process("Th"),
        DEFAULT_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.REFINED_THORIUM.id,
        TextureSet.SET_SHINY);

    public static final Werkstoff PLUTONIUM_239_DIOXIDE = new Werkstoff(
        rgb(0xac4643),
        "Plutonium-239 Dioxide",
        process("↑239PuO↓2"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.PLUTONIUM_239_DIOXIDE.id,
        TextureSet.SET_DULL);

    public static final Werkstoff PLUTONIUM_241_DIOXIDE = new Werkstoff(
        rgb(0xd30024),
        "Plutonium-241 Dioxide",
        process("↑241PuO↓2"),
        RADIO_STATS,
        Werkstoff.Types.COMPOUND,
        DUST,
        MaterialIDs.PLUTONUM_241_DIOXIDE.id,
        TextureSet.SET_SHINY);

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
                    case '0' -> CustomGlyphs.SUBSCRIPT0.charAt(0);
                    case '1' -> '₁';
                    case '2' -> '₂';
                    case '3' -> '₃';
                    case '4' -> '₄';
                    case '5' -> '₅';
                    case '6' -> '₆';
                    case '7' -> '₇';
                    case '8' -> '₈';
                    case '9' -> '₉';
                    default -> {
                        sub = false;
                        yield chars[i];
                    }
                };
            } else if (sup) {
                nu[i] = switch (chars[i]) {
                    case '0' -> CustomGlyphs.SUPERSCRIPT0.charAt(0);
                    case '1' -> CustomGlyphs.SUPERSCRIPT1.charAt(0);
                    case '2' -> CustomGlyphs.SUPERSCRIPT2.charAt(0);
                    case '3' -> CustomGlyphs.SUPERSCRIPT3.charAt(0);
                    case '4' -> CustomGlyphs.SUPERSCRIPT4.charAt(0);
                    case '5' -> CustomGlyphs.SUPERSCRIPT5.charAt(0);
                    case '6' -> CustomGlyphs.SUPERSCRIPT6.charAt(0);
                    case '7' -> CustomGlyphs.SUPERSCRIPT7.charAt(0);
                    case '8' -> CustomGlyphs.SUPERSCRIPT8.charAt(0);
                    case '9' -> CustomGlyphs.SUPERSCRIPT9.charAt(0);
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
