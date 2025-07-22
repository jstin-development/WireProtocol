package net.wireprotocol.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.AllArgsConstructor;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;
import net.wireprotocol.event.PacketEvent;

import java.util.List;

@AllArgsConstructor
public final class PacketDecoder extends MessageToMessageDecoder<Packet<?>> {

    private final Player player;
    private final PacketEventManager eventManager;

    @Override
    protected void decode(ChannelHandlerContext ctx, Packet<?> packet, List<Object> out) {
        var event = new PacketEvent(player, packet);

        this.eventManager.call(event);

        if (!event.isCancelled()) {
            out.add(packet);
        }
    }
}