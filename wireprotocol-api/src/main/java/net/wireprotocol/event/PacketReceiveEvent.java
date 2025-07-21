package net.wireprotocol.event;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Uses reflection to extract the ByteBuf field from the packet.
     * Assumes the ByteBuf is stored in a field of type ByteBuf.
     *
     * @param packet the packet to inspect
     * @return the ByteBuf if found, otherwise null
     */
    private final ClassValue<List<Field>> BYTE_BUF_FIELDS_CACHE = new ClassValue<>() {
        @Override
        protected List<Field> computeValue(Class<?> type) {
            List<Field> fields = new ArrayList<>();
            Class<?> clazz = type;
            while (clazz != null && clazz != Object.class) {
                for (Field field : clazz.getDeclaredFields()) {
                    if (ByteBuf.class.isAssignableFrom(field.getType())) {
                        field.setAccessible(true);
                        fields.add(field);
                    }
                }
                clazz = clazz.getSuperclass();
            }
            return fields;
        }
    };

    /**
     * Extracts the first found ByteBuf from a Minecraft packet.
     * @param packet The packet to inspect.
     * @return The ByteBuf if found, or null if none exists.
     */
    public ByteBuf getByteBufFromPacket(Packet<?> packet) {
        if (packet == null) return null;

        for (Field field : BYTE_BUF_FIELDS_CACHE.get(packet.getClass())) {
            try {
                Object value = field.get(packet);
                if (value instanceof ByteBuf) {
                    return (ByteBuf) value;
                }
            } catch (IllegalAccessException ignored) {
                // Skip inaccessible fields (shouldn't happen since we setAccessible)
            }
        }
        return null;
    }

    /**
     * Extracts all ByteBufs from a Minecraft packet (useful if multiple exist).
     * @param packet The packet to inspect.
     * @return A list of ByteBufs (empty if none found).
     */
    public List<ByteBuf> getAllByteBufsFromPacket(Packet<?> packet) {
        List<ByteBuf> buffers = new ArrayList<>();
        if (packet == null) return buffers;

        for (Field field : BYTE_BUF_FIELDS_CACHE.get(packet.getClass())) {
            try {
                Object value = field.get(packet);
                if (value instanceof ByteBuf) {
                    buffers.add((ByteBuf) value);
                }
            } catch (IllegalAccessException ignored) {
                // Skip inaccessible fields
            }
        }
        return buffers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
