package com.recursive_pineapple.nuclear_horizons.reactors.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

public class FluidBlock extends BlockFluidFinite {

    private String stillTextureName, flowingTextureName;
    
    public IIcon stillIcon, flowingIcon;

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
}
