package com.recursive_pineapple.nuclear_horizons.reactors.tile.simulator;

import java.util.HashMap;

import javax.annotation.Nullable;

import net.minecraft.item.Item;

import com.recursive_pineapple.nuclear_horizons.reactors.items.ItemList;


public class SimulationItems {

    private static final HashMap<Integer, Item> reactorItemsById = new HashMap<>();
    private static final HashMap<Item, Integer> reactorItemIdsByItem = new HashMap<>();

    public static void registerSimulationItem(int id, Item item) {
        reactorItemsById.put(id, item);
        reactorItemIdsByItem.put(item, id);
    }

    public static void registerSimulationItem(int id, gregtech.api.enums.ItemList item) {
        reactorItemsById.put(id, item.getItem());
        reactorItemIdsByItem.put(item.getItem(), id);
    }

    public static @Nullable Item getSimulationItem(int id) {
        return reactorItemsById.get(id);
    }

    public static @Nullable Integer getSimulationItemId(Item item) {
        return reactorItemIdsByItem.get(item);
    }

    public static void registerSimulationItems() {
        registerSimulationItem(1, gregtech.api.enums.ItemList.RodUranium);
        registerSimulationItem(2, gregtech.api.enums.ItemList.RodUranium2);
        registerSimulationItem(3, gregtech.api.enums.ItemList.RodUranium4);
        registerSimulationItem(7, ItemList.NEUTRON_REFLECTOR);
        registerSimulationItem(8, ItemList.THICK_NEUTRON_REFLECTOR);
        registerSimulationItem(9, ItemList.BASIC_HEAT_VENT);
        registerSimulationItem(10, ItemList.ADVANCED_HEAT_VENT);
        registerSimulationItem(11, ItemList.REACTOR_HEAT_VENT);
        registerSimulationItem(12, ItemList.COMPONENT_HEAT_VENT);
        registerSimulationItem(13, ItemList.OVERCLOCKED_HEAT_VENT);
        registerSimulationItem(14, ItemList.COOLANT_CELL_10k);
        registerSimulationItem(15, ItemList.COOLANT_CELL_30k);
        registerSimulationItem(16, ItemList.COOLANT_CELL_60k);
        registerSimulationItem(17, ItemList.BASIC_HEAT_EXCHANGER);
        registerSimulationItem(18, ItemList.ADVANCED_HEAT_EXCHANGER);
        registerSimulationItem(19, ItemList.REACTOR_HEAT_EXCHANGER);
        registerSimulationItem(20, ItemList.COMPONENT_HEAT_EXCHANGER);
        registerSimulationItem(21, ItemList.REACTOR_PLATING);
        registerSimulationItem(22, ItemList.REACTOR_PLATING_HEAT);
        registerSimulationItem(23, ItemList.REACTOR_PLATING_EXPLOSIVE);
        registerSimulationItem(24, ItemList.RSH_CONDENSATOR);
        registerSimulationItem(25, ItemList.LZH_CONDENSATOR);
        registerSimulationItem(26, gregtech.api.enums.ItemList.RodThorium);
        registerSimulationItem(27, gregtech.api.enums.ItemList.RodThorium2);
        registerSimulationItem(28, gregtech.api.enums.ItemList.RodThorium4);
        registerSimulationItem(29, gregtech.api.enums.ItemList.Reactor_Coolant_He_1);
        registerSimulationItem(30, gregtech.api.enums.ItemList.Reactor_Coolant_He_3);
        registerSimulationItem(31, gregtech.api.enums.ItemList.Reactor_Coolant_He_6);
        registerSimulationItem(32, gregtech.api.enums.ItemList.Reactor_Coolant_NaK_1);
        registerSimulationItem(33, gregtech.api.enums.ItemList.Reactor_Coolant_NaK_3);
        registerSimulationItem(34, gregtech.api.enums.ItemList.Reactor_Coolant_NaK_6);
        registerSimulationItem(35, gregtech.api.enums.ItemList.Neutron_Reflector);
        registerSimulationItem(45, gregtech.api.enums.ItemList.RodNaquadah);
        registerSimulationItem(46, gregtech.api.enums.ItemList.RodNaquadah2);
        registerSimulationItem(47, gregtech.api.enums.ItemList.RodNaquadah4);
        registerSimulationItem(48, gregtech.api.enums.ItemList.RodNaquadria);
        registerSimulationItem(49, gregtech.api.enums.ItemList.RodNaquadria2);
        registerSimulationItem(50, gregtech.api.enums.ItemList.RodNaquadria4);
        registerSimulationItem(51, gregtech.api.enums.ItemList.RodTiberium);
        registerSimulationItem(52, gregtech.api.enums.ItemList.RodTiberium2);
        registerSimulationItem(53, gregtech.api.enums.ItemList.RodTiberium4);
        registerSimulationItem(54, gregtech.api.enums.ItemList.RodNaquadah32); // the core
        registerSimulationItem(55, gregtech.api.enums.ItemList.Reactor_Coolant_Sp_1);
        registerSimulationItem(56, gregtech.api.enums.ItemList.Reactor_Coolant_Sp_2);
        registerSimulationItem(57, gregtech.api.enums.ItemList.Reactor_Coolant_Sp_3);
        registerSimulationItem(58, gregtech.api.enums.ItemList.Reactor_Coolant_Sp_6);
        registerSimulationItem(59, gregtech.api.enums.ItemList.neutroniumHeatCapacitor);
        registerSimulationItem(60, gregtech.api.enums.ItemList.RodHighDensityUranium);
        registerSimulationItem(61, gregtech.api.enums.ItemList.RodHighDensityUranium2);
        registerSimulationItem(62, gregtech.api.enums.ItemList.RodHighDensityUranium4);
        registerSimulationItem(63, gregtech.api.enums.ItemList.RodHighDensityPlutonium);
        registerSimulationItem(64, gregtech.api.enums.ItemList.RodHighDensityPlutonium2);
        registerSimulationItem(65, gregtech.api.enums.ItemList.RodHighDensityPlutonium4);
        registerSimulationItem(66, gregtech.api.enums.ItemList.RodExcitedUranium);
        registerSimulationItem(67, gregtech.api.enums.ItemList.RodExcitedUranium2);
        registerSimulationItem(68, gregtech.api.enums.ItemList.RodExcitedUranium4);
        registerSimulationItem(69, gregtech.api.enums.ItemList.RodExcitedPlutonium);
        registerSimulationItem(70, gregtech.api.enums.ItemList.RodExcitedPlutonium2);
        registerSimulationItem(71, gregtech.api.enums.ItemList.RodExcitedPlutonium4);
        registerSimulationItem(72, gregtech.api.enums.ItemList.RodGlowstone);
    }
}
