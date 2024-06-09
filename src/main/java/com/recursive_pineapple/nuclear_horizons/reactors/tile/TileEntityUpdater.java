package com.recursive_pineapple.nuclear_horizons.reactors.tile;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.recursive_pineapple.nuclear_horizons.networking.PacketDispatcher;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityUpdater {
    
    private static final HashMap<Class<? extends TileEntity>, List<TileEntityField>> FIELD_CACHE = new HashMap<>();

    private final TileEntity te;
    private final List<TileEntityField> fields;
    
    private NBTTagCompound sentData;


    public TileEntityUpdater(TileEntity te) {
        if(!IUpdateableTileEntity.class.isAssignableFrom(te.getClass())) {
            throw new IllegalArgumentException("te");
        }

        this.te = te;
        this.fields = FIELD_CACHE.computeIfAbsent(te.getClass(), TileEntityUpdater::discoverFields);
    }

    public void pollForUpdates() {
        var pending = this.getNetworkUpdateData();

        if(!Objects.equals(pending, sentData)) {
            
        }
    }

    public void onNetworkUpdate(NBTTagCompound data) {
        
    }

    public NBTTagCompound getNetworkUpdateData() {
        NBTTagCompound data = new NBTTagCompound();

        for(var field : fields) {

        }

        return null;
    }

    private static List<TileEntityField> discoverFields(Class<? extends TileEntity> clazz) {
        return null;
    }

    private static class TileEntityField {
        public String name;
        public BiConsumer<TileEntity, NBTTagCompound> reader, writer;
    }

}
