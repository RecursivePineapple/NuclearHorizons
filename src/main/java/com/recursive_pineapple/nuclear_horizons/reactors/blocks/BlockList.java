package com.recursive_pineapple.nuclear_horizons.reactors.blocks;

import static cpw.mods.fml.common.registry.GameRegistry.registerBlock;
import static cpw.mods.fml.common.registry.GameRegistry.registerTileEntity;

import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.fluids.FluidList;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileAccessHatch;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileFluidPort;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileReactorCore;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileRedstonePort;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileThermalSensor;

import net.minecraft.block.material.Material;

public class BlockList {
    
    public static final String REACTOR_CORE_NAME = "reactor_core";
    public static final String REACTOR_CHAMBER_NAME = "reactor_chamber";
    public static final String PRESSURE_VESSEL_NAME = "pressure_vessel";
    public static final String REACTOR_FLUID_PORT_NAME = "reactor_fluid_port";
    public static final String REACTOR_ACCESS_HATCH_NAME = "reactor_access_hatch";
    public static final String REACTOR_REDSTONE_PORT_NAME = "reactor_redstone_port";
    public static final String REACTOR_THERMAL_SENSOR_NAME = "reactor_thermal_sensor";
    public static final String COOLANT_BLOCK_NAME = "nh_coolant";
    public static final String HOT_COOLANT_BLOCK_NAME = "nh_hot_coolant";

    public static ReactorCore REACTOR_CORE;
    public static ReactorChamber REACTOR_CHAMBER;
    public static PressureVessel PRESSURE_VESSEL;
    public static ReactorFluidPort REACTOR_FLUID_PORT;
    public static ReactorAccessHatch REACTOR_ACCESS_HATCH;
    public static ReactorRedstonePort REACTOR_REDSTONE_PORT;
    public static ReactorThermalSensor REACTOR_THERMAL_SENSOR;

    public static FluidBlock COOLANT_BLOCK;
    public static FluidBlock HOT_COOLANT_BLOCK;

    public static void registerBlocks() {
        REACTOR_CORE = new ReactorCore();
        REACTOR_CHAMBER = new ReactorChamber();
        PRESSURE_VESSEL = new PressureVessel();
        REACTOR_FLUID_PORT = new ReactorFluidPort();
        REACTOR_ACCESS_HATCH = new ReactorAccessHatch();
        REACTOR_REDSTONE_PORT = new ReactorRedstonePort();
        REACTOR_THERMAL_SENSOR = new ReactorThermalSensor();

        COOLANT_BLOCK = new FluidBlock(
            FluidList.COOLANT,
            Material.water,
            NuclearHorizons.MODID + ":coolant_still",
            NuclearHorizons.MODID + ":coolant_flow"
        );
        COOLANT_BLOCK.setBlockName(COOLANT_BLOCK_NAME);

        HOT_COOLANT_BLOCK = new FluidBlock(
            FluidList.HOT_COOLANT,
            Material.water,
            NuclearHorizons.MODID + ":hot_coolant_still",
            NuclearHorizons.MODID + ":hot_coolant_flow"
        );
        HOT_COOLANT_BLOCK.setBlockName(HOT_COOLANT_BLOCK_NAME);

        registerBlock(REACTOR_CORE, REACTOR_CORE_NAME);
        registerTileEntity(TileReactorCore.class, REACTOR_CORE_NAME);

        registerBlock(REACTOR_CHAMBER, REACTOR_CHAMBER_NAME);

        registerBlock(PRESSURE_VESSEL, PRESSURE_VESSEL_NAME);

        registerBlock(REACTOR_FLUID_PORT, REACTOR_FLUID_PORT_NAME);
        registerTileEntity(TileFluidPort.class, REACTOR_FLUID_PORT_NAME);

        registerBlock(REACTOR_ACCESS_HATCH, REACTOR_ACCESS_HATCH_NAME);
        registerTileEntity(TileAccessHatch.class, REACTOR_ACCESS_HATCH_NAME);

        registerBlock(REACTOR_REDSTONE_PORT, REACTOR_REDSTONE_PORT_NAME);
        registerTileEntity(TileRedstonePort.class, REACTOR_REDSTONE_PORT_NAME);

        registerBlock(REACTOR_THERMAL_SENSOR, REACTOR_THERMAL_SENSOR_NAME);
        registerTileEntity(TileThermalSensor.class, REACTOR_THERMAL_SENSOR_NAME);

        registerBlock(COOLANT_BLOCK, COOLANT_BLOCK_NAME);
        registerBlock(HOT_COOLANT_BLOCK, HOT_COOLANT_BLOCK_NAME);
    }
}
