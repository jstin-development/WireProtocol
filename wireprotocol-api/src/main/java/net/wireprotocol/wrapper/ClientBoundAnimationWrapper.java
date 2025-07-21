package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;

public class ClientBoundAnimationWrapper implements PacketWrapper {

    private final PacketPlayOutAnimation packet;

    public ClientBoundAnimationWrapper(PacketPlayOutAnimation packet) {
        this.packet = packet;
    }

    @Override
    public PacketPlayOutAnimation getHandle() {
        return packet;
    }
}
