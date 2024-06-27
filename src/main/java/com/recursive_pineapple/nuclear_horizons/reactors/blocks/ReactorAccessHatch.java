package com.recursive_pineapple.nuclear_horizons.reactors.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.gtnewhorizons.modularui.api.UIInfos;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileAccessHatch;

public class ReactorAccessHatch extends BlockContainer {

    public ReactorAccessHatch() {
        super(Material.rock);
        setBlockName(BlockList.REACTOR_ACCESS_HATCH_NAME);
        setBlockTextureName("nuclear_horizons:access_hatch");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileAccessHatch();
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX,
        float subY, float subZ) {
        var reactor = ((TileAccessHatch) worldIn.getTileEntity(x, y, z)).getReactor();

        if (!worldIn.isRemote) {
            if (reactor != null) {
                UIInfos.TILE_MODULAR_UI.open(player, worldIn, reactor.xCoord, reactor.yCoord, reactor.zCoord);
            }
        }

        if (reactor != null) {
            return true;
        } else {
            return false;
        }
    }
}
