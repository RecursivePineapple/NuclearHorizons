package com.recursive_pineapple.nuclear_horizons.reactors.tile.simulator;

import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.ADVANCED_HEAT_EXCHANGER;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.ADVANCED_HEAT_VENT;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.BASIC_HEAT_EXCHANGER;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.BASIC_HEAT_VENT;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.COMPONENT_HEAT_EXCHANGER;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.COMPONENT_HEAT_VENT;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.COOLANT_CELL_10k;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.COOLANT_CELL_30k;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.COOLANT_CELL_60k;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.LZH_CONDENSATOR;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.NEUTRON_REFLECTOR;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.OVERCLOCKED_HEAT_VENT;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.REACTOR_HEAT_EXCHANGER;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.REACTOR_HEAT_VENT;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.REACTOR_PLATING;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.REACTOR_PLATING_EXPLOSIVE;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.REACTOR_PLATING_HEAT;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.RSH_CONDENSATOR;
import static com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList.THICK_NEUTRON_REFLECTOR;
import static gregtech.api.enums.ItemList.Neutron_Reflector;
import static gregtech.api.enums.ItemList.Reactor_Coolant_He_1;
import static gregtech.api.enums.ItemList.Reactor_Coolant_He_3;
import static gregtech.api.enums.ItemList.Reactor_Coolant_He_6;
import static gregtech.api.enums.ItemList.Reactor_Coolant_NaK_1;
import static gregtech.api.enums.ItemList.Reactor_Coolant_NaK_3;
import static gregtech.api.enums.ItemList.Reactor_Coolant_NaK_6;
import static gregtech.api.enums.ItemList.Reactor_Coolant_Sp_1;
import static gregtech.api.enums.ItemList.Reactor_Coolant_Sp_2;
import static gregtech.api.enums.ItemList.Reactor_Coolant_Sp_3;
import static gregtech.api.enums.ItemList.Reactor_Coolant_Sp_6;
import static gregtech.api.enums.ItemList.RodExcitedPlutonium;
import static gregtech.api.enums.ItemList.RodExcitedPlutonium2;
import static gregtech.api.enums.ItemList.RodExcitedUranium;
import static gregtech.api.enums.ItemList.RodExcitedUranium2;
import static gregtech.api.enums.ItemList.RodExcitedUranium4;
import static gregtech.api.enums.ItemList.RodGlowstone;
import static gregtech.api.enums.ItemList.RodHighDensityPlutonium;
import static gregtech.api.enums.ItemList.RodHighDensityPlutonium2;
import static gregtech.api.enums.ItemList.RodHighDensityPlutonium4;
import static gregtech.api.enums.ItemList.RodHighDensityUranium;
import static gregtech.api.enums.ItemList.RodHighDensityUranium2;
import static gregtech.api.enums.ItemList.RodHighDensityUranium4;
import static gregtech.api.enums.ItemList.RodNaquadah;
import static gregtech.api.enums.ItemList.RodNaquadah2;
import static gregtech.api.enums.ItemList.RodNaquadah32;
import static gregtech.api.enums.ItemList.RodNaquadah4;
import static gregtech.api.enums.ItemList.RodNaquadria;
import static gregtech.api.enums.ItemList.RodNaquadria2;
import static gregtech.api.enums.ItemList.RodNaquadria4;
import static gregtech.api.enums.ItemList.RodThorium;
import static gregtech.api.enums.ItemList.RodThorium2;
import static gregtech.api.enums.ItemList.RodThorium4;
import static gregtech.api.enums.ItemList.RodTiberium;
import static gregtech.api.enums.ItemList.RodTiberium2;
import static gregtech.api.enums.ItemList.RodTiberium4;
import static gregtech.api.enums.ItemList.RodUranium;
import static gregtech.api.enums.ItemList.RodUranium2;
import static gregtech.api.enums.ItemList.RodUranium4;
import static gregtech.api.enums.ItemList.neutroniumHeatCapacitor;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.gtnhlib.util.data.ItemId;
import com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList;

import gregtech.api.enums.ItemList;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenCustomHashMap;

public class SimulationItems {

    private static final Int2ObjectOpenHashMap<ItemStack> reactorItemsById = new Int2ObjectOpenHashMap<>();
    private static final Object2IntOpenCustomHashMap<ItemStack> reactorItemIdsByItem = new Object2IntOpenCustomHashMap<>(
        ItemId.STACK_ITEM_META_STRATEGY);

