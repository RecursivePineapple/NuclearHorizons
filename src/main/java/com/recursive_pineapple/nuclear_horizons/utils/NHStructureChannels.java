package com.recursive_pineapple.nuclear_horizons.utils;

import net.minecraft.item.ItemStack;

import com.gtnewhorizon.structurelib.StructureLibAPI;
import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import gregtech.api.enums.Mods;
import gregtech.api.structure.IStructureChannels;
import gregtech.common.misc.GTStructureChannels;

public enum NHStructureChannels implements IStructureChannels {
    FLUID("fluid", "Reactor Type (1=Fluid)"),
    CHAMBERS("chambers", "Reactor Chamber Count");

    private final String channel;
    private final String defaultTooltip;

    NHStructureChannels(String aChannel, String defaultTooltip) {
        channel = aChannel;
        this.defaultTooltip = defaultTooltip;
    }

    @Override
    public String get() {
        return channel;
    }

    @Override
    public String getDefaultTooltip() {
        return defaultTooltip;
    }

    @Override
    public void registerAsIndicator(ItemStack indicator, int channelValue) {
        StructureLibAPI.registerChannelItem(get(), NuclearHorizons.MODID, channelValue, indicator);
    }

    public static void register() {
        for (NHStructureChannels value : values()) {
            StructureLibAPI.registerChannelDescription(
                value.get(),
                NuclearHorizons.MODID,
                "channels." + NuclearHorizons.MODID + "." + value.get());
        }
    }
}
