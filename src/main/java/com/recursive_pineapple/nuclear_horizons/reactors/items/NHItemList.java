package com.recursive_pineapple.nuclear_horizons.reactors.items;

import com.recursive_pineapple.nuclear_horizons.reactors.items.basic.*;
import com.recursive_pineapple.nuclear_horizons.recipes.GTMats;

public class NHItemList {

    public static final BasicFuelRodItem THORIUM_1X_ROD = new BasicFuelRodItem(
        "fuelRodThorium",
        "reactorThoriumSingle",
        0.4,
        1.0,
        1,
        false,
        20_000);

    public static final BasicFuelRodProductItem DEPLETED_THORIUM_1X_ROD = new BasicFuelRodProductItem(
        "depletedFuelRodThorium",
        "reactorThoriumSingleDepleted");

    public static final BasicFuelRodItem THORIUM_2X_ROD = new BasicFuelRodItem(
        "fuelRodThoriumDual",
        "reactorThoriumDual",
        0.4,
        1.0,
        2,
        false,
        20_000);

    public static final BasicFuelRodProductItem DEPLETED_THORIUM_2X_ROD = new BasicFuelRodProductItem(
        "depletedFuelRodThoriumDual",
        "reactorThoriumDualDepleted");

    public static final BasicFuelRodItem THORIUM_4X_ROD = new BasicFuelRodItem(
        "fuelRodThoriumQuad",
        "reactorThoriumQuad",
        0.4,
        1.0,
        4,
        false,
        20_000);

    public static final BasicFuelRodProductItem DEPLETED_THORIUM_4X_ROD = new BasicFuelRodProductItem(
        "depletedFuelRodThoriumQuad",
        "reactorThoriumQuadDepleted");

    public static final BasicFuelRodItem URANIUM_1X_ROD = new BasicFuelRodItem(
        "fuelRodUranium",
        "reactorUraniumSingle",
        2.0,
        4.0,
        1,
        false,
        20_000);

    public static final BasicFuelRodProductItem DEPLETED_URANIUM_1X_ROD = new BasicFuelRodProductItem(
        "depletedFuelRodUranium",
        "reactorUraniumSingleDepleted");

    public static final BasicFuelRodItem URANIUM_2X_ROD = new BasicFuelRodItem(
        "dualFuelRodUranium",
        "reactorUraniumDual",
        2.0,
        4.0,
        2,
        false,
        20_000);

    public static final BasicFuelRodProductItem DEPLETED_URANIUM_2X_ROD = new BasicFuelRodProductItem(
        "depletedFuelRodUraniumDual",
        "reactorUraniumDualDepleted");

    public static final BasicFuelRodItem URANIUM_4X_ROD = new BasicFuelRodItem(
        "quadFuelRodUranium",
        "reactorUraniumQuad",
        2.0,
        4.0,
        4,
        false,
        20_000);

    public static final BasicFuelRodProductItem DEPLETED_URANIUM_4X_ROD = new BasicFuelRodProductItem(
        "depletedFuelRodUraniumQuad",
        "reactorUraniumQuadDepleted");

    public static final BasicFuelRodItem MOX_1X_ROD = new BasicFuelRodItem(
        "fuelRodMOX",
        "reactorMOXSingle",
        2.0,
        4.0,
        1,
        true,
        10_000);

    public static final BasicFuelRodProductItem DEPLETED_MOX_ROD = new BasicFuelRodProductItem(
        "depletedFuelRodMOX",
        "reactorMOXSingleDepleted");
    public static final BasicFuelRodItem MOX_2X_ROD = new BasicFuelRodItem(
        "dualFuelRodMOX",
        "reactorMOXDual",
        2.0,
        4.0,
        2,
        true,
        10_000);

    public static final BasicFuelRodProductItem DEPLETED_MOX_2X_ROD = new BasicFuelRodProductItem(
        "depletedFuelRodMOXDual",
        "reactorMOXDualDepleted");
    public static final BasicFuelRodItem MOX_4X_ROD = new BasicFuelRodItem(
        "quadFuelRodMOX",
        "reactorMOXQuad",
        2.0,
        4.0,
        4,
        true,
        10_000);

