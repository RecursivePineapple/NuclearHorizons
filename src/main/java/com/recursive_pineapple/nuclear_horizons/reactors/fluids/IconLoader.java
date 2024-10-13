package com.recursive_pineapple.nuclear_horizons.reactors.fluids;

import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;

import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class IconLoader {

    public static final IconLoader INSTANCE = new IconLoader();

    public void register() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerIcons(TextureStitchEvent.Pre event) {
        if (event.map.getTextureType() == 0) {
            FluidList.COOLANT.setIcons(
                event.map.registerIcon(NuclearHorizons.MODID + ":coolant_still"),
                event.map.registerIcon(NuclearHorizons.MODID + ":coolant_flow"));

            FluidList.HOT_COOLANT.setIcons(
                event.map.registerIcon(NuclearHorizons.MODID + ":hot_coolant_still"),
                event.map.registerIcon(NuclearHorizons.MODID + ":hot_coolant_flow"));

            FluidList.PSEUDO_LIQUID_NAQUADAH.setIcons(
                event.map.registerIcon(NuclearHorizons.MODID + ":cold_pnaq_still"),
                event.map.registerIcon(NuclearHorizons.MODID + ":cold_pnaq_flow"));

            FluidList.HOT_PSEUDO_LIQUID_NAQUADAH.setIcons(
                event.map.registerIcon(NuclearHorizons.MODID + ":hot_pnaq_still"),
                event.map.registerIcon(NuclearHorizons.MODID + ":hot_pnaq_flow"));
        }
    }
}
