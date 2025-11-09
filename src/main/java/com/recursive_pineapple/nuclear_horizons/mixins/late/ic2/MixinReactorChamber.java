package com.recursive_pineapple.nuclear_horizons.mixins.late.ic2;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.SoftOverride;

import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.IReactorBlock;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileReactorCore;
import ic2.api.Direction;
import ic2.core.block.reactor.tileentity.TileEntityNuclearReactorElectric;
import ic2.core.block.reactor.tileentity.TileEntityReactorChamberElectric;

@Mixin(TileEntityReactorChamberElectric.class)
public abstract class MixinReactorChamber extends TileEntity {

//    public @Nullable IReactorGrid getReactor() {
//        for(Direction value : Direction.directions) {
//            TileEntity te = value.applyToTileEntity(this);
//            if (te instanceof TileEntityNuclearReactorElectric) {
//                return (IReactorGrid) (Object) te;
//            }
//        }
//
//        Block blk = this.getBlockType();
//        if (blk != null) {
//            blk.onNeighborBlockChange(this.worldObj, this.xCoord, this.yCoord, this.zCoord, blk);
//        }
//
//        return null;
//    }
//
//    @Override
//    public void setReactor(TileReactorCore reactor) {
//        throw new UnsupportedOperationException();
//    }
}