    public static final BasicFuelRodProductItem DEPLETED_MOX_4X_ROD = new BasicFuelRodProductItem(
        "depletedFuelRodMOXQuad",
        "reactorMOXQuadDepleted");

    public static final BasicHeatVentItem BASIC_HEAT_VENT = new BasicHeatVentItem(
        "heatVent",
        "reactorVent",
        0,
        0,
        6,
        1000);
    public static final BasicHeatVentItem ADVANCED_HEAT_VENT = new BasicHeatVentItem(
        "advancedHeatVent",
        "reactorVentDiamond",
        0,
        0,
        12,
        1000);
    public static final BasicHeatVentItem REACTOR_HEAT_VENT = new BasicHeatVentItem(
        "reactorHeatVent",
        "reactorVentCore",
        5,
        0,
        5,
        1000);
    public static final BasicHeatVentItem COMPONENT_HEAT_VENT = new BasicHeatVentItem(
        "componentHeatVent",
        "reactorVentSpread",
        0,
        4,
        0,
        0);
    public static final BasicHeatVentItem OVERCLOCKED_HEAT_VENT = new BasicHeatVentItem(
        "overclockedHeatVent",
        "reactorVentGold",
        36,
        0,
        20,
        1000);

    public static final BasicHeatVentCoolantItem COOLANT_HEAT_EXCHANGER_1 = new BasicHeatVentCoolantItem(
        "reactorVentCoolant1",
        "reactorVentCoolant1",
        40,
        0,
        30,
        750);
    public static final BasicHeatVentCoolantItem COOLANT_HEAT_EXCHANGER_2 = new BasicHeatVentCoolantItem(
        "reactorVentCoolant2",
        "reactorVentCoolant2",
        120,
        0,
        90,
        2250);
    public static final BasicHeatVentCoolantItem COOLANT_HEAT_EXCHANGER_3 = new BasicHeatVentCoolantItem(
        "reactorVentCoolant3",
        "reactorVentCoolant3",
        360,
        0,
        270,
        6750);

    public static final BasicHeatExchangerItem BASIC_HEAT_EXCHANGER = new BasicHeatExchangerItem(
        "heatExchanger",
        "reactorHeatSwitch",
        4,
        12,
        2500);
    public static final BasicHeatExchangerItem ADVANCED_HEAT_EXCHANGER = new BasicHeatExchangerItem(
        "advancedHeatExchanger",
        "reactorHeatSwitchDiamond",
        8,
        24,
        10_000);
    public static final BasicHeatExchangerItem REACTOR_HEAT_EXCHANGER = new BasicHeatExchangerItem(
        "coreHeatExchanger",
        "reactorHeatSwitchCore",
        72,
        0,
        5000);
    public static final BasicHeatExchangerItem COMPONENT_HEAT_EXCHANGER = new BasicHeatExchangerItem(
        "componentHeatExchanger",
        "reactorHeatSwitchSpread",
        0,
        36,
        5000);

    public static final BasicHeatAbsorberItem COOLANT_CELL_10k = new BasicHeatAbsorberItem(
        "coolantCell10k",
        "reactorCoolantSimple",
        10_000,
        true);
    public static final BasicHeatAbsorberItem COOLANT_CELL_30k = new BasicHeatAbsorberItem(
        "coolantCell30k",
        "reactorCoolantTriple",
        30_000,
        true);
    public static final BasicHeatAbsorberItem COOLANT_CELL_60k = new BasicHeatAbsorberItem(
        "coolantCell60k",
        "reactorCoolantSix",
        60_000,
        true);
    public static final BasicHeatAbsorberItem RSH_CONDENSATOR = new BasicHeatAbsorberItem(
        "rshCondensator",
        "reactorCondensator",
        20_000,
        false);
    public static final BasicHeatAbsorberItem LZH_CONDENSATOR = new BasicHeatAbsorberItem(
        "lzhCondensator",
        "reactorCondensatorLap",
        100_000,
        false);

    public static final BasicNeutronReflectorItem NEUTRON_REFLECTOR = new BasicNeutronReflectorItem(
        "neutronReflector",
        "reactorReflector",
        30_000);
    public static final BasicNeutronReflectorItem THICK_NEUTRON_REFLECTOR = new BasicNeutronReflectorItem(
        "thickNeutronReflector",
        "reactorReflectorThick",
        120_000);

