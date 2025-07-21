package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInResourcePackStatus;

public class ClientBoundResourcePackStatusWrapper implements PacketWrapper {

    private final PacketPlayInResourcePackStatus packet;

    public ClientBoundResourcePackStatusWrapper(PacketPlayInResourcePackStatus packet) {
        this.packet = packet;
    }

    /**
     * Returns the status of the resource pack (enum value).
     */
    public PacketPlayInResourcePackStatus.EnumResourcePackStatus getStatus() {
        return packet.b;
    }

    @Override
    public PacketPlayInResourcePackStatus getHandle() {
        return packet;
    }
}
