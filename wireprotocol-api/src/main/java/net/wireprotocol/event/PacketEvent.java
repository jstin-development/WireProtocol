package net.wireprotocol.event;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.*;
import net.wireprotocol.protocol.ClientPacketType;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

@Getter
public class PacketEvent {

    private final Player player;
    private final Packet<?> packet;
    @Setter
    private boolean cancelled;

    public PacketEvent(Player player, Packet<?> packet) {
        this.player = player;
        this.packet = packet;
        this.cancelled = false;
    }

    /**
     * Returns the client packet type using your custom enum mapping.
     */
    public ClientPacketType getPacketType() {
        return ClientPacketType.fromPacket(packet);
    }

    /**
     * Serializes the packet into a ByteBuf.
     *
     * @return ByteBuf containing serialized packet data or null if serialization failed.
     */
    public ByteBuf getByteBufFromPacket() {
        if (packet == null) return null;

        try {
            var byteBuf = Unpooled.buffer();
            var serializer = new PacketDataSerializer(byteBuf);

            // Get the player's protocol (packet pipeline) to find packet ID
            var protocol = ((CraftPlayer) player)
                    .getHandle()
                    .playerConnection
                    .networkManager
                    .channel
                    .attr(NetworkManager.c)
                    .get();

            var packetId = protocol.a(EnumProtocolDirection.SERVERBOUND, packet);
            if (packetId < 0) return null;

            serializer.b(packetId);
            packet.b(serializer);

            return byteBuf;
        } catch (Exception e) {
            System.err.println("Failed to serialize packet: " + e.getMessage());
            return null;
        }
    }
}
