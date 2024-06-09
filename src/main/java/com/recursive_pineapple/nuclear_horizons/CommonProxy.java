package com.recursive_pineapple.nuclear_horizons;

import com.recursive_pineapple.nuclear_horizons.networking.PacketDispatcher;
import com.recursive_pineapple.nuclear_horizons.reactors.blocks.BlockList;
import com.recursive_pineapple.nuclear_horizons.reactors.fluids.FluidList;
import com.recursive_pineapple.nuclear_horizons.reactors.items.ItemList;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        NuclearHorizons.LOG.info("Loading Nuclear Horizons version " + Tags.VERSION);

        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        FluidList.registerFluids();
        BlockList.registerBlocks();
        ItemList.registerItems();
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        // PacketDispatcher.registerPackets();
        // PacketDispatcher.TileEntityUpdatedMessage.init();
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {}

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {}
}
