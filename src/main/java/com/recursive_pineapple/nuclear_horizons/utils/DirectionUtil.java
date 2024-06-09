package com.recursive_pineapple.nuclear_horizons.utils;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public enum DirectionUtil {
    Up(0, 1, 0),
    Down(0, -1, 0),
    North(0, 0, -1),
    South(0, 0, 1),
    East(1, 0, 0),
    West(-1, 0, 0);
    
    public final int offsetX;
    public final int offsetY;
    public final int offsetZ;

    private DirectionUtil(int x, int y, int z) {
        this.offsetX = x;
        this.offsetY = y;
        this.offsetZ = z;
    }

    public Block getBlock(World world, int x, int y, int z) {
        return world.getBlock(offsetX + x, offsetY + y, offsetZ + z);
    }

    public TileEntity getTileEntity(World world, int x, int y, int z) {
        return world.getTileEntity(offsetX + x, offsetY + y, offsetZ + z);
    }
}
