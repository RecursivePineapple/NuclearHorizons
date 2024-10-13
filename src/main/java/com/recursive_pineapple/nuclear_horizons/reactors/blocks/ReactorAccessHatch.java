package com.recursive_pineapple.nuclear_horizons.reactors.blocks;

import java.util.ArrayList;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.gtnewhorizons.modularui.api.UIInfos;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileAccessHatch;

import gregtech.api.interfaces.IDebugableBlock;
import gregtech.api.interfaces.tileentity.IDebugableTileEntity;

public class ReactorAccessHatch extends BlockContainer implements IDebugableBlock {

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

    @Override
    public ArrayList<String> getDebugInfo(EntityPlayer aPlayer, int aX, int aY, int aZ, int aLogLevel) {
        if (aPlayer.getEntityWorld()
            .getTileEntity(aX, aY, aZ) instanceof IDebugableTileEntity te) {
            return te.getDebugInfo(aPlayer, aLogLevel);
        } else {
            return new ArrayList<>();
        }
    }
}
