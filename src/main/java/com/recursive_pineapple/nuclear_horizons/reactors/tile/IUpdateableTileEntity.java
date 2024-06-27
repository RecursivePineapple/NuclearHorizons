package com.recursive_pineapple.nuclear_horizons.reactors.tile;

import net.minecraft.nbt.NBTTagCompound;

public interface IUpdateableTileEntity {

    public NBTTagCompound getNetworkUpdateData();

    public void onNetworkUpdate(NBTTagCompound data);
}
