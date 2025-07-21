package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInKeepAlive;

public class ClientBoundKeepAliveWrapper implements PacketWrapper {

    private final PacketPlayInKeepAlive packet;

    public ClientBoundKeepAliveWrapper(PacketPlayInKeepAlive packet) {
        this.packet = packet;
    }

    /**
     * Gets the keep-alive ID sent by the client.
     *
     * @return the keep-alive ID
     */
    public int getId() {
        return packet.a();
    }

    /**
     * Gets the raw NMS packet instance.
     *
     * @return the PacketPlayInKeepAlive instance
     */
    public PacketPlayInKeepAlive getHandle() {
        return packet;
    }
}
