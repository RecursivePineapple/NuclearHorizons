package com.recursive_pineapple.nuclear_horizons.mixins.late.ic2;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.IReactorBlock;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileReactorCore;
import ic2.api.Direction;
import ic2.core.block.invslot.InvSlotReactor;
import ic2.core.block.reactor.tileentity.TileEntityNuclearReactorElectric;
import ic2.core.block.reactor.tileentity.TileEntityReactorChamberElectric;

@Mixin(TileEntityReactorChamberElectric.class)
public abstract class MixinReactorChamber extends TileEntity implements IReactorBlock {

    @Override
    public @Nullable IReactorGrid getReactor() {
        for(Direction value : Direction.directions) {
            TileEntity te = value.applyToTileEntity(this);
            if (te instanceof TileEntityNuclearReactorElectric) {
                return (IReactorGrid) te;
            }
        }

        Block blk = this.getBlockType();
        if (blk != null) {
            blk.onNeighborBlockChange(this.worldObj, this.xCoord, this.yCoord, this.zCoord, blk);
        }

        return null;
    }

    @Override
    public void setReactor(TileReactorCore reactor) {
        throw new UnsupportedOperationException();
    }
}
