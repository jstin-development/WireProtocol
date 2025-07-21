package net.wireprotocol.event;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.lang.reflect.Field;

@Setter
@Getter
public class PacketReceiveEvent extends Event {

    private Packet<?> packet;
    private boolean cancelled;
    private ByteBuf byteBuf;
    private static final HandlerList handlers = new HandlerList();

    public PacketReceiveEvent(Packet<?> packet) {
        this.packet = packet;
        this.byteBuf = this.extractByteBuf(packet);
    }

    /**
     * Uses reflection to extract the ByteBuf field from the packet.
     * Assumes the ByteBuf is stored in a field of type ByteBuf.
     *
     * @param packet the packet to inspect
     * @return the ByteBuf if found, otherwise null
     */
    private ByteBuf extractByteBuf(Packet<?> packet) {
        if (packet == null) return null;

        for (Field field : packet.getClass().getDeclaredFields()) {
            if (ByteBuf.class.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                try {
                    return (ByteBuf) field.get(packet);
                } catch (IllegalAccessException ignored) {
                    // Ignore this exception
                }
            }
        }
        return null;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
