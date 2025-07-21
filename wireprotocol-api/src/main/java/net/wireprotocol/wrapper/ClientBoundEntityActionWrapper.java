package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction;
import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction.EnumPlayerAction;

public class ClientBoundEntityActionWrapper implements PacketWrapper {

    private final PacketPlayInEntityAction packet;

    public ClientBoundEntityActionWrapper(PacketPlayInEntityAction packet) {
        this.packet = packet;
    }

    /**
     * Gets the action enum (e.g., START_SNEAKING, STOP_SNEAKING, etc.).
     */
    public EnumPlayerAction getAction() {
        return packet.b();
    }

    /**
     * Gets the auxiliary data associated with the action.
     */
    public int getAuxiliaryData() {
        return packet.c();
    }

    /**
     * Returns the raw NMS packet instance.
     */
    @Override
    public PacketPlayInEntityAction getHandle() {
        return packet;
    }
}
