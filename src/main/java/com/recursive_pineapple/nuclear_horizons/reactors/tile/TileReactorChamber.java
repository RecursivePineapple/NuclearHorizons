package com.recursive_pineapple.nuclear_horizons.reactors.tile;

import javax.annotation.Nullable;

import com.recursive_pineapple.nuclear_horizons.reactors.blocks.BlockList;

import gregtech.api.interfaces.tileentity.IEnergyConnected;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileReactorChamber extends TileEntity implements IReactorBlock, IInventory, IEnergyConnected {

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
            this.reactorRelX = reactor != null ? reactor.xCoord - xCoord : 0;
            this.reactorRelY = reactor != null ? reactor.yCoord - yCoord : 0;
            this.reactorRelZ = reactor != null ? reactor.zCoord - zCoord : 0;
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
    public byte getColorization() {
        return -1;
    }

    @Override
    public byte setColorization(byte arg0) {
        return -1;
    }

    @Override
    public long injectEnergyUnits(ForgeDirection arg0, long arg1, long arg2) {
        return 0;
    }

    @Override
    public boolean inputEnergyFrom(ForgeDirection arg0) {
        return false;
    }

    @Override
    public boolean outputsEnergyTo(ForgeDirection arg0) {
        return true;
    }

    @Override
    public int getSizeInventory() {
        var reactor = getReactor();

        if(reactor != null) {
            return reactor.getSizeInventory();
        } else {
            return 0;
        }
    }

    @Override
    public ItemStack getStackInSlot(int slotIn) {
        var reactor = getReactor();

        if(reactor != null) {
            return reactor.getStackInSlot(slotIn);
        } else {
            return null;
        }
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        var reactor = getReactor();

        if(reactor != null) {
            return reactor.decrStackSize(index, count);
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        var reactor = getReactor();

        if(reactor != null) {
            return reactor.getStackInSlotOnClosing(index);
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        var reactor = getReactor();

        if(reactor != null) {
            reactor.setInventorySlotContents(index, stack);;
        }
    }

    @Override
    public String getInventoryName() {
        return BlockList.REACTOR_CHAMBER_NAME;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        var reactor = getReactor();

        if(reactor != null) {
            return reactor.getInventoryStackLimit();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        var reactor = getReactor();

        if(reactor != null) {
            return reactor.isUseableByPlayer(player);
        } else {
            return false;
        }
    }

    @Override
    public void openInventory() {
        var reactor = getReactor();

        if(reactor != null) {
            reactor.openInventory();
        }
    }

    @Override
    public void closeInventory() {
        var reactor = getReactor();

        if(reactor != null) {
            reactor.closeInventory();
        }
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        var reactor = getReactor();

        if(reactor != null) {
            return reactor.isItemValidForSlot(index, stack);
        } else {
            return false;
        }
    }
}