    public static void registerSimulationItem(int id, NHItemList item) {
        reactorItemsById.put(id, item.get(1));
        reactorItemIdsByItem.put(item.get(1), id);
    }

    public static void registerSimulationItem(int id, ItemList item) {
        reactorItemsById.put(id, item.get(1));
        reactorItemIdsByItem.put(item.get(1), id);
    }

    public static @Nullable ItemStack getSimulationItem(int id) {
        return reactorItemsById.get(id);
    }

    public static @Nullable Integer getSimulationItemId(ItemStack item) {
        return reactorItemIdsByItem.get(item);
    }

    public static void registerSimulationItems() {
        registerSimulationItem(1, RodUranium);
        registerSimulationItem(2, RodUranium2);
        registerSimulationItem(3, RodUranium4);
        registerSimulationItem(7, NEUTRON_REFLECTOR);
        registerSimulationItem(8, THICK_NEUTRON_REFLECTOR);
        registerSimulationItem(9, BASIC_HEAT_VENT);
        registerSimulationItem(10, ADVANCED_HEAT_VENT);
        registerSimulationItem(11, REACTOR_HEAT_VENT);
        registerSimulationItem(12, COMPONENT_HEAT_VENT);
        registerSimulationItem(13, OVERCLOCKED_HEAT_VENT);
        registerSimulationItem(14, COOLANT_CELL_10k);
        registerSimulationItem(15, COOLANT_CELL_30k);
        registerSimulationItem(16, COOLANT_CELL_60k);
        registerSimulationItem(17, BASIC_HEAT_EXCHANGER);
        registerSimulationItem(18, ADVANCED_HEAT_EXCHANGER);
        registerSimulationItem(19, REACTOR_HEAT_EXCHANGER);
        registerSimulationItem(20, COMPONENT_HEAT_EXCHANGER);
        registerSimulationItem(21, REACTOR_PLATING);
        registerSimulationItem(22, REACTOR_PLATING_HEAT);
        registerSimulationItem(23, REACTOR_PLATING_EXPLOSIVE);
        registerSimulationItem(24, RSH_CONDENSATOR);
        registerSimulationItem(25, LZH_CONDENSATOR);
        registerSimulationItem(26, RodThorium);
        registerSimulationItem(27, RodThorium2);
        registerSimulationItem(28, RodThorium4);
        registerSimulationItem(29, Reactor_Coolant_He_1);
        registerSimulationItem(30, Reactor_Coolant_He_3);
        registerSimulationItem(31, Reactor_Coolant_He_6);
        registerSimulationItem(32, Reactor_Coolant_NaK_1);
        registerSimulationItem(33, Reactor_Coolant_NaK_3);
        registerSimulationItem(34, Reactor_Coolant_NaK_6);
        registerSimulationItem(35, Neutron_Reflector);
        registerSimulationItem(45, RodNaquadah);
        registerSimulationItem(46, RodNaquadah2);
        registerSimulationItem(47, RodNaquadah4);
        registerSimulationItem(48, RodNaquadria);
        registerSimulationItem(49, RodNaquadria2);
        registerSimulationItem(50, RodNaquadria4);
        registerSimulationItem(51, RodTiberium);
        registerSimulationItem(52, RodTiberium2);
        registerSimulationItem(53, RodTiberium4);
        registerSimulationItem(54, RodNaquadah32);
        registerSimulationItem(55, Reactor_Coolant_Sp_1);
        registerSimulationItem(56, Reactor_Coolant_Sp_2);
        registerSimulationItem(57, Reactor_Coolant_Sp_3);
        registerSimulationItem(58, Reactor_Coolant_Sp_6);
        registerSimulationItem(59, neutroniumHeatCapacitor);
        registerSimulationItem(60, RodHighDensityUranium);
        registerSimulationItem(61, RodHighDensityUranium2);
        registerSimulationItem(62, RodHighDensityUranium4);
        registerSimulationItem(63, RodHighDensityPlutonium);
        registerSimulationItem(64, RodHighDensityPlutonium2);
        registerSimulationItem(65, RodHighDensityPlutonium4);
        registerSimulationItem(66, RodExcitedUranium);
        registerSimulationItem(67, RodExcitedUranium2);
        registerSimulationItem(68, RodExcitedUranium4);
        registerSimulationItem(69, RodExcitedPlutonium);
        registerSimulationItem(70, RodExcitedPlutonium2);
        registerSimulationItem(71, RodExcitedPlutonium);
        registerSimulationItem(72, RodGlowstone);
    }
}
