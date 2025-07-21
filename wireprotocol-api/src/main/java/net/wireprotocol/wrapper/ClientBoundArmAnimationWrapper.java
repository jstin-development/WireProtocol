package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;

public class ClientBoundArmAnimationWrapper implements PacketWrapper {

    private final PacketPlayInArmAnimation packet;

    public ClientBoundArmAnimationWrapper(PacketPlayInArmAnimation packet) {
        this.packet = packet;
    }

    /**
     * Returns the raw NMS packet instance.
     */
    @Override
    public PacketPlayInArmAnimation getHandle() {
        return packet;
    }
}
