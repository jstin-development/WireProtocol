package net.wireprotocol.event;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Setter
@Getter
public class PacketReceiveEvent extends Event {

    private Packet<?> packet;
    private Player player;
    private boolean cancelled;
    private ByteBuf byteBuf;
    private static final HandlerList handlers = new HandlerList();

    public PacketReceiveEvent(Packet<?> packet, Player player) {
        this.packet = packet;
        this.player = player;
        this.byteBuf = this.getByteBufFromPacket(packet);
    }

    public ByteBuf getByteBufFromPacket(Packet<?> packet) {
        if (packet == null) return null;

        try {
            // Create a new empty buffer to write the packet to
            ByteBuf byteBuf = Unpooled.buffer();

            // Wrap it in PacketDataSerializer (NMS helper)
            PacketDataSerializer serializer = new PacketDataSerializer(byteBuf);

            // Determine protocol from your channel or context
            EnumProtocol protocol = ((CraftPlayer)player).getHandle().playerConnection.networkManager.channel.attr(NetworkManager.c).get();

            // Get the packet ID for this packet class and direction
            int packetId = protocol.a(EnumProtocolDirection.SERVERBOUND, packet);

            if (packetId < 0) {
                // Packet not registered
                return null;
            }

            // Write packet ID (varint)
            serializer.b(packetId);

            // Serialize packet data into buffer
            packet.b(serializer);

            return byteBuf;
        } catch (Exception e) {
            System.err.println("Failed to serialize packet: " + e.getMessage());
            return null;
        }
    }


    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
