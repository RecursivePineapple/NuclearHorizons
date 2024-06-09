package com.recursive_pineapple.nuclear_horizons.networking;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.IUpdateableTileEntity;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PacketDispatcher {
    private static byte packetId = 0;
    
    public static final SimpleNetworkWrapper DISPATCHER = NetworkRegistry.INSTANCE.newSimpleChannel(NuclearHorizons.MODID);
    
    public static final void registerPackets() {
        DISPATCHER.registerMessage(TileEntityUpdatedMessage::handle, TileEntityUpdatedMessage.class, packetId++, Side.CLIENT);
    }

    public static class TileEntityUpdatedMessage implements IMessage {

        public int dimensionId, x, y, z, tileEntityId;
        public NBTTagCompound data;

        private static Map<Integer, Class<? extends TileEntity>> idToClass;
        private static Map<Class<? extends TileEntity>, Integer> classToId;

        @SuppressWarnings("unchecked")
        public static void init() {
            Map<String, Class<? extends TileEntity>> nameToClass;

            try {
                Field field = TileEntity.class.getField("nameToClassMap");
                field.setAccessible(true);
                nameToClass = (Map<String, Class<? extends TileEntity>>) field.get(null);
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                throw new RuntimeException(e);
            }

            List<Map.Entry<String, Class<? extends TileEntity>>> updateableTileEntities = nameToClass
                .entrySet()
                .stream()
                .filter(e -> IUpdateableTileEntity.class.isAssignableFrom(e.getValue()))
                .sorted((a, b) -> a.getKey().compareTo(b.getKey()))
                .collect(Collectors.toList());

            int nextId = 0;

            for(var e : updateableTileEntities) {
                idToClass.put(nextId, e.getValue());
                classToId.put(e.getValue(), nextId);
                nextId++;
            }

            NuclearHorizons.LOG.info("Found %d updateable tile entities", nextId);
        }

        public static @Nullable TileEntityUpdatedMessage fromUpdateableTileEntity(World world, int x, int y, int z) {
            TileEntity te = world.getTileEntity(x, y, z);

            if(te == null) {
                NuclearHorizons.LOG.warn(
                    "Tried to update null tile entity! dimension=%d, x=%d, y=%d, z=%d, tileEntity=%s",
                    world.provider.dimensionId,
                    x, y, z,
                    Objects.toString(te)
                );

                return null;
            }

            if(te instanceof IUpdateableTileEntity ute) {
                var msg = new TileEntityUpdatedMessage();

                msg.dimensionId = world.provider.dimensionId;
                msg.x = x;
                msg.y = y;
                msg.z = z;
                msg.tileEntityId = classToId.get(te.getClass());
                msg.data = ute.getNetworkUpdateData();

                return msg;
            } else {
                NuclearHorizons.LOG.warn(
                    "Tried to update non-IUpdateableTileEntity tile entity! dimension=%d, x=%d, y=%d, z=%d, tileEntity=%s",
                    world.provider.dimensionId,
                    x, y, z,
                    Objects.toString(te)
                );

                return null;
            }
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            this.dimensionId = buf.readInt();
            this.x = buf.readInt();
            this.y = buf.readInt();
            this.z = buf.readInt();
            this.tileEntityId = buf.readInt();

            this.data = ByteBufUtils.readTag(buf);
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeInt(dimensionId);
            buf.writeInt(x);
            buf.writeInt(y);
            buf.writeInt(z);
            buf.writeInt(tileEntityId);

            ByteBufUtils.writeTag(buf, data);
        }

        public static IMessage handle(IMessage message, MessageContext ctx) {
            if(ctx.side == Side.CLIENT && message instanceof TileEntityUpdatedMessage msg) {
                var world = Minecraft.getMinecraft().theWorld;

                Class<? extends TileEntity> msgTeClass = idToClass.get(msg.tileEntityId);

                if(world.provider.dimensionId == msg.dimensionId) {
                    TileEntity te = world.getTileEntity(msg.x, msg.y, msg.z);

                    if(te != null && te.getClass() == msgTeClass && te instanceof IUpdateableTileEntity ute) {
                        ute.onNetworkUpdate(msg.data);
                    } else {
                        NuclearHorizons.LOG.warn(
                            "Received update for invalid tile entity! dimension=%d, x=%d, y=%d, z=%d, tileEntityClass=%s, actualTileEntity=%s, data=%s",
                            msg.dimensionId,
                            msg.x, msg.y, msg.z,
                            msgTeClass.getName(),
                            te,
                            msg.data.toString()
                        );
                    }
                } else {
                    NuclearHorizons.LOG.warn(
                        "Received update for tile entity in another dimension! dimension=%d, x=%d, y=%d, z=%d, tileEntityClass=%s, data=%s",
                        msg.dimensionId,
                        msg.x, msg.y, msg.z,
                        msgTeClass.getName(),
                        msg.data.toString()
                    );
                }
            }

            return null;
        }
    }
}
