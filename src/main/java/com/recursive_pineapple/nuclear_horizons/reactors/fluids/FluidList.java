package com.recursive_pineapple.nuclear_horizons.reactors.fluids;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.recursive_pineapple.nuclear_horizons.Config;
import com.recursive_pineapple.nuclear_horizons.reactors.items.ItemList;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.registries.LHECoolantRegistry;

public class FluidList {

    public static final String DISTILLED_WATER_NAME = "distilled_water";
    public static final String COOLANT_NAME = "nh_coolant";
    public static final String HOT_COOLANT_NAME = "nh_hot_coolant";
    public static final String PSEUDO_LIQUID_NAQUADAH_NAME = "pseudo_liquid_naquadah";
    public static final String HOT_PSEUDO_LIQUID_NAQUADAH_NAME = "hot_pseudo_liquid_naquadah";
    public static final Fluid DISTILLED_WATER = new Fluid(DISTILLED_WATER_NAME);
    public static final Fluid COOLANT = new Fluid(COOLANT_NAME);
    public static final Fluid HOT_COOLANT = new Fluid(HOT_COOLANT_NAME);
    public static final Fluid PSEUDO_LIQUID_NAQUADAH = new Fluid(PSEUDO_LIQUID_NAQUADAH_NAME);
    public static final Fluid HOT_PSEUDO_LIQUID_NAQUADAH = new Fluid(HOT_PSEUDO_LIQUID_NAQUADAH_NAME);

    public static void registerFluids() {
        FluidRegistry.registerFluid(DISTILLED_WATER);

        FluidRegistry.registerFluid(COOLANT);

        HOT_COOLANT.setTemperature(273 + 200);
        FluidRegistry.registerFluid(HOT_COOLANT);

        PSEUDO_LIQUID_NAQUADAH.setTemperature(273 + 90);
        FluidRegistry.registerFluid(PSEUDO_LIQUID_NAQUADAH);

        HOT_PSEUDO_LIQUID_NAQUADAH.setTemperature(273 + 500);
        FluidRegistry.registerFluid(HOT_PSEUDO_LIQUID_NAQUADAH);
    }

    public static void registerContainers() {
        registerCell(COOLANT, 0);
        registerCell(HOT_COOLANT, 1);
        registerCell(PSEUDO_LIQUID_NAQUADAH, 2);
        registerCell(HOT_PSEUDO_LIQUID_NAQUADAH, 3);
        registerCell(DISTILLED_WATER, 4);
    }

    private static void registerCell(Fluid fluid, int metadata) {
        ItemStack empty = new ItemStack(GameRegistry.findItem("IC2", "itemCellEmpty"));

        FluidContainerRegistry.registerFluidContainer(
            new FluidContainerData(new FluidStack(fluid, 1000), new ItemStack(ItemList.CELLS, 1, metadata), empty));
    }

    public static void registerCoolants() {
        CoolantRegistry.registerCoolant(COOLANT, HOT_COOLANT, Config.COOLANT_SPECIFIC_HEAT);
        CoolantRegistry.registerCoolant(
            FluidRegistry.getFluid("ic2coolant"),
            FluidRegistry.getFluid("ic2hotcoolant"),
            Config.COOLANT_SPECIFIC_HEAT);

        CoolantRegistry.registerCoolant(
            DISTILLED_WATER,
            FluidRegistry.getFluid("steam"), 1);

        CoolantRegistry
            .registerCoolant(PSEUDO_LIQUID_NAQUADAH, HOT_PSEUDO_LIQUID_NAQUADAH, Config.NAQ_COOLANT_SPECIFIC_HEAT);

        double baseMultiplier = 0.5;
        double baseThreshold = 0.2;

        LHECoolantRegistry.registerCoolant(
            COOLANT_NAME,
            HOT_COOLANT_NAME,
            baseMultiplier * Config.COOLANT_SPECIFIC_HEAT,
            baseThreshold / Config.COOLANT_SPECIFIC_HEAT);

        LHECoolantRegistry.registerCoolant(
            PSEUDO_LIQUID_NAQUADAH_NAME,
            HOT_PSEUDO_LIQUID_NAQUADAH_NAME,
            baseMultiplier * Config.NAQ_COOLANT_SPECIFIC_HEAT,
            baseThreshold / Config.NAQ_COOLANT_SPECIFIC_HEAT);
    }
}
