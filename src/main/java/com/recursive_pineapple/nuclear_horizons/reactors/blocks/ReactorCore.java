package com.recursive_pineapple.nuclear_horizons.reactors.blocks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.gtnewhorizons.modularui.api.UIInfos;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileReactorCore;
import com.recursive_pineapple.nuclear_horizons.utils.DirectionUtil;

import gregtech.api.interfaces.IDebugableBlock;
import gregtech.api.interfaces.tileentity.IDebugableTileEntity;

public class ReactorCore extends BlockContainer implements IDebugableBlock {

    private IIcon iconTop, iconSideInactive, iconSideActive, iconBottom;

    public ReactorCore() {
        super(Material.iron);

        setHardness(5.0f);
        setBlockName(BlockList.REACTOR_CORE_NAME);
        setStepSound(soundTypeMetal);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.iconTop = reg.registerIcon("nuclear_horizons:reactor_chamber_side");
        this.iconBottom = reg.registerIcon("nuclear_horizons:reactor_chamber_top");
        this.iconSideInactive = reg.registerIcon("nuclear_horizons:reactor_core_side");
        this.iconSideActive = reg.registerIcon("nuclear_horizons:reactor_core_side_active");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 1) {
            return iconTop;
        }

        if (side == 0) {
            return iconBottom;
        }

        if (meta == 1) {
            return iconSideActive;
        } else {
            return iconSideInactive;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileReactorCore();
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX,
        float subY, float subZ) {
        if (!worldIn.isRemote) {
            UIInfos.TILE_MODULAR_UI.open(player, worldIn, x, y, z);
        }

        return true;
    }

    @Override
    public void onBlockPreDestroy(World worldIn, int x, int y, int z, int meta) {
        ((TileReactorCore) worldIn.getTileEntity(x, y, z)).dropInventory();
    }

    public static int getAttachedChambers(World worldIn, int x, int y, int z) {
        int chamberCount = 0;

        for (var d : DirectionUtil.values()) {
            if (d.getBlock(worldIn, x, y, z) == BlockList.REACTOR_CHAMBER) {
                chamberCount++;
            }
        }

        return chamberCount;
    }

    @Override
    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor) {
        if (worldIn.isRemote) return;

        TileReactorCore te = (TileReactorCore) worldIn.getTileEntity(x, y, z);

        if (getAttachedChambers(worldIn, x, y, z) != te.getChamberCount()) {
            ((TileReactorCore) worldIn.getTileEntity(x, y, z)).queueStructureCheck();
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
