package net.wireprotocol.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import net.minecraft.server.v1_8_R3.*;
import net.wireprotocol.event.PacketEvent;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

public final class Codec extends ByteToMessageDecoder {

    private final PacketEventManager eventManager = new PacketEventManager();
    private final Player player;
    private final NetworkManager networkManager;

    public Codec(Player player) {
        this.player = player;
        this.networkManager = ((CraftPlayer) player).getHandle().playerConnection.networkManager;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws IOException, IllegalAccessException, InstantiationException {
        if (!in.isReadable()) return;

        // Save reader index for reset if decoding fails
        in.markReaderIndex();

        // Create packet data serializer
        var serializer = new PacketDataSerializer(in);

        // Get protocol and packet ID
        var protocol = networkManager.channel.attr(NetworkManager.c).get();
        int packetId = serializer.e();

        // Decode packet
        Packet<?> packet = protocol.a(EnumProtocolDirection.SERVERBOUND, packetId);
        if (packet == null) {
            in.resetReaderIndex();
            return;
        }

        packet.a(serializer);

        // Process through event system
        var event = new PacketEvent(player, packet);
        eventManager.call(event);

        if (!event.isCancelled()) {
            out.add(packet);
        }
    }
}