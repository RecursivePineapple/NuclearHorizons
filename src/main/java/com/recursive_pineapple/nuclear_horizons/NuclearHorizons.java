package com.recursive_pineapple.nuclear_horizons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear;

import bartworks.API.WerkstoffAdderRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(
    modid = NuclearHorizons.MODID,
    version = Tags.VERSION,
    name = "Nuclear Horizons",
    acceptedMinecraftVersions = "[1.7.10]")
public class NuclearHorizons {

    public static final String MODID = "nuclear_horizons";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(
        clientSide = "com.recursive_pineapple.nuclear_horizons.ClientProxy",
        serverSide = "com.recursive_pineapple.nuclear_horizons.ServerProxy")
    public static CommonProxy proxy;

    @Instance
    public static NuclearHorizons instance = new NuclearHorizons();

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        // werkstoff material registration
        WerkstoffAdderRegistry.addWerkstoffAdder(new MaterialsNuclear());

        proxy.preInit(event);
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
