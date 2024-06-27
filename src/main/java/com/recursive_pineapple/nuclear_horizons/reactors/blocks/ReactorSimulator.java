package com.recursive_pineapple.nuclear_horizons.reactors.blocks;

import com.gtnewhorizons.modularui.api.UIInfos;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileReactorSimulator;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ReactorSimulator extends BlockContainer {
    protected ReactorSimulator() {
        super(Material.iron);

        setBlockName(BlockList.REACTOR_SIMULATOR_NAME);
        setStepSound(soundTypeMetal);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileReactorSimulator();
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
        if(!worldIn.isRemote) {
            UIInfos.TILE_MODULAR_UI.open(player, worldIn, x, y, z);
        }

        return true;
    }
}
