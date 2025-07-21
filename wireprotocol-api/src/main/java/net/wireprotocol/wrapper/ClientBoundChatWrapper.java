package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInChat;

public class ClientBoundChatWrapper implements PacketWrapper {

    private final PacketPlayInChat packet;

    public ClientBoundChatWrapper(PacketPlayInChat packet) {
        this.packet = packet;
    }

    public String getMessage() {
        return packet.a();
    }

    @Override
    public PacketPlayInChat getHandle() {
        return packet;
    }
}