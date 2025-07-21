package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInTabComplete;

public class ClientBoundTabCompleteWrapper implements PacketWrapper{

    private final PacketPlayInTabComplete packet;

    public ClientBoundTabCompleteWrapper(PacketPlayInTabComplete packet) {
        this.packet = packet;
    }

    public String getCommand() {
        return packet.a();
    }

    @Override
    public Object getHandle() {
        return packet;
    }
}
