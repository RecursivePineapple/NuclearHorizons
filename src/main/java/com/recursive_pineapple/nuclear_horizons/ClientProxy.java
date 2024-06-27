package com.recursive_pineapple.nuclear_horizons;

import com.gtnewhorizons.modularui.common.peripheral.ModularUIPeripheralInputHandler;
import com.gtnewhorizons.modularui.integration.nei.ModularUIContainerObjectHandler;

import codechicken.nei.guihook.GuiContainerManager;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        GuiContainerManager.addInputHandler(new ModularUIPeripheralInputHandler());
        GuiContainerManager.addObjectHandler(new ModularUIContainerObjectHandler());
        // NetworkRegistry.INSTANCE.registerGuiHandler(NuclearHorizons.instance, new GuiHandler());
    }

}
