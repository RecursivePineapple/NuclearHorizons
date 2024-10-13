package com.recursive_pineapple.nuclear_horizons;

import com.gtnewhorizons.modularui.common.peripheral.ModularUIPeripheralInputHandler;
import com.gtnewhorizons.modularui.integration.nei.ModularUIContainerObjectHandler;
import com.recursive_pineapple.nuclear_horizons.reactors.fluids.IconLoader;

import codechicken.nei.guihook.GuiContainerManager;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

    @Override
    public Side getSide() {
        return Side.CLIENT;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        IconLoader.INSTANCE.register();

        GuiContainerManager.addInputHandler(new ModularUIPeripheralInputHandler());
        GuiContainerManager.addObjectHandler(new ModularUIContainerObjectHandler());
        // NetworkRegistry.INSTANCE.registerGuiHandler(NuclearHorizons.instance, new GuiHandler());
    }

}
