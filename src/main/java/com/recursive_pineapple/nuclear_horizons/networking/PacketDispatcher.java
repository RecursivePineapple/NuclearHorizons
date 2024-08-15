package com.recursive_pineapple.nuclear_horizons.networking;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileReactorSimulator;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.simulator.SimulationResult;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;

public class PacketDispatcher {

    private static byte packetId = 0;

    public static final SimpleNetworkWrapper DISPATCHER = NetworkRegistry.INSTANCE
        .newSimpleChannel(NuclearHorizons.MODID);

    public static final void registerPackets() {
        DISPATCHER.registerMessage(
            ReactorSimulationFinishedMessage::handle,
            ReactorSimulationFinishedMessage.class,
            packetId++,
            Side.SERVER);
    }

    public static class ReactorSimulationFinishedMessage implements IMessage {

        public int dimensionId, x, y, z;

        public SimulationResult result;

        @Override
        public void fromBytes(ByteBuf buf) {
            this.dimensionId = buf.readInt();
            this.x = buf.readInt();
            this.y = buf.readInt();
            this.z = buf.readInt();

            this.result = SimulationResult.read(buf);
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeInt(dimensionId);
            buf.writeInt(x);
            buf.writeInt(y);
            buf.writeInt(z);

            SimulationResult.write(buf, result);
        }

        public static IMessage handle(IMessage message, MessageContext ctx) {
            if (!(message instanceof ReactorSimulationFinishedMessage msg)) {
                return null;
            }

            WorldServer dim = null;

            for (var world : MinecraftServer.getServer().worldServers) {
                if (world.provider.dimensionId == msg.dimensionId) {
                    dim = world;
                    break;
                }
            }

            if (dim == null) {
                return null;
            }

            if (dim.getTileEntity(msg.x, msg.y, msg.z) instanceof TileReactorSimulator sim) {
                sim.setSimulationResult(msg.result);
            }

            return null;
        }
    }

}
