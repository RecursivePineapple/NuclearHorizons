package com.recursive_pineapple.nuclear_horizons.reactors.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FluidBlock extends BlockFluidClassic {

    public static final DamageSource HOT_FLUID_DAMAGE = new DamageSource("nh_hot_fluid");

    private String stillTextureName, flowingTextureName;

    public IIcon stillIcon, flowingIcon;
    private boolean burnsEntities = false;

    public FluidBlock(Fluid fluid, Material material, String stillTextureName, String flowingTextureName) {
        super(fluid, material);

        this.stillTextureName = stillTextureName;
        this.flowingTextureName = flowingTextureName;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side != 0 && side != 1 ? flowingIcon : stillIcon;
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        stillIcon = reg.registerIcon(stillTextureName);
        flowingIcon = reg.registerIcon(flowingTextureName);
    }

    public FluidBlock setBurnsEntities(boolean burnsEntities) {
        this.burnsEntities = burnsEntities;
        return this;
    }

    @Override
    public boolean isBurning(IBlockAccess world, int x, int y, int z) {
        return burnsEntities;
    }
}
