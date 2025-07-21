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

    private final Packet<?> packet;
    private final Player player;
    private final ByteBuf byteBuf;
    private boolean cancelled;
    private static final HandlerList handlers = new HandlerList();

    public PacketReceiveEvent(Packet<?> packet, Player player) {
        this.packet = packet;
        this.player = player;
        this.byteBuf = this.getByteBufFromPacket(packet);
    }

    public ByteBuf getByteBufFromPacket(Packet<?> packet) {
        if (packet == null) return null;

        try {

            var byteBuf = Unpooled.buffer();
            var serializer = new PacketDataSerializer(byteBuf);
            var protocol = ((CraftPlayer)player).getHandle().playerConnection.networkManager.channel.attr(NetworkManager.c).get();
            var packetId = protocol.a(EnumProtocolDirection.SERVERBOUND, packet);

            if (packetId < 0) {
                return null;
            }

            serializer.b(packetId);
            packet.b(serializer);

            return byteBuf;
        } catch (Exception exception) {
            System.err.println("Failed to serialize packet: " + exception.getMessage());
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
