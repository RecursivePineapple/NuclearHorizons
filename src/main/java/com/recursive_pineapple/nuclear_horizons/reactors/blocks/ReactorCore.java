package com.recursive_pineapple.nuclear_horizons.reactors.blocks;

import com.gtnewhorizons.modularui.api.UIInfos;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileReactorCore;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ReactorCore extends BlockContainer {
    
    private IIcon iconTop, iconSide;

    public ReactorCore() {
        super(Material.iron);

        setHardness(5.0f);
        setBlockName(BlockList.REACTOR_CORE_NAME);
        setStepSound(soundTypeMetal);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.iconTop = reg.registerIcon("nuclear_horizons:reactor_chamber");
        this.iconSide = reg.registerIcon("nuclear_horizons:reactor_core");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return iconTop;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileReactorCore();
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
        if(!worldIn.isRemote) {
            UIInfos.TILE_MODULAR_UI.open(player, worldIn, x, y, z);
        }

        return true;
    }

    @Override
    public void onBlockPreDestroy(World worldIn, int x, int y, int z, int meta) {
        ((TileReactorCore)worldIn.getTileEntity(x, y, z)).dropInventory();
    }
}
