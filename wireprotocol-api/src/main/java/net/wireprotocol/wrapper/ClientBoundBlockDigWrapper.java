package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.EnumPlayerDigType;

public class ClientBoundBlockDigWrapper implements PacketWrapper {

    private final PacketPlayInBlockDig packet;

    public ClientBoundBlockDigWrapper(PacketPlayInBlockDig packet) {
        this.packet = packet;
    }

    /**
     * Gets the dig action type (START_DESTROY_BLOCK, STOP_DESTROY_BLOCK, etc).
     */
    public EnumPlayerDigType getDigType() {
        return packet.c();
    }

    /**
     * Gets the block position of the block being interacted with.
     */
    public BlockPosition getPosition() {
        return packet.a();
    }

    /**
     * Returns the underlying NMS packet.
     */
    @Override
    public PacketPlayInBlockDig getHandle() {
        return packet;
    }
}