    public static final BasicReactorPlatingItem REACTOR_PLATING = new BasicReactorPlatingItem(
        "reactorPlating",
        "reactorPlating",
        0.95,
        1000);
    public static final BasicReactorPlatingItem REACTOR_PLATING_HEAT = new BasicReactorPlatingItem(
        "reactorPlatingHeat",
        "reactorPlatingHeat",
        0.99,
        1700);
    public static final BasicReactorPlatingItem REACTOR_PLATING_HEAT_2 = new BasicReactorPlatingItem(
        "reactorPlatingHeat2",
        "reactorPlatingHeat2",
        0.95,
        6800);
    public static final BasicReactorPlatingItem REACTOR_PLATING_EXPLOSIVE = new BasicReactorPlatingItem(
        "reactorPlatingExplosive",
        "reactorPlatingExplosive",
        0.9,
        500);

    public static final DebugHeatAbsorber DEBUG_HEAT_ABSORBER = new DebugHeatAbsorber(
        "debugHeatAbsorber",
        "debugHeatAbsorber");
    public static final BasicReactorPlatingItem REACTOR_PLATING_HEAT_DEBUG = new BasicReactorPlatingItem(
        "reactorPlatingHeatDebug",
        "reactorPlatingHeatDebug",
        0.0,
        2_000_000_000);

    public static final MetaCellItem CELLS = new MetaCellItem();

    public static void registerItems() {

        BASIC_HEAT_VENT.register();
        ADVANCED_HEAT_VENT.register();
        REACTOR_HEAT_VENT.register();
        COMPONENT_HEAT_VENT.register();
        OVERCLOCKED_HEAT_VENT.register();

        COOLANT_HEAT_EXCHANGER_1.register();
        COOLANT_HEAT_EXCHANGER_2.register();
        COOLANT_HEAT_EXCHANGER_3.register();

        BASIC_HEAT_EXCHANGER.register();
        ADVANCED_HEAT_EXCHANGER.register();
        REACTOR_HEAT_EXCHANGER.register();
        COMPONENT_HEAT_EXCHANGER.register();

        COOLANT_CELL_10k.register();
        COOLANT_CELL_30k.register();
        COOLANT_CELL_60k.register();
        RSH_CONDENSATOR.register();
        LZH_CONDENSATOR.register();

        NEUTRON_REFLECTOR.register();
        THICK_NEUTRON_REFLECTOR.register();

        REACTOR_PLATING.register();
        REACTOR_PLATING_HEAT.register();
        REACTOR_PLATING_HEAT_2.register();
        REACTOR_PLATING_EXPLOSIVE.register();

        DEBUG_HEAT_ABSORBER.register();
        REACTOR_PLATING_HEAT_DEBUG.register();

        CELLS.register();
    }

    public static void setupMaterials() {
        THORIUM_1X_ROD.setSpargeGas(GTMats.NEON.getFluid(1).getFluid(), 2, 8);
        THORIUM_2X_ROD.setSpargeGas(GTMats.NEON.getFluid(1).getFluid(), 4, 16);
        THORIUM_4X_ROD.setSpargeGas(GTMats.NEON.getFluid(1).getFluid(), 8, 32);

        URANIUM_1X_ROD.setSpargeGas(GTMats.RADON.getFluid(1).getFluid(), 2, 8);
        URANIUM_2X_ROD.setSpargeGas(GTMats.RADON.getFluid(1).getFluid(), 4, 16);
        URANIUM_4X_ROD.setSpargeGas(GTMats.RADON.getFluid(1).getFluid(), 8, 32);

        MOX_1X_ROD.setSpargeGas(GTMats.KRYPTON.getFluid(1).getFluid(), 2, 8);
        MOX_2X_ROD.setSpargeGas(GTMats.KRYPTON.getFluid(1).getFluid(), 4, 16);
        MOX_4X_ROD.setSpargeGas(GTMats.KRYPTON.getFluid(1).getFluid(), 8, 32);
    }
}
