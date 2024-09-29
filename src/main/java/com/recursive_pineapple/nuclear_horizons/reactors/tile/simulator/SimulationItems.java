package com.recursive_pineapple.nuclear_horizons.reactors.tile.simulator;

import java.util.HashMap;

import javax.annotation.Nullable;

import net.minecraft.item.Item;

import com.recursive_pineapple.nuclear_horizons.reactors.items.ItemList;

import bartworks.system.material.BWNonMetaMaterialItems;
import goodgenerator.loader.FuelRodLoader;

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
        registerSimulationItem(21, ItemList.REACTOR_PLATING);
        registerSimulationItem(22, ItemList.REACTOR_PLATING_HEAT);
        registerSimulationItem(23, ItemList.REACTOR_PLATING_EXPLOSIVE);
        registerSimulationItem(24, ItemList.RSH_CONDENSATOR);
        registerSimulationItem(25, ItemList.LZH_CONDENSATOR);
        registerSimulationItem(26, gregtech.api.enums.ItemList.ThoriumCell_1);
        registerSimulationItem(27, gregtech.api.enums.ItemList.ThoriumCell_2);
        registerSimulationItem(28, gregtech.api.enums.ItemList.ThoriumCell_4);
        registerSimulationItem(29, gregtech.api.enums.ItemList.Reactor_Coolant_He_1);
        registerSimulationItem(30, gregtech.api.enums.ItemList.Reactor_Coolant_He_3);
        registerSimulationItem(31, gregtech.api.enums.ItemList.Reactor_Coolant_He_6);
        registerSimulationItem(32, gregtech.api.enums.ItemList.Reactor_Coolant_NaK_1);
        registerSimulationItem(33, gregtech.api.enums.ItemList.Reactor_Coolant_NaK_3);
        registerSimulationItem(34, gregtech.api.enums.ItemList.Reactor_Coolant_NaK_6);
        registerSimulationItem(35, gregtech.api.enums.ItemList.Neutron_Reflector);
        registerSimulationItem(45, gregtech.api.enums.ItemList.NaquadahCell_1);
        registerSimulationItem(46, gregtech.api.enums.ItemList.NaquadahCell_2);
        registerSimulationItem(47, gregtech.api.enums.ItemList.NaquadahCell_4);
        registerSimulationItem(48, gregtech.api.enums.ItemList.MNqCell_1);
        registerSimulationItem(49, gregtech.api.enums.ItemList.MNqCell_2);
        registerSimulationItem(50, gregtech.api.enums.ItemList.MNqCell_4);
        registerSimulationItem(51, BWNonMetaMaterialItems.TiberiumCell_1.getItem());
        registerSimulationItem(52, BWNonMetaMaterialItems.TiberiumCell_2.getItem());
        registerSimulationItem(53, BWNonMetaMaterialItems.TiberiumCell_4.getItem());
        registerSimulationItem(54, BWNonMetaMaterialItems.TheCoreCell.getItem());
        registerSimulationItem(55, gregtech.api.enums.ItemList.Reactor_Coolant_Sp_1);
        registerSimulationItem(56, gregtech.api.enums.ItemList.Reactor_Coolant_Sp_2);
        registerSimulationItem(57, gregtech.api.enums.ItemList.Reactor_Coolant_Sp_3);
        registerSimulationItem(58, gregtech.api.enums.ItemList.Reactor_Coolant_Sp_6);
        registerSimulationItem(59, gregtech.api.enums.ItemList.neutroniumHeatCapacitor);
        registerSimulationItem(60, FuelRodLoader.rodCompressedUranium);
        registerSimulationItem(61, FuelRodLoader.rodCompressedUranium_2);
        registerSimulationItem(62, FuelRodLoader.rodCompressedUranium_4);
        registerSimulationItem(63, FuelRodLoader.rodCompressedPlutonium);
        registerSimulationItem(64, FuelRodLoader.rodCompressedPlutonium_2);
        registerSimulationItem(65, FuelRodLoader.rodCompressedPlutonium_4);
        registerSimulationItem(66, FuelRodLoader.rodLiquidUranium);
        registerSimulationItem(67, FuelRodLoader.rodLiquidUranium_2);
        registerSimulationItem(68, FuelRodLoader.rodLiquidUranium_4);
        registerSimulationItem(69, FuelRodLoader.rodLiquidPlutonium);
        registerSimulationItem(70, FuelRodLoader.rodLiquidPlutonium_2);
        registerSimulationItem(71, FuelRodLoader.rodLiquidPlutonium_4);
        registerSimulationItem(72, gregtech.api.enums.ItemList.GlowstoneCell);
    }
}
