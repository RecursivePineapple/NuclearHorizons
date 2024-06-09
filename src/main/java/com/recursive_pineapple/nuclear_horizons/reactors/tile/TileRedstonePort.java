package com.recursive_pineapple.nuclear_horizons.reactors.tile;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileRedstonePort extends TileEntity implements IReactorBlock {

    public int reactorRelX, reactorRelY, reactorRelZ;
    
    private boolean inverted, hasSignal;

    @Override
    public @Nullable TileReactorCore getReactor() {
        if(worldObj.getTileEntity(xCoord + reactorRelX, yCoord + reactorRelY, zCoord + reactorRelZ) instanceof TileReactorCore reactor) {
            return reactor;
        } else {
            return null;
        }
    }

    @Override
    public void setReactor(TileReactorCore reactor) {
        if(getReactor() != reactor) {
            this.reactorRelX = reactor.xCoord - xCoord;
            this.reactorRelY = reactor.yCoord - yCoord;
            this.reactorRelZ = reactor.zCoord - zCoord;
            this.markDirty();
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    @Override
    public ReactorEnableState getEnableState() {
        boolean hasSignal = worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) > 0;

        if(inverted) {
            if(hasSignal) {
                return ReactorEnableState.Inhibiting;
            } else {
                return ReactorEnableState.Active;
            }
        } else {
            if(hasSignal) {
                return ReactorEnableState.Active;
            } else {
                return ReactorEnableState.Idle;
            }
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        var data = new NBTTagCompound();

        data.setInteger("reactorRelX", reactorRelX);
        data.setInteger("reactorRelY", reactorRelY);
        data.setInteger("reactorRelZ", reactorRelZ);
        data.setBoolean("inverted", inverted);

        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, blockMetadata, data);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        var data = pkt.func_148857_g();

        this.reactorRelX = data.getInteger("reactorRelX");
        this.reactorRelY = data.getInteger("reactorRelY");
        this.reactorRelZ = data.getInteger("reactorRelZ");
        this.inverted = data.getBoolean("inverted");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setInteger("version", 1);

        compound.setInteger("reactorRelX", this.reactorRelX);
        compound.setInteger("reactorRelY", this.reactorRelY);
        compound.setInteger("reactorRelZ", this.reactorRelZ);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        switch(compound.getInteger("version")) {
            case 1: {
                this.reactorRelX = compound.getInteger("reactorRelX");
                this.reactorRelY = compound.getInteger("reactorRelY");
                this.reactorRelZ = compound.getInteger("reactorRelZ");
                break;
            }
        }
    }

    public boolean isInverted() {
        return inverted;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public boolean isReactorActive() {
        var reactor = getReactor();

        return reactor == null ? false : reactor.isActive();
    }
}
