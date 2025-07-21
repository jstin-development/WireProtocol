package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInSpectate;

public class ClientBoundSpectateWrapper implements PacketWrapper {

    private final PacketPlayInSpectate packet;

    public ClientBoundSpectateWrapper(PacketPlayInSpectate packet) {
        this.packet = packet;
    }

    /**
     * Gets the UUID of the entity/player being spectated as a string.
     */
    public String getSpectatedUUID() {
        return packet.toString();
    }

    @Override
    public PacketPlayInSpectate getHandle() {
        return packet;
    }
}
