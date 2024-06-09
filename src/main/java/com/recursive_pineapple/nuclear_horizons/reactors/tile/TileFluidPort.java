package com.recursive_pineapple.nuclear_horizons.reactors.tile;

import javax.annotation.Nullable;

import com.recursive_pineapple.nuclear_horizons.reactors.fluids.FluidList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileFluidPort extends TileEntity implements IFluidHandler, IReactorBlock {

    public int reactorRelX, reactorRelY, reactorRelZ;

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
    public Packet getDescriptionPacket() {
        var data = new NBTTagCompound();

        data.setInteger("reactorRelX", reactorRelX);
        data.setInteger("reactorRelY", reactorRelY);
        data.setInteger("reactorRelZ", reactorRelZ);

        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, blockMetadata, data);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        var data = pkt.func_148857_g();

        this.reactorRelX = data.getInteger("reactorRelX");
        this.reactorRelY = data.getInteger("reactorRelY");
        this.reactorRelZ = data.getInteger("reactorRelZ");
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

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        var reactor = this.getReactor();

        if(reactor == null) {
            return 0;
        }

        if(resource.getFluid() == FluidList.COOLANT) {
            int remaining = reactor.maxCoolant - reactor.storedCoolant;

            int consumed = Math.min(remaining, resource.amount);

            if(doFill) {
                reactor.storedCoolant += consumed;
            }

            return consumed;
        } else {
            return 0;
        }
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        var reactor = this.getReactor();

        if(reactor == null) {
            return null;
        }

        if(resource.getFluid() == FluidList.HOT_COOLANT) {
            int consumed = Math.min(reactor.storedHotCoolant, resource.amount);

            if(doDrain) {
                reactor.storedHotCoolant -= consumed;
            }

            return new FluidStack(FluidList.HOT_COOLANT, consumed);
        } else {
            return null;
        }
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        var reactor = this.getReactor();

        if(reactor == null) {
            return null;
        }

        int consumed = Math.min(reactor.storedHotCoolant, maxDrain);

        if(doDrain) {
            reactor.storedHotCoolant -= consumed;
        }

        return new FluidStack(FluidList.HOT_COOLANT, consumed);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return fluid == FluidList.COOLANT;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return fluid == FluidList.HOT_COOLANT;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        var reactor = this.getReactor();

        if(reactor == null) {
            return new FluidTankInfo[0];
        }

        return new FluidTankInfo[] {
            new FluidTankInfo(new FluidStack(FluidList.COOLANT, reactor.storedCoolant), reactor.maxCoolant),
            new FluidTankInfo(new FluidStack(FluidList.HOT_COOLANT, reactor.storedHotCoolant), reactor.maxHotCoolant),
        };
    }
}
