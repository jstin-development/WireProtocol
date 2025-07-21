package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInTransaction;

public class ClientBoundTransactionWrapper implements PacketWrapper {

    private final PacketPlayInTransaction packet;

    public ClientBoundTransactionWrapper(PacketPlayInTransaction packet) {
        this.packet = packet;
    }

    /**
     * Gets the window ID where the transaction happened.
     */
    public int getWindowId() {
        return packet.a();
    }

    /**
     * Gets the transaction UID.
     */
    public short getUid() {
        return packet.b();
    }

    @Override
    public PacketPlayInTransaction getHandle() {
        return packet;
    }
}
