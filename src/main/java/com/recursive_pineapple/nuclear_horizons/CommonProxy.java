package com.recursive_pineapple.nuclear_horizons;

import com.recursive_pineapple.nuclear_horizons.networking.PacketDispatcher;
import com.recursive_pineapple.nuclear_horizons.reactors.blocks.BlockList;
import com.recursive_pineapple.nuclear_horizons.reactors.fluids.FluidList;
import com.recursive_pineapple.nuclear_horizons.reactors.items.ForeignItems;
import com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList;
import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsChemical;
import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.simulator.SimulationItems;
import com.recursive_pineapple.nuclear_horizons.recipes.*;

import com.recursive_pineapple.nuclear_horizons.utils.NHStructureChannels;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy {

    public Side getSide() {
        return null;
    }

    public void preInit(FMLPreInitializationEvent event) {
        NuclearHorizons.LOG.info("Loading Nuclear Horizons version " + Tags.VERSION);

        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        NHItemList.registerItems();
        BlockList.registerBlocks();
        FluidList.registerFluids();

        MaterialsNuclear.init();
        MaterialsChemical.init();
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        PacketDispatcher.registerPackets();

        NHStructureChannels.register();
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        FluidList.registerContainers();
        FluidList.registerCoolants();
        SimulationItems.registerSimulationItems();
        ForeignItems.registerForeignReactorItems();

        ReprocessingRecipes.registerRecipes();
        FuelProcessingRecipes.registerRecipes();
        ChemicalRecipes.registerRecipes();
        CoolantRecipes.registerRecipes();
        FuelRodRecipes.registerRecipes();
        ComponentRecipes.registerRecipes();
        MiscRecipes.registerRecipes();
    }

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {

    }
}
