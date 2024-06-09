package com.recursive_pineapple.nuclear_horizons.reactors.blocks;

import com.gtnewhorizons.modularui.api.UIInfos;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileReactorChamber;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileReactorCore;
import com.recursive_pineapple.nuclear_horizons.utils.DirectionUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ReactorChamber extends BlockContainer {
    
    public ReactorChamber() {
        super(Material.iron);
        
        setHardness(5.0f);
        setBlockName(BlockList.REACTOR_CHAMBER_NAME);
        setStepSound(soundTypeMetal);
        setBlockTextureName("nuclear_horizons:reactor_chamber");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileReactorChamber();
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, int x, int y, int z) {
        return getAttachedReactors(worldIn, x, y, z) == 1;
    }

    @Override
    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor) {
        onBlocksChanged(worldIn, x, y, z);
    }

    @Override
    public void onBlockAdded(World worldIn, int x, int y, int z) {
        onBlocksChanged(worldIn, x, y, z);
    }

    private void onBlocksChanged(World worldIn, int x, int y, int z) {
        if(getAttachedReactors(worldIn, x, y, z) != 1) {
            worldIn.setBlock(x, y, z, Blocks.air);
            worldIn.spawnEntityInWorld(new EntityItem(worldIn, x + 0.5, y + 0.5, z + 0.5, new ItemStack(this, 1)));

            var worldclient = Minecraft.getMinecraft().theWorld;

            worldclient.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(this) + (worldclient.getBlockMetadata(x, y, z) << 12));
        } else {
            ((TileReactorChamber)worldIn.getTileEntity(x, y, z)).setReactor(null);

            for(var d : DirectionUtil.values()) {
                if(d.getTileEntity(worldIn, x, y, z) instanceof TileReactorCore reactor) {
                    ((TileReactorChamber)worldIn.getTileEntity(x, y, z)).setReactor(reactor);
                    break;
                }
            }
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
        var reactor = ((TileReactorChamber)worldIn.getTileEntity(x, y, z)).getReactor();

        if(reactor != null) {
            if(!worldIn.isRemote) {
                UIInfos.TILE_MODULAR_UI.open(player, worldIn, reactor.xCoord, reactor.yCoord, reactor.zCoord);
            }

            return true;
        } else {
            return false;
        }
    }

    private static int getAttachedReactors(World worldIn, int x, int y, int z) {
        int reactorCount = 0;

        for(var d : DirectionUtil.values()) {
            if(d.getBlock(worldIn, x, y, z) == BlockList.REACTOR_CORE) {
                reactorCount++;
            }
        }

        return reactorCount;
    }

}
