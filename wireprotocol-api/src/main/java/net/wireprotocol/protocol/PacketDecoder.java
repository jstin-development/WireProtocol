package net.wireprotocol.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.AllArgsConstructor;
import net.minecraft.server.v1_8_R3.Packet;
import net.wireprotocol.event.PacketReceiveEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

@AllArgsConstructor
public class PacketDecoder extends MessageToMessageDecoder<Packet<?>> {

    private final Player player;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Packet<?> packet, List<Object> list) {
        // This method is called when a packet is received.
        // It decodes the packet and adds it to the list for further processing.

        PacketReceiveEvent packetReceiveEvent = new PacketReceiveEvent(packet, this.player);
        Bukkit.getPluginManager().callEvent(packetReceiveEvent);

        if (!packetReceiveEvent.isCancelled()) {
            list.add(packet);
        }
    }
}
