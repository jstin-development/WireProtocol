package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInCloseWindow;

public class ClientBoundCloseWindowWrapper implements PacketWrapper {

    private final PacketPlayInCloseWindow packet;

    public ClientBoundCloseWindowWrapper(PacketPlayInCloseWindow packet) {
        this.packet = packet;
    }

    @Override
    public PacketPlayInCloseWindow getHandle() {
        return packet;
    }
}
