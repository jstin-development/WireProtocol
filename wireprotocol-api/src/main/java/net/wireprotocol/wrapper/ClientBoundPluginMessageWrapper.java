package net.wireprotocol.wrapper;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload;

public class ClientBoundPluginMessageWrapper implements PacketWrapper {

    private final PacketPlayInCustomPayload packet;

    public ClientBoundPluginMessageWrapper(PacketPlayInCustomPayload packet) {
        this.packet = packet;
    }

    /**
     * Gets the plugin channel name.
     */
    public String getChannel() {
        return packet.a(); // channel name
    }

    /**
     * Gets the raw payload ByteBuf.
     */
    public ByteBuf getPayload() {
        return packet.b(); // payload data
    }

    @Override
    public PacketPlayInCustomPayload getHandle() {
        return packet;
    }
}
