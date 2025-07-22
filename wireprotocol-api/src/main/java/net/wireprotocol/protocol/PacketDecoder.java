package net.wireprotocol.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;
import net.wireprotocol.event.PacketEvent;

import java.util.List;

public final class PacketDecoder extends MessageToMessageDecoder<Packet<?>> {

    private final Player player;
    private final PacketEventManager eventManager;

    // Initialize ThreadLocal without player reference
    private final ThreadLocal<PacketEvent> eventCache = new ThreadLocal<>();

    public PacketDecoder(Player player, PacketEventManager eventManager) {
        this.player = player;
        this.eventManager = eventManager;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, Packet<?> packet, List<Object> out) {
        // Get or create event object for this thread
        var event = eventCache.get();
        if (event == null) {
            event = new PacketEvent(player, packet);
            eventCache.set(event);
        }
        eventManager.call(event);
        if (!event.isCancelled()) {
            out.add(packet);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) {
        this.eventCache.remove(); // Prevent memory leaks
    }
}