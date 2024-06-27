package com.recursive_pineapple.nuclear_horizons.reactors.tile.simulator;

import java.util.HashMap;

import javax.annotation.Nullable;

import com.recursive_pineapple.nuclear_horizons.reactors.items.ItemList;

import net.minecraft.item.Item;

public class SimulationItems {

    private static final HashMap<Integer, Item> reactorItemsById = new HashMap<>();
    private static final HashMap<Item, Integer> reactorItemsByItem = new HashMap<>();

    public static void registerSimulationItem(int id, Item item) {
        reactorItemsById.put(id, item);
        reactorItemsByItem.put(item, id);
    }

    public static @Nullable Item getSimulationItem(int id) {
        return reactorItemsById.get(id);
    }

    public static @Nullable Integer getSimulationItemId(Item item) {
        return reactorItemsByItem.get(item);
    }

    public static void init() {
        registerSimulationItem(1, ItemList.URANIUM_1X_ROD);
        registerSimulationItem(2, ItemList.URANIUM_2X_ROD);
        registerSimulationItem(3, ItemList.URANIUM_4X_ROD);
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
        // registerSimulationItem(21, ItemList.);
        // registerSimulationItem(22, ItemList.);
        // registerSimulationItem(23, ItemList.);
        registerSimulationItem(24, ItemList.RSH_CONDENSATOR);
        registerSimulationItem(25, ItemList.LZH_CONDENSATOR);
        // registerSimulationItem(26, ItemList.);
        // registerSimulationItem(27, ItemList.);
        // registerSimulationItem(28, ItemList.);
        registerSimulationItem(35, ItemList.IRIDIUM_NEUTRON_REFLECTOR);
    }
}
