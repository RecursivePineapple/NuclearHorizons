package com.recursive_pineapple.nuclear_horizons.reactors.tile;

import javax.annotation.Nullable;

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

import com.recursive_pineapple.nuclear_horizons.reactors.fluids.CoolantRegistry;

public class TileFluidPort extends TileEntity implements IFluidHandler, IReactorBlock {

    public int reactorRelX, reactorRelY, reactorRelZ;

    @Override
    public @Nullable TileReactorCore getReactor() {
        // spotless:off
        if (worldObj.getTileEntity(xCoord + reactorRelX, yCoord + reactorRelY, zCoord + reactorRelZ) instanceof TileReactorCore reactor) {
            return reactor;
        } else {
            return null;
        }
        // spotless:on
    }

    @Override
    public void setReactor(TileReactorCore reactor) {
        if (getReactor() != reactor) {
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

        switch (compound.getInteger("version")) {
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

        if (reactor == null) {
            return 0;
        }

        // this should only be called a few times a second, so we don't need to cache it
        if(!CoolantRegistry.isColdCoolant(resource.getFluid())) {
            return 0;
        }

        var tank = reactor.coolantTank;

        if(tank.getFluid() == null || resource.getFluid() == tank.getFluid().getFluid()) {
            return tank.fill(resource, doFill);
        } else {
            return 0;
        }
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        var reactor = this.getReactor();

        if (reactor == null) {
            return null;
        }

        var tank = reactor.hotCoolantTank;

        if(tank.getFluid() != null && resource.getFluid() == tank.getFluid().getFluid()) {
            return tank.drain(resource.amount, doDrain);
        } else {
            return null;
        }
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        var reactor = this.getReactor();

        if (reactor == null) {
            return null;
        }

        var tank = reactor.hotCoolantTank;

        if(tank.getFluid() != null) {
            return tank.drain(maxDrain, doDrain);
        } else {
            return null;
        }
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return CoolantRegistry.isColdCoolant(fluid);
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return CoolantRegistry.isHotCoolant(fluid);
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        var reactor = this.getReactor();

        if (reactor == null) {
            return new FluidTankInfo[0];
        }

        return new FluidTankInfo[] {
            reactor.coolantTank.getInfo(),
            reactor.hotCoolantTank.getInfo()
        };
    }
}
