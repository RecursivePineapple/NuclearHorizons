package com.recursive_pineapple.nuclear_horizons.reactors.items;

import com.recursive_pineapple.nuclear_horizons.reactors.components.ComponentRegistry;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemList {
    
    public static final BasicFuelRodItem URANIUM_1X_ROD = new BasicFuelRodItem(
        "fuelRodUranium", "reactorUraniumSimple",
        100.0, 2.0, 1, false, 20_000
    );
    public static final BasicFuelRodItem URANIUM_2X_ROD = new BasicFuelRodItem(
        "dualFuelRodUranium", "reactorUraniumDual",
        200.0, 4.0, 2, false, 20_000
    );
    public static final BasicFuelRodItem URANIUM_4X_ROD = new BasicFuelRodItem(
        "quadFuelRodUranium", "reactorUraniumQuad",
        400.0, 8.0, 4, false, 20_000
    );

    public static final BasicFuelRodItem MOX_1X_ROD = new BasicFuelRodItem(
        "fuelRodMOX", "reactorMOXSimple",
        100.0, 2.0, 1, true, 10_000
    );
    public static final BasicFuelRodItem MOX_2X_ROD = new BasicFuelRodItem(
        "dualFuelRodMOX", "reactorMOXDual",
        200.0, 4.0, 2, true, 10_000
    );
    public static final BasicFuelRodItem MOX_4X_ROD = new BasicFuelRodItem(
        "quadFuelRodMOX", "reactorMOXQuad",
        400.0, 8.0, 4, true, 10_000
    );

    public static final BasicHeatVentItem BASIC_HEAT_VENT = new BasicHeatVentItem(
        "heatVent", "reactorVent",
        0, 0, 6, 1000
    );
    public static final BasicHeatVentItem ADVANCED_HEAT_VENT = new BasicHeatVentItem(
        "advancedHeatVent", "reactorVentDiamond",
        0, 0, 12, 1000
    );
    public static final BasicHeatVentItem REACTOR_HEAT_VENT = new BasicHeatVentItem(
        "reactorHeatVent", "reactorVentCore",
        5, 0, 5, 1000
    );
    public static final BasicHeatVentItem COMPONENT_HEAT_VENT = new BasicHeatVentItem(
        "componentHeatVent", "reactorVentSpread",
        0, 4, 0, 0
    );
    public static final BasicHeatVentItem OVERCLOCKED_HEAT_VENT = new BasicHeatVentItem(
        "overclockedHeatVent", "reactorVentGold",
        36, 0, 20, 1000
    );

    public static final BasicHeatExchangerItem BASIC_HEAT_EXCHANGER = new BasicHeatExchangerItem(
        "heatExchanger", "reactorHeatSwitch",
        4, 12, 2500
    );
    public static final BasicHeatExchangerItem ADVANCED_HEAT_EXCHANGER = new BasicHeatExchangerItem(
        "advancedHeatExchanger", "reactorHeatSwitchDiamond",
        8, 24, 10_000
    );
    public static final BasicHeatExchangerItem REACTOR_HEAT_EXCHANGER = new BasicHeatExchangerItem(
        "coreHeatExchanger", "reactorHeatSwitchCore",
        72, 0, 5000
    );
    public static final BasicHeatExchangerItem COMPONENT_HEAT_EXCHANGER = new BasicHeatExchangerItem(
        "componentHeatExchanger", "reactorHeatSwitchSpread",
        0, 36, 5000
    );

    public static final BasicHeatAbsorberItem COOLANT_CELL_10k = new BasicHeatAbsorberItem(
        "coolantCell10k", "reactorCoolantSimple", 10_000, true
    );
    public static final BasicHeatAbsorberItem COOLANT_CELL_30k = new BasicHeatAbsorberItem(
        "coolantCell30k", "reactorCoolantTriple", 30_000, true
    );
    public static final BasicHeatAbsorberItem COOLANT_CELL_60k = new BasicHeatAbsorberItem(
        "coolantCell60k", "reactorCoolantSix", 60_000, true
    );
    public static final BasicHeatAbsorberItem RSH_CONDENSATOR = new BasicHeatAbsorberItem(
        "rshCondensator", "reactorCondensator", 20_000, false
    );
    public static final BasicHeatAbsorberItem LZH_CONDENSATOR = new BasicHeatAbsorberItem(
        "lzhCondensator", "reactorCondensatorLap", 100_000, false
    );

    public static final BasicNeutronReflectorItem NEUTRON_REFLECTOR = new BasicNeutronReflectorItem(
        "neutronReflector", "reactorReflector", 30_000
    );
    public static final BasicNeutronReflectorItem THICK_NEUTRON_REFLECTOR = new BasicNeutronReflectorItem(
        "thickNeutronReflector", "reactorReflectorThick", 120_000
    );
    public static final BasicNeutronReflectorItem IRIDIUM_NEUTRON_REFLECTOR = new BasicNeutronReflectorItem("iridiumNeutronReflector", "gt.neutronreflector");

    public static void registerItems() {
        register(URANIUM_1X_ROD);
        register(URANIUM_2X_ROD);
        register(URANIUM_4X_ROD);

        register(MOX_1X_ROD);
        register(MOX_2X_ROD);
        register(MOX_4X_ROD);

        register(BASIC_HEAT_VENT);
        register(ADVANCED_HEAT_VENT);
        register(REACTOR_HEAT_VENT);
        register(COMPONENT_HEAT_VENT);
        register(OVERCLOCKED_HEAT_VENT);

        register(BASIC_HEAT_EXCHANGER);
        register(ADVANCED_HEAT_EXCHANGER);
        register(REACTOR_HEAT_EXCHANGER);
        register(COMPONENT_HEAT_EXCHANGER);

        register(COOLANT_CELL_10k);
        register(COOLANT_CELL_30k);
        register(COOLANT_CELL_60k);
        register(RSH_CONDENSATOR);
        register(LZH_CONDENSATOR);

        register(NEUTRON_REFLECTOR);
        register(THICK_NEUTRON_REFLECTOR);
        register(IRIDIUM_NEUTRON_REFLECTOR);
    }

    private static void register(BasicFuelRodItem fuelRod) {
        GameRegistry.registerItem(fuelRod, fuelRod.getUnlocalizedName().substring("item.".length()));
        ComponentRegistry.registerAdapter(fuelRod, fuelRod);
    }

    private static void register(BasicHeatVentItem heatVent) {
        GameRegistry.registerItem(heatVent, heatVent.getUnlocalizedName().substring("item.".length()));
        ComponentRegistry.registerAdapter(heatVent, heatVent);
    }

    private static void register(BasicHeatExchangerItem heatExchanger) {
        GameRegistry.registerItem(heatExchanger, heatExchanger.getUnlocalizedName().substring("item.".length()));
        ComponentRegistry.registerAdapter(heatExchanger, heatExchanger);
    }

    private static void register(BasicHeatAbsorberItem heatAbsorber) {
        GameRegistry.registerItem(heatAbsorber, heatAbsorber.getUnlocalizedName().substring("item.".length()));
        ComponentRegistry.registerAdapter(heatAbsorber, heatAbsorber);
    }

    private static void register(BasicNeutronReflectorItem heatAbsorber) {
        GameRegistry.registerItem(heatAbsorber, heatAbsorber.getUnlocalizedName().substring("item.".length()));
        ComponentRegistry.registerAdapter(heatAbsorber, heatAbsorber);
    }
}
