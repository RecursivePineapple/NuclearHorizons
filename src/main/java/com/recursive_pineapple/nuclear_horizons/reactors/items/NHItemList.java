package com.recursive_pineapple.nuclear_horizons.reactors.items;

import net.minecraft.item.ItemStack;

import com.recursive_pineapple.nuclear_horizons.reactors.items.basic.BasicBreederRodItem;
import com.recursive_pineapple.nuclear_horizons.reactors.items.basic.BasicFuelRodProductItem;
import com.recursive_pineapple.nuclear_horizons.reactors.items.basic.BasicHeatAbsorberItem;
import com.recursive_pineapple.nuclear_horizons.reactors.items.basic.BasicHeatExchangerItem;
import com.recursive_pineapple.nuclear_horizons.reactors.items.basic.BasicHeatVentCoolantItem;
import com.recursive_pineapple.nuclear_horizons.reactors.items.basic.BasicHeatVentItem;
import com.recursive_pineapple.nuclear_horizons.reactors.items.basic.BasicNeutronReflectorItem;
import com.recursive_pineapple.nuclear_horizons.reactors.items.basic.BasicReactorPlatingItem;
import com.recursive_pineapple.nuclear_horizons.reactors.items.basic.DebugHeatAbsorber;

public enum NHItemList {

    BASIC_HEAT_VENT(new BasicHeatVentItem(
        "heatVent",
        "reactorVent",
        0,
        0,
        6,
        1000)),
    ADVANCED_HEAT_VENT(new BasicHeatVentItem(
        "advancedHeatVent",
        "reactorVentDiamond",
        0,
        0,
        12,
        1000)),
    REACTOR_HEAT_VENT(new BasicHeatVentItem(
        "reactorHeatVent",
        "reactorVentCore",
        5,
        0,
        5,
        1000)),
    COMPONENT_HEAT_VENT(new BasicHeatVentItem(
        "componentHeatVent",
        "reactorVentSpread",
        0,
        4,
        0,
        0)),
    OVERCLOCKED_HEAT_VENT(new BasicHeatVentItem(
        "overclockedHeatVent",
        "reactorVentGold",
        36,
        0,
        20,
        1000)),

    COOLANT_HEAT_EXCHANGER_1(new BasicHeatVentCoolantItem(
        "reactorVentCoolant1",
        "reactorVentCoolant1",
        40,
        0,
        30,
        750)),
    COOLANT_HEAT_EXCHANGER_2(new BasicHeatVentCoolantItem(
        "reactorVentCoolant2",
        "reactorVentCoolant2",
        120,
        0,
        90,
        2250)),
    COOLANT_HEAT_EXCHANGER_3(new BasicHeatVentCoolantItem(
        "reactorVentCoolant3",
        "reactorVentCoolant3",
        360,
        0,
        270,
        6750)),

    BASIC_HEAT_EXCHANGER(new BasicHeatExchangerItem(
        "heatExchanger",
        "reactorHeatSwitch",
        4,
        12,
        2500)),
    ADVANCED_HEAT_EXCHANGER(new BasicHeatExchangerItem(
        "advancedHeatExchanger",
        "reactorHeatSwitchDiamond",
        8,
        24,
        10_000)),
    REACTOR_HEAT_EXCHANGER(new BasicHeatExchangerItem(
        "coreHeatExchanger",
        "reactorHeatSwitchCore",
        72,
        0,
        5000)),
    COMPONENT_HEAT_EXCHANGER(new BasicHeatExchangerItem(
        "componentHeatExchanger",
        "reactorHeatSwitchSpread",
        0,
        36,
        5000)),

    COOLANT_CELL_10k(new BasicHeatAbsorberItem(
        "coolantCell10k",
        "reactorCoolantSimple",
        10_000,
        true)),
    COOLANT_CELL_30k(new BasicHeatAbsorberItem(
        "coolantCell30k",
        "reactorCoolantTriple",
        30_000,
        true)),
    COOLANT_CELL_60k(new BasicHeatAbsorberItem(
        "coolantCell60k",
        "reactorCoolantSix",
        60_000,
        true)),
    RSH_CONDENSATOR(new BasicHeatAbsorberItem(
        "rshCondensator",
        "reactorCondensator",
        20_000,
        false)),
    LZH_CONDENSATOR(new BasicHeatAbsorberItem(
        "lzhCondensator",
        "reactorCondensatorLap",
        100_000,
        false)),

    NEUTRON_REFLECTOR(new BasicNeutronReflectorItem(
        "neutronReflector",
        "reactorReflector",
        30_000)),
    THICK_NEUTRON_REFLECTOR(new BasicNeutronReflectorItem(
        "thickNeutronReflector",
        "reactorReflectorThick",
        120_000)),

    REACTOR_PLATING(new BasicReactorPlatingItem(
        "reactorPlating",
        "reactorPlating",
        0.95,
        1000)),
    REACTOR_PLATING_HEAT(new BasicReactorPlatingItem(
        "reactorPlatingHeat",
        "reactorPlatingHeat",
        0.99,
        1700)),
    REACTOR_PLATING_HEAT_2(new BasicReactorPlatingItem(
        "reactorPlatingHeat2",
        "reactorPlatingHeat2",
        0.95,
        6800)),
    REACTOR_PLATING_EXPLOSIVE(new BasicReactorPlatingItem(
        "reactorPlatingExplosive",
        "reactorPlatingExplosive",
        0.9,
        500)),

    DEBUG_HEAT_ABSORBER(new DebugHeatAbsorber(
        "debugHeatAbsorber",
        "debugHeatAbsorber")),
    REACTOR_PLATING_HEAT_DEBUG(new BasicReactorPlatingItem(
        "reactorPlatingHeatDebug",
        "reactorPlatingHeatDebug",
        0.0,
        2_000_000_000)),

    THORIUM_BREEDER_ROD_FINISHED(new BasicFuelRodProductItem(
        "thoriumBreederRodFinished",
        "reactorThoriumSingleDepleted")),

    THORIUM_BREEDER_ROD(new BasicBreederRodItem(
        "thoriumBreederRod",
        "reactorThoriumSingle",
        1_000,
        1,
        5_000)
        .setProduct(() -> THORIUM_BREEDER_ROD_FINISHED.get(1))),

    ITEMS(new MetaItem()),

    EMPTY_FUEL_ROD_BASIC,
    //
    ;

    private final NHItem item;
    private ItemStack stack;

    NHItemList() {
        item = null;
    }

    NHItemList(NHItem item) {
        this.item = item;
        this.stack = new ItemStack(item, 1);
    }

    public void set(ItemStack stack) {
        if (this.stack != null) throw new IllegalStateException("Cannot assign " + name() + " twice");
        this.stack = stack;
    }

    public ItemStack get(int amount) {
        if (this.stack == null) throw new IllegalStateException("Item " + name() + " was not set");
        ItemStack s = stack.copy();
        s.stackSize = amount;
        return s;
    }

    public <T extends NHItem> T getItem() {
        if (this.stack == null) throw new IllegalStateException("Item " + name() + " was not set");
        //noinspection unchecked
        return (T) stack.getItem();
    }

    public static void registerPreInit() {
        for (NHItemList item : values()) {
            if (item.item != null) {
                item.item.registerPreInit();
            }
        }
    }

    public static void registerInit() {
        for (NHItemList item : values()) {
            if (item.item != null) {
                item.item.registerInit();
            }
        }
    }

    public static void registerPostInit() {
        for (NHItemList item : values()) {
            if (item.item != null) {
                item.item.registerPostInit();
            }
        }
    }
}
