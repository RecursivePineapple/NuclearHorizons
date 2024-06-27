package com.recursive_pineapple.nuclear_horizons.reactors.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileRedstonePort;

public class ReactorRedstonePort extends BlockContainer {

    public ReactorRedstonePort() {
        super(Material.rock);
        setBlockName(BlockList.REACTOR_REDSTONE_PORT_NAME);
        setBlockTextureName("nuclear_horizons:redstone_port");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileRedstonePort();
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(World worldIn, int x, int y, int z, int side) {
        return ((TileRedstonePort) worldIn.getTileEntity(x, y, z)).isReactorActive() ? 15 : 0;
    }
}
