package com.recursive_pineapple.nuclear_horizons.mixins.late.ic2;

import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;

import ic2.core.block.reactor.tileentity.TileEntityReactorChamberElectric;

@Mixin(TileEntityReactorChamberElectric.class)
public abstract class MixinReactorChamber extends TileEntity {

    // public @Nullable IReactorGrid getReactor() {
    // for(Direction value : Direction.directions) {
    // TileEntity te = value.applyToTileEntity(this);
    // if (te instanceof TileEntityNuclearReactorElectric) {
    // return (IReactorGrid) (Object) te;
    // }
    // }
    //
    // Block blk = this.getBlockType();
    // if (blk != null) {
    // blk.onNeighborBlockChange(this.worldObj, this.xCoord, this.yCoord, this.zCoord, blk);
    // }
    //
    // return null;
    // }
    //
    // @Override
    // public void setReactor(TileReactorCore reactor) {
    // throw new UnsupportedOperationException();
    // }
}
