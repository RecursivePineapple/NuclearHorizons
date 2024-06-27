package com.recursive_pineapple.nuclear_horizons.reactors.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class PressureVessel extends Block {

    public PressureVessel() {
        super(Material.rock);
        setBlockName(BlockList.PRESSURE_VESSEL_NAME);
        setBlockTextureName("nuclear_horizons:pressure_vessel");
    }

}
