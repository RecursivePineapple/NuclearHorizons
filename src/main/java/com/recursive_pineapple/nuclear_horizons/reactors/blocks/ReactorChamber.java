package com.recursive_pineapple.nuclear_horizons.reactors.blocks;

import com.recursive_pineapple.nuclear_horizons.utils.DirectionUtil;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ReactorChamber extends Block {
    
    private IIcon icon;

    public ReactorChamber() {
        super(Material.iron);
        
        setHardness(5.0f);
        setBlockName(BlockList.REACTOR_CHAMBER_NAME);
        setStepSound(soundTypeMetal);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.icon = reg.registerIcon("nuclear_horizons:reactor_chamber");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return icon;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, int x, int y, int z) {
        return getAttachedReactors(worldIn, x, y, z) == 1;
    }

    @Override
    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor) {
        if(getAttachedReactors(worldIn, x, y, z) != 1) {
            worldIn.setBlock(x, y, z, Blocks.air);
            worldIn.spawnEntityInWorld(new EntityItem(worldIn, x + 0.5, y + 0.5, z + 0.5, new ItemStack(this, 1)));

            var worldclient = Minecraft.getMinecraft().theWorld;

            worldclient.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(this) + (worldclient.getBlockMetadata(x, y, z) << 12));
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
        for(var d : DirectionUtil.values()) {
            if(d.getBlock(worldIn, x, y, z) == BlockList.REACTOR_CORE) {
                return BlockList.REACTOR_CORE.onBlockActivated(
                    worldIn,
                    d.offsetX + x, d.offsetY + y, d.offsetZ + z,
                    player,
                    side,
                    0f, 0f, 0f
                );
            }
        }

        return false;
    }

    private int getAttachedReactors(World worldIn, int x, int y, int z) {
        int reactorCount = 0;

        for(var d : DirectionUtil.values()) {
            if(d.getBlock(worldIn, x, y, z) == BlockList.REACTOR_CORE) {
                reactorCount++;
            }
        }

        return reactorCount;
    }

}
