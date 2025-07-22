package net.wireprotocol.event;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.*;
import net.wireprotocol.protocol.ClientPacketType;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Represents a packet event containing the player and packet data.
 * This event can be cancelled to prevent packet processing.
 */
@Getter
public final class PacketEvent {
    private final Player player;
    private final Packet<?> packet;
    /**
     * -- SETTER --
     *  Sets whether this packet event should be cancelled.
     *
     */
    @Setter
    private boolean cancelled;

    // Cached constant to avoid repeated Enum lookup
    private static final EnumProtocolDirection SERVERBOUND = EnumProtocolDirection.SERVERBOUND;

    /**
     * Creates a new PacketEvent instance.
     *
     * @param player The player associated with this packet
     * @param packet The packet being processed
     */
    public PacketEvent(Player player, Packet<?> packet) {
        this.player = player;
        this.packet = packet;
    }

    /**
     * Gets the client packet type using the custom enum mapping.
     *
     * @return The packet type as a ClientPacketType enum value
     */
    public ClientPacketType getPacketType() {
        return ClientPacketType.fromPacket(packet);
    }

    /**
     * Serializes the packet into a ByteBuf for inspection or modification.
     * <p>
     * The returned ByteBuf must be released after use to prevent memory leaks.
     *
     * @return ByteBuf containing serialized packet data, or null if serialization failed
     */
    public ByteBuf getByteBufFromPacket() {
        if (packet == null) {
            return null;
        }

        try {
            var byteBuf = ByteBufAllocator.DEFAULT.buffer();
            var serializer = new PacketDataSerializer(byteBuf);

            // Get network components with explicit types for security-critical path
            var craftPlayer = (CraftPlayer) player;
            var networkManager = craftPlayer.getHandle().playerConnection.networkManager;
            var protocol = networkManager.channel.attr(NetworkManager.c).get();

            var packetId = protocol.a(SERVERBOUND, packet);
            if (packetId == null || packetId < 0) {
                byteBuf.release();
                return null;
            }

            serializer.b(packetId);
            packet.b(serializer);
            return byteBuf;
        } catch (Exception e) {
            return null; // Silent fail - logging could be added in debug mode
        }
    }

}