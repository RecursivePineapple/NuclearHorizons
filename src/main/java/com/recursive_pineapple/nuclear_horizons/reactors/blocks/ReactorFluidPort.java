package com.recursive_pineapple.nuclear_horizons.reactors.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileFluidPort;

public class ReactorFluidPort extends BlockContainer {

    public ReactorFluidPort() {
        super(Material.rock);
        setBlockName(BlockList.REACTOR_FLUID_PORT_NAME);
        setBlockTextureName("nuclear_horizons:fluid_port");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileFluidPort();
    }
}
