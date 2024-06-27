package com.recursive_pineapple.nuclear_horizons.reactors.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.gtnewhorizons.modularui.api.UIInfos;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileThermalSensor;

public class ReactorThermalSensor extends BlockContainer {

    protected ReactorThermalSensor() {
        super(Material.piston);
        setBlockName(BlockList.REACTOR_THERMAL_SENSOR_NAME);
        setBlockTextureName("nuclear_horizons:thermal_sensor");
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public boolean shouldCheckWeakPower(IBlockAccess world, int x, int y, int z, int side) {
        return false;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess worldIn, int x, int y, int z, int side) {
        var te = (TileThermalSensor) worldIn.getTileEntity(x, y, z);

        return te.isActive() ? 15 : 0;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileThermalSensor();
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX,
        float subY, float subZ) {
        if (!worldIn.isRemote) {
            UIInfos.TILE_MODULAR_UI.open(player, worldIn, x, y, z);
        }

        return true;
    }
}
