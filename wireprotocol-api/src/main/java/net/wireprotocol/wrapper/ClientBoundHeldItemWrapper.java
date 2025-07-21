package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInHeldItemSlot;

public class ClientBoundHeldItemWrapper implements PacketWrapper {

    private final PacketPlayInHeldItemSlot packet;

    public ClientBoundHeldItemWrapper(PacketPlayInHeldItemSlot packet) {
        this.packet = packet;
    }

    /**
     * Gets the slot ID of the held item selected (usually 0-8).
     *
     * @return the selected slot
     */
    public int getSlot() {
        return packet.a(); // This returns the selected hotbar slot
    }

    /**
     * Gets the raw NMS packet instance.
     *
     * @return the raw PacketPlayInHeldItemSlot packet
     */
    public PacketPlayInHeldItemSlot getHandle() {
        return packet;
    }
}
